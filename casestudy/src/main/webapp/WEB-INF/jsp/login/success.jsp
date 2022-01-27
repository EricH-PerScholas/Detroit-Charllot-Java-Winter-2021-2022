<jsp:include page="../include/header.jsp" />

User <b>${usernameSessionKey }</b> is logged in ( from session )
<br>
Logged in user = <b>${loggedInUser }</b> ( from response model )
<br>
<a href="/logout">Logout</a>

<jsp:include page="../include/footer.jsp" />