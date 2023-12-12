

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
			PreparedStatement ps=con.prepareStatement("select username from loginDB where username=?");
			ps.setString(1, username);
			ResultSet rs= ps.executeQuery();
			String errormessage = "";
			request.removeAttribute(errormessage);
			if(rs.next()) {
				errormessage = "Username already taken please try again!";
				request.getSession().setAttribute("errormessage", errormessage);
				RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
			}else {
				PreparedStatement preparedstmt=con.prepareStatement("insert into loginDB (username, password) values (?, ?)");
				preparedstmt.setString(1, username);
				preparedstmt.setString(2, password);
				preparedstmt.execute();
				con.close();
				errormessage = "Data has been added!";
				request.getSession().setAttribute("errormessage", errormessage);
				RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
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