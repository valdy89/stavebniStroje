<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Machines</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />

        <script language="javascript">
            var baseUrl = '<%=application.getInitParameter("server") + application.getInitParameter("path")%>';
            var items;

            /**
             * Function is used to show alert in case of any error
             */
            function fail() {
                $('#alert').show();
            }

            /**
             * Function for date format
             * @param {date} date date for formating
             */
            function formatDate(date) {
                var pad = function (val) {
                    var value = String(val);
                    while (value.length < 2)
                        value = '0' + value;
                    return value;
                };
                return date.getFullYear() + '-' + pad(date.getMonth() + 1) + '-' + pad(date.getDate());
            }

            /**
             * Function used for preparing data about revison for the output 
             * @param {data} data which needs to be present by the table
             * @param {Long} id of the revision
             */
            function flushRevisionList(id, data) {
                console.log(data);
                var ul = $('#mRevisions');
                ul.empty();
                ul.append('<button onclick="addRevision(' + id + ', \'' + formatDate(new Date()) + '\')">Add revision</button>');
                $.each(data, function () {
                    var li = $('<li class="list-group-item"></li>');
                    li.html('<span class="badge"><button type="button" onclick="deleteRevision(' + this.id + ', ' + id + ')"><span aria-hidden="true">&times;</span></button></span>');
                    li.append(new Date(this.dateOfRevision).toDateString());
                    ul.append(li);
                });
            }

            /**
             * Function used for preparing data about machine for the output 
             * @param data 
             */
            function flushMachine(data) {
                $('#mId').text(data.id);
                $('#mName').text(data.name);
                $('#mType').text(data.type);
                $('#mPrice').text(data.price);
                $('#mAvail').text(data.available ? 'Yes' : 'No');
                $('#mDescription').text(data.description);
                flushRevisionList(data.id, data.revisions);
            }

            /**
             * Function returns machine with given id
             * @param {Long} id of the machine
             * @returns data about machine
             */
            function getMachine(id) {
                $.ajax({
                    url: baseUrl + '/machine/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        flushMachine(data);
                    },
                    error: fail
                });
            }

            /**
             * Function for deleting revision
             * @param {Long} id id of the revision
             * @param {Long} machineId id of the machine 
             */
            function deleteRevision(id, machineId) {
                $.ajax({
                    url: baseUrl + '/revision/delete/' + id,
                    type: 'DELETE',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: function (data, textStatus) {
                        console.log(data);
                        getMachine(machineId);
                    },
                    error: fail
                });
            }

            /**
             * Function for ading new revision.
             * @param {Long} id id of the machine
             * @param {Date} date date of the revision
             * @returns {Machine} returns machine with given id
             */
            function addRevision(id, date) {
                $.ajax({
                    url: baseUrl + '/revision',
                    type: 'POST',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    contentType: 'application/json',
                    data: JSON.stringify(
                            {
                                machineId: id,
                                dateOfRevision: date
                            }),
                    success: function () {
                        getMachine(id);
                    },
                    error: fail
                });
            }

            /**
             * Function used for generating body of the table based on the input data
             * @param {type} data which needs to be present by the table
             */
            function flushList(data) { // funkce success zpracovává data
                var print = '';
                items = new Array();

                $.each(data, function () {
                    items[this.id] = {
                        name: this.name,
                        type: this.type,
                        description: this.description,
                        price: this.price
                    };
                    print += "<tr>";
                    print += "<td>" + this.id + "</td>";
                    print += "<td>" + this.name + "</td>"
                    print += "<td>" + this.type + "</td>";
                    print += "<td>" + this.description + "</td>";
                    print += "<td>" + this.price + "</td>";
                    print += "<td>";
                    print += "<button type='button' class='btn btn-default' onclick='getMachine(" + this.id + ")'>Detail</button>";
                    print += "<button type='button' class='btn btn-default' onclick='editMachine(" + this.id + ")'>Edit</button>";
                    print += "<button type='button' class='btn btn-default' onclick='updateMachine(" + this.id + ")' >Update</button>";
                    print += "<button type='button' class='btn btn-danger' onclick='deleteMachine(" + this.id + ")' ><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Delete</button>";
                    print += "</td>";
                    print += "</tr>";
                });
                $('table#machines tbody').empty().append(print);


            }

            /**
             * Function returns all amchines from the DB
             * @returns list of the machines
             */
            function getAllMachines() {
                $.ajax({
                    url: baseUrl + '/machine', // ukazujeme URL a
                    type: 'GET',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: flushList,
                    error: fail
                });
            }

            /**
             * Function for searching machines by Id
             * @param {Long} id id of the amchine
             * @returns machine with given id
             */
            function getMachineById(id) {
                $.ajax({
                    url: baseUrl + '/machine/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        var print = '';
                        print += "<tr>" + "<td>" + id + "</td>" + "<td>" + data.name + "</td>" + "<td>" + data.type + "</td>" + "<td>" + data.description + "</td>" + "<td>" + data.price + "</td>" + "<td>" + "<button type='button' class='btn btn-default' onclick='getMachine(" + id + ")'>Detail</button>" + "<button type='button' class='btn btn-default' onclick='editMachine(" + id + ")'>Edit</button>" + "<button type='button' class='btn btn-default' onclick='updateMachine(" + id + ")' >Update</button>" + "<button type='button' class='btn btn-danger' onclick='deleteMachine(" + id + ")' ><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Delete</button>" + "</td>" + "</tr>";
                        $('table#machines tbody').empty().append(print);
                    },
                    error: fail
                });
            }

            /**
             * Function is used for creating new machine
             */
            function createMachine() {
                var mName = $("#machineName").val();
                var mType = $("#machineType").val();
                var maDescription = $("#machineDescription").val();
                var mPrice = $("#machinePrice").val();

                $.ajax({
                    url: baseUrl + '/machine', // ukazujeme URL a
                    type: 'POST',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    contentType: "application/json",
                    data: JSON.stringify(
                            {
                                "name": mName,
                                "type": mType,
                                "description": maDescription,
                                "price": mPrice
                            }),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllMachines();
                        resetForm();
                    },
                    error: fail
                });
            }

            /**
             * Function loads machine's information to the form in order to reuse for new machne or for updating existing
             * @param {Long} id Id of the machine
             */
            function editMachine(id) {
                $.ajax({
                    url: baseUrl + '/machine/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        $("#machineName").val(data.name);
                        $("#machineType").val(data.type);
                        $("#machineDescription").val(data.description);
                        $("#machinePrice").val(data.price);
                    },
                    error: fail
                });
            }

            /**
             * Function for updating machine
             * @param {Long} id of the machine which needs to be updated
             
             */
            function updateMachine(id) {
                var mName = $("#machineName").val();
                var mType = $("#machineType").val();
                var maDescription = $("#machineDescription").val();
                var mPrice = $("#machinePrice").val();

                $.ajax({
                    url: baseUrl + '/machine/update/' + id, // ukazujeme URL a
                    type: 'PUT',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    contentType: 'application/json',
                    data: JSON.stringify(
                            {
                                "name": mName,
                                "type": mType,
                                "description": maDescription,
                                "price": mPrice
                            }),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllMachines();
                        resetForm();
                    },
                    error: fail
                });
            }

            /**
             * Function for deleting machine with given Id
             * @param {Long} id of the machine
             */
            function deleteMachine(id) {
                $.ajax({
                    url: baseUrl + '/machine/delete/' + id, // ukazujeme URL a
                    type: 'DELETE',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllMachines();
                    },
                    error: fail
                });
            }

            /**
             * Function returns list of amchines with given type
             * @param {String} search type of the machine
             * @returns list of the machines
             */
            function getType(search) {
                $.ajax({
                    url: baseUrl + '/machine/type/' + search, // ukazujeme URL a
                    type: 'GET',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: flushList,
                    error: fail
                });
            }

            /**
             * Function for resetting the form
             */    
            function resetForm() {
                $("#machineName").val("");
                $("#machineType").val("");
                $("#machineDescription").val("");
                $("#machinePrice").val("");
            }

            /**
             * Multiple choice    
             */
            function machineType() {
                //c_legal = document.forms[0].c_legal.value;
                machineType = document.forms[0].machineType.value;
            }
        </script>
    </head>
    <body>

        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <!-- potrebujeme dodat class="active" pokud ma byt odkaz aktivni -->
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="customers.jsp">Customers</a></li>
                        <li><a href="machines.jsp">Machines</a></li>

                    </ul> 
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/about.jsp">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="alert alert-danger" id="alert">
            <a href="#" class="close" onclick="$('#alert').hide();">&times;</a>
            <strong>Error!</strong> there was a problem with your network connection. Or maybe another problem.
        </div>        
        <br>
        <br>        
        <h3>Machine form</h3>

        <table class="table table-striped" id="machinesForm">
            <tr>
                <th>Name:</th>
                <th><input id="machineName" type="text"></th>
            </tr>
            <tr>
                <th>Type:</th>
                <th>
                    <select id="machineType" onchange="machineType()">
                        <option value="N/A"></option>
                        <option value="TRACTOR">Tractor</option>
                        <option value="EXCAVATOR">Excavator</option>
                        <option value="LORRY">Lorry</option>                               
                    </select>
                </th>
            </tr>
            <tr>
                <th>Description:</th>
                <th><input id="machineDescription" type="text"></th>
            </tr> 
            <tr>
                <th>Price:</th>
                <th><input id="machinePrice" type="number"></th>
            </tr> 

            <tr>
                <th>&nbsp;</th>
                <th>
                    <button id="create" type='button' class='btn btn-default' onClick="createMachine()">Create machine</button>
                    <button id="reset"  type='button' class='btn btn-default' onClick="resetForm()">Reset</button>  
                </th>
            </tr>  
        </table>    
        <br>
        <br>
        <h3>Search machines</h3>
        <table class="table table-striped" id="machinesList">
            <th>By type:</th>
            <th>
                <select id="typeOfMachine" onchange="machineType()">
                    <option value="N/A"></option>
                    <option value="TRACTOR">Tractor</option>
                    <option value="EXCAVATOR">Excavator</option>
                    <option value="LORRY">Lorry</option>                               
                </select>
            </th>
            <th>
                <button id="search"  type='button' class='btn btn-default' onClick="getType($('#typeOfMachine').val())">Search by type</button>
            </th>
            <th>
                <input id="machineId" type="text">
            </th>
            <th>
                <button id="getAllCustomer" type='button' class='btn btn-default' onClick="getMachineById($('#machineId').val())">Search by Id</button>  
            </th>              
            <th>
                <button id="getAllCustomer" type='button' class='btn btn-default' onClick="getAllMachines()">Get all machines</button>  
            </th>




            <th>&nbsp;</th>
        </tr>             
    </table>
    <br>
    <br>
    <br>
    <table class="table table-striped" id="machines">
        <thead>
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Type</th>
                <th>Description</th>
                <th>Price</th>
                <th>Tools</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <h3>Detail</h3>
    <div class="row">
        <div class="col-sm-1">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">Id</h4>
                </div>
                <div class="panel-body" id="mId">
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">Name</h4>
                </div>
                <div class="panel-body" id="mName">
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">Type</h4>
                </div>
                <div class="panel-body" id="mType">
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">Price</h4>
                </div>
                <div class="panel-body" id="mPrice">
                </div>
            </div>
        </div>
        <div class="col-sm-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">Available</h4>
                </div>
                <div class="panel-body" id="mAvail">
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Description</h3>
            </div>
            <div class="panel-body" id="mDescription">
            </div>
        </div>
    </div>
    <br>

    <h3>Revisions</h3>

    <ul class="list-group" id="mRevisions">
    </ul>

    <script>
        $('#alert').hide();
        $(function () {
            getAllMachines();
        });
    </script>

</body>
</html>
