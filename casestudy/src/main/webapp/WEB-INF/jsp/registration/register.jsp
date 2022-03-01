<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<c:choose>
  <c:when test="${empty formBeanKey.id}">
    <h1>Create New User</h1>
  </c:when>
  <c:otherwise>
    <h1>Edit User</h1>
  </c:otherwise>
</c:choose>

<form method="POST" action="/registration-url-path/registerSubmit">
    <input type="hidden" name="id" value="${formBeanKey.id}">

    <table cellpadding="5">
        <tr>
            <td>Username</td>
            <td><input type="text" name="username" value="${formBeanKey.username}"><td>
        </tr>
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


	<button type="submit" class="btn btn-primary" role="button">Submit</button>

    <c:if test="${not empty formBeanKey.id}">
        <a class="btn btn-danger" role="button" href="/registration-url-path/deleteUser?id=${formBeanKey.id}">Delete</a>
    </c:if>


</form>

<div>
    <c:forEach items="${errorMessages}" var="message">
        <span style="color:red">${message}</span><br>
    </c:forEach>
</div>


<jsp:include page="../include/footer.jsp" />