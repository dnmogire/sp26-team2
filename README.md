# FitMatch

## Team Members
- David Mogire
- Giselle Marentes

## Description
FitMatch is a web-based platform designed to help beginners or fitness enthusiasts connect with certified trainers. Users can browse different trainer profiles, view available programs, book sessions with a trainer that fits their needs, and leave reviews based on the session. Trainers can manage their profiles by updating their availability, setting rates, and tracking client appointments.

## App Functions

### Client (User)
1. Create/modify customer profile - Create and update profile to show preferences and name.
2. View available services - Browse trainers and their specialties.
3. Subscribe to available services - Book training sessions with a trainer.
4. Write reviews for subscribed services - Send feedback to trainers after completed sessions.

### Provider (Trainer)
1. Create/modify/remove provider profile - Create, update, or remove trainer profile to show skills, certifications, and area of expertise.
2. Create services - Add and manage programs, workout plans, and sessions.
3. View customer statistics - Access dashboard showing pending requests, confirmed sessions, completed sessions, average ratings, and recent reviews.
4. Reply to reviews - Respond to client feedback to maintain engagement and address concerns.

## How to Run

### Prerequisites
- Java 21 or higher
- Maven (included via the mvnw wrapper)
- Internet connection (connects to Neon PostgreSQL database)

### Steps

1. Clone the repository:
```
git clone https://github.com/dnmogire/sp26-team2.git
```

2. Navigate to the mvc-app folder:
```
cd sp26-team2/mvc-app
```

3. Run the application:

On Mac/Linux:
```
./mvnw spring-boot:run
```

On Windows:
```
.\mvnw spring-boot:run
```

4. Wait for the server to start. You will see:
```
Started Application in X seconds
```

5. Open your browser and go to:
```
http://localhost:8080
```

### Test Accounts
Register a new account from the home page, or use the app to create trainer and client accounts.

- To access the trainer dashboard: `http://localhost:8080/provider/dashboard/{trainerId}`
- To access the client view: `http://localhost:8080`

## Tech Stack
- Java 21
- Spring Boot 3.4.3
- Spring Data JPA
- FreeMarker (server-side templating)
- PostgreSQL (Neon cloud database)
- HTML / CSS