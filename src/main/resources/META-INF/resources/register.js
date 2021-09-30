const URL = 'http://localhost:8080';

if (localStorage.getItem("token") != '') {
    location.replace("entries.html");
}

function register() {
    fetch(`${URL}/auth/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: '{"password": "' + document.getElementById("password").value + '", "username": "' + document.getElementById("username").value + '"}'
    }).then((result) => {
          result.text().then((result) => {
                localStorage.setItem("token", result);
                getUserId();
          });
    });
}

function getUserId() {
    fetch(`${URL}/user/findByName/${document.getElementById("username").value}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((id) => {
            id.json().then((id) => {
                localStorage.setItem("user_id", id);
                location.replace("entries.html");
            })
        })
}