<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Become a Trainer</title>
    <link rel="stylesheet" href="/css/userStyle.css">
</head>

<body class="user-app">

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

                <#if loggedInUser??>
                    <div class="nav-avatar">
                        ${loggedInUser.firstName?substring(0,1)}
                        ${loggedInUser.lastName?substring(0,1)}
                    </div>
                    <#else>
                        <img src="/images/profileicon.png" alt="Profile">
                </#if>

            </a>
        </nav>
    </header>

    <section class="auth-container">

        <div class="auth-card">
        <h1>Become a Trainer</h1>

        <p>Hi ${loggedInUser.firstName}, complete your trainer profile!</p>

        <form action="/become-trainer" method="post">

            <label><strong>Bio</strong></label>
            <textarea name="bio" required></textarea>

            <label><strong>Specialties</strong></label>
            <input type="text" name="specialties" placeholder="Strength, HIIT, Yoga">

            <label><strong>Certifications</strong></label>
            <input type="text" name="certifications">

            <label><strong>Hourly Rate</strong></label>
            <input type="number" name="hourlyRate" step="0.01">

            <label><strong>Location</strong></label>
            <input type="text" name="location">

            <label><strong>Phone</strong></label>
            <input type="text" name="phone">

            <button type="submit">Save & Continue</button>

        </form>
        </div>

    </section>

     <footer>
        2026 FitMatch
    </footer>


</body>

</html>