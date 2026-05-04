<!DOCTYPE html>
<html>

<head>
    <title>Leave Review</title>
    <link rel="stylesheet" href="/css/userStyle.css">
</head>

<body class="user-app">

<header>
    <div class="logo">
        <img src="/images/logo.png" alt="FitMatch Logo">
        <a href="/"><h3>FitMatch</h3></a>
    </div>
</header>

<section class="review-page">

    <div class="review-card">

        <h2>Leave a Review</h2>

        <!-- SESSION DETAILS -->
        <div class="session-box">

            <h3>Session Details</h3>

            <p>
                <strong>Trainer:</strong>
                ${booking.trainer.user.firstName}
                ${booking.trainer.user.lastName}
            </p>

            <p>
                <strong>Service:</strong>
                ${booking.service.name}
            </p>

            <p>
                <strong>Date:</strong>
                ${booking.date}
            </p>

            <p>
                <strong>Time:</strong>
                ${booking.time}
            </p>

        </div>

        <!-- REVIEW FORM -->
        <form method="post">

            <label>Rating</label>
            <select name="rating" required>
                <option value="5">⭐⭐⭐⭐⭐</option>
                <option value="4">⭐⭐⭐⭐</option>
                <option value="3">⭐⭐⭐</option>
                <option value="2">⭐⭐</option>
                <option value="1">⭐</option>
            </select>

            <label>Your Review</label>
            <textarea name="text" placeholder="How was your session?" required></textarea>

            <button type="submit">Submit Review</button>

        </form>

    </div>

</section>

 <footer>
        2026 FitMatch
    </footer>


</body>
</html>