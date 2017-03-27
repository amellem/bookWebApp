<%-- 
    Document   : authorAdd
    Created on : Feb 20, 2017, 12:28:25 PM
    Author     : Aerius
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${pageContext.request.locale}" scope="session" />

<fmt:setBundle basename="edu.wctc.asm.i18n.messages" />

<!DOCTYPE html>
<html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="page.header.title.add"/></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">Add Author</h1>
            <form name="addAuthor" class="form-horizontal" method="POST" action="<%= response.encodeURL("AuthorController?action=confirmAdd")%>">
                <div class="form-group">
                    <div class="col-sm-10">
                        <input type="text" class="form-control col-md-4" id="authorName" name="authorName" placeholder="Author Name" required="true"
                               oninvalid="this.setCustomValidity('Enter an Author name, yo!')" oninput="setCustomValidity('')">
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
