**Project Name:**   FitMatch
**Version:** 1.0
**Date:**  05/04/26
**Purpose:** This document outlines comprehensive test scenarios for each functional requirement (user story) in the FitMatch system. 

## Actors
- Provider P: Trainer
- Customer C: Client
- Service S: Training Sessions

## Use Cases
#### 1. Customer: US‑CUST‑001 — Register & manage profile
1. Customer C1 registers a new account using name, email, and password.
2. C1 logs in with valid credentials
3. C1 views profile page and edits personal details (name, email, password).
4. System updates and persists changes in database.

#### 2. Customer: US-CUST-002 - Browse and view trainers
1. C1 navigates to Browse Trainers page.
2. System displays list of all active trainers.
3. C1 searches for trainer by location
4. System filters accordingly.
5. C1 clicks on a trainer to view full profile and sevices offered.

### 3. Customer: US-CUST-003 - Book a training session
1. C1 selects a trainer service
2. C1 chooses date and time.
3. C1 sumbits booking request.
4. System creates booking with status "PENDING"
5. Booking appears in C1 profile.

### 4. Customer: US-CUST-004 - Leave a review after session
1. C1 completes a training session
2. System marks booking as "COMPLETED"
3. C1 clicks "Leave Review"
4. C1 submits rating (1-5) and written feedback
5. Review is stored and linked to trainer profile.
6. Trainer average updates

#### 5. Provider: US-PROV-001 - Create trainer profile 
1. P1 logs registers as user
2. P1 clicks on Profile, then Become Trainer
3. P1 completes "Become a trainer" form (Bio, specialties, rate, location)
4. System saves trainer profile and links to user account.
5. P1 is redirected to trainer dashboard

#### 6. Provider: US-PROV-002 - Manage services 
1. P1 adds new training service (name, description, price)
2. System saves service under trainer profile
3. P1 edits or deletes existing services.
4. Changes are reflected on trainer page

### 7. Provider: US-PROV-003 - Manage Bookings
1. P1 views pending bookings.
2. P1 confirms or rejects pending bookings.
3. P1 makrs completed session after training.
4. System updates booking status
5. Completed bookings allow clients to leave reviews.

### 8. Provider: US-PROV-004 - Respond to reviews
1. P1 views client reviews on dashboard
2. P1 writes a response to a review
3. System stores trainer reply linked to review

## CROSS-CUTTING TEST SCENARIOS (Non-Functional Requirements)

### Performance Requirements

**Scenario P1: Browse page response time < 1.5 seconds**
- **Setup:** 
System has 5+ trainers, multiple bookings an reviews
- **Steps:**
  1. Load "Browse Trainers"
  2. Measure response time
- **Expected Outcome:** 
95% of requests ≤ 1.5 seconds

**Scenario P2: Login system performance**
- **Setup:** 
20 users logging in
- **Steps:**
  1. Multiple users attempt login at same time
  2. System authenticates requests
- **Expected Outcome:** 
All valid logins succeed within 2 seconds. 

### Security & Privacy Requirements

**Scenario S1: Unauthorized access**
- **Setup:** 
User not logged in
- **Steps:**
  1. Attempt to access /profile or /provider/dashboard
- **Expected Outcome:** 
System redirects to login page

**Scenario S2: Data isolation**
- **Setup:**  
Two different users
- **Steps:**
  1. C1 logs in
  2. Attempts to view C2 bookings via URL
- **Expected Outcome:** 
Access denied or redirected

### Usability Requirements

**Scenario U1: Booking flow** 
- **Setup:** 
New user 
- **Steps:**
  1. Browse trainers
  2. Select service
  3. Book session
- **Expected Outcome:** 
Booking completed in under 3 easy steps

**Scenario U2: Review submission**
- **Setup:**  
Completed booking exists
- **Steps:** 
  1. User clicks "Leave Review"
  2. Enters rating + comment
  3. Submit form
- **Expected Outcome:** 
Review is saved 