package com.quadratic.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quadratic.test.entity.Employee;
import com.quadratic.test.util.DBConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;						 //for fetching from db
	PreparedStatement preparedStatement = null;		//for saving to db

	@Override
	public List<Employee> get() {
		
		//create refrence variables
		List<Employee> list = null;
		Employee employee = null;
		
		try {
			list = new ArrayList<Employee>();
			
			//create sql query
			String sql = "select * from empTable";
			
			//get the database connection
			connection = DBConnectionUtil.openConnection();
			
			//create statement
			statement = connection.createStatement();
			
			//execute the statement
			resultSet = statement.executeQuery(sql);
			
			//process the resultset
			while(resultSet.next()) {
				
				employee = new Employee();
				
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setDob(resultSet.getString("dob"));
				
				//add employee object to the list
				list.add(employee);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean save(Employee e) {
		boolean flag = false;
		
		try {
			String sql = "insert into empTable(name, dob, department) values('"+e.getName()+"', '"+e.getDob()+"', '"+e.getDepartment()+"')";
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public Employee get(int id) {
		Employee employee = null;
		
		try {
			employee = new Employee();
			
			String sql = "select * from empTable where id =" +id;
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setDepartment(resultSet.getString("department"));
				employee.setDob(resultSet.getString("dob"));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	
		return employee;
	}

	@Override
	public boolean update(Employee e) {
		boolean flag = false;
		
		try {
			String sql = "update empTable set name=' "+e.getName()+" ', dob=' "+e.getDob()+" ', department=' "+e.getDepartment()+" ' where id="+e.getId();
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			flag = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(int id) {
		boolean flag = false;
		
		try {
			String sql = "delete from empTable where id=" +id;
			connection = DBConnectionUtil.openConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			flag = true;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

}
