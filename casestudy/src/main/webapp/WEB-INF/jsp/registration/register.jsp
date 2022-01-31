<jsp:include page="../include/header.jsp" />

<form method="GET" action="/registration-url-path/registerSubmit">

    <table cellpadding="5">
        <tr>
            <td>Email</td>
            <td><input type="text" name="email"><td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName"><td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName"><td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="text" name="age"><td>
        </tr>
        <tr>
            <td>Password</td><td>
            <input type="password" name="password"><td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="confirmPassword"><td>
        </tr>
    </table>

	<button type="submit">Submit</button>

</form>


<jsp:include page="../include/footer.jsp" />