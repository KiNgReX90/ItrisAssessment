# To start the application locally:
- Run `docker run --name redis -p 6379:6379 redis` to start the redis container
- Run `docker run --name pg -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=itris -p 5432:5432 -d postgres:16` to start the postgres container

- Cd to the Backend directory
- Run `mvn spring-boot:run` to start the backend

- Cd to the Frontend directory
- Run `ng serve` to start the frontend

- Navigate to `http://localhost:4200/` to interact with application
