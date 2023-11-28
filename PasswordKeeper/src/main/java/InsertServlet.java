

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
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			DBConnection.getDBConnection();
			Connection con= DBConnection.connection;
			String website=request.getParameter("website");
			String wusername=request.getParameter("wusername");
			String wpassword = request.getParameter("wpassword");
			String errormessage = "";
			request.removeAttribute(errormessage);
			String username = (String)request.getSession().getAttribute("username");
			System.out.println("username");
				PreparedStatement preparedstmt=con.prepareStatement("insert into loginInfo (username, website, wusername, wpassword) values (?,?,?,?)");
				preparedstmt.setString(1, username);
				preparedstmt.setString(2, website);
				preparedstmt.setString(3, wusername);
				preparedstmt.setString(4, wpassword);
				preparedstmt.execute();
				con.close();
				errormessage = "Data has been added!";
				request.getSession().setAttribute("errormessage", errormessage);
				RequestDispatcher rd=request.getRequestDispatcher("Insert.jsp");
				rd.forward(request, response);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}