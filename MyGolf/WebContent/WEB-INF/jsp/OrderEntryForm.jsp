<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="Header.jsp"/>


<div class="container-fluid"> <br/>
  	
	  	<div id="order-entry-errors" class="alert alert-primary" role="alert">
	  		<c:if test = "${errors != null}">
				<p> <c:out value="${errors}"></c:out> </p>
			</c:if>
		</div>
	

  <div class="row justify-content-md-center">
    <div class="col-md-8">
      <form:form id="order-entry-form" modelAttribute="order" method="post" action="purchase/submitItems">

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
						<form:hidden  path="items[${loop.index}].name" value="${item.name}"/>
						
						<td><c:out value="$${item.price}"></c:out></td>
						<form:hidden path="items[${loop.index}].price" value="${item.price}"/>
						
						<td><form:input id="${item.name}" class="form-control" path="items[${loop.index}].quantity" /></td>
					</tr>
				</c:forEach>
	    </table>
	    <input id="order-submit-button" class="btn btn-primary"  value="Purchase">
	</form:form>
    </div>
  </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script><%@include file="/WEB-INF/javascripts/quantityValidation.js"%></script>

<jsp:include page="Footer.jsp"/>

