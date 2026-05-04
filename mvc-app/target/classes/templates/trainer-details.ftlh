<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Trainer Details</title>
    <link rel="stylesheet" href="/css/userStyle.css">
</head>

<body class="user-app">

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


    <section class="trainer-page">

<#if trainer??>

    <!-- MAIN LAYOUT -->
    <div class="trainer-layout">

        <!-- LEFT: TRAINER INFO -->
        <div class="trainer-profile-card">

            <img class="trainer-img" src="/images/trainer1.jpg" alt="Trainer Image">

            <div class="trainer-info">

                <h2>
                    ${trainer.user.firstName!"Unknown"}
                    ${trainer.user.lastName!"Trainer"}
                </h2>

                <p><strong>Bio:</strong> ${trainer.bio!"No bio available"}</p>
                <p><strong>Certifications:</strong> ${trainer.certifications!"None"}</p>
                <p><strong>Specialties:</strong> ${trainer.specialties!"General Fitness"}</p>
                <p><strong>Location:</strong> ${trainer.location!"N/A"}</p>
                <p><strong>Hourly Rate:</strong> $${trainer.hourlyRate!0}</p>

                <p class="rating">⭐ ${trainer.avgRating!0.0}</p>

            </div>
        </div>

        <!-- RIGHT: BOOKING -->
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

    </div>


    <!-- REVIEWS (FULL WIDTH) -->
    <div class="reviews-section">

        <h3>Reviews</h3>

        <#if reviews?? && reviews?size gt 0>

            <div class="review-row">

                <#list reviews as r>
                    <div class="review-card">

                        <p class="review-text">
                            "${r.text}"
                        </p>

                        <p class="rating">
                            ⭐ ${r.rating}
                        </p>

                        <p class="reviewer">
                            - ${r.client.firstName!"User"}
                        </p>

                    </div>
                </#list>

            </div>

        <#else>
            <p>No reviews yet for this trainer.</p>
        </#if>

    </div>

<#else>

    <p>Trainer not found.</p>

</#if>

</section>

 <footer>
        2026 FitMatch
    </footer>


</body>

</html>