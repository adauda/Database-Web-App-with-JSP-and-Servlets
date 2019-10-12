
//Testing Database Connection
package com.quadratic.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectionServlet
 */
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//define the fields
		String username = "quadri";
		String password = "quadri";
		String jdbcURL = "jdbc:mysql://localhost:3306/employeeD";
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			//get the printwriter object
			PrintWriter writer = response.getWriter();
			writer.println("Connecting to database " +jdbcURL);
			
			//load driver
			Class.forName(driver);
			
			//get connection
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			writer.println("Connection Successfully..");
			
			//close connection
			connection.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
