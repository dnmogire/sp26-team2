# FitMatch Test Plan

## Team Members
- David Mogire (Provider/Trainer Actor)
- Giselle Marentes (Client/User Actor)

## Overview
This test plan outlines the scenarios used to demonstrate the full functionality of FitMatch during the final project presentation. Each scenario walks through a complete user flow from start to finish, covering all use cases for both actors.

---

## Actor 1: Client (Giselle Marentes)

### Scenario: A new user signs up and books a training session

#### Use Case 1 — Register an Account
1. Go to `http://localhost:8080`
2. Click **Register**
3. Fill in first name, last name, email, and password
4. Click **Register**
5. **Expected:** Account is created and user is redirected to the home page

#### Use Case 2 — Browse Trainers
1. From the home page, click **Find a Trainer**
2. Browse the list of available trainers
3. Optionally filter by location or specialty
4. **Expected:** List of active trainer profiles loads from the database

#### Use Case 3 — View Trainer Details and Book a Session
1. Click on a trainer's profile
2. View their bio, specialties, certifications, hourly rate, and services
3. Select a service and click **Book Session**
4. Choose a date and time
5. Click **Confirm Booking**
6. **Expected:** Booking is saved to the database with status PENDING

#### Use Case 4 — View Booking History
1. Navigate to **My Profile**
2. View the list of bookings and their statuses
3. **Expected:** All bookings show with correct status (Pending, Confirmed, Completed)

#### Use Case 5 — Leave a Review
1. Find a completed booking in the booking history
2. Click **Leave a Review**
3. Select a star rating and write a comment
4. Click **Submit**
5. **Expected:** Review is saved and the trainer's average rating updates

#### Use Case 6 — Edit User Profile
1. Navigate to **My Profile**
2. Click **Edit Profile**
3. Update name or other details
4. Click **Save**
5. **Expected:** Profile updates are saved and reflected immediately

---

## Actor 2: Provider/Trainer (David Mogire)

### Scenario: A trainer manages their profile and handles client bookings

#### Use Case 1 — View Dashboard
1. Navigate to `http://localhost:8080/provider/dashboard/{trainerId}`
2. **Expected:** Dashboard loads showing pending requests, confirmed sessions, completed sessions, average rating, hourly rate, and recent reviews

#### Use Case 2 — Manage Bookings
1. Click **Bookings** in the nav bar
2. View the **Pending** tab — see client booking requests
3. Click **Confirm** on a pending booking
4. **Expected:** Booking moves to the Confirmed tab
5. Click **Decline** on another pending booking
6. **Expected:** Booking moves to the Declined tab
7. On a confirmed booking, click **Mark Complete**
8. **Expected:** Booking moves to the Completed tab

#### Use Case 3 — Edit Trainer Profile
1. Click **Profile** in the nav bar
2. Click **Edit Profile**
3. Update bio, specialties, certifications, hourly rate, location, or phone
4. Click **Save Changes**
5. **Expected:** Changes are saved to the database and the updated profile is displayed

#### Use Case 4 — Manage Services
1. Click **Services** in the nav bar
2. Fill in the Add New Service form with name, description, price, duration, and category
3. Click **Add Service**
4. **Expected:** New service appears in the Your Services list
5. Click **Remove** on an existing service
6. **Expected:** Service is deactivated and removed from the list

#### Use Case 5 — View and Reply to Reviews
1. Click **Reviews** in the nav bar
2. View all client reviews with star ratings and comments
3. Click on a review and type a reply in the text box
4. Click **Post Reply**
5. **Expected:** Reply is saved and displayed under the review

#### Use Case 6 — Logout
1. Click **Logout** in the nav bar
2. **Expected:** User is logged out and redirected to the home page

---

## Database Verification
All data created during the demo is persisted in the Neon PostgreSQL database. To verify:
- Bookings table shows updated statuses after confirm/decline/complete actions
- Reviews table shows new reviews and trainer replies
- Services table shows new and deactivated services
- Trainer_profiles table shows updated profile information