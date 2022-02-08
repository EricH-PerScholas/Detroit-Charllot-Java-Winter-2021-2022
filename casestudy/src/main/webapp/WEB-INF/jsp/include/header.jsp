<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<html>

<head>
<title>PerScholas Case Study</title>

 <script
      src="https://code.jquery.com/jquery-3.6.0.js"
      integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
      crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
        crossorigin="anonymous">

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

</head>
<body>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- implement any html you need to show on every page as a header here -->
<table cellpadding="5">
<tr>
    <td><a href="/">Index</a></td>
    <td>|</td>
    <sec:authorize access="isAuthenticated()">
         <td><a href="/login/logout">Logout</a></td>
          <td>|</td>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
          <td><a href="/login/login">Login</a></td>
          <td>|</td>
    </sec:authorize>

    <td><a href="/registration-url-path/register">User Registration</a></td>
    <td>|</td>
    <td><a href="/registration-url-path/userList">User Search</a></td>

   <sec:authorize access="hasAnyAuthority('ADMIN', 'USER')">
        <td>|</td>
        <td><a href="/user/fileUpload">Upload File</a></td>
   </sec:authorize>

   <sec:authorize access="hasAuthority('ADMIN')">
        <td>|</td>
        <td><a href="/admin/home">Admin Home</a></td>
   </sec:authorize>

   <td>|</td>
       <td><a href="/user/profile">Profile</a></td>
</tr>
</table>

<hr>

<div class="container">