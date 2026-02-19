// Tab switching function
function showTab(tabName) {
    // Hide all tab contents
    const tabContents = document.querySelectorAll('.tab-content');
    tabContents.forEach(content => {
        content.classList.remove('active');
    });

    // Remove active class from all buttons
    const tabButtons = document.querySelectorAll('.tab-btn');
    tabButtons.forEach(btn => {
        btn.classList.remove('active');
    });

    // Show selected tab
    const selectedTab = document.getElementById(tabName);
    if (selectedTab) {
        selectedTab.classList.add('active');
    }

    // Add active class to clicked button
    event.target.classList.add('active');
}

// Toggle service form
function toggleForm() {
    const form = document.getElementById('serviceForm');
    if (form) {
        if (form.style.display === 'none') {
            form.style.display = 'block';
        } else {
            form.style.display = 'none';
        }
    }
}

// Demo button handlers
document.addEventListener('DOMContentLoaded', function() {
    // Confirm buttons
    const confirmBtns = document.querySelectorAll('.btn-success');
    confirmBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            alert('Booking confirmed! (Demo mode)');
        });
    });

    // Decline buttons
    const declineBtns = document.querySelectorAll('.btn-danger');
    declineBtns.forEach(btn => {
        if (btn.textContent === 'Decline') {
            btn.addEventListener('click', function() {
                alert('Booking declined. (Demo mode)');
            });
        }
    });
});
