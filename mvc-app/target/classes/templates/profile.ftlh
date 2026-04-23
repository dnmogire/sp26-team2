<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile - FitMatch</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
    <header>
        <div class="logo"><h3>FitMatch</h3></div>
        <nav class="navigation">
            <a href="/provider/dashboard/${trainerId?c}">Dashboard</a>
            <a href="/provider/bookings/${trainerId?c}">Bookings</a>
            <a href="/provider/profile/${trainerId?c}" class="active">Profile</a>
        </nav>
    </header>

    <main class="dashboard-content">
        <div class="welcome-section">
            <h1>My Profile</h1>
            <p>Manage your trainer information</p>
        </div>

        <#if profile??>
            <section class="section">
                <div class="profile-header">
                    <div class="profile-avatar">
                        ${profile.user.firstName?substring(0,1)}${profile.user.lastName?substring(0,1)}
                    </div>
                    <div>
                        <h2>${profile.user.firstName} ${profile.user.lastName}</h2>
                        <p>${profile.user.email}</p>
                    </div>
                </div>
                <div class="profile-stats">
                    <div class="stat-item">
                        <strong>${profile.avgRating!'N/A'}</strong>
                        <span>Rating</span>
                    </div>
                    <div class="stat-item">
                        <strong><#if profile.hourlyRate??>$${profile.hourlyRate?string["0.##"]}<#else>N/A</#if></strong>
                        <span>Per Hour</span>
                    </div>
                    <div class="stat-item">
                        <strong>${profile.location!'N/A'}</strong>
                        <span>Location</span>
                    </div>
                </div>
            </section>

            <section class="section">
                <h2>About Me</h2>
                <p>${profile.bio!'No bio added yet.'}</p>
            </section>

            <section class="section">
                <h2>Specialties</h2>
                <#if profile.specialties??>
                    <div class="tags">
                        <#list profile.specialties?split(",") as specialty>
                            <span class="tag">${specialty?trim}</span>
                        </#list>
                    </div>
                <#else>
                    <p style="color:#888;">No specialties listed.</p>
                </#if>
            </section>

            <section class="section">
                <h2>Certifications</h2>
                <p>${profile.certifications!'No certifications listed.'}</p>
            </section>

            <section class="section">
                <h2>Contact & Rate</h2>
                <p><strong>Phone:</strong> ${profile.phone!'Not provided'}</p>
                <p><strong>Location:</strong> ${profile.location!'Not provided'}</p>
                <p><strong>Hourly Rate:</strong> <#if profile.hourlyRate??>$${profile.hourlyRate?string["0.##"]}/hour<#else>N/A</#if></p>
            </section>

            <div style="margin-top:20px; display:flex; gap:12px;">
                <a href="/provider/profile/${trainerId?c}/edit" class="btn btn-primary">Edit Profile</a>
                <a href="/provider/dashboard/${trainerId?c}" class="btn btn-secondary">Back to Dashboard</a>
            </div>
        <#else>
            <div class="section"><p>Profile not found.</p></div>
        </#if>
    </main>

    <footer><p>&copy; 2026 FitMatch. All rights reserved.</p></footer>
</body>
</html>
