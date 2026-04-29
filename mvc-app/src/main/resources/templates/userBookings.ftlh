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

<section class="bookings-page">
<h1 class="page-title">My Bookings</h1>

<div class="bookings-container">
<#if bookings?has_content>
    <#list bookings as b>

        <div class="booking-card">

            <div class="booking-header">
            <h3>${b.trainer.user.firstName!"Unknown"} ${b.trainer.user.lastName!"Trainer"}</h3>
            <span class="status ${b.status?lower_case}">${b.status}</span>
            </div>
            <div class="booking-details">
            <p><strong>Date:</strong> ${b.date}</p>
            <p><strong>Time:</strong> ${b.time}</p>
        </div>
    </div>

    </#list>
<#else>
    <p class="no-booking">No bookings yet.</p>
</#if>

</body>
</html>