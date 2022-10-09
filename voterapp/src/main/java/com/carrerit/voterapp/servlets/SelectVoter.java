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

public class SelectVoter extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	    try {
	    	PrintWriter pw=res.getWriter();
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","system");
	        //Read voter id from html page
			String id = req.getParameter("id");
			int vid = Integer.parseInt(id);
			PreparedStatement pstmt = con.prepareStatement("select * from voter_details where id=?");
			pstmt.setInt(1, vid);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				pw.println("<html><body><form action='updatevoter' method='post'>");
				pw.println("<input type='hidden' name='id' value="+vid+">");
				pw.println("<table align='center' bgcolor='cyan'>");
				pw.println("<tr><td>Name</td><td><input type='text' name='uname' value="+rs.getString("name")+"></td></tr>");
				pw.println("<tr><td>Email</td><td><input type='text' name='email' value="+rs.getString("email")+"></td></tr>");
				pw.println("<tr><td>Age</td><td><input type='text' name='age' value="+rs.getInt("age")+"></td></tr>");
				pw.println("<tr><td>Address</td><td><input type='text' name='address' value="+rs.getString("address")+"></td></tr>");
				pw.println("<tr><td><input type='submit'  value='Update'></td></tr>");
				pw.println("</table>");
				pw.println("</form></body></html>");
				pw.println("<a href='displayvoter'>Back</a>");
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}

}
