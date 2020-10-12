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
		<form:form modelAttribute="payment" method="post" action="submitPayment">
			
	    <table class="table table-bordered table-light" >
	     <thead class="thead-dark">
	    	<tr>
	    		<th colspan="2"> Enter Payment Details</th>
	    	</tr>
	    </thead>
				<tr>
					<td>Cardholder Name</td>
					<td><form:input path="cardHolderName"/></td>
				</tr>
				<tr>	
					<td>Credit Card Number</td>
					<td><form:input path="creditCardNumber"/></td>
				</tr>
				<tr>	
					<td>Expiration Date</td>
					<td><form:input path="expirationDate"/></td>
				</tr>
				<tr>	
					<td>cvv</td>
					<td><form:input path="cvvCode"/></td>
				</tr>
	    </table>
	    <input class="btn btn-primary" type="submit" value="Purchase">
	</form:form>
	    </div>
  </div>
</div>

<jsp:include page="Footer.jsp"/>