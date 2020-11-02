<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="Header.jsp"/>

	<div class="container-fluid">
		<c:if test = "${errors != null}">
			<div class="alert alert-primary" role="alert">
				<p> <c:out value="${errors}"></c:out> </p>
			</div>
		</c:if>
	 <div class="row justify-content-md-center">
     <div class="col-md-8">
	<form:form method="post" action="confirmOrder">

	    <table class="table table-light table-bordered">
	    <thead class="thead-dark">
	    <tr>
	    	<th colspan="3"> Review Items </th>
	    </tr>
	    </thead>
	    	<tr>
	    		<th> Item's name</th>
	    		<th> Price </th>
	    		<th> Quantity </th>
	    	</tr>
			<c:forEach items="${order.lineItems}" var="item">
				<tr>
					<td>${item.name}</td>
					<td>$${item.price}</td>
					<td>${item.quantity}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2"> </td>
				<td> <b>Total: <c:out value="$${total}"></c:out></b></td>
			</tr> 
	    </table>
	    
	    <table class="table table-light table-bordered">
	    <thead class="thead-dark">
	    <tr>
	    	<th colspan="3"> Review Payment Details </th>
	    </tr>
	    </thead>
	    	<tr>
	    		<td> Name </td>
	    		<td>${payment.cardHolderName}</td>
	    	</tr>
	    	<tr>
	    		<td> Credit Card Number </td>
	    		<td>${payment.creditCardNumber}</td>
	    	</tr>
	    	<tr>
	    		<td> Expiration Date</td>
	    		<td>${payment.expirationDate}</td>
	    	</tr>
	    	<tr>
	    		<td> cvv Code</td>
	    		<td>${payment.cvvCode}</td>
	    	</tr>
	    </table>

	    <table class="table table-light table-bordered">
	     <thead class="thead-dark">
	    <tr>
	    	<th colspan="3"> Review Shipping Details </th>
	    </tr>
	    </thead>
	    	<tr> 
	    		<td> Name </td>
	    		<td> ${shipping.name}</td>
	    	</tr>
	    	<tr> 
	    		<td> Email </td>
	    		<td> ${shipping.emailAddress}</td>
	    	</tr>
	    	<tr>
	    		<td> Address Line 1 </td>
	    		<td> ${shipping.addressLine1}</td>
	    	</tr>
	    	<tr>
	    		<td> Address Line 2 </td>
	    		<td> ${shipping.addressLine2}</td>
	    	</tr>
	    	<tr>
	    		<td> City </td>
	    		<td> ${shipping.city}</td>
	    	</tr>  
	    	<tr>
	    		<td> State </td>
	    		<td> ${shipping.state}</td>
	    	</tr>
	    	<tr>
	    		<td> State </td>
	    		<td> ${shipping.country}</td>
	    	</tr>
	    	<tr>
	    		<td> Zipcode </td>
	    		<td> ${shipping.zip}</td>
	    	</tr>
	    </table>
	    <input class="btn btn-primary" type="submit" value="Confirm Order">
	</form:form>
	    </div>
  </div>
</div>

<jsp:include page="Footer.jsp"/>
