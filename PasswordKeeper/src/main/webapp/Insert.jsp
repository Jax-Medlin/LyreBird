<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to the CSS file containing the BEST CSS styling -->
</head>
<body>
    <div align="center">
        <h1>Register</h1> <!-- Changed from Login to Register -->
    </div>
    <form action="InsertServlet" method="post">
    <h2></h2>
        <table>
            <tr>
                <td>Website:</td>
                <td><input type="text" name="website" required="required" class="login-input"></td> <!-- Added class for styling -->
            </tr>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="wusername" required="required" class="login-input"></td> <!-- Added class for styling -->
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="wpassword" required="required" class="login-input"></td> <!-- Added class for styling -->
            </tr>
            <tr>
                <td><input type="submit" value="Insert" class="login-input"></td> <!-- Added class for styling -->
                <td><input type="button" value="Back to Dashboard" onclick="window.location='Dashboard.jsp'" class="login-input"></td> <!-- Added class for styling -->
            </tr>
            <tr>
                <td colspan="2" class="error-message">${errormessage}<br></td> <!-- Adjusted for error message display -->
            </tr>
        </table>
    </form>
</body>