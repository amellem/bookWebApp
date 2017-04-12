<%-- 
    Document   : confirmBookUpdate
    Created on : Apr 12, 2017, 7:20:11 AM
    Author     : CloudAerius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Book</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">Update and Confirm</h1>

            <form name="bookToUpdate" class="form-horizontal" method="POST" action="BookController?action=updateBookConfirmed">
                <div class="form-group">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID Number</th>
                                <th>Book Title</th>
                                <th>ISBN</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" name="bookId" value="${bookId}" readonly="readonly">
                                </td>
                                <td>
                                    <input type="text" name="bookTitle" placeholder="${bookTitle}" required="true"
                                           oninvalid="this.setCustomValidity('Enter an Author name, yo!')" oninput="setCustomValidity('')">
                                </td>
                                <td>
                                    <input type="text" name="isbn" placeholder="${isbn}" required="true"
                                           oninvalid="this.setCustomValidity('Enter an Author name, yo!')" oninput="setCustomValidity('')">
                                </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-outline-primary">Confirm Update</button>
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
