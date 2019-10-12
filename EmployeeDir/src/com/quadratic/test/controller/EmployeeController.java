//Testing The View and Controller logic
package com.quadratic.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quadratic.test.dao.EmployeeDAO;
import com.quadratic.test.dao.EmployeeDAOImpl;
import com.quadratic.test.entity.Employee;


public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dispatcher = null;
       
	//create a ref varaible for employee DAO
	EmployeeDAO employeeDAO = null;
			
	//create a constructor and initialize employee DAO
	public EmployeeController() {
		employeeDAO = new EmployeeDAOImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "LIST";
		}
		
		switch(action) {
			case "LIST":
				listEmployees(request, response);
				break;
				
			case "EDIT":
				getSingleEmployee(request, response);
				break;
				
			case "DELETE":
				deleteEmployee(request, response);
				break;
				
			default:
				listEmployees(request, response);
				break;
				
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("firstname");
		String dob = request.getParameter("dob");
		String department = request.getParameter("department");
		
		Employee e = new Employee();
		
		e.setName(name);
		e.setDob(dob);
		e.setDepartment(department);
		
		if(id.isEmpty() || id == null) {
			//save operation
			if(employeeDAO.save(e)) {
				request.setAttribute("message",	"save successfully!!");
			}
		}else {
			//update operation
			e.setId(Integer.parseInt(id));
			
			if(employeeDAO.update(e)) {
				request.setAttribute("message",	"update successfully!!");
			}
		}
		
		listEmployees(request, response);
	}
	
	public void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//call dao method to get list of employees
		List<Employee> list = employeeDAO.get();
				
		//add the employees to request object
		request.setAttribute("list", list);
				
		//get the request dispatcher
		dispatcher = request.getRequestDispatcher("/views/employee-list.jsp");
				
		//forward the req and res objects
		dispatcher.forward(request, response);
	}
	
	public void getSingleEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		//call the get(int) in DAOImpl and store result in employee obj.
		Employee employee = employeeDAO.get(Integer.parseInt(id));
		
		//display result
		request.setAttribute("employee", employee);
		dispatcher = request.getRequestDispatcher("/views/employee-add.jsp");
		dispatcher.forward(request, response);
	}
	
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if(employeeDAO.delete(Integer.parseInt(id))) {
			request.setAttribute("message", "Record deleted...");
		}
		
		listEmployees(request, response);
		
	}

}








