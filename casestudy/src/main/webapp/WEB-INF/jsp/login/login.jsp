<jsp:include page="../include/header.jsp" />

<form action="/login/loginSecurityPost" method="POST">
    <h1 style="color:red">${errorMessage}</h1>

	Username : <input type="text" name="username">
	<br>
	Password: <input type="password" name="password">
	
	<button type="submit">Submit</button>

</form>

<jsp:include page="../include/footer.jsp" />