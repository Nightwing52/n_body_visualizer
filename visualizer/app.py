from flask import Flask, request, jsonify
import os
import matplotlib.pyplot as plt

app = Flask(__name__)

# Ensure a directory exists to save the frames
if not os.path.exists('frames'):
    os.makedirs('frames')

@app.route('/process_data', methods=['POST'])
def process_data():
    # Get the JSON data from the request
    data = request.get_json()

    if 'frameList' not in data:
        return jsonify({"error": "frameList not found in the request"}), 400

    saved_frames = []

    # Process each frame in frameList
    for frame in data['frameList']:
        frame_number = frame['frameNumber']
        particle_list = frame['particleList']
        
        # Create a new figure for each frame
        plt.figure()

        # Process each particle in particleList
        x_list = []
        y_list = []
        for particle in particle_list:
            x = float(particle['x'])
            y = float(particle['y'])
            if x > 0.0 and x < 1.0 and y > 0.0 and y < 1.0:
                x_list.append(particle['x'])
                y_list.append(particle['y'])
            
        plt.scatter(x_list, y_list, label="Particle positions")

        # Add labels and title
        plt.xlim((0.0, 1.0))
        plt.ylim((0.0, 1.0))
        plt.xlabel('X Coordinate')
        plt.ylabel('Y Coordinate')
        plt.title(f'Particle Positions - Frame {frame_number}')
        plt.legend()

        # Save the plot to a file
        filename = f'/app/frames/frame_{frame_number}.png'
        plt.savefig(filename)
        saved_frames.append(filename)
        plt.close()
    
    # Return a list of saved frame file names
    return jsonify({
        "status": "success",
        "saved_frames": saved_frames
    }), 200

if __name__ == '__main__':
    app.run(debug=True)
