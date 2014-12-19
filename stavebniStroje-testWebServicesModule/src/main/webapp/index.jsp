<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script language="javascript">

                    function getUser(){
                    var id = 1;
                            $.ajax({
                            url: 'http://localhost/pa165/rest/service/customer/get/' + id, // ukazujeme URL a
                                    type : 'GET',
                                    success: function (data, textStatus) { // funkce success zpracovává data
                                    console.log(data);
                                    }
                            });
                    }
            function updateUser(){
            var id = 1;
                    $.ajax({
                    url: 'http://localhost/pa165/rest/service/customer/delete' + id, // ukazujeme URL a
                            type : 'PUT',
                            success: function (data, textStatus) { // funkce success zpracovává data
                            console.log(data);
                            }
                    });
            }

            function getAllCustomers(){
            $.ajax({
            url: 'http://localhost/pa165/rest/service/customer', // ukazujeme URL a
                    type : 'GET',
                    success: function (data, textStatus) { // funkce success zpracovává data
                    console.log(data);
                    }
            });
            }
            function deleteUser(){
            var id = 1;
                    $.ajax({
                    url: 'http://localhost/pa165/rest/service/customer/delete' + id, // ukazujeme URL a
                            type : 'DELETE',
                            success: function (data, textStatus) { // funkce success zpracovává data
                            console.log(data);
                            }
                    });
            }

        </script>
    </head>
    <body>

        <h1>Hello World!</h1>
        <button text="create customer" onClick="getUser()">Create customer</button>
    </body>
</html>
