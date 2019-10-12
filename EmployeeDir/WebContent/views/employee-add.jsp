<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Edit Employee</title>
</head>

<link href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet"/>

<body>
	<div class="container">
		<h1>Employee Directory</h1>
		<hr/>
		<div class= "row">
		
			<div class="col-md-4">
				<form action="${pageContext.request.contextPath}/EmployeeController" method="post">
					<div class="form-group">
						<input type="text" name="firstname" value="${employee.name}" placeholder="Enter Name" class="form-control"/><br/>
					</div>
					<div class="form-group">
						<input type="date" name="dob" value="${employee.dob}" placeholder="Enter Date of Birth" class="form-control"/></br>
					</div>
					<div class="form-group">
						<input type="text" name="department" value="${employee.department}" placeholder="Enter Department" class="form-control"/></br>
					</div>
					
					<input type="hidden" value="${employee.id}" name="id"/>
					<button class="btn btn-primary" type="submit">Save Employee</button>
				</form>
			</div>
			
		</div>
	</div>

</body>
</html>