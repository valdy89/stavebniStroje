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


            function getUser(id) {
                //var id = 1;
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer/get/' + id, // ukazujeme URL a
                    type: 'GET',
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        flushCustomer(data);
                    },
                    error: fail
                });
            }


            function editUser(id) {
                //$('#cfn').text(getUser(id).firstName);
                document.getElementById("c_firstName").value = "Johnny";
                document.getElementById("c_secondName").value = "Bravo";
                document.getElementById("c_address").value = "NY"; 
                document.getElementById("c_legal").value = "LEGAL";  
            }            


            function flushList(data) { // funkce success zpracovává data
                var print = '';
                $.each(data, function () {
                    print += "<tr><td>" + this.id + "</td><td>" + this.firstName + "</td><td>" + this.secondName + "</td><td>" + this.address + "</td><td>" + this.legalStatus +"</td><td><button type='button' class='btn btn-default' onclick='editUser(" + this.id + ")' >Edit</button><button type='button' class='btn btn-default' onclick='updateUser(" + this.id + ")' >Update</button><button type='button' class='btn btn-danger' onclick='deleteUser(" + this.id + ")' ><span class='glyphicon glyphicon-remove' aria-hidden='true'></span>Delete</button></td></tr>";
                });
                $('table#users tbody').empty().append(print);
            }

            function flushCustomer(data) {
                $('#cId').text(data.id);
                $('#cFirstName').text(data.firstName);
                $('#cLastName').text(data.secondName);
                $('#cAddress').text(data.address);
                $('#clegalStatus').text(data.legalStatus ? 'NATURAL' : 'LEGAL');
             
            }
            
            function getAllCustomers() {
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer', // ukazujeme URL a
                    type: 'GET',
                    success: flushList,
                    error: fail
                });
            }


            function createUser() {
               var fName, text;
               var lName, text;
               var cAddress, text;
               var cLegal, text;
               
                fName   = document.getElementById("c_firstName").value;
                lName   = document.getElementById("c_secondName").value;
                cAddress = document.getElementById("c_address").value;
                cLegal  = document.getElementById("c_legal").value;
                
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer', // ukazujeme URL a
                    type: 'POST',
                    contentType: "application/json",
                    data: JSON.stringify({"firstName": fName, "secondName": lName, "address": cAddress, "legalStatus": cLegal}),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllCustomers();
                    },
                    error: fail
                });
            }
            
            
            function updateUser(id) {
//dodato
               var fName, text;
               var lName, text;
               var cAddress, text;
               var cLegal, text;
               
                fName   = document.getElementById("c_firstName").value;
                lName   = document.getElementById("c_secondName").value;
                cAddress = document.getElementById("c_address").value;
                cLegal  = document.getElementById("c_legal").value;
//dovde
                    
                    
                
                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer/update/' + id, // ukazujeme URL a
                    type: 'PUT',
                    contentType: 'application/json',
                      data: JSON.stringify({"firstName": fName, "secondName": lName, "address": cAddress, "legalStatus": cLegal}),
//                    data: JSON.stringify({"firstName": "----", "secondName": "----", "address": "----", "legalStatus": "NATURAL"}),
                    success: function (data, textStatus) { // funkce success zpracovává data
                        console.log(data);
                        getAllCustomers();
                    },
                    error: fail
                });
            }

            function deleteUser(id) {

                $.ajax({
                    url: 'http://localhost:8080/pa165/rest/service/customer/delete/' + id, // ukazujeme URL a
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
                    url: 'http://localhost:8080/pa165/rest/service/customer/search/' + search, // ukazujeme URL a
                    type: 'GET',
                    success: flushList,
                    error: fail
                });
            }
        function preferedBrowser() {
            prefer = document.forms[0].browsers.value;
             alert("You prefer browsing internet with " + prefer);
        }
 
        function legalStatus() {
            c_legal = document.forms[0].c_legal.value;
        }
        
        function resetForm() {
            document.getElementById("theForm").reset();
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

    <!--    
        <button onClick="getAllCustomers()">Get ALL customers</button>
        <button onclick="searchUser('----')">Get ALL vaporized</button>
    -->
    <br>
    <br>
    <h2>Customer form</h2>

   
        <table class="table table-striped" id="users">
            <thead>

                 <form id="theForm">     
                    <tr>
                        <th>Licno ime</th>
                        <th><input id="c_firstName" type="text" value=""></th>
                    </tr>
                    <tr>
                        <th>Prezime</th>
                        <th><input id="c_secondName" type="text" value=""></th>
                    </tr>                          
                    <tr>
                        <th>Adresa</th>
                        <th><input id="c_address" type="text" value=""></th>
                    </tr>          
                    <tr>
                        <th>Status</th>
                        <th>
                            <select id="c_legal" onchange="legalStatus()">
                               <option value="NATURAL">Natural</option>
                               <option value="LEGAL">Legal</option>
                            </select>
                        </th>
                    </tr>   
                    <tr>
                        <th>&nbsp;</th>
                        <th>
                            <button id="create" onClick="createUser()">Create customer</button>
                            <button id="reset"  onClick="resetForm()">Reset</button>  
                        </th>
                        <th>&nbsp;</th>
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
