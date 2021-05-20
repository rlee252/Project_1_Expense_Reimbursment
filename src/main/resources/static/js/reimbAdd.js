document.querySelector('#submit').addEventListener('click', addReimb);

function addReimb(event) {
    console.log("im in reimbAdd")

    let tp = document.querySelector('#type').value;
    let am = document.querySelector('#amount').value;
    let txt = document.querySelector('#txtArea').value;
    event.preventDefault();

    let data = {
        reimbType: tp,
        reimbAmount: am,
        description: txt,
        receipt: null
    }

    fetch('http://localhost:7000/Reimb', {
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


        }
    })


}