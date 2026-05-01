<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Become a Trainer</title>
    <link rel="stylesheet" href="/css/userStyle.css">
</head>

<body>

<header>
        <div class="logo">
            <img src="/images/logo.png" alt="FitMatch Logo">
            <a href="/">
                <h3>FitMatch</h3>
            </a>
        </div>

        <nav class="navigation">
            <a href="/trainers" class="browse-link">Browse Trainers</a>

            <#if loggedInUser??>
                <a href="/logout" class="signup-btn">Log out</a>
                <#else>
                    <a href="/login" class="signup-btn">Log in</a>
            </#if>

            <a href="/profile" class="profile-icon">
                <img src="/images/profileicon.png" alt="Profile">
            </a>
        </nav>
    </header>

<section class="auth-container">

    <h1>Become a Trainer</h1>

    <p>Hi ${loggedInUser.firstName}, complete your trainer profile</p>

    <form action="/become-trainer" method="post">

        <label>Bio</label>
        <textarea name="bio" required></textarea>

        <label>Specialties</label>
        <input type="text" name="specialties"
               placeholder="Strength, HIIT, Yoga">

        <label>Certifications</label>
        <input type="text" name="certifications">

        <label>Hourly Rate</label>
        <input type="number" name="hourlyRate" step="0.01">

        <label>Location</label>
        <input type="text" name="location">

        <label>Phone</label>
        <input type="text" name="phone">

        <button type="submit">Save & Continue</button>

    </form>

</section>

</body>
</html>