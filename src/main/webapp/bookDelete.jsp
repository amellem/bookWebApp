<%-- 
    Document   : bookDelete
    Created on : Apr 12, 2017, 7:36:19 AM
    Author     : CloudAerius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Book</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">Delete a Book</h1>
            <form name="bookToDelete" class="form-horizontal" method="POST" action="BookController?action=bookToDelete">
                <div class="form-group">
                    <select class="form-control" name="bookId">
                        <c:forEach var="b" items="${books}">
                            <option id="${b.bookId}" value="${b.bookId}">
                                <div name="bookTitle">${b.title}</div>
                            </option>
                        </c:forEach>
                    </select>    
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-outline-primary">Delete</button>
                        <a style="margin-left: 2em;" class="btn btn-outline-primary" href="AuthorController?action=authorList" role="button">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</body>
</html>
