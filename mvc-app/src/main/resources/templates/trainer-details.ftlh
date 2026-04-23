<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>${trainer.name}</title>
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
        <a href="/login" class="signup-btn">Log in</a>
        <a href="/profile" class="profile-icon">
            <img src="/images/profileicon.png" alt="Profile">
        </a>
    </nav>
</header>


<section class="trainer1-profile">

    <!-- HERO -->
    <div class="trainer1-hero">
        <h2>${trainer.name}</h2>
    </div>

    <!-- IMAGE -->
    <div class="tprofile-image-container">
        <img src="/images/${trainer.image! 'default.jpg'}" class="tprofile-img">
    </div>

    <!-- INFO -->
    <div class="tprofile-info">

        <p class="rating">Rating: ${trainer.rating}</p>

        <p class="cert">Certifications: ${trainer.certification}</p>

        <div class="specialties">
            <#list trainer.specialties?split(",") as s>
                <span class="specialty-box">${s}</span>
            </#list>
        </div>

        <p class="price"><b>Price:</b> $${trainer.price}/session</p>

        <p class="location">📍 ${trainer.location}</p>

        <p class="availability"><b>Availability:</b> ${trainer.availability! "Contact for schedule"}</p>

        <p class="member">Member since ${trainer.memberSince}</p>

        <a href="/book/${trainer.id}" class="bookt-btn">Book This Trainer</a>

    </div>

</section>


<!-- REVIEWS -->
<section class="reviews-section">

    <h3>Reviews</h3>

    <div class="review-row">

        <#if reviews?? && reviews?size gt 0>

            <#list reviews as review>
                <div class="review-card">
                    <p class="rating">${review.rating} ★</p>
                    <p class="review-text">${review.comment}</p>
                    <p class="reviewer">- ${review.userName}</p>
                </div>
            </#list>

        <#else>

            <p>No reviews yet.</p>

        </#if>

    </div>

</section>

</body>
</html>