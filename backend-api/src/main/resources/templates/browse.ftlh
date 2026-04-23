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

    <section class="trainer-details">

        <#if trainer??>

            <div class="trainer-card">

                <!-- STATIC IMAGE (safe, no DB dependency) -->
                <img src="/images/trainer1.jpg" alt="Trainer Image">

                <h2>${trainer.user.firstName!""} ${trainer.user.lastName!""}</h2>

                <p>⭐ ${trainer.avgRating!"0.0"} / 5</p>

                <p><strong>Bio:</strong> ${trainer.bio!"No bio available"}</p>

                <p><strong>Certifications:</strong> ${trainer.certifications!"None"}</p>

                <p><strong>Specialties:</strong> ${trainer.specialties!"General Fitness"}</p>

                <p><strong>Location:</strong> ${trainer.location!"N/A"}</p>

                <p><strong>Hourly Rate:</strong> $${trainer.hourlyRate!"0"}</p>

            </div>

            <#else>
                <p>Trainer not found.</p>
        </#if>

    </section>

</body>

</html>