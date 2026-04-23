<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>FitMatch</title>
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

    <section class="hero">
        <h1>Find Your Perfect Trainer</h1>
        <p>Find certified fitness professionals in your area.</p>

        <div class="searchbar">
            <input type="text" placeholder="Search Trainers">
            <a href="/trainers"><button>Search</button></a>
        </div>
    </section>

    <section class="featuredTrainers">
        <h2>Featured Trainers</h2>

        <div class="trainer-container">

            <#list featuredTrainers![] as t>
                <div class="trainer-card">
                    <img src="/images/trainer1.jpg" alt="Trainer Image">
                    <h4>${t.user.firstName} ${t.user.lastName}</h4>
                    <p>${t.specialties}</p>
                    <p class="rating">${t.avgRating!"0.0"}</p>
                </div>
            </#list>

        </div>
    </section>

    <footer>
        2026 FitMatch
    </footer>

</body>

</html>