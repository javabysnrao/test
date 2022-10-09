package com.carrerit.voterapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RegisterServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		//read the input form data
		String name=req.getParameter("uname");
		String age = req.getParameter("uage");
		int uage = Integer.parseInt(age);
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String city = req.getParameter("city");
		String address = req.getParameter("address");
		PrintWriter pw = res.getWriter();
		//JDBC Code to Save the data in database
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
			PreparedStatement pstmt = con.prepareStatement("insert into voter_details values(voterseq.nextval,?,?,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setInt(2, uage);
			pstmt.setString(3, email);
			pstmt.setString(4, gender);
			pstmt.setString(5, city);
			pstmt.setString(6, address);
			int i=pstmt.executeUpdate();
			if(i>0) {
				pw.println("<h3 style='color:green;text-align:center'>Registration done successfully</h3>");
				pw.println("<br><a href='register.html'>Register User Again.....</a>");
			}else {
				pw.println("<h3>User Registration Failed....</h3>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
