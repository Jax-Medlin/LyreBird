import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = utils.DBConnection.getDBConnection();
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr); // Parse the id as integer

            PreparedStatement preparedstmt = con.prepareStatement("DELETE FROM loginInfo WHERE id = ?");
            preparedstmt.setInt(1, id);
            preparedstmt.execute();

            response.sendRedirect(request.getContextPath() + "/Dashboard.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Consider adding more informative error handling here
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
