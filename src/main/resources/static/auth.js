function isAuthenticated() {
    const token = localStorage.getItem("token");
    const isAuthenticated = token !== null;
    console.log("User isAuthenticated:", isAuthenticated);
    return token !== null;
}


async function fetchUserInfo() {
    const token = localStorage.getItem("token");
    console.log("Token found in localStorage:", token);
    if (!token) {
        console.log("No token found. Returning null.");
        return null;
    }

    const response = await fetch('/api/user', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    });

    if (response.ok) {
        const data = await response.json();
        console.log("User info fetched:", data);
        return data.username;
    } else {
        console.log("Failed to fetch user info. Status:", response.status);
        return null;
    }
}

async function displayUserInfo() {
    try {
        console.log("Trying to select element with id 'user-info'");
        const userInfoElement = document.getElementById("user-info");
        if (userInfoElement) {
            console.log("Element found:", userInfoElement);
        } else {
            console.log("Element with id 'user-info' not found.");
        }

        const username = await fetchUserInfo();
        if (username) {
            document.getElementById("user-info").innerHTML = `
                <span>${username}</span>
                <button onclick="logout()">Log out</button>
            `;
        }
    } catch (error) {
        console.error("Failed to display user info:", error);
    }
}

function logout() {
    localStorage.removeItem("token");
    window.location.href = "login.html";
}

document.addEventListener("DOMContentLoaded", () => {
    if (isAuthenticated()) {
        displayUserInfo();
    }
});
