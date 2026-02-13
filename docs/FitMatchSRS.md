
# Requirements – Starter Template

**Project Name:** Your App Name \
**Team:** Names and roles \
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-01-30

---

## 1. Overview
**Vision.** One or two sentences: who this is for, the core problem, and the outcome.

**Glossary** Terms used in the project
- **Term 1:** description.
- **Term 2:** description

**Primary Users / Roles.**
- **Customer (e.g., Student/Patient/Pet Owner/etc. )** — 1 line goal statement.
- **Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — 1 line goal statement.
- **SysAdmin (optional)** — 1 line goal statement.

**Scope (this semester).**
- <capability 1>
- <capability 2>
- <capability 3>

**Out of scope (deferred).**
- <deferred 1>
- <deferred 2>

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.

### 2.1 Customer Stories
- **US‑CUST‑001 — <short title>**  
  _Story:_ As a customer, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US‑CUST‑002 — <short title>**  
  _Story:_ As a customer, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
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

### 2.3 SysAdmin Stories
- **US‑ADMIN‑001 — <short title>**  
  _Story:_ As a sysadmin, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US‑ADMIN‑002 — <short title>**  
  _Story:_ As a sysadmin, I want … so that …  
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
