<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>${param.pageTitle}</title>
		<!-- CSS only -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

		<!-- JS, Popper.js, and jQuery -->
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

		<style><%@include file="/WEB-INF/css/mystyle.css"%></style>
	
	</head>
	
	<body style="background-color:#f0f0f0;">
	
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end">
		    <a class="navbar-brand" href="/MyGolf/">MyGolfStore</a>
		    
		    <div class="navbar-nav ml-auto">
	
		    	<a class="nav-item nav-link active " href="#">Purchase</a>

		    </div>
		    
		    <div class="collapse navbar-collapse flex-grow-0" id="navbarSupportedContent">
		        <ul class="navbar-nav text-right">
		            <li class="nav-item active">
		                <a class="nav-link" href="#">About Us</a>
		            </li>
		            <li class="nav-item active">
		                <a class="nav-link" href="#">Contact Us</a>
		            </li>
		        </ul>
		    </div>
		</nav>


