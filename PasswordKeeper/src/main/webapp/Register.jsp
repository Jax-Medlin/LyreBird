<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
<div align=center>
<h1>Login</h1>
</div>
<form action=RegisterServlet method=post>
<table>
<tr><td>Register Username:</td><td><input type=text name=username></td></tr>
<tr><td>Register Password:</td><td><input type=password name=password></td></tr>
<tr><td><input type=submit value=Register></td><td><input type="button" value="Back to Login" onclick="window.location='Login.jsp'" ></td></tr>
<tr><font color = red>${errormessage}<br></tr>
</table>
</form>
</body>
</html>