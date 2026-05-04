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
<body>
    <section class="auth-container">

    <div class="auth-card">

        <h1>Edit Profile</h1>

        <form action="/profile/update" method="post">

            <label>First Name</label>
            <input type="text" name="firstName" value="${loggedInUser.firstName}" required>

            <label>Last Name</label>
            <input type="text" name="lastName" value="${loggedInUser.lastName}" required>

            <label>Email</label>
            <input type="email" name="email" value="${loggedInUser.email}" required>

            <label>New Password</label>
            <input type="password" name="password" placeholder="Leave blank to keep same">

            <button type="submit">Save Changes</button>

        </form>

    </div>

</section>
</body>
</html>