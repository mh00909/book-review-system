function isAuthenticated() {
    const token = localStorage.getItem("token");
    return token !== null;
}

function redirectToLoginIfNotAuthenticated() {
    if (!isAuthenticated()) {
        window.location.href = "login.html";
    }
}


function redirectToAccountIfAuthenticated() {
    if (isAuthenticated()) {
        window.location.href = "account.html";
    }
}

function getUsernameFromToken() {
    const token = localStorage.getItem("token");
    if (token) {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.sub;
    }
    return null;
}

async function fetchUserInfo() {
    const token = localStorage.getItem("token");
    if (!token) {
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
        return data.username;
    } else {
        return null;
    }
}

async function displayUserInfo() {
    const username = await fetchUserInfo();
    if (username) {
        document.getElementById("user-info").innerHTML = `
            <span>${username}</span>
            <button onclick="logout()">Log out</button>
        `;
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
