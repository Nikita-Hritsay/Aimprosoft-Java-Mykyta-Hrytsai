function component() {
    $.getJSON(
        'http://localhost:8080/emplpoyee'
    ).then(function (result) {
        console.log('result', result)
        document.getElementById("app").innerHTML = result[0]
    }).catch(function (err) {
        console.log('err', err)
    })
}

component()
