<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login - FitMatch</title>
    <link rel="stylesheet" href="/css/userStyle.css">
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

    <section class="auth-container">

        <h1>Login</h1>

        <#if error??>
            <p style="color:red;">${error}</p>
        </#if>

        <form action="/login" method="post">

            <div id="loginFields">
                <h3>Welcome Back</h3>

                <input type="hidden" name="role" id="role" value="CLIENT">
                
                <label>Email</label>
                <input type="email" name="email" required>

                <label>Password</label>
                <input type="password" name="password" required>

            </div>

            <button type="submit">Login</button>

        </form>

        <p>Don't have an account? <a href="/register">Sign up</a></p>

    </section>

    <script src="/js/auth.js">
        
    </script>

</body>

</html>