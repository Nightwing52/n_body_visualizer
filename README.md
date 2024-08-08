# n_body_visualizer
Interactive n body simulator that visualizes the results. Practice for building multi-service architecture.

## Docker Setup
Two services, Consul and the visualizer service, are run via Docker. For local development simply cd into the directories and run:

**Consul**
```
sudo docker compose up
```
**Visualizer** (first time setup)
```
docker compose up --build
```
**Visualizer** (subsequent runs)
```
docker compose up
```
These services can be taken down with '*docker compose down*'.

Note: the Consul service utilizes a volume so your configuration will not be lost when you stop the services.

## Database Configuration
The database used for this project is **Postgres 16**. I am using a managed RDS database from AWS but you are free to use whatever provider. The only thing that needs to be done to setup the project is to create a database named *nbody_db* and schemas named *dev* and *prod* and grant your user permissions on these schemas. Once you do this Flyway will then take care of the table setup and migrations. Grant the permissions with the following commands:
```
ALTER DEFAULT PRIVILEGES IN SCHEMA <SCHEMA>
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO <FLYWAY USER>
```
For database administration I recommend **pgAdmin**.

## Consul Configuration
This project uses Consul for distributed configuration. You are free to host your Consul server anywhere. The properties directory looks like this:
```
config:
    application
    core,dev
    core,prod
    core
    simulator
```
A template is given in the *config* folder. Please use these to create your configuration in Consul.