document.querySelector('#login').addEventListener('click', login);



function login(event) {
    let un = document.querySelector('#username').value;
    let pw = document.querySelector('#password').value;

    event.preventDefault();

    let data = {
        username: un,
        password: pw
    }

    fetch('http://localhost:7000/login', {
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

function displayInvalidLogin() {
    //let bodyElement = document.querySelector('#invalid');
    let pElement = document.querySelector('#invalid');
    pElement.style.color = 'red';
    pElement.innerHTML = 'Invalid login';

}