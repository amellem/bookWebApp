<%-- 
    Document   : authorUpdate
    Created on : Feb 22, 2017, 11:36:26 AM
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
        <title>Update Authors</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">Update an Author</h1>

            <form name="authorToUpdate" class="form-horizontal" method="POST" action="AuthorController?action=authorToUpdate">
                <div class="form-group">
                </div>
                <div class="table-responsive">
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
                                    <td>
                                        <input type="radio" id="selectedAuthor" name="selectedAuthor" value="${a.authorId}" checked="checked"><c:out value="${a.authorId}"/>
                                    </td>
                                    <td>
                                        <c:out value="${a.authorName}"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate pattern="M/d/yyyy" value="${a.dateAdded}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>    
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-outline-primary">Update</button>
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
