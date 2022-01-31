<jsp:include page="../include/header.jsp" />

<form method="GET" action="/registration-url-path/registerSubmit">

    <table cellpadding="5">
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${formBeanKey.email}"><td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" value="${formBeanKey.firstName}"><td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" value="${formBeanKey.lastName}"><td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="text" name="age" value="${formBeanKey.age}"><td>
        </tr>
        <tr>
            <td>Password</td><td>
            <input type="password" name="password" value="${formBeanKey.password}"><td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="confirmPassword" value="${formBeanKey.confirmPassword}"><td>
        </tr>
    </table>

	<button type="submit">Submit</button>

</form>


<jsp:include page="../include/footer.jsp" />