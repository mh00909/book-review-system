document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");
    const registerForm = document.getElementById("register-form");

    if (loginForm) {
        loginForm.addEventListener("submit", async (event) => {
            event.preventDefault();
            const username = document.getElementById("login-username").value;
            const password = document.getElementById("login-password").value;

            try {
                const response = await fetch("/api/login", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({ username, password })
                });

                console.log("Login response status:", response.status);

                if (response.ok) {
                    const data = await response.json();
                    localStorage.setItem("token", data.token);
                    window.location.href = "home.html";
                } else {
                    const errorData = await response.json();
                    alert("Login failed: " + errorData.message);
                }
            } catch (error) {
                console.error("Login error:", error);
                alert("An error occurred during login. Please try again.");
            }
        });
    }

    if (registerForm) {
        registerForm.addEventListener("submit", async (event) => {
            event.preventDefault();
            const username = document.getElementById("register-username").value;
            const firstName = document.getElementById("first-name").value;
            const lastName = document.getElementById("last-name").value;
            const email = document.getElementById("email").value;
            const password = document.getElementById("register-password").value;

            try {
                const response = await fetch("/api/register", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({ username, firstName, lastName, email, password })
                });

                if (response.ok) {
                    const data = await response.json();
                    alert("Registration successful!");

                    localStorage.setItem("token", data.token);
                    window.location.href = "home.html";
                } else {
                    const errorData = await response.json();
                    if (response.status === 409) {
                        if (errorData.message === "Username already exists") {
                            alert("Registration failed: Username already exists");
                        } else if (errorData.message === "Email already exists") {
                            alert("Registration failed: Email already exists");
                        } else {
                            alert("Registration failed: " + errorData.message);
                        }
                    } else {
                        alert("Registration failed: " + errorData.message);
                    }
                }
            } catch (error) {
                console.error("Registration error:", error);
                alert("An error occurred during registration. Please try again.");
            }
        });
    }
});

async function deleteBook(bookId) {
    if (!confirm("Are you sure you want to delete this book?")) {
        return;
    }
    const response = await fetch(`/api/books/${bookId}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });
    if (response.ok) {
        alert("Book deleted successfully");
        window.location.href = '/books.html';
    } else {
        alert("Failed to delete book");
    }
}

async function deleteCategory(categoryId) {
    if (!confirm("Are you sure you want to delete this category?")) {
        return;
    }
    const response = await fetch(`/api/categories/${categoryId}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    });
    if (response.ok) {
        alert("Category deleted successfully");
        window.location.href = '/books.html';
    } else {
        alert("Failed to delete category");
    }
}
