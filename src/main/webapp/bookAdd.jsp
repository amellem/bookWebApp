<%-- 
    Document   : bookAdd
    Created on : Apr 10, 2017, 7:23:26 AM
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
        <title>Add a Book</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">List of Authors</h1>
            <div class="table-responsive">
                
                <form method="POST" action="BookController?action=bookToAdd">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID Number</th>
                                <th>Author's Name</th>
                                <th>Date Added</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="a" items="${authors}">
                                <tr>
                                    <td><input type="radio" name="authorId" value="${a.authorId}"> ${a.authorId}</td>
                                    <td>${a.authorName}</td>
                                    <td>
                                        <fmt:formatDate pattern="M/d/yyyy" value="${a.dateAdded}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                    <button class="btn btn-outline-primary" role="submit">Add Book</button>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script> 
    </body>
</html>
