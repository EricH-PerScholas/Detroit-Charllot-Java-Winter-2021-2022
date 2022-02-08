<jsp:include page="../include/header.jsp" />

<h3> File Upload</h3>

<form method="POST" enctype="multipart/form-data" action="/user/fileUploadSubmit">
    Title : <input type="text" name="title"/>
    <br>
    Select File : <input type="file" name="file" />
    <br>
    <input type="submit" value="Submit" />
</form>

<jsp:include page="../include/footer.jsp" />