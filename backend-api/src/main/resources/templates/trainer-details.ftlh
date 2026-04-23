<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Trainer Details</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

<header>
    <div class="logo">
        <img src="/images/logo.png" alt="FitMatch Logo">
        <a href="/home"><h3>FitMatch</h3></a>
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


<section class="trainer-page">

    <#if trainer??>

        <!-- PROFILE CARD -->
        <div class="trainer-profile-card">

            <img class="trainer-img" src="/images/trainer1.jpg" alt="Trainer Image">

            <div class="trainer-info">

                <h2>
                    ${trainer.user.firstName!"Unknown"}
                    ${trainer.user.lastName!"Trainer"}
                </h2>

                <p class="rating">⭐ ${trainer.avgRating!0.0}</p>

                <p><strong>Bio:</strong> ${trainer.bio!"No bio available"}</p>
                <p><strong>Certifications:</strong> ${trainer.certifications!"None"}</p>
                <p><strong>Specialties:</strong> ${trainer.specialties!"General Fitness"}</p>
                <p><strong>Location:</strong> ${trainer.location!"N/A"}</p>
                <p><strong>Hourly Rate:</strong> $${trainer.hourlyRate!0}</p>

            </div>
        </div>

        <!-- BOOKING SECTION -->
        <div class="booking-section">

            <h3>Book This Trainer</h3>

            <#if loggedInUser??>

                <form action="/book" method="post" class="booking-form">

                    <label>Service</label>
                    <select name="serviceId">
                        <#list services as s>
                            <option value="${s.id}">
                                ${s.name} - $${s.price}
                            </option>
                        </#list>
                    </select>

                    <label>Date</label>
                    <input type="date" name="date" required>

                    <label>Time</label>
                    <input type="time" name="time" required>

                    <button type="submit">Book Session</button>

                </form>

            <#else>
                <p class="login-msg">
                    Please <a href="/login">log in</a> to book a trainer.
                </p>
            </#if>

        </div>

    <#else>

        <p>Trainer not found.</p>

    </#if>

</section>

</body>
</html>