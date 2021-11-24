
function component() {
    const element = document.createElement('div');
    $.getJSON({
        url: 'http://localhost:8080'
    }).then(function (result) {
        console.log('result', result)
        document.getElementById("app").innerHTML = result[0].address

    }).catch(function (err) {
        console.log('err', err)
    })


    return element;
}

document.body.appendChild(component());


