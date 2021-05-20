document.querySelector('#signup').addEventListener('click', signup);

function signup(event) {


    let un = document.querySelector('#username').value;
    let pw = document.querySelector('#password').value;
    let fn = document.querySelector('#firstname').value;
    let ln = document.querySelector('#lastname').value;
    let em = document.querySelector('#email').value;
    let rl = document.querySelector('#roles').value;
    event.preventDefault();

    let data = {
        username: un,
        password: pw,
        firstname: fn,
        lastname: ln,
        email: em,
        role: rl
    }

    fetch('http://localhost:7000/signup', {
        method: 'POST',
        credentials: 'include', // this specifies that when you receive cookies you should include them in future requests
        //its important so that the backend can identify if we are logged in or not.
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/index.html';
        } else {
            displayInvalidLogin();

        }
    })

}