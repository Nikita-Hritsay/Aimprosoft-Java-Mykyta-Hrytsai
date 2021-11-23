let promise = $.getJSON({
    url: 'http://localhost:8080'
}).then(function (result) {
    console.log('result', result)
}).catch(function (err) {
    console.log('err', err)
})



