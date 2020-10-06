<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="Header.jsp"/>


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

<jsp:include page="Footer.jsp"/>

