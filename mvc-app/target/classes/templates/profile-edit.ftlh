<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile - FitMatch</title>
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
            <h1>Edit Profile</h1>
            <p>Update your trainer information</p>
        </div>

        <#if profile??>
            <form action="/provider/profile/${trainerId?c}/edit" method="post" class="form-section">
                <div class="form-group">
                    <label for="bio">About Me</label>
                    <textarea id="bio" name="bio" rows="5"
                              placeholder="Tell clients about yourself...">${profile.bio!''}</textarea>
                </div>
                <div class="form-group">
                    <label for="specialties">Specialties</label>
                    <input type="text" id="specialties" name="specialties"
                           value="${profile.specialties!''}"
                           placeholder="e.g. Strength Training, HIIT (comma separated)">
                </div>
                <div class="form-group">
                    <label for="certifications">Certifications</label>
                    <input type="text" id="certifications" name="certifications"
                           value="${profile.certifications!''}"
                           placeholder="e.g. ACE CPT, NASM CPT">
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="hourlyRate">Hourly Rate ($)</label>
                        <input type="number" id="hourlyRate" name="hourlyRate"
                               value="${profile.hourlyRate!''}" step="0.01" min="0">
                    </div>
                    <div class="form-group">
                        <label for="location">Location</label>
                        <input type="text" id="location" name="location"
                               value="${profile.location!''}"
                               placeholder="e.g. Greensboro, NC">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" id="phone" name="phone"
                           value="${profile.phone!''}"
                           placeholder="(336) 555-0123">
                </div>
                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                    <a href="/provider/profile/${trainerId?c}" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        <#else>
            <p>Profile not found.</p>
        </#if>
    </main>

    <footer><p>&copy; 2026 FitMatch. All rights reserved.</p></footer>
</body>
</html>