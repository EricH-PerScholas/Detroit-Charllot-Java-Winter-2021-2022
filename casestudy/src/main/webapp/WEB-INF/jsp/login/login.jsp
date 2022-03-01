<jsp:include page="../include/header.jsp" />

<!-- form submission must align with security config see .loginProcessingUrl in Security Config -->
<form action="/login/loginSecurityPost" method="POST">
    <h1 style="color:red">${errorMessage}</h1>

    <!-- username and password input text fields must have the names username and password
         for spring boot to be able to authenticate the user --!>
	Email : <input type="text" name="username">
	<br>
	Password: <input type="password" name="password">
	
	<button type="submit">Submit</button>

</form>

<jsp:include page="../include/footer.jsp" />