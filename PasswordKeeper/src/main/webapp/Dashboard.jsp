<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="content">
        <h2 class="dashboard-header">Dashboard</h2>
        <h2><a href="Insert.jsp">Insert</a></h2>
        <h2><a href="Login.jsp">Logout</a></h2>
        <table class="dashboard-table">
            <tr>
                <th>Website</th>
                <th>Username</th>
                <th>Password</th>
                <th>Delete</th>
            </tr>
            <% 
            request.getSession().removeAttribute("errormessage");
            Connection con = null;
            try {
                // Establish connection
                con = utils.DBConnection.getDBConnection();
				
                // Get username
                String username = (String)request.getSession().getAttribute("username");
                if(username == null){
                    // Redirect to login page if not logged in
                    response.sendRedirect("Login.jsp");
                    return;
                }

                // Prepare SQL query to GET DAT user data
                PreparedStatement ps = con.prepareStatement("SELECT id, website, wusername, wpassword FROM loginInfo WHERE username=?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                // Iterate over each row in the result set
                while(rs.next()) {
            %>
                    <tr>
                        <td><%= rs.getString("website") %></td>
                        <td><%= rs.getString("wusername") %></td>
                        <td><%= rs.getString("wpassword") %></td>
                        <td>
                            <!-- Form for delete button -->
                            <form method="post" action="DeleteServlet">
                                <!-- Hidden field to hold the id of the entry -->
                                <input type="hidden" name="id" value="<%= rs.getInt("id") %>"/>
                                <!-- User-friendly delete button -->
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>
            <% 
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                // Close the database connection
                if (con != null) {
                    try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            }
            %>
        </table>
    </div>
</body>
</html>
