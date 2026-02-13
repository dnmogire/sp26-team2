
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
- <capability 1> User registration/login for both clients and trainers.
- <capability 2> Filter by specialty, location, or price.
- <capability 3> Trainer profile management: Set price and availability 
- <capability 4> Booking system for scheduling sessions.
- <capability 5> Review system for clients to provide feedback.

**Out of scope (deferred).**
- <deferred 1> Payment processing
- <deferred 2> Video calls or virtual sessions

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
- **US‑PROV‑001 — <short title>**  
  _Story:_ As a provider, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US‑PROV‑002 — <short title>**  
  _Story:_ As a provider, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

---

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** description 
- **Availability/Reliability:** description
- **Security/Privacy:** description
- **Usability:** description

---

## 4. Assumptions, Constraints, and Policies
- list any rules, policies, assumptions, etc.

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
