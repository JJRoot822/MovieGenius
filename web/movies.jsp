<%-- 
    Document   : movies
    Created on : Oct 9, 2023, 1:24:04 PM
    Author     : tmdel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movies</title>
    </head>
    <body>
        <h1>Movie List</h1>
        <h2>Upcoming Movies</h2>
        <h2>Current Movies</h2>
        <form action="Private" method="post">
            <input type="hidden" name="action" value="review">
            <input type="submit" value="Review">
        </form>
    </body>
</html>