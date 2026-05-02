<!DOCTYPE html>
<html>

<head>
    <title>My Dashboard - FitMatch</title>
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

    <section class="dashboard">

        <div class="dashboard-header">
            <h1>Welcome, ${loggedInUser.firstName}</h1>

            <#if loggedInUser??>
                <a href="/become-trainer" class="btn btn-primary">
                    Become a Trainer
                </a>
            </#if>
        </div>

        <!-- BOOKINGS SECTION -->
        <div class="panel">

            <h2>My Sessions</h2>

            <#if bookings?? && bookings?size gt 0>

                <#list bookings as b>
                    <div class="booking-item">

                        <div class="booking-info">
                            <strong>${b.trainer.user.firstName!"Trainer"} ${b.trainer.user.lastName!""}</strong>
                            <p>${b.date} at ${b.time}</p>
                        </div>

                        <div class="booking-actions">

                            <span class="status ${b.status?lower_case}">
                                ${b.status}
                            </span>

                            <#if b.status=="COMPLETED">
                                <a href="/review/${b.id}" class="leave-review-btn">
                                    Leave Review
                                </a>
                            </#if>

                        </div>

                    </div>
                </#list>

                <#else>
                    <p>No bookings yet.</p>
            </#if>

        </div>

        <!-- REVIEWS SECTION -->
        <div class="panel">

            <h2>My Reviews</h2>

            <#if reviews?? && reviews?size gt 0>

                <#list reviews as r>
                    <div class="review-item">

                        <div class="review-header">
                            <strong>
                                ${r.trainer.user.firstName} ${r.trainer.user.lastName}
                            </strong>

                            <span class="rating">
                                ⭐ ${r.rating}
                            </span>
                        </div>

                        <p class="review-text">
                            ${r.text}
                        </p>

                        <small class="review-date">
                            ${r.createdAt}
                        </small>

                    </div>
                </#list>

                <#else>
                    <p>No reviews yet.</p>
            </#if>

        </div>

    </section>

</body>

</html>