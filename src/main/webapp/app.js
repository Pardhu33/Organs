document.addEventListener("DOMContentLoaded", function () {

	const donorForm = document.getElementById("donorRegister");
	if (donorForm) {
	    donorForm.addEventListener("submit", function (e) {
	        e.preventDefault();

	        const formData = new FormData(donorForm);
	        const formBody = new URLSearchParams();
	        formData.forEach((value, key) => {
	            formBody.append(key, value);
	        });

	        console.log("Submitting donor form...");
	        console.log("Age:", formData.get("age"));

	        fetch("/Organs/donorRegister", {
	            method: "POST",
	            headers: {
	                'Content-Type': 'application/x-www-form-urlencoded'
	            },
	            body: formBody.toString(),
	        })
	        .then(response => response.text())
	        .then(data => {
	            console.log("Server Response:", data);
	            if (data.trim() === "Success") {
	                window.location.href = "/Organs/sucessDonotRegistor.html";
	            } else {
	                alert(data); // Show backend error
	            }
	        })
	        .catch(error => {
	            console.error("Error during donor registration:", error);
	            alert("An unexpected error occurred.");
	        });
	    });
	}
    const loginForm = document.getElementById("loginUser");
    if (loginForm) {
        loginForm.addEventListener("submit", function (e) {
            e.preventDefault();

            const username = document.getElementById("username").value.trim();
            const password = document.getElementById("password").value.trim();

            console.log("Logging in...");
            console.log("Username:", username);
            console.log("Password:", password);

            const formBody = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`;

            fetch("/Organs/loginUser", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: formBody,
            })
            .then(response => response.text())
            .then(data => {
                if (data.trim() === "Success") {
                   
                    window.location.href = "/Organs/donorListServlet";
                } else {
                    alert(data); // Show backend error
                }
            })
            .catch(error => {
                console.error("Error during login:", error);
                alert("An unexpected error occurred.");
            });
        });
    }

    const userForm = document.getElementById("registerUser");
    if (userForm) {
        userForm.addEventListener("submit", function (e) {
            e.preventDefault();

            const username = document.getElementById("username").value.trim();
            const password = document.getElementById("password").value.trim();

            console.log("Submitting form...");
            console.log("Username:", username);
            console.log("Password:", password);

            const formBody = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`;

            fetch("/Organs/registerUser", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: formBody,
            })
            .then(response => response.text())
            .then(data => {
                if (data.trim() === "Success") {
                    alert("Registered successfully!");
                    window.location.href = "/Organs/login.html";
                } else {
                    alert(data); // Show actual error from backend
                }
            })
            .catch(error => {
                console.error("Error during registration:", error);
                alert("An unexpected error occurred.");
            });
        });
    }

    });