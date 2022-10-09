package com.carrerit.voterapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DisplayServlet extends GenericServlet{
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		try {
			PrintWriter pw=res.getWriter();
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","system");
			PreparedStatement pstmt = con.prepareStatement("select * from voter_details");
			ResultSet rs=pstmt.executeQuery();
			pw.print("<html><body>");
			pw.println("<h3 style='color:blue;text-align:center'>Voter Details</h3>");
			pw.println("<table align='center' bgcolor='#D6EEEE'><tr><td>Name</td><td>Age</td><td>Email</td><td>Gender</td><td>City</td><td>Address</td><td>Action</td></tr>");
			while(rs.next()) {
				    System.out.println(rs.getString("name")+"    "+rs.getInt("age")+"   "+rs.getString("email"));
			        pw.println("<tr><td>"+rs.getString("name")+"</td><td>"+rs.getInt("age")+"</td><td>"+rs.getString("email")+"</td><td>"+rs.getString("gender")+"</td><td>"+rs.getString("city")+"</td><td>"+rs.getString("address")+"</td><td><a href='deleteuser?id="+rs.getInt("id")+"'>Delete</a>&nbsp;&nbsp;&nbsp;<a href='selectvoter?id="+rs.getInt("id")+"'>Update</a></td></tr>");
			}
			pw.print("</body></html>");
			pw.println("<a href='register.html'>Home</a>");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
