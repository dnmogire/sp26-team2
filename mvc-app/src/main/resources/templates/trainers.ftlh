<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Browse Trainers</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>

    <header>
        <div class="logo">
            <img src="/images/logo.png" alt="FitMatch Logo">
            <a href="/home">
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


    <section class="browse">
        <h1>Browse Trainers</h1>

        <div class="browse-grid">

            <#list trainers as trainer>

                <div class="browse-card">

                    <img src="/images/trainer${trainer.id % 3 + 1}.jpg" alt="Trainer Image">

                    <div class="trainer-info">

                        <h4>
                            ${trainer.user.firstName!} ${trainer.user.lastName!}
                        </h4>

                        <p class="rating">
                            ⭐ ${trainer.avgRating!0.0}
                        </p>

                        <p class="specialty">
                            ${trainer.specialties!""}
                        </p>

                        <p class="cert">
                            ${trainer.certifications!""}
                        </p>

                        <p class="location">
                            ${trainer.location!""}
                        </p>

                        <p class="price">
                            $${trainer.hourlyRate!0}/hour
                        </p>

                        <a href="/trainer/${trainer.id}" class="book-btn">BOOK</a>

                    </div>

                </div>

            </#list>

        </div>
    </section>

</body>

</html>