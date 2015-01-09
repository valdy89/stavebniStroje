<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />

        <script language="javascript">

            function fail() {
                $('#alert').show();
            }

            function editUser(id) {
                $.ajax({
                    url: '/pa165/rest/service/customer/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        $("#update").show();
                        $("#customerFirstName").val(data.firstName);
                        $("#customerSecondName").val(data.secondName);
                        $("#customerAddress").val(data.address);
                        $("#customerLegal").val(data.legalStatus);
                    },
                    error: fail
                });                
    

    }  


            function flushList(data) { // funkce success zpracovává data
                var print = '';
                $.each(data, function () {
                    print += "<tr><td>" + this.id + "</td><td>" + this.firstName + "</td><td>" + this.secondName + "</td><td>" + this.address + "</td><td>" + this.legalStatus +"</td><td><button type='button' class='btn btn-default' onclick='editUser(" + this.id + ")' >Edit</button><button type='button' class='btn btn-default' onclick='updateUser(" + this.id + ")' >Update</button><button type='button' class='btn btn-danger' onclick='deleteUser(" + this.id + ")' ><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Delete</button></td></tr>";
                });
                $('table#users tbody').empty().append(print);
            }


            
            function getAllCustomers() {
                $.ajax({
                    url: '/pa165/rest/service/customer', // ukazujeme URL a
                    type: 'GET',
                    success: flushList,
                    error: fail
                });
            }

            function getUser(id) {
                var id = 35;
                $.ajax({
                    url: '/pa165/rest/service/customer/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        flushList(data);
                    },
                    error: fail
                });
            }


            function createUser() {
                
                var fName   = $("#customerFirstName").val();
                var lName   = $("#customerSecondName").val();
                var cAddress= $("#customerAddress").val();
                var cLegal  = $("#customerLegal").val();
                
                $.ajax({
                    url: '/pa165/rest/service/customer', // ukazujeme URL a
                    type: 'POST',
                    contentType: "application/json",
                    data: JSON.stringify({"firstName": fName, "secondName": lName, "address": cAddress, "legalStatus": cLegal}),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllCustomers();
                        resetForm();
                    },
                    error: fail
                });
            }
            
            
            function updateUser(id) {

            var fName   = $("#customerFirstName").val();
            var lName   = $("#customerSecondName").val();
            var cAddress= $("#customerAddress").val();
            var cLegal  = $("#customerLegal").val();

                    
                    
                
                $.ajax({
                    url: '/pa165/rest/service/customer/update/' + id, // ukazujeme URL a
                    type: 'PUT',
                    contentType: 'application/json',
                      data: JSON.stringify({"firstName": fName, "secondName": lName, "address": cAddress, "legalStatus": cLegal}),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllCustomers();
                        resetForm();
                    },
                    error: fail
                });
            }

            function deleteUser(id) {

                $.ajax({
                    url: '/pa165/rest/service/customer/delete/' + id, // ukazujeme URL a
                    type: 'DELETE',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllCustomers();
                    },
                    error: fail
                });
            }
        
        function searchUser(search) {
                $.ajax({
                    url: '/pa165/rest/service/customer/search/' + search, // ukazujeme URL a
                    type: 'GET',
                    success: flushList,
                    error: fail
                });
            }
        function preferedBrowser() {
            prefer = document.forms[0].browsers.value;
             alert("You prefer browsing internet with " + prefer);
        }

        //multiple choice    
        function legalStatus() {
            c_legal = document.forms[0].c_legal.value;
        }
        
        function resetForm() {
            $("#customerFirstName").val("");
            $("#customerSecondName").val("");
            $("#customerAddress").val("");
            $("#customerLegal").val("");
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
                        <li><a href="customers.jsp">Customer tests</a></li>
                        <li><a href="machines.jsp">Machines tests</a></li>
                        <li><a href="rents.jsp">Rents tests</a></li>
                    </ul> 
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/about.jsp">About</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <h1>Customers tests</h1>

        <div class="alert alert-danger" id="alert">
            <a href="#" class="close" onclick="$('#alert').hide();">&times;</a>
            <strong>Error!</strong> There was a problem with your network connection. Or maybe another problem.
        </div>        

    <br>
    <br>
    <h2>Customer form</h2>

   
        <table class="table table-striped" id="users">
            <thead>

                 <form id="theForm">     
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
                        <th>&nbsp;</th>
                        <th>
                            <button id="create" type='button' class='btn btn-default' onClick="createUser()">Create customer</button>
                            <button id="reset"  type='button' class='btn btn-default' onClick="resetForm()">Reset</button>  
                        </th>
                        <th>
                            
                        </th>                        
                        <th>
                        <th>&nbsp;</th>
                    </tr>
                    <tr>
                        <th>Search for :</th>
                        <th>
                            <input id="searchParameter" type="text">
                            <button id="search"  type='button' class='btn btn-default' onClick="searchUser($('#searchParameter').val())">Search by name</button>
                            <button id="getAllCustomer" type='button' class='btn btn-default' onClick="getAllCustomers()">Get all customers</button>
                        </th>                            
                        
                    </tr>
                    </form>
     
         <tr>

            <th>id</th>
            <th>First name</th>
            <th>Second name</th>
            <th>Address</th>
            <th>Legal Status</th>
            <th>Tools</th>
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
