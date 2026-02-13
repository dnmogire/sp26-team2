
# Requirements – FitMatch

**Project Name:** FitMatch
**Team:** David Mogire - Provider  Giselle Marentes - Customer
**Course:** CSC 340
**Version:** 1.0\
**Date:** 2026-02-13

---

## 1. Overview
**Vision.** FitMatch is a web-based platform designed to help beginners or fitness enthusiasts connect with certified trainers. Users can browse different trainer profiles, view available programs, book sessions with a trainer that fits their needs, and leave reviews based on the session. Trainers can manage their profiles by updating their availabilty, setting rates, and tracking client appointments. 

**Glossary** Terms used in the project
- **Client:** A person who's looking for fitness training, who can search and book different trainers.
- **Trainer:** A Professional who provides fitness training and guidance to clients.
- **Session:** A scheduled workout between a client and trainer. 

**Primary Users / Roles.**
- **Client (Person seeking training)** — Can find local trainers, book sessions, and review exprience.
- **Trainer (Professional coach)** — Can manage profile by setting price/availability, accept/decline session requests, and provide training to clients.


**Scope (this semester).**
-  User registration/login for both clients and trainers.
-  Filter by specialty, location, or price.
-  Trainer profile management: Set price and availability
-  Booking system for scheduling sessions.
-  Review system for clients to provide feedback.

**Out of scope (deferred).**
-  Payment processing
-  Video calls or virtual sessions

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.

### 2.1 Customer Stories
- **US‑CUST‑001 — <Discover Trainers>**  
  _Story:_ As a customer, I want to search and filter for trainers, so that I can find the right trainer for my needs.  
  _Acceptance:_
  ```gherkin
  Scenario: Client searches for trainer by location
    Given the client is logged in
    When  client enters "Greensboro" in search bar
    Then  system displays all trainers located in Greensboro
  ```

- **US‑CUST‑002 — <Booking Sessions>**  
  _Story:_ As a customer, I want to book a session with a trainer, so that I can schedule a workout at a time that works best for me. 
  _Acceptance:_
  ```gherkin
  Scenario: Client books a session
    Given a trainer's available slots
    When  client selects date and time
    Then  system sends request to trainer
  ```

  - **US‑CUST‑003 — <Rate a Trainer>**  
  _Story:_ As a customer, I want to rate and review after a session, so that other clients can see feedback and trainers can improve. 
  _Acceptance:_
  ```gherkin
  Scenario: Client rates/reviews a trainer
    Given a completed session
    When  client submits rating and review
    Then  system records feedback and updates trainers review page. 
  ```

### 2.2 Provider Stories
- **US-PROV-001** — **Create and manage professional profile**
  _Story:_ As a provider, I want to create and manage my professional profile with certifications and expertise, so that potential clients can find me and understand my qualifications.
  _Acceptance:_
```gherkin
  Scenario: Provider creates professional profile
    Given the provider is registering on the platform
    When provider enters name, certifications, specialties, rate, and availability
    Then system creates provider account and profile
    And profile appears in public trainer directory
    And provider receives confirmation email
```

- **US-PROV-002** — **Create and manage services**
  _Story:_ As a provider, I want to create and manage workout programs and training sessions, so that clients can browse and book my specialized services.
  _Acceptance:_
```gherkin
  Scenario: Provider creates new service offering
    Given the provider has an active account
    When provider clicks "Add Service" and enters details (name, description, duration, price, category)
    Then system saves the service to provider's profile
    And service becomes visible to customers in search results
    And provider can edit or deactivate service anytime
```
- **US-PROV-003** — **Manage client bookings**
  _Story:_ As a provider, I want to view, confirm, and reschedule client bookings, so that I can manage my schedule efficiently.
  _Acceptance:_
```gherkin
  Scenario: Provider manages client bookings
    Given the provider has pending booking requests
    When provider views bookings dashboard
    Then system displays tabs for Pending, Confirmed, and Completed sessions
    And provider can click "Confirm" to accept or "Decline" to reject
    And provider can propose reschedule for confirmed bookings
    And calendar updates automatically with confirmed sessions
```

- **US-PROV-004** — **Reply to client reviews**
  _Story:_ As a provider, I want to respond to client reviews and feedback, so that I can engage professionally and address concerns publicly.
  _Acceptance:_
```gherkin
  Scenario: Provider replies to client review
    Given a client has left a review on provider's profile
    When provider navigates to Reviews section and clicks "Reply"
    Then provider can write and post a response
    And reply appears publicly below client's review with timestamp
    And client receives notification of provider's reply
    And provider can edit reply within 48 hours
```

---

### 3.1 Performance Requirements
- System shall load trainer search results within 2 seconds.
- Session booking shall be confirmed within 3 seconds.
- Platform shall support 500 concurrent users.

### 3.2 Availability Requirements
- System shall maintain 99% uptime during business hours.
- Backup of all data shall occur daily.
---

### Assumptions
- Users have reliable internet access
- Trainers possess valid certifications
- Platform accessed via web browsers

### Constraints
- One semester development timeline (Spring 2026)
- Two-person development team
- No third-party payment integration in v1

---

## 5. Milestones (course‑aligned)
- **M2 Requirements** — this file + stories opened as issues. 
- **M3 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M4 Design** — architecture, schema, API outline. 
- **M5 Backend API** — key endpoints + tests. 
- **M6 Increment** — ≥2 use cases end‑to‑end. 
- **M7 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.
