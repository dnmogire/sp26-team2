<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account</title>
</head>
<body>
    <header>
        <h2>FitMatch</h2>
    </header>


    <main class="dashboard-content">
        <h1>Create Account</h1>

        <form action="/create-user" method="post" class="form-section">
            <div class="form-group">
                <label>First Name</label>
                <input name = "firstName" required>
            </div>

            <div class="form-group">
                <label>Last Name</label>
                <input name = "lastName" required>
            </div>

            <div class="form-group">
                <label>Email</label>
                <input name = "email"  required>
            </div>

            <button class="btn btn-primary" type="submit">Create Account</button>

        </form>
</body>
</html>