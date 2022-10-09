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

public class DeleteVoter extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		try {
			System.out.println("service...");
			//Read voter from html page (when we click delete hyper link)
			String id = req.getParameter("id");
			int vid = Integer.parseInt(id);
			PrintWriter pw=res.getWriter();
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","system");
		    PreparedStatement pstmt = con.prepareStatement("delete from voter_details where id=?");
		    pstmt.setInt(1, vid);
		    int i = pstmt.executeUpdate();
		   if(i>0) {
			   pw.println("<b>Voter Deleted Successfully.......</b><br>");
			   pw.println("<a href='register.html'>Home</a>");
		   }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
