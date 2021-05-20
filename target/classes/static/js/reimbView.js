
//------------------------------------------------------------------------------
window.onload = function () {
    renderCurrentUser();
}

function renderCurrentUser() {
    let add = document.querySelector('#data');
    fetch('http://localhost:7000/ReimbV2', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if (response.status === 400) {
            window.location.href = '/';
        }
        return response.json();
    }).then((data) => {


        //console.log(data);
        for (let i = 0; i < data.length; i++) {

            let id = data[i].reimbId;
            let status = data[i].reimbStatus;
            let type = data[i].reimbType;
            let amount = data[i].reimbAmount;
            let description = data[i].description;
            let createdTime = data[i].createdOn;
            let receipt = data[i].receipt;

            let tr = document.createElement('tr');
            add.appendChild(tr)

            let td = document.createElement('td');
            td.innerHTML = `${id}`;
            tr.appendChild(td);

            let td2 = document.createElement('td');
            td2.innerHTML = `${status}`;
            tr.appendChild(td2);

            let td3 = document.createElement('td');
            td3.innerHTML = `${type}`;
            tr.appendChild(td3);

            let td4 = document.createElement('td');
            td4.innerHTML = `${amount}`;
            tr.appendChild(td4);

            let td5 = document.createElement('td');
            td5.innerHTML = `${description}`;
            tr.appendChild(td5);

            let td6 = document.createElement('td');
            td6.innerHTML = `${createdTime}`;
            tr.appendChild(td6);

            let td7 = document.createElement('td');
            td7.innerHTML = `${receipt}`;
            tr.appendChild(td7);

        }

    })
}