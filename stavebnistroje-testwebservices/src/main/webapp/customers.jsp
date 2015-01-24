<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customers</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />

        <script language="javascript">
            var baseUrl = '<%=application.getInitParameter("server") + application.getInitParameter("path")%>';

            /**
             * Function is used to show alert in case of any error
             */
            function fail() {
                $('#alert').show();
            }

            /**
             * Function loads user's information to the form in order to reuse for new user or for updating existing
             * @param {Long} id Id of the user
             */
            function editUser(id) {
                $.ajax({
                    url: baseUrl + '/customer/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        $("#update").show();
                        $("#customerFirstName").val(data.firstName);
                        $("#customerSecondName").val(data.secondName);
                        $("#customerAddress").val(data.address);
                        $("#customerLegal").val(data.legalStatus);
                        $("#username").val(data.username);
                        $("#password").val(data.password);
                        $("#userRole").val(data.role);
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
                $.each(data, function () {
                    print += "<tr><td>" + this.id + "</td><td>" + this.firstName + "</td><td>" + this.secondName + "</td><td>" + this.address + "</td><td>" + this.legalStatus + "</td><td><button type='button' class='btn btn-default' onclick='editUser(" + this.id + ")' >Edit</button><button type='button' class='btn btn-default' onclick='updateUser(" + this.id + ")' >Update</button><button type='button' class='btn btn-danger' onclick='deleteUser(" + this.id + ")' ><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Delete</button></td></tr>";
                });
                $('table#users tbody').empty().append(print);
            }

            /*
             * Funciton returns all users from the DB
             * @returns {undefined}
             */
            function getAllCustomers() {
                $.ajax({
                    url: baseUrl + '/customer', // ukazujeme URL a
                    type: 'GET',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: flushList,
                    error: fail
                });
            }

            /**
             * Function returns user with given id
             * @param {long} id of the user 
             * @returns user with given id 
             */
            function getUser(id) {
                $.ajax({
                    url: baseUrl + '/customer/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        var print = '';
                        print += "<tr><td>" + id + "</td><td>" + data.firstName + "</td><td>" + data.secondName + "</td><td>" + data.address + "</td><td>" + data.legalStatus + "</td><td><button type='button' class='btn btn-default' onclick='editUser(" + data.id + ")' >Edit</button><button type='button' class='btn btn-default' onclick='updateUser(" + data.id + ")' >Update</button><button type='button' class='btn btn-danger' onclick='deleteUser(" + data.id + ")' ><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Delete</button></td></tr>";
                        $('table#users tbody').empty().append(print);
                    },
                });

            }

            /**
             * Function for creating new user
             */
            function createUser() {

                var fName = $("#customerFirstName").val();
                var lName = $("#customerSecondName").val();
                var cAddress = $("#customerAddress").val();
                var cLegal = $("#customerLegal").val();
                var cUsername = $("#username").val();
                var cPassword = $("#password").val();
                var cRole = $("#userRole").val();

                $.ajax({
                    url: baseUrl + '/customer', // ukazujeme URL a
                    type: 'POST',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    contentType: "application/json",
                    data: JSON.stringify({"firstName": fName, "secondName": lName, "address": cAddress, "legalStatus": cLegal, "username": cUsername, "password": cPassword, "role": cRole}),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllCustomers();
                        resetForm();
                    },
                    error: fail
                });
            }

            /**
             * Function for udpating the user with given id
             * @param {long} id of the user
             * @returns list of users
             */
            function updateUser(id) {

                var fName = $("#customerFirstName").val();
                var lName = $("#customerSecondName").val();
                var cAddress = $("#customerAddress").val();
                var cLegal = $("#customerLegal").val();
                var cUsername = $("#username").val();
                var cPassword = $("#password").val();
                var cRole = $("#userRole").val();



                $.ajax({
                    url: baseUrl + '/customer/update/' + id, // ukazujeme URL a
                    type: 'PUT',
                    contentType: 'application/json',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    data: JSON.stringify({"firstName": fName, "secondName": lName, "address": cAddress, "legalStatus": cLegal, "username": cUsername, "password": cPassword, "role": cRole}),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllCustomers();
                        resetForm();
                    },
                    error: fail
                });
            }

            /**
             * Function which delete user with given id
             * @param {long} id of the user
             */
            function deleteUser(id) {
                $.ajax({
                    url: baseUrl + '/customer/delete/' + id, // ukazujeme URL a
                    type: 'DELETE',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllCustomers();
                    },
                    error: fail
                });
            }

            /**
             * Search user by name
             * @param {String} search name of the user
             * @returns list of users whith given name
             */
            function searchUser(search) {
                $.ajax({
                    url: baseUrl + '/customer/search/' + search, // ukazujeme URL a
                    type: 'GET',
                    headers: {
                        "Authorization": "Basic " + btoa("rest:rest")
                    },
                    success: flushList,
                    error: fail
                });
            }

            /**
             * multiple choice for user's legal status
             */
            function legalStatus() {
                var customerLegal = document.forms[0].customerLegal.value;
            }

            /**
             * multiple choice for user's roles
             */
            function userRole() {
                var userRole = document.forms[0].userRole.value;
            }

            /**
             * Function for resetting the form
             */
            function resetForm() {
                $("#customerFirstName").val("");
                $("#customerSecondName").val("");
                $("#customerAddress").val("");
                $("#customerLegal").val("");
                $("#username").val("");
                $("#password").val("");
                $("#userRole").val("");
            }

            function preferedBrowser() {
                prefer = document.forms[0].browsers.value;
                alert("You prefer browsing internet with " + prefer);
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
                        <li><a href="customers.jsp">Customer</a></li>
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
            <strong>Error!</strong> There was a problem with your network connection. Or maybe another problem.
        </div>        

        <br>
        <br>
        <h3>Customer form</h3>
        <br>
        <table class="table table-striped" id="usersForm">
            <tr>
                <th>First name:</th>
                <th><input id="customerFirstName" type="text"></th>
            </tr>
            <tr>
                <th>Second name:</th>
                <th><input id="customerSecondName" type="text"></th>
            </tr>                          
            <tr>
                <th>Address:</th>
                <th><input id="customerAddress" type="text"></th>
            </tr>          
            <tr>
                <th>Legal status:</th>
                <th>
                    <select id="customerLegal" onchange="legalStatus()">
                        <option value="NATURAL">Natural</option>
                        <option value="LEGAL">Legal</option>
                    </select>
                </th>
            </tr>   
            <tr>
                <th>Username:</th>
                <th><input id="username" type="text"></th>
            </tr>  
            <tr>
                <th>Password:</th>
                <th><input id="password" type="password"></th>
            </tr>   
            <tr>
                <th>User role:</th>
                <th>
                    <select id="userRole" onchange="userRole()">
                        <option value="ROLE_USER">ROLE_USER</option>
                        <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                    </select>
                </th>
            </tr>                      
            <tr>
                <th>&nbsp;</th>
                <th>
                    <button id="create" type='button' class='btn btn-default' onClick="createUser()">Create customer</button>
                    <button id="reset"  type='button' class='btn btn-default' onClick="resetForm()">Reset</button>  
                </th>
                <th>

                </th>                        
                <th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
            </tr>
        </table>
        <br>
        <br>
        <h3>Search customers</h3>
        <br>    
        <table class="table table-striped" id="usersSearch">
            <th><input id="searchParameter" type="text"></th>
            <th>
                <button id="search"  type='button' class='btn btn-default' onClick="searchUser($('#searchParameter').val())">Search by name</button>
                <button id="search"  type='button' class='btn btn-default' onClick="getUser($('#searchParameter').val())">Search by ID</button>
                <button id="getAllCustomer" type='button' class='btn btn-default' onClick="getAllCustomers()">Get all customers</button>
            </th>
            <th></th>
        </table>
        <table class="table table-striped" id="users">
            <thead>
                <tr>
                    <th width="5%">id</th>
                    <th width="19%">First name</th>
                    <th width="19%">Second name</th>
                    <th width="19%">Address</th>
                    <th width="19%">Legal Status</th>
                    <th width="19%">Tools</th>
                </tr>                
            </thead>
            <tbody>
            </tbody>
        </table>

        <script>
            $('#alert').hide();
            $(function () {
                getAllCustomers();
            });
        </script>

    </body>
</html>
