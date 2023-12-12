<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<div align=center>
<h1>Login</h1>
</div>
<%
    // Clearing the session attribute "username"
    String username = (String)request.getSession().getAttribute("username");
    if (username != null) {
        request.getSession().removeAttribute("username");
    }
%>
<form action=LoginServlet method=post>
<table>
<%request.getSession().removeAttribute("errormessage"); %>
<tr><td>Enter Username:</td><td><input type=text name=username required="required"></td></tr>
<tr><td>Enter Password:</td><td><input type=password name=password required="required"></td></tr>
<tr><td><input type=submit value=Login></td><td><input type="button" value="Register" onclick="window.location='Register.jsp'" ></td></tr>
<tr><td colspan="2" class="error-message">${errormessage}<br></td></tr>
</table>
</form>
</body>
</html>