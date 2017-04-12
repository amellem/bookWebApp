<%-- 
    Document   : bookUpdate
    Created on : Apr 8, 2017, 11:36:26 AM
    Author     : Aerius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Books</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">Update a Book</h1>

            <form name="bookrToUpdate" class="form-horizontal" method="POST" action="BookController?action=bookToUpdate">
                <div class="form-group">
                </div>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID Number</th>
                                <th>Book Title</th>
                                <th>ISBN</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="b" items="${books}">
                                <tr>
                                    <td>
                                        <input type="radio" id="selectedBook" name="selectedBook" value="${b.bookId}" checked="checked"><c:out value="${b.bookId}"/>
                                    </td>
                                    <td>
                                        <c:out value="${b.title}"/>
                                    </td>
                                    <td>
                                        <c:out value="${b.isbn}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>    
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-outline-primary">Update</button>
                        <a style="margin-left: 2em;" class="btn btn-outline-primary" href="BookController?action=bookIndex" role="button">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</html>
