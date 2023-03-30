package crudoperations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatelink")
public class update extends HttpServlet {Connection con;

@Override
public void init() throws ServletException {
try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7?user=root&password=sql@123");
	
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//fetch the data from html
String id=req.getParameter("studentid");
String name=req.getParameter("studentname");
String stream=req.getParameter("studentstream");
String dob=req.getParameter("studentdob");

//parsing
int sid=Integer.parseInt(id);
	
	PreparedStatement pstmt = null;
	
	String query="update student_info set Student_Name=?, Student_Stream=?, student_dob=? where Student_Id=?";
	
	try {
		pstmt=con.prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setString(2, stream);
		pstmt.setString(3, dob);
		pstmt.setInt(4, sid);
		int count = pstmt.executeUpdate();
				
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>"+count+"RECORD UPDATED SUCCESSFULLY</h1>");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		

}

}
