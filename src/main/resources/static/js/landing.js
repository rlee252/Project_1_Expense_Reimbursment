

document.querySelector('#logout').addEventListener('click', logout);

function logout() {
    fetch('http://localhost:7000/logout', {
        method: 'POST'
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = '/index.html';
        }
    })
}