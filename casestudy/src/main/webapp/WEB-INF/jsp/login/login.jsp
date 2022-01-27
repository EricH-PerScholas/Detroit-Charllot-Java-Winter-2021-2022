<jsp:include page="../include/header.jsp" />

<form action="/loginFormSubmit2">
    <h1 style="color:red">${errorMessage}</h1>

	Username : <input type="text" name="usernameFromForm">
	<br>
	Password: <input type="password" name="passwordFromForm">
	
	<button type="submit">Submit</button>

</form>

<jsp:include page="../include/footer.jsp" />