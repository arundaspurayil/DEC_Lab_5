<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delivery Details</title>
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body style="background-color:#f0f0f0;">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="/MyGolf/purchase">MyGolfStore</a>
</nav>
		<div class="container-fluid">
			<c:if test = "${errors != null}">
				<div class="alert alert-primary" role="alert">
					<p> <c:out value="${errors}"></c:out> </p>
				</div>
			</c:if>
	  	<div class="row justify-content-md-center">
	    <div class="col-md-8">
		<form:form modelAttribute="shipping" method="post" action="submitShipping">
		
	    <table class="table table-bordered table-light" >
	    <thead class="thead-dark">
	    	<tr>
	    		<th colspan="2"> Enter Shipping Details</th>
	    	</tr>
	    </thead>
				<tr>
					<td>Name</td>
					<td><form:input path="name"/></td>
				</tr>
				<tr>	
					<td>Address Line 1</td>
					<td><form:input path="addressLine1"/></td>
				</tr>
				<tr>	
					<td>Address Line 2</td>
					<td><form:input path="addressLine2"/></td>
				</tr>
				<tr>	
					<td>City</td>
					<td><form:input path="city"/></td>
				</tr>
				<tr>	
					<td>State</td>
					<td><form:input path="state"/></td>
				</tr>
				<tr>	
					<td>Zipcode</td>
					<td><form:input path="zip"/></td>
				</tr>
		
	    </table>
	    <input class="btn btn-primary" type="submit" value="Add Address">
	</form:form>
</div>
</div>
</div>
</body>
</html>