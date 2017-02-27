<%-- 
    Document   : authorToUpdate
    Created on : Feb 27, 2017, 6:11:45 AM
    Author     : CloudAerius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Authors</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">Update and Confirm</h1>

            <form name="authorToUpdate" class="form-horizontal" method="POST" action="AuthorController?action=updateConfirmed">
                <div class="form-group">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID Number</th>
                                <th>Author's Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" name="authorId" value="${authorId}" readonly="readonly">
                                </td>
                                <td>
                                    <input type="text" name="authorName" placeholder="${authorName}">
                                </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-outline-primary">Confirm Update</button>
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
