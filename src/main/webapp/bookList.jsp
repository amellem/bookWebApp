<%-- 
    Document   : bookList
    Created on : Apr 3, 2017, 12:29:45 PM
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
        <title>Books</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body style="color: lightgray">
        <div class="container">
            <h1 style="text-align: center">List of Books</h1>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID Number</th>
                            <th>Book Title</th>
                            <th>ISBN</th>
                            <th>Author Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="b" items="${books}">
                            <tr>
                                <td>${b.bookId}</td>
                                <td>${b.title}</td>
                                <td>${b.isbn}</td>
                                <td>${b.authorEntity.authorName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>    
            </div>
            <div id="buttonContainer">
                <a style="margin-right: 2em;" class="btn btn-outline-primary" href="index.html" role="button">Home</a>
                <a style="margin-right: 2em;" class="btn btn-outline-primary" href="BookController?action=bookAdd" role="button">Add</a>
                <a style="margin-right: 2em;" class="btn btn-outline-primary" href="BookController?action=bookUpdate" role="button">Update</a>
                <a style="margin-right: 2em;" class="btn btn-outline-primary" href="BookController?action=bookDelete" role="button">Delete</a>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>    
    </body>
</html>
