const URL = 'http://localhost:8080';

if (localStorage.getItem("token") != '') {
    location.replace("entries.html");
}

function login() {
    fetch(`${URL}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: '{"password": "' + document.getElementById("password").value + '", "username": "' + document.getElementById("username").value + '"}'
    }).then((result) => {
          result.text().then((result) => {
                console.log(result);
                if (result != "") {
                    localStorage.setItem("token", result);
                    getUserId();
                }
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