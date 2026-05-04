<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Browse Trainers</title>
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

    <section class="hero">
    <h1>Browse Trainers</h1>

  
    <form action="/trainers" method="get" class="search-bar">
        <input type="text" name="location" placeholder="Location" />
        <button type="submit">Search</button>
    </form>
    </section>

    <div class="browse-grid">

        <#list trainers as trainer>

            <div class="browse-card">

       
                <img src="/images/trainer${trainer.id % 3 + 1}.jpg" alt="Trainer Image">

                <div class="trainer-info">

                 
                    <h4>
                        ${trainer.user.firstName!} ${trainer.user.lastName!}
                    </h4>

  
                    <div class="tag-row">

                        <div class="tag rating">
                          ☆${trainer.avgRating!0.0}
                    </div>

                        <span class="tag">
                             ${trainer.specialties!""}
                        </span>

                        <span class="tag cert">
                             ${trainer.certifications!""}
                        </span>

                        <span class="tag location">
                             ${trainer.location!""}
                        </span>

                        <span class="tag price">
                             $${trainer.hourlyRate!0}/hr
                        </span>

                    </div>

                    <a href="/trainer/${trainer.id}" class="book-btn">
                        BOOK
                    </a>

                </div>
            </div>

        </#list>

    </div>

     <footer>
        2026 FitMatch
    </footer>


</section>

</body>
</html>