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
                    console.log("Login response data:", data);


                    localStorage.setItem("token", data.token);

                    window.location.href = "home.html";
                } else {
                    const errorData = await response.json();
                    console.log("Login error data:", errorData);
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

            console.log("Attempting registration with", username, firstName, lastName, email);

            try {
                const response = await fetch("/api/register", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({ username, firstName, lastName, email, password })
                });

                console.log("Registration response status:", response.status);

                if (response.ok) {
                    const data = await response.json();
                    console.log("Registration response data:", data);
                    alert("Registration successful!");

                    localStorage.setItem("token", data.token);
                    window.location.href = "home.html";
                } else {
                    const errorData = await response.json();
                    console.log("Registration error data:", errorData);
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


async function addBook(event) {
    event.preventDefault();
    const title = document.getElementById("book-title").value;
    const author = document.getElementById("book-author").value;
    const category = document.getElementById("book-category").value;

    const response = await fetch("/api/books/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({ title, author, category })
    });

    if (response.ok) {
        alert("Book added successfully");
    } else {
        alert("Failed to add book");
    }
}

async function addAuthor(event) {
    event.preventDefault();
    const name = document.getElementById("author-name").value;

    const response = await fetch("/api/authors/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({ name })
    });

    if (response.ok) {
        alert("Author added successfully");
    } else {
        alert("Failed to add author");
    }
}

async function addCategory(event) {
    event.preventDefault();
    const name = document.getElementById("category-name").value;

    const response = await fetch("/api/categories/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({ name })
    });

    if (response.ok) {
        alert("Category added successfully");
    } else {
        alert("Failed to add category");
    }
}



async function addReview(event) {
    event.preventDefault();
    const book = document.getElementById("review-book").value;
    const content = document.getElementById("review-content").value;
    const rating = document.getElementById("review-rating").value;

    const response = await fetch("/api/books/review", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({ book, content, rating })
    });

    if (response.ok) {
        alert("Review added successfully");
    } else {
        alert("Failed to add review");
    }
}

async function markBookAsRead(event) {
    event.preventDefault();
    const book = document.getElementById("read-book").value;

    const response = await fetch("/api/books/mark-read", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({ book })
    });

    if (response.ok) {
        alert("Book marked as read");
    } else {
        alert("Failed to mark book as read");
    }
}



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
