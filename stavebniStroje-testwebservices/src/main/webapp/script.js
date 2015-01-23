function printData(data, textStatus) {
    console.log("data: ");
    console.log(data);
}

function errorAPI() {
    $('#errorAlert').show();
}

function customerTable() {
    var t = $('<table class="table">');
    var r = $('<tr>');
    r.append("<th>id</th>");
    r.append("<th>first name</th>");
    r.append("<th>second name</th>");
    r.append("<th>address</th>");
    r.append("<th>status</th>");
    t.append(r);
    return t;
}

function customerLayout(data) {
    var t = customerTable();
    if (!(data instanceof Array)) {
        _data = new Array();
        _data[0] = data;
        data = _data;
    }
    for (var i = 0; i < data.length; ++i) {
        var r = $('<tr>');
        r.append("<td>" + data[i].id + "</td>");
        r.append("<td>" + data[i].firstName + "</td>");
        r.append("<td>" + data[i].secondName + "</td>");
        r.append("<td>" + data[i].address + "</td>");
        r.append("<td>" + data[i].legalStatus + "</td>");
        t.append(r);
    }
    return t;
}

function machineTable() {
    var t = $('<table class="table">');
    var r = $('<tr>');
    r.append("<th>id</th>");
    r.append("<th>name</th>");
    r.append("<th>type</th>");
    r.append("<th>description</th>");
    r.append("<th>status</th>");
    t.append(r);
    return t;
}

function machineLayout(data) {
    var t = machineTable();
    if (!(data instanceof Array)) {
        _data = new Array();
        _data[0] = data;
        data = _data;
    }
    for (var i = 0; i < data.length; ++i) {
        var r = $('<tr>');
        r.append("<td>" + data[i].id + "</td>");
        r.append("<td>" + data[i].name + "</td>");
        r.append("<td>" + data[i].type + "</td>");
        r.append("<td>" + data[i].description + "</td>");
        r.append("<td>" + data[i].state + "</td>");
        t.append(r);
    }
    return t;
}


function getLayout(service) {
    switch (service) {
        case 'customer':
            return customerLayout;
        case 'machine':
            return machineLayout;
        default:
            return function () {
                return '';
            };
    }
}

function printLayouted(layout) {
    return function (data, textStatus) {
        $('#result').html(layout(data));
    };
}

function getItem(service) {
    var id = prompt('Type an id:');
    if (id === null)
        return;
    id = parseInt(id);
    if (isNaN(id)) {
        $('#warnId').show();
        return;
    }
    $.ajax({
        url: 'http://localhost:8080/pa165/rest/service/'+service+'/get/' + id,
        type: 'GET',
        success: printLayouted(getLayout(service)),
        error: errorAPI
    });
}
function createCustomer() {

    $.ajax({
        url: 'http://localhost:8080/pa165/rest/service/customer',
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify({"firstName": "tert", "secondName": "test", "address": "test", "legalStatus": "NATURAL"}),
        success: printData
//        error: function()
    });
}
function updateUser() {
    var id = parseInt(prompt('Type an id:'));
    if (isNaN(id)) {
        $('#warnId').show();
        return;
    }
    $.ajax({
        url: 'http://localhost:8080/pa165/rest/service/customer/delete' + id,
        type: 'PUT',
        success: printData,
        error: errorAPI
    });
}

function getAllItems(service) {
    $.ajax({
        url: 'http://localhost:8080/pa165/rest/service/' + service,
        type: 'GET',
        success: printLayouted(getLayout(service)),
        error: errorAPI
    });
}

function deleteItem(service) {
    var id = prompt('Type an id:');
    if (id === null)
        return;
    id = parseInt(id);
    if (isNaN(id)) {
        $('#warnId').show();
        return;
    }
    $.ajax({
        url: 'http://localhost:8080/pa165/rest/service/'+service+'/delete/' + id,
        type: 'DELETE',
        success: function(){
            console.log('deletion successfull');
            getAllItems(service);
        },
        error: errorAPI
    });
}