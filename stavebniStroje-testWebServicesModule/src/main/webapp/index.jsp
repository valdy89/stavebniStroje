<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script language="javascript">
            $.ajax({
                url: 'http://localhost/pa165/webresources/customers', // ukazujeme URL a
                dataType: "json", // typ odesílaných dat
                success: function (data, textStatus) { // funkce success zpracovává data
                    console.log(data);
                }
            });
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
