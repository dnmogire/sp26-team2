<!DOCTYPE html>
<html>
<head>
    <title>My Bookings</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<h1>My Bookings</h1>

<#if bookings?has_content>
    <#list bookings as b>
        <div class="booking-card">
            <p>Trainer: ${b.trainer.user.name}</p>
            <p>Date: ${b.date}</p>
            <p>Time: ${b.time}</p>
            <p>Status: ${b.status}</p>
        </div>
    </#list>
<#else>
    <p>No bookings yet.</p>
</#if>

</body>
</html>