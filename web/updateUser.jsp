<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User Info</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <form action="Private" method="post">
                <input type="hidden" name="action" value="updateUser">
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
            </form>
            <br><br><br>
            <form action="Private" method="post">
                <input type="hidden" name="action" value="deleteAccount">
                <input type="submit" value="Delete Account">
            </form>
        </main>
    </body>
</html>
