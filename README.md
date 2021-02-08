# Orchestrator
Microservice orchestrator, works with https://github.com/jordanDSmoothStack/microservice (airports) and https://github.com/mvance-smoothstack/microservice-main (flights and bookings).

# Endpoints
## Airports

GET `http://localhost:8090/utopia/airports/list`: List all airports.

GET `http://localhost:8090/utopia/airports/{AirportID}`: Get an airport by three-letter abbreviation.

POST `http://localhost:8090/utopia/airports/airport`: Add a new airport.

PUT `http://localhost:8090/utopia/airports/airport`: Update an existing airport.

DELETE `http://localhost:8090/utopia/airports/airport`: Delete an airport, only works if the airport is not being used by the airline.

## Flights

GET `http://localhost:8090/utopia/flights`: List all flights.

GET `http://localhost:8090/utopia/flights/from/{airportID}`: List flights departing from an airport with the specified ID.

## Bookings

GET `http://localhost:8090/utopia/bookings/{id}`: Get a booking by ID number.

POST `http://localhost:8090/utopia/bookings/flight/{id}`: Create a booking on the specified flight by its ID number.

PUT `http://localhost:8090/utopia/bookings/cancel/{id}`: Cancel an existing booking by ID number, doesn't work if already canceled.
