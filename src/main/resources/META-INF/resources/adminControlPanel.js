const URL = 'http://localhost:8080';
document.getElementById("editEntry").style.display = "inline-block";

function saveCategory() {
fetch(`${URL}/categories`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + localStorage.getItem("token")
        },
        body: '{"title": "' + document.getElementById("category").value + '"}'
    })
}

function savePlace() {
    fetch(`${URL}/places`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + localStorage.getItem("token")
            },
            body: '{"title": "' + document.getElementById("place").value + '"}'
        })
}