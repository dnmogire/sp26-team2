<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Bookings - FitMatch</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
    <header>
        <div class="logo"><h3>FitMatch</h3></div>
        <nav class="navigation">
            <a href="/provider/dashboard/${trainerId?c}">Dashboard</a>
            <a href="/provider/bookings/${trainerId?c}" class="active">Bookings</a>
            <a href="/provider/profile/${trainerId?c}">Profile</a>
        </nav>
    </header>

    <main class="dashboard-content">
        <div class="welcome-section">
            <h1>Manage Bookings</h1>
            <p>Review and manage your client session requests</p>
        </div>

        <div class="tabs">
            <button class="tab-btn active" onclick="showTab('pending', this)">Pending (${pendingBookings?size})</button>
            <button class="tab-btn" onclick="showTab('confirmed', this)">Confirmed (${confirmedBookings?size})</button>
            <button class="tab-btn" onclick="showTab('completed', this)">Completed (${completedBookings?size})</button>
            <button class="tab-btn" onclick="showTab('declined', this)">Declined (${declinedBookings?size})</button>
        </div>

        <div id="pending" class="tab-content active">
            <#if pendingBookings?has_content>
                <#list pendingBookings as booking>
                    <div class="booking-card">
                        <div class="booking-header">
                            <h3>${booking.client.firstName} ${booking.client.lastName}</h3>
                            <span class="badge pending">Pending</span>
                        </div>
                        <p><strong>Date:</strong> ${booking.date} at ${booking.time}</p>
                        <p><strong>Service:</strong> ${booking.service.name}</p>
                        <p><strong>Price:</strong> $${booking.service.price?string["0.##"]}</p>
                        <div class="btn-group">
                            <a href="/provider/bookings/${booking.id?c}/confirm/${trainerId?c}" class="btn btn-success">Confirm</a>
                            <a href="/provider/bookings/${booking.id?c}/decline/${trainerId?c}"
                               onclick="return confirm('Decline this booking?')" class="btn btn-danger">Decline</a>
                        </div>
                    </div>
                </#list>
            <#else>
                <p style="color:#888; padding:20px 0;">No pending requests.</p>
            </#if>
        </div>

        <div id="confirmed" class="tab-content">
            <#if confirmedBookings?has_content>
                <#list confirmedBookings as booking>
                    <div class="booking-card">
                        <div class="booking-header">
                            <h3>${booking.client.firstName} ${booking.client.lastName}</h3>
                            <span class="badge confirmed">Confirmed</span>
                        </div>
                        <p><strong>Date:</strong> ${booking.date} at ${booking.time}</p>
                        <p><strong>Service:</strong> ${booking.service.name}</p>
                        <p><strong>Price:</strong> $${booking.service.price?string["0.##"]}</p>
                        <div class="btn-group">
                            <a href="/provider/bookings/${booking.id?c}/complete/${trainerId?c}" class="btn btn-secondary">Mark Complete</a>
                        </div>
                    </div>
                </#list>
            <#else>
                <p style="color:#888; padding:20px 0;">No confirmed sessions.</p>
            </#if>
        </div>

        <div id="completed" class="tab-content">
            <#if completedBookings?has_content>
                <#list completedBookings as booking>
                    <div class="booking-card">
                        <div class="booking-header">
                            <h3>${booking.client.firstName} ${booking.client.lastName}</h3>
                            <span class="badge completed">Completed</span>
                        </div>
                        <p><strong>Date:</strong> ${booking.date} at ${booking.time}</p>
                        <p><strong>Service:</strong> ${booking.service.name}</p>
                        <p><strong>Price:</strong> $${booking.service.price?string["0.##"]}</p>
                    </div>
                </#list>
            <#else>
                <p style="color:#888; padding:20px 0;">No completed sessions yet.</p>
            </#if>
        </div>

        <div id="declined" class="tab-content">
            <#if declinedBookings?has_content>
                <#list declinedBookings as booking>
                    <div class="booking-card">
                        <div class="booking-header">
                            <h3>${booking.client.firstName} ${booking.client.lastName}</h3>
                            <span class="badge" style="background:#FEE2E2; color:#991B1B;">Declined</span>
                        </div>
                        <p><strong>Date:</strong> ${booking.date} at ${booking.time}</p>
                        <p><strong>Service:</strong> ${booking.service.name}</p>
                    </div>
                </#list>
            <#else>
                <p style="color:#888; padding:20px 0;">No declined bookings.</p>
            </#if>
        </div>
    </main>

    <footer><p>&copy; 2026 FitMatch. All rights reserved.</p></footer>

    <script>
        function showTab(tabName, btn) {
            document.querySelectorAll('.tab-content').forEach(t => t.classList.remove('active'));
            document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
            document.getElementById(tabName).classList.add('active');
            btn.classList.add('active');
        }
    </script>
</body>
</html>