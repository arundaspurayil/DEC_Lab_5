<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
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
	<div class="container-fluid"> <br/>
	
  	<c:if test = "${errors != null}">
  	<div class="alert alert-primary" role="alert">
		<p> <c:out value="${errors}"></c:out> </p>
	</div>
	</c:if>

  <div class="row justify-content-md-center">
    <div class="col-md-8">
      <form:form modelAttribute="order" method="post" action="purchase/submitItems">

	    <table class="table table-bordered table-light" >
	    <thead class="thead-dark">
	    <tr>
	    	<th colspan="3"> Select Items </th>
	    </tr>
	    	<tr>
	    		<th> Item's name</th>
	    		<th> Price </th>
	    		<th> Quantity </th>
	    	</tr>
	    </thead>
			<c:forEach items="${order.items}" var="item" varStatus="loop">
				<tr>
					<td><c:out value="${item.name}"></c:out></td>
					<form:hidden path="items[${loop.index}].name" value="${item.name}"/>
					
					<td><c:out value="$${item.price}"></c:out></td>
					<form:hidden path="items[${loop.index}].price" value="${item.price}"/>
					
					<td><form:input class="form-control" path="items[${loop.index}].quantity" /></td>
				</tr>
			</c:forEach>
		
	    </table>
	    <input class="btn btn-primary" type="submit" value="Purchase">
	</form:form>
    </div>
  </div>
</div>
	
	
</body>
</html>