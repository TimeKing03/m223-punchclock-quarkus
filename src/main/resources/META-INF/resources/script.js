const URL = 'http://localhost:8080';
let entries = [];

if (!localStorage.getItem("token")) {
    location.replace("index.html");
}

console.log(localStorage.getItem("user_id"));

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    entry['entryUser'] = {'id': localStorage.getItem("user_id")};
    entry['category'] = {'id': document.getElementById("createCategory").options[document.getElementById("createCategory").selectedIndex].value};

    console.log(entry);

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};

const indexEntries = () => {
    fetch(`${URL}/entries/entriesFromUser/${localStorage.getItem("user_id")}`, {
        method: 'GET',
        headers: {
            'Authorization': "Bearer " + localStorage.getItem("token")
        }
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};


function deleteEntry(id) {
    fetch(`${URL}/entries/` + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + localStorage.getItem("token")
        },
    });
    renderEntries();
};


const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        let deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.value = entry.id;
        deleteButton.onclick = function() {
            deleteEntry(deleteButton.value);
            location.replace("index.html");
        }
        row.appendChild(deleteButton);
        let editButton = document.createElement("button");
        editButton.innerText = "Edit";
        editButton.onclick = function() {
            editEntry(entry);
        }
        row.appendChild(editButton)
        display.appendChild(row);
    });
};

function logout() {
    localStorage.setItem("token", '');
    location.reload();
}

function editEntry(entry) {
    document.getElementById("editCheckIn").value = new Date(entry.checkIn).toLocaleDateString('en-CA').replace(/(T.*)/, "");
    document.getElementById("editCheckOut").value = new Date(entry.checkOut).toLocaleDateString('en-CA').replace(/(T.*)/, "");
    document.getElementById("editCheckInTime").value = entry.checkIn.replace(/(.*T)/, "");
    document.getElementById("editCheckOutTime").value = entry.checkOut.replace(/(.*T)/, "");
    document.getElementById("entryId").value = entry.id;
    preLoad("editCategory");
    document.getElementById("editEntry").style.display = "inline-block";
}

function saveEditEntry() {
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(document.getElementById('editCheckIn').value, document.getElementById('editCheckInTime').value);
    entry['checkOut'] = dateAndTimeToDate(document.getElementById('editCheckOut').value, document.getElementById('editCheckOutTime').value);
    entry['entryUser'] = {'id': parseInt(localStorage.getItem("user_id"))};
    entry['id'] = parseInt(document.getElementById("entryId").value);

    fetch(`${URL}/entries`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(entry)
    });
}

function preLoad(id) {
    fetch(`${URL}/categories`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + localStorage.getItem("token")
        }
    }).then((results) => {
        results.json().then((results) => {
            results.forEach((result) => {
                let option = new Option(result.title);
                option.value = result.id;
                document.getElementById(id).add(option);
            })
        })
    })
}

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
    preLoad("createCategory");
});