<!DOCTYPE html>
<html>
<head>
    <title>My Bookings</title>
    <link rel="stylesheet" href="/css/style.css">
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

<h1>My Bookings</h1>

<#if bookings?has_content>
    <#list bookings as b>
        <div class="booking-card">
            <p>Trainer: ${b.trainer.user.firstName!"Unknown"} ${b.trainer.user.lastName!"Trainer"}</p>
            <p>Date: ${b.date}</p>
            <p>Time: ${b.time}</p>
            <p>Status: ${b.status}</p>
        </div>
    </#list>
<#else>
    <p>No bookings yet.</p>
</#if>

</body>
</html>