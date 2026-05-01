<!DOCTYPE html>
<html>
<head>
    <title>My Dashboard - FitMatch</title>
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

<section class="dashboard">

    <h1>Welcome, ${loggedInUser.firstName}</h1>

    <#if loggedInUser??>
    <a href="/become-trainer" class="btn btn-primary">
        Become a Trainer
    </a>
</#if>

    <div class="dashboard-cards">

        <div class="card">
            <h3>Browse Trainers</h3>
            <a href="/trainers">View</a>
        </div>

        <div class="card">
            <h3>My Bookings</h3>
            <a href="/userBookings">View</a>
        </div>

    </div>

</section>

</body>
</html>