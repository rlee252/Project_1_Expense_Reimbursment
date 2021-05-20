window.onload = function () {
    checkRole();
    renderCurrentUser();

}

function declineFilter() {
    window.location.href = '/filterDecline.html';
}
function pendingFilter() {
    window.location.href = '/filterPending.html';
}

function acceptedFilter() {



    fetch('http://localhost:7000/filterAccept', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/filter.html';
        }
        return response.json();
    }).then((data) => {
        makeFinanceTableHeader();
        populateFinanceTable2(data);
    })
}



function updateStatus() {
    //  let status = document.querySelector('input[name="status"]').value;
    let reimbIdData = document.querySelector('#reimbId').value;
    let statusData;


    var ele = document.getElementsByName('status');

    for (i = 0; i < ele.length; i++) {
        if (ele[i].checked)
            statusData = ele[i].value;
    }

    let data = {
        reimbId: reimbIdData,
        status: statusData
    }

    fetch('http://localhost:7000/updateStatus', {
        method: 'POST',
        credentials: 'include', // this specifies that when you receive cookies you should include them in future requests
        //its important so that the backend can identify if we are logged in or not.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/landing.html';
        } else {
            displayInvalidLogin();

        }
    })
}

function acceptDelineForm() {
    let acceptDeclineForm = document.querySelector('#acceptDeclineForm');
    let accept = document.createElement('p');


    accept.innerHTML = '<input type="radio" id="accepted" name="status" value="accepted"><label for="accepted">accept</label><input type="radio" id="declined" name="status" value="declined" required><label for="declined">decline</label><input type="text" placeholder="reimb id" id="reimbId" maxlength="20" required /><button id="statusSubmit">submit</button>';

    //submit.innerHTML = ' <button id="statusSubmit">submit</button>';

    acceptDeclineForm.appendChild(accept);


}

function filterButtons() {
    let filterButton = document.querySelector('#filterButtons');

    let filter = document.createElement('p');
    filter.innerHTML = '<p>filter by:</p> <button id="accepted2">accepted</button><button id="pending">pending</button><button id="declined2">declined</button>';
    filterButton.appendChild(filter);
}

function renderCurrentUser() {

    fetch('http://localhost:7000/current_user', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if (response.status === 400) {
            window.location.href = '/';

        }
        return response.json();
    }).then((data) => {

        let username = data.username;

        let userInfoElement = document.querySelector('#userdisplay');
        userInfoElement.innerText = ` user logged in as: ${username}`;
    })
}
function checkRole() {

    fetch('http://localhost:7000/current_user', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if (response.status === 400) {
            window.location.href = '/';
        }
        return response.json();
    }).then((data) => {


        let role = data.userRole.userRole;

        if (role == "Finance Manager") {
            financeManagerView();
        } else if (role == "employee") {
            employeeView();
        }

    })

}

function employeeView() {

    fetch('http://localhost:7000/ReimbV2', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if (response.status === 400) {
            window.location.href = '/';
        }
        return response.json();
    }).then((data) => {
        makeEmployeeTableHeader();
        populateEmployeeTable(data);

    })
}

function financeManagerView() {

    fetch('http://localhost:7000/financeManagerView', {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if (response.status === 400) {
            window.location.href = '/';
        }
        return response.json();
    }).then((data) => {
        makeFinanceTableHeader();
        populateFinanceTable(data)
    })
}

function makeFinanceTableHeader() {
    acceptDelineForm();
    document.querySelector('#statusSubmit').addEventListener('click', updateStatus);
    filterButtons();

    document.querySelector('#declined2').addEventListener('click', declineFilter);
    document.querySelector('#pending').addEventListener('click', pendingFilter);

    document.querySelector('#accepted2').addEventListener('click', acceptedFilter);
    let financeTr = document.querySelector('#finance');


    let th1 = document.createElement('th');
    let th2 = document.createElement('th');
    let th3 = document.createElement('th');
    let th4 = document.createElement('th');
    let th5 = document.createElement('th');
    let th6 = document.createElement('th');
    let th7 = document.createElement('th');
    let th8 = document.createElement('th');
    let th9 = document.createElement('th');
    let th10 = document.createElement('th');
    let th11 = document.createElement('th');
    let th12 = document.createElement('th');
    let th14 = document.createElement('th');
    let th13 = document.createElement('th');


    th1.innerHTML = 'User Id';
    th2.innerHTML = 'username';
    th3.innerHTML = 'first name';
    th4.innerHTML = 'last name';
    th5.innerHTML = 'email';
    th6.innerHTML = 'role';
    th7.innerHTML = 'reimb id';
    th8.innerHTML = 'status';
    th9.innerHTML = 'type';
    th10.innerHTML = 'amount';
    th11.innerHTML = 'description';
    th12.innerHTML = 'created on';
    th14.innerHTML = 'updated on';
    th13.innerHTML = 'receipt';



    financeTr.appendChild(th1);
    financeTr.appendChild(th2);
    financeTr.appendChild(th3);
    financeTr.appendChild(th4);
    financeTr.appendChild(th5);
    financeTr.appendChild(th6);
    financeTr.appendChild(th7);
    financeTr.appendChild(th8);
    financeTr.appendChild(th9);
    financeTr.appendChild(th10);
    financeTr.appendChild(th11);
    financeTr.appendChild(th12);
    financeTr.appendChild(th14);
    financeTr.appendChild(th13);

}

function makeEmployeeTableHeader() {
    let employeeTr = document.querySelector('#employee');

    let th1 = document.createElement('th');
    let th2 = document.createElement('th');
    let th3 = document.createElement('th');
    let th4 = document.createElement('th');
    let th5 = document.createElement('th');
    let th6 = document.createElement('th');
    let th9 = document.createElement('th');
    let th7 = document.createElement('th');
    let th8 = document.createElement('th');

    th1.innerHTML = 'reimbursement Id';
    th2.innerHTML = 'status';
    th3.innerHTML = 'type';
    th4.innerHTML = 'amount';
    th5.innerHTML = 'description';
    th6.innerHTML = 'created on';
    th9.innerHTML = 'updated on';
    th7.innerHTML = 'receipt';
    th8.innerHTML = 'resolver';

    employeeTr.appendChild(th1);
    employeeTr.appendChild(th2);
    employeeTr.appendChild(th3);
    employeeTr.appendChild(th4);
    employeeTr.appendChild(th5);
    employeeTr.appendChild(th6);
    employeeTr.appendChild(th9);
    employeeTr.appendChild(th7);
    employeeTr.appendChild(th8);


}

function populateEmployeeTable(data) {
    let add = document.querySelector('#data');

    for (let i = 0; i < data.length; i++) {

        let id = data[i].reimbId;
        let status = data[i].reimbStatus;
        let type = data[i].reimbType;
        let amount = data[i].reimbAmount;
        let description = data[i].description;
        let createdTime = data[i].createdOn;
        let updatedTime = data[i].updatedOn;
        let receipt = data[i].receipt;
        let resolver = data[i].reimbResolver;

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

        let td9 = document.createElement('td');
        td9.innerHTML = `${updatedTime}`;
        tr.appendChild(td9);

        let td7 = document.createElement('td');
        td7.innerHTML = `${receipt}`;
        tr.appendChild(td7);

        let td8 = document.createElement('td');
        td8.innerHTML = `${resolver}`;
        tr.appendChild(td8);
    }
}

function populateFinanceTable(data) {
    let add = document.querySelector('#financeView');

    for (let i = 0; i < data.length; i++) {
        let userId = data[i].userId;
        let username = data[i].username;
        let firstname = data[i].firstname;
        let lastname = data[i].lastname;
        let email = data[i].email;
        let role = data[i].role;

        let reimdId = data[i].reimbViewDTO.reimbId;
        let reimbStatus = data[i].reimbViewDTO.reimbStatus;
        let reimbType = data[i].reimbViewDTO.reimbType;
        let reimbAmount = data[i].reimbViewDTO.reimbAmount;
        let description = data[i].reimbViewDTO.description;
        let createdOn = data[i].reimbViewDTO.createdOn;
        let updatedOn = data[i].reimbViewDTO.updatedOn;
        let receipt = data[i].reimbViewDTO.receipt;

        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');

        let td7 = document.createElement('td');
        let td8 = document.createElement('td');
        let td9 = document.createElement('td');
        let td10 = document.createElement('td');
        let td11 = document.createElement('td');
        let td12 = document.createElement('td');
        let td14 = document.createElement('td');
        let td13 = document.createElement('td');

        let tr = document.createElement('tr');
        add.appendChild(tr)

        td1.innerHTML = `${userId}`;
        td2.innerHTML = `${username}`;
        td3.innerHTML = `${firstname}`;
        td4.innerHTML = `${lastname}`;
        td5.innerHTML = `${email}`;
        td6.innerHTML = `${role}`;
        td7.innerHTML = `${reimdId}`;
        td8.innerHTML = `${reimbStatus}`;
        td9.innerHTML = `${reimbType}`;
        td10.innerHTML = `${reimbAmount}`;
        td11.innerHTML = `${description}`;
        td12.innerHTML = `${createdOn}`;
        td14.innerHTML = `${updatedOn}`;
        td13.innerHTML = `${receipt}`;

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        tr.appendChild(td10);
        tr.appendChild(td11);
        tr.appendChild(td12);
        tr.appendChild(td14);
        tr.appendChild(td13);

    }
}

function populateFinanceTable2(data) {
    let add = document.querySelector('#financeView2');

    for (let i = 0; i < data.length; i++) {
        let userId = data[i].userId;
        let username = data[i].username;
        let firstname = data[i].firstname;
        let lastname = data[i].lastname;
        let email = data[i].email;
        let role = data[i].role;

        let reimdId = data[i].reimbViewDTO.reimbId;
        let reimbStatus = data[i].reimbViewDTO.reimbStatus;
        let reimbType = data[i].reimbViewDTO.reimbType;
        let reimbAmount = data[i].reimbViewDTO.reimbAmount;
        let description = data[i].reimbViewDTO.description;
        let createdOn = data[i].reimbViewDTO.createdOn;
        let updatedOn = data[i].reimbViewDTO.updatedOn;
        let receipt = data[i].reimbViewDTO.receipt;

        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');

        let td7 = document.createElement('td');
        let td8 = document.createElement('td');
        let td9 = document.createElement('td');
        let td10 = document.createElement('td');
        let td11 = document.createElement('td');
        let td12 = document.createElement('td');
        let td14 = document.createElement('td');
        let td13 = document.createElement('td');

        let tr = document.createElement('tr');
        add.appendChild(tr)

        td1.innerHTML = `${userId}`;
        td2.innerHTML = `${username}`;
        td3.innerHTML = `${firstname}`;
        td4.innerHTML = `${lastname}`;
        td5.innerHTML = `${email}`;
        td6.innerHTML = `${role}`;
        td7.innerHTML = `${reimdId}`;
        td8.innerHTML = `${reimbStatus}`;
        td9.innerHTML = `${reimbType}`;
        td10.innerHTML = `${reimbAmount}`;
        td11.innerHTML = `${description}`;
        td12.innerHTML = `${createdOn}`;
        td14.innerHTML = `${updatedOn}`;
        td13.innerHTML = `${receipt}`;

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        tr.appendChild(td10);
        tr.appendChild(td11);
        tr.appendChild(td12);
        tr.appendChild(td14);
        tr.appendChild(td13);

    }
}

