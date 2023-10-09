<%-- 
    Document   : updateUser
    Created on : Oct 9, 2023, 2:36:06 PM
    Author     : tmdel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User Info</title>
    </head>
    <body>
        <h1>Update Information</h1>
        <label>Email:</label>
        <input type="text" name="email">
        <br><br>
        <label>User Name:</label>
        <input type="text" name="userName">
        <br><br>
        <label>Enter New Password:</label>
        <input type="text" name="newPassword">
        <br><br>
        <label>Enter Re-enter New Password:</label>
        <input type="text" name="checkNewPassword">
        <br><br>
        <label>Enter Old Password:</label>
        <input type="text" name="oldPassword">
        <br><br>
        <label>Viewable: </label>
        <br>
        <input type="radio" id="public" name="viewable" value="drill" checked="true">
        <label>Public</label>
        <br>
        <input type="radio" id="private" name="viewable" value="test">
        <label>Private</label>
        <br><br>
        <input type="submit" value="Update">
        <br><br><br>
        <input type="submit" value="Delete Account">
    </body>
</html>
