# FitMatch MVC App

This is the full-stack MVC layer of FitMatch, built on top of the backend-api using Spring Boot, FreeMarker templates, and a Neon PostgreSQL database.

---

## Project Structure
mvc-app/
├── src/main/java/com/example/fitmatch/
│   ├── controller/
│   │   ├── TrainerMvcController.java       # Provider/Trainer UI routes
│   │   ├── BookingController.java          # REST API - bookings
│   │   ├── ReviewController.java           # REST API - reviews
│   │   ├── ServiceController.java          # REST API - services
│   │   ├── TrainerProfileController.java   # REST API - trainer profiles
│   │   └── UserController.java             # REST API - users
│   ├── mvc/controller/
│   │   ├── AppUiController.java            # Login/logout/register routes
│   │   └── ClientUiController.java         # Client UI routes
│   ├── model/
│   │   ├── Booking.java
│   │   ├── Review.java
│   │   ├── Service.java
│   │   ├── TrainerProfile.java
│   │   └── User.java
│   ├── repository/
│   │   ├── BookingRepository.java
│   │   ├── ReviewRepository.java
│   │   ├── ServiceRepository.java
│   │   ├── TrainerProfileRepository.java
│   │   └── UserRepository.java
│   └── service/
│       ├── BookingService.java
│       ├── ReviewService.java
│       ├── ServiceService.java
│       └── TrainerProfileService.java
├── src/main/resources/
│   ├── templates/
│   │   ├── index.ftlh                      # Home page
│   │   ├── login.ftlh                      # Login page
│   │   ├── register.ftlh                   # Registration page
│   │   ├── trainers.ftlh                   # Browse trainers
│   │   ├── trainer-details.ftlh            # Trainer detail page
│   │   ├── userProfile.ftlh                # Client profile
│   │   ├── edit-userProfile.ftlh           # Edit client profile
│   │   ├── review-form.ftlh                # Leave a review
│   │   ├── become-trainer.ftlh             # Become a trainer
│   │   ├── dashboard.ftlh                  # Trainer dashboard
│   │   ├── bookings.ftlh                   # Manage bookings
│   │   ├── services.ftlh                   # Manage services
│   │   ├── reviews.ftlh                    # View and reply to reviews
│   │   ├── profile.ftlh                    # Trainer profile view
│   │   └── profile-edit.ftlh              # Edit trainer profile
│   └── static/
│       ├── style.css                       # Trainer side styles
│       └── css/userStyle.css               # Client side styles
---

## Use-Case to MVC Mapping

### Client Actor (Giselle Marentes)

| Use Case | Controller Method | Template | Route |
|---|---|---|---|
| Register | `AppUiController.register()` | `register.ftlh` | `POST /register` |
| Login | `AppUiController.login()` | `login.ftlh` | `POST /login` |
| Logout | `AppUiController.logout()` | — | `GET /logout` |
| Browse Trainers | `ClientUiController.showTrainers()` | `trainers.ftlh` | `GET /trainers` |
| View Trainer Details | `ClientUiController.trainerDetails()` | `trainer-details.ftlh` | `GET /trainers/{id}` |
| Book a Session | `ClientUiController.bookSession()` | — | `POST /trainers/{id}/book` |
| View Booking History | `ClientUiController.userProfile()` | `userProfile.ftlh` | `GET /profile` |
| Leave a Review | `ClientUiController.submitReview()` | `review-form.ftlh` | `POST /review/{bookingId}` |
| Edit User Profile | `ClientUiController.editProfile()` | `edit-userProfile.ftlh` | `POST /profile/edit` |
| Become a Trainer | `ClientUiController.becomeTrainer()` | `become-trainer.ftlh` | `POST /become-trainer` |

### Provider Actor (David Mogire)

| Use Case | Controller Method | Template | Route |
|---|---|---|---|
| View Dashboard | `TrainerMvcController.dashboard()` | `dashboard.ftlh` | `GET /provider/dashboard/{trainerId}` |
| Manage Bookings | `TrainerMvcController.viewBookings()` | `bookings.ftlh` | `GET /provider/bookings/{trainerId}` |
| Confirm Booking | `TrainerMvcController.confirmBooking()` | — | `GET /provider/bookings/{bookingId}/confirm/{trainerId}` |
| Decline Booking | `TrainerMvcController.declineBooking()` | — | `GET /provider/bookings/{bookingId}/decline/{trainerId}` |
| Complete Booking | `TrainerMvcController.completeBooking()` | — | `GET /provider/bookings/{bookingId}/complete/{trainerId}` |
| View Profile | `TrainerMvcController.viewProfile()` | `profile.ftlh` | `GET /provider/profile/{trainerId}` |
| Edit Profile | `TrainerMvcController.updateProfile()` | `profile-edit.ftlh` | `POST /provider/profile/{trainerId}/edit` |
| Manage Services | `TrainerMvcController.viewServices()` | `services.ftlh` | `GET /provider/services/{trainerId}` |
| Add Service | `TrainerMvcController.addService()` | — | `POST /provider/services/{trainerId}/add` |
| Remove Service | `TrainerMvcController.deleteService()` | — | `POST /provider/services/{serviceId}/delete/{trainerId}` |
| View Reviews | `TrainerMvcController.viewReviews()` | `reviews.ftlh` | `GET /provider/reviews/{trainerId}` |
| Reply to Review | `TrainerMvcController.replyToReview()` | — | `POST /provider/reviews/{reviewId}/reply/{trainerId}` |

---

## MVC Pattern

- **Model** — JPA entity classes mapped to PostgreSQL tables via Hibernate
- **View** — FreeMarker `.ftlh` templates that receive data from the controller and render HTML
- **Controller** — `@Controller` classes that handle HTTP requests, call service methods, add data to the model, and return template names or redirects

The service layer sits between the controller and repository handling all business logic. The controller never accesses the database directly.

---

## How to Run

From the `mvc-app` directory:

On Windows: .\mvnw spring-boot:run
On Mac/Linux: ./mvnw spring-boot:run

Then open `http://localhost:8080` in your browser.
