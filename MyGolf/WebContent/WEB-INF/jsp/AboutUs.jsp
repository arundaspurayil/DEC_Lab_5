<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="Header.jsp"/>

<div class="jumbotron">
  <h1 class="display-5">About MyGolfStore</h1>
  
  <hr class="my-4">
  <div class="product-card">
  	<img class="h-100 w-100" src="images/Arun.JPG" alt="..." class="img-thumbnail">
  </div>
  <blockquote class="blockquote text-center">
  <p class="mb-0">I am an undergraduate senior studying computer science from Powell, Ohio. My interests in programming is in web development, focusing on backend development. I will be graduating this December and after graduation I will be starting my career as a Software Engineer at Root Insurance.</p>
  <footer class="blockquote-footer">Arun Purayil, <cite title="Source Title">CEO, TheGolfStore</cite></footer>
  </blockquote>
  
  <div class="product-card">
  	<img class="h-100 w-100" src="images/Siddhartha.jpg" alt="..." class="img-thumbnail">
  </div>
  <blockquote class="blockquote text-center">
  <p class="mb-0">I am a graduate student studying computer science from India. My interests lie in Software development and Golf and hence TheGolfStore is the perfect fit.</p>
  <footer class="blockquote-footer">Siddhartha Srivastava, <cite title="Source Title">CTO, TheGolfStore</cite></footer>
  </blockquote>
</div>

<jsp:include page="Footer.jsp"/>