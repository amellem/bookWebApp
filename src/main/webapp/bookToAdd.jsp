<%-- 
    Document   : bookToAdd
    Created on : Apr 10, 2017, 7:39:33 AM
    Author     : CloudAerius
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${pageContext.request.locale}" scope="session" />

<!DOCTYPE html>
<html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">Add Book</h1>
            <form name="addBook" class="form-horizontal" method="POST" action="<%= response.encodeURL("AuthorController?action=confirmAdd")%>">
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="text" class="form-control col-md-4" id="bookTitle" name="bookTitle" placeholder="Book Title" required="true"
                               oninvalid="this.setCustomValidity('Enter a Book Title, yo!')" oninput="setCustomValidity('')">
                    </div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control col-md-4" id="isbn" name="isbn" placeholder="ISBN" required="true"
                               oninvalid="this.setCustomValidity('Enter an ISBN, yo!')" oninput="setCustomValidity('')">
                    </div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control col-md-4" id="authorName" name="authorName" placeholder="Author Name" required="true"
                               oninvalid="this.setCustomValidity('Enter an author name, yo!')" oninput="setCustomValidity('')">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-outline-primary">Add</button>
                        <a style="margin-left: 2em;" class="btn btn-outline-primary" href="AuthorController?action=authorList" role="button">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script> 
</html>