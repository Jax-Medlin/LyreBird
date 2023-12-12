import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            utils.DBConnection.getDBConnection();
            con = utils.DBConnection.getDBConnection();

            String website = request.getParameter("website");
            String wusername = request.getParameter("wusername");
            String wpassword = request.getParameter("wpassword");
            String username = (String) request.getSession().getAttribute("username");

            // Debugging statement --delete me!
            // System.out.println("Inserting: Website - " + website + ", Username - " + wusername + ", Password - " + wpassword);

            if (username == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
                rd.forward(request, response);
                return;
            }

            if (website.isEmpty() || wusername.isEmpty() || wpassword.isEmpty()) {
                // Set error message and forward back to the form
                request.setAttribute("errormessage", "Please fill all fields.");
                RequestDispatcher rd = request.getRequestDispatcher("Insert.jsp");
                rd.forward(request, response);
                return;
            }

            PreparedStatement preparedstmt = con.prepareStatement("INSERT INTO loginInfo (username, website, wusername, wpassword) VALUES (?, ?, ?, ?)");
            preparedstmt.setString(1, username);
            preparedstmt.setString(2, website);
            preparedstmt.setString(3, wusername);
            preparedstmt.setString(4, wpassword);
            preparedstmt.execute();

            request.getSession().setAttribute("errormessage", "Data has been added!");
            response.sendRedirect("Insert.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errormessage", "Error occurred: " + e.getMessage());
            response.sendRedirect("Insert.jsp");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
