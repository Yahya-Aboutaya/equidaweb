<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Course" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equida</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
        <style>
            body  { padding-top: 50px; }
            .special { padding-top: 50px; }
            .header-actions {
                margin-bottom: 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>

        <div class="container special">
            <div class="header-actions">
                <h2 class="h2">Liste des courses</h2>
                <a href="<%= request.getContextPath() %>/course-servlet/add" class="btn btn-primary">
                    <span class="glyphicon glyphicon-plus"></span> Ajouter une course
                </a>
            </div>

            <div class="table-responsive">
                <% ArrayList<Course> lesCourses = (ArrayList<Course>) request.getAttribute("pLesCourses"); %>
                <table class="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Lieu</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (lesCourses != null) {
                               for (Course c : lesCourses) { %>
                            <tr>
                                <td><%= c.getId() %></td>
                                <td>
                                    <a href="<%= request.getContextPath() %>/course-servlet/show?idCourse=<%= c.getId() %>">
                                        <%= c.getNom() %>
                                    </a>
                                </td>
                                <td><%= c.getLieu() != null ? c.getLieu() : "—" %></td>
                                <td><%= c.getDate() != null ? c.getDate() : "—" %></td>
                            </tr>
                        <% }} %>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
