<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<h1>This is a product list</h1>

<table border="1">
    <tr>
        <td><b>Id</b></td>
        <td><b>Product Name</b></td>
        <td><b>Image URL</b></td>
        <td><b>Product Image</b></td>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.productName}</td>
            <td><a href="${product.imageUrl}">${product.imageUrl}</a></td>
            <td>
                <c:if test="${not empty product.imageUrl}">
                    <a href="/product/detail?id=${product.id}"><img height="200px" src="${product.imageUrl}"/></a>
                 </c:if>
                 </td>
        </tr>
    </c:forEach>
</table>
<br class="mb-5">

<jsp:include page="../include/footer.jsp" />