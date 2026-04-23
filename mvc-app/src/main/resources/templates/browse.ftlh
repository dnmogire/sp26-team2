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


<section class="browse">
    <h1>Browse Trainers</h1>

    <div class="browse-grid">

        <#list trainers as trainer>

            <div class="browse-card">

                <a href="/trainer/${trainer.id}">
                    <img src="/images/trainer1.jpg" alt="Trainer Image">
                </a>

                <div class="trainer-info">
                    <h4>${trainer.name}</h4>

                    <p class="rating">
                        ${trainer.rating?string("0.0")}
                    </p>

                    <p class="cert">${trainer.certification}</p>

                    <p class="specialty">${trainer.specialties}</p>

                    <p class="member">Member since ${trainer.memberSince}</p>

                    <p class="price">Price: $${trainer.price}/session</p>

                    <p class="location">${trainer.location}</p>

                    <a href="/trainer/${trainer.id}" class="book-btn">BOOK</a>
                </div>

            </div>

        </#list>

    </div>
</section>

</body>
</html>