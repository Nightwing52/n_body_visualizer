from flask import Flask, request, jsonify
import os
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import numpy as np
from functools import partial
from collections import deque

app = Flask(__name__)

def init(fig, ax):
    ax.set_xlim((0.0, 1.0))
    ax.set_ylim((0.0, 1.0))
    ax.set_xlabel('X Coordinate')
    ax.set_ylabel('Y Coordinate')
    ax.set_title('Particle Positions')

    # Create scatter object (initialized with empty data)
    scatter = ax.scatter([], [])
    return scatter,

# Update the scatter plot using precomputed frame data
def update(i, scatter, frame_queue):
    particle_list = frame_queue.popleft()['particleList']  # Access the precomputed data for frame i

    # Process each particle in particleList
    x_list = []
    y_list = []
    for particle in particle_list:
        x = float(particle['x'])
        y = float(particle['y'])
        if x > 0.0 and x < 1.0 and y > 0.0 and y < 1.0:
            x_list.append(x)
            y_list.append(y)

    scatter.set_offsets(np.c_[x, y])  # Update the scatter plot data
    return scatter,

# Ensure a directory exists to save the frames
if not os.path.exists('frames'):
    os.makedirs('frames')
    os.chmod("/app/frames/precomputed_scatter_animation_with_frames.mp4", 0o644)


@app.route('/process_data', methods=['POST'])
def process_data():
    # Get the JSON data from the request
    data = request.get_json()

    if 'frameList' not in data:
        return jsonify({"error": "frameList not found in the request"}), 400
    
    # Initialize a deque as a queue for frame data
    frame_queue = deque(data['frameList'])

    # Create a figure and axis
    fig, ax = plt.subplots()

    # Use functools.partial to pass the ax and fig to the init function
    init_with_args = partial(init, ax=ax, fig=fig)

    # Initialize the scatter object
    scatter = init_with_args()[0]

    # Create the animation and pass the scatter object and frames to the update function
    ani = animation.FuncAnimation(fig, update, frames=len(frame_queue), init_func=init_with_args, fargs=(scatter, frame_queue), blit=True)

    # Save the animation as an MP4 file
    ani.save('/app/frames/precomputed_scatter_animation_with_frames.mp4', writer='ffmpeg', fps=60, dpi=100)

    plt.show()
    plt.close()

    # Return a list of saved frame file names
    return jsonify({
        "status": "success",
    }), 200

if __name__ == '__main__':
    app.run(debug=True)
