

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			utils.DBConnection.getDBConnection();
			Connection con= utils.DBConnection.getDBConnection();
			String username=request.getParameter("username");
			String password = request.getParameter("password");
			PreparedStatement ps=con.prepareStatement("select username from loginDB where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs= ps.executeQuery();
			String errormessage = "";
			request.getSession().removeAttribute("errormessage");
			request.setAttribute("errormessage", errormessage);
			if(rs.next()) {
				request.getSession().setAttribute("username", username);
				response.sendRedirect(request.getContextPath() + "/Dashboard.jsp");
			}else {
				errormessage = "Username or Password are not correct!";
				request.setAttribute("errormessage", errormessage);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
