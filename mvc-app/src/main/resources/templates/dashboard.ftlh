<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Provider Dashboard - FitMatch</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
    <header>
        <div class="logo"><h3>FitMatch</h3></div>
        <nav class="navigation">
            <a href="/provider/dashboard/${trainerId?c}" class="active">Dashboard</a>
            <a href="/provider/bookings/${trainerId?c}">Bookings</a>
            <a href="/provider/profile/${trainerId?c}">Profile</a>
        </nav>
    </header>

    <main class="dashboard-content">
        <div class="welcome-section">
            <h1>Provider Dashboard</h1>
            <#if profile??>
                <p>Welcome back, ${profile.user.firstName}!</p>
            <#else>
                <p>Welcome back!</p>
            </#if>
        </div>

        <section class="stats-grid">
            <div class="stat-card">
                <h3>Pending Requests</h3>
                <p class="stat-number">${pendingBookings?size}</p>
            </div>
            <div class="stat-card">
                <h3>Confirmed Sessions</h3>
                <p class="stat-number">${confirmedBookings?size}</p>
            </div>
            <div class="stat-card">
                <h3>Average Rating</h3>
                <p class="stat-number">
                    <#if profile?? && profile.avgRating??>${profile.avgRating} ⭐<#else>N/A</#if>
                </p>
            </div>
            <div class="stat-card">
                <h3>Hourly Rate</h3>
                <p class="stat-number">
                    <#if profile?? && profile.hourlyRate??>$${profile.hourlyRate?string["0.##"]}<#else>N/A</#if>
                </p>
            </div>
        </section>

        <section class="sessions-section">
            <h2>Upcoming Confirmed Sessions</h2>
            <div class="sessions-list">
                <#if confirmedBookings?has_content>
                    <#list confirmedBookings as booking>
                        <div class="session-item">
                            <div class="session-time">${booking.time}</div>
                            <div class="session-info">
                                <strong>${booking.client.firstName} ${booking.client.lastName}</strong>
                                <p>${booking.service.name} — ${booking.date}</p>
                            </div>
                            <span class="badge confirmed">Confirmed</span>
                        </div>
                    </#list>
                <#else>
                    <p style="color:#888; padding:20px 0;">No confirmed sessions yet.</p>
                </#if>
            </div>
        </section>

        <div style="margin-top:30px; display:flex; gap:12px;">
            <a href="/provider/bookings/${trainerId?c}" class="btn btn-primary">Manage Bookings</a>
            <a href="/provider/profile/${trainerId?c}" class="btn btn-secondary">View Profile</a>
        </div>
    </main>

    <footer><p>&copy; 2026 FitMatch. All rights reserved.</p></footer>
</body>
</html>