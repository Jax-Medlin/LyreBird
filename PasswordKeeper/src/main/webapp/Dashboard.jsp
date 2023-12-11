<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.sql.PreparedStatement" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="content"> <!-- Added for potential additional styling and better structure -->
        <h2 class="dashboard-header"><h2>Dashboard</h2><h2><a href ="Insert.jsp">Insert</a></h2><h2><a href ="Login.jsp">Logout</a></h2></h2> <!-- Dashboard Header -->
        <form method="Post" action="DeleteServlet">
            <table class="dashboard-table"> <!-- Removed the 'border' attribute to rely on CSS for styling -->
                <tr>
                    <th>Website</th>
                    <th>Username</th>
                    <th>Password</th>
                </tr>
                <% 
                request.getSession().removeAttribute("id");
                String errormessage = "";
                request.removeAttribute(errormessage);
            	request.getSession().setAttribute("errormessage", errormessage);
                Connection con = null;
            		String url = "jdbc:mysql://ec2-3-14-254-207.us-east-2.compute.amazonaws.com:3306/PKDB?allowPublicKeyRetrieval=true&useSSL=false";
                String usrname = "group_remote";
                String password = "group";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(url,usrname,password);
                    String username= (String)request.getSession().getAttribute("username");
                    if(username == null){
                    	RequestDispatcher rd=getServletContext().getRequestDispatcher("/Login.jsp");
        				rd.forward(request, response);
                    }
                    PreparedStatement ps=con.prepareStatement("select id, website, wusername, wpassword from loginInfo where username=?");
                    ps.setString(1, username);
                    ResultSet rs= ps.executeQuery();
                %>
                    <% 
                    while(rs.next()){
                   %>
                        <tr>
                            <td><%=rs.getString(2)%></td>
                            <td><%=rs.getString(3)%></td>
                            <td><%=rs.getString(4)%></td>
                            <td><input class="dashboard-deletetype" type="submit" name="id" value=<%=rs.getString(1)%>/></td>
                        </tr>
                    <% 
                    }
                    %>
                <% 
                } catch(Exception e) {
                    e.printStackTrace();
                }
                %>
            </table>
        </form>
    </div>
</body>
</html>
