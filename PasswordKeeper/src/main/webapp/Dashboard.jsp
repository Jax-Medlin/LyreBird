<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
    <%@ page import= "java.sql.PreparedStatement" %>
    <!DOCTYPE html>
<html>
<body>
<br><br>
<form method="post" name="form">
<table border="1">
<tr><th>Website</th><th>Username</th><th>Password</th></tr>
    <% 
    Connection con = null;
    String url = "jdbc:mysql://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:3306/PKDB?useSSL=false";
 	String usrname = "group_remote";
 	String password = "group";
    try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url,usrname,password);
		String username= (String)request.getSession().getAttribute("username");
		PreparedStatement ps=con.prepareStatement("select website, wusername, wpassword from loginInfo where username=?");
		ps.setString(1, username);
		ResultSet rs= ps.executeQuery();
		%>
		<% 
		while(rs.next()){
		%>
		<tr><td><%=rs.getString(1)%></td>
		<td><%=rs.getString(2)%></td>
		<td><%=rs.getString(3)%></td>
		</tr>
		<% 
		}
		%>
		<% 
    }
		
		catch(Exception e){
			e.printStackTrace();
		}
    %>

</table>
</form>
</body>
</html>