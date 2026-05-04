<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Services - FitMatch</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
    <header>
        <div class="logo"><h3>FitMatch</h3></div>
        <nav class="navigation">
            <a href="/provider/dashboard/${trainerId?c}">Dashboard</a>
            <a href="/provider/bookings/${trainerId?c}">Bookings</a>
            <a href="/provider/services/${trainerId?c}" class="active">Services</a>
            <a href="/provider/reviews/${trainerId?c}">Reviews</a>
            <a href="/provider/profile/${trainerId?c}">Profile</a>
            <a href="/logout">Logout</a>
        </nav>
    </header>

    <main class="dashboard-content">
        <div class="welcome-section">
            <h1>Manage Services</h1>
            <p>Add and manage the services you offer to clients</p>
        </div>

        <section class="section">
            <h2>Add New Service</h2>
            <form action="/provider/services/${trainerId?c}/add" method="post" class="form-section">
                <div class="form-group">
                    <label for="name">Service Name</label>
                    <input type="text" id="name" name="name"
                           placeholder="e.g. 1-Hour Personal Training Session" required>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" rows="3"
                              placeholder="Describe what this service includes..."></textarea>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="price">Price ($)</label>
                        <input type="number" id="price" name="price"
                               step="0.01" min="0" placeholder="60.00" required>
                    </div>
                    <div class="form-group">
                        <label for="duration">Duration (minutes)</label>
                        <input type="number" id="duration" name="duration"
                               min="1" placeholder="60">
                    </div>
                </div>
                <div class="form-group">
                    <label for="category">Category</label>
                    <input type="text" id="category" name="category"
                           placeholder="e.g. Strength, Cardio, Yoga">
                </div>
                <div class="btn-group">
                    <button type="submit" class="btn btn-primary">Add Service</button>
                </div>
            </form>
        </section>

        <section class="section">
            <h2>Your Services</h2>
            <#if services?has_content>
                <#list services as service>
                    <#if service.isActive!true>
                        <div class="booking-card">
                            <div class="booking-header">
                                <h3>${service.name}</h3>
                                <span class="badge confirmed">Active</span>
                            </div>
                            <p>${service.description!""}</p>
                            <p>
                                <strong>Price:</strong> $${service.price?string["0.##"]}
                                <#if service.duration??> &nbsp;|&nbsp; <strong>Duration:</strong> ${service.duration} min</#if>
                                <#if service.category??> &nbsp;|&nbsp; <strong>Category:</strong> ${service.category}</#if>
                            </p>
                            <form action="/provider/services/${service.id?c}/delete/${trainerId?c}" method="post"
                                  onsubmit="return confirm('Remove this service?')">
                                <button type="submit" class="btn btn-danger">Remove</button>
                            </form>
                        </div>
                    </#if>
                </#list>
            <#else>
                <p style="color:#888; padding:20px 0;">No services added yet. Add your first service above.</p>
            </#if>
        </section>
    </main>

    <footer><p>&copy; 2026 FitMatch. All rights reserved.</p></footer>
</body>
</html>