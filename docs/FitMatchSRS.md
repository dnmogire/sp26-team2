# Requirements – FitMatch

**Project Name:** FitMatch  
**Team:** David Mogire - Provider | Giselle Marentes - Customer  
**Course:** CSC 340  
**Version:** 2.0 (Final)  
**Date:** 2026-05-05  

---

## 1. Overview

**Vision.** FitMatch is a web-based platform designed to help beginners or fitness enthusiasts connect with certified personal trainers. Clients can browse trainer profiles, view available services, book training sessions, and leave reviews. Trainers can manage their profiles, create services, handle booking requests, and respond to client feedback.

**Glossary**
- **Client:** A user looking for fitness training who can search, book, and review trainers.
- **Trainer (Provider):** A professional who provides fitness training and manages their profile and bookings.
- **Session/Booking:** A scheduled training appointment between a client and a trainer.
- **Service:** A specific training offering created by a trainer (e.g. "1-Hour HIIT Session").

**Primary Users / Roles.**
- **Client** — Can find trainers, book sessions, leave reviews, and manage their profile.
- **Trainer** — Can manage their profile, create services, confirm/decline bookings, and reply to reviews.

**Scope (implemented).**
- User registration, login, and logout with session management
- Client: browse trainers, view trainer details, book sessions, view booking history, leave reviews, edit profile, become a trainer
- Trainer: dashboard with stats, manage bookings (confirm/decline/complete), edit profile, manage services, view and reply to reviews
- Full-stack MVC with FreeMarker server-side rendering
- Neon PostgreSQL database for data persistence

**Out of scope (deferred).**
- Payment processing
- Video calls or virtual sessions
- Real-time notifications
- Calendar integration

---

## 2. Functional Requirements (User Stories)

### 2.1 Client Stories

- **US-CUST-001 — Register and Login**  
  _Story:_ As a client, I want to register an account and log in, so that I can access the platform securely.  
  _Acceptance:_
```gherkin