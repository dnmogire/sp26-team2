<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Reviews - FitMatch</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
    <header>
        <div class="logo"><h3>FitMatch</h3></div>
        <nav class="navigation">
            <a href="/provider/dashboard/${trainerId?c}">Dashboard</a>
            <a href="/provider/bookings/${trainerId?c}">Bookings</a>
            <a href="/provider/services/${trainerId?c}">Services</a>
            <a href="/provider/reviews/${trainerId?c}" class="active">Reviews</a>
            <a href="/provider/profile/${trainerId?c}">Profile</a>
            <a href="/logout">Logout</a>
        </nav>
    </header>

    <main class="dashboard-content">
        <div class="welcome-section">
            <h1>Client Reviews</h1>
            <p>See what your clients are saying and reply to their feedback</p>
        </div>

        <#if reviews?has_content>
            <#list reviews as review>
                <div class="booking-card">
                    <div class="booking-header">
                        <h3>${review.client.firstName} ${review.client.lastName}</h3>
                        <span class="badge confirmed">${review.rating}/5 ⭐</span>
                    </div>
                    <p>${review.text!""}</p>
                    <p style="color:#888; font-size:0.85rem;">
                        ${review.createdAt!""}
                    </p>
                    <#if review.trainerReply??>
                        <div style="background:#f0fdf4; border-left:3px solid #22c55e; padding:10px; margin-top:12px; border-radius:4px;">
                            <strong>Your Reply:</strong>
                            <p>${review.trainerReply}</p>
                        </div>
                    <#else>
                        <form action="/provider/reviews/${review.id?c}/reply/${trainerId?c}" method="post" style="margin-top:12px;">
                            <div class="form-group">
                                <textarea name="replyText" rows="2"
                                          placeholder="Write a reply to this review..."
                                          style="width:100%; padding:8px; border:1px solid #ddd; border-radius:6px;"
                                          required></textarea>
                            </div>
                            <button type="submit" class="btn btn-secondary">Post Reply</button>
                        </form>
                    </#if>
                </div>
            </#list>
        <#else>
            <div class="section">
                <p style="color:#888;">No reviews yet. Reviews appear here after clients complete sessions and leave feedback.</p>
            </div>
        </#if>
    </main>

    <footer><p>&copy; 2026 FitMatch. All rights reserved.</p></footer>
</body>
</html>