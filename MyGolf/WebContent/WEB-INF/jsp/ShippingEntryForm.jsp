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
<jsp:include page="Footer.jsp"/>
