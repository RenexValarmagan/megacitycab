function validateForm() {
    let form = document.forms["registerForm"];
    let fullName = form["customer_name"].value.trim();
    let nic = form["nic"].value.trim();
    let phone = form["phone"].value.trim();
    let address = form["address"].value.trim();
    let username = form["username"].value.trim();
    let password = form["password"].value.trim();

    // Full Name Validation (Only letters and spaces)
    let nameRegex = /^[A-Za-z\s]+$/;
    if (!nameRegex.test(fullName)) {
        alert("Full Name should contain only letters and spaces.");
        return false;
    }

    // NIC Validation (Sri Lankan old and new format)
    let oldNICPattern = /^[0-9]{9}[VXvx]$/;  // Old NIC (9 digits + V/X)
    let newNICPattern = /^[0-9]{12}$/;       // New NIC (12 digits)
    if (!oldNICPattern.test(nic) && !newNICPattern.test(nic)) {
        alert("Enter a valid Sri Lankan NIC (Old: 9 digits + V/X or New: 12 digits).");
        return false;
    }

    // Phone Number Validation (10 digits, starts with 0)
    let phonePattern = /^0[0-9]{9}$/;
    if (!phonePattern.test(phone)) {
        alert("Phone number must be exactly 10 digits and start with 0.");
        return false;
    }

    // Address Validation (should not be empty)
    if (address === "") {
        alert("Address cannot be empty.");
        return false;
    }

    // Username Validation (should not be empty and follow standard)
    let usernamePattern = /^[a-zA-Z0-9_]{6,}$/; // At least 6 characters, letters, numbers, or underscores
    if (!usernamePattern.test(username)) {
        alert("Username must be at least 6 characters long and can only contain letters, numbers, and underscores.");
        return false;
    }

    // Password Validation (should meet strength criteria)
    let passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/; // At least 8 chars, 1 upper, 1 lower, 1 number, 1 special character
    if (!passwordPattern.test(password)) {
        alert("Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character.");
        return false;
    }

    return true; // If all validations pass, allow form submission
}
