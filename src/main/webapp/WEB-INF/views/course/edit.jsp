<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Course" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equida - Modifier une course</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <style>
            body { padding-top: 50px; }
            .special { padding-top: 50px; }
            .form-container {
                background-color: #f8f9fa;
                border-radius: 5px;
                padding: 30px;
                margin-top: 20px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }
        </style>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>

        <% Course laCourse = (Course) request.getAttribute("pLaCourse"); %>

        <div class="container special">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="form-container">
                        <h2>Modifier la course : <%= laCourse != null ? laCourse.getNom() : "" %></h2>

                        <% if(request.getAttribute("message") != null) { %>
                            <div class="alert alert-danger"><%= request.getAttribute("message") %></div>
                        <% } %>

                        <% if (laCourse != null) { %>
                        <form class="form-horizontal" action="<%= request.getContextPath() %>/course-servlet/edit" method="POST">
                            <input type="hidden" name="idCourse" value="<%= laCourse.getId() %>">

                            <div class="form-group">
                                <label for="nom" class="col-sm-3 control-label">Nom *</label>
                                <div class="col-sm-9">
                                    <input type="text" name="nom" id="nom" class="form-control" value="<%= laCourse.getNom() %>" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="lieu" class="col-sm-3 control-label">Lieu *</label>
                                <div class="col-sm-9">
                                    <input type="text" name="lieu" id="lieu" class="form-control" value="<%= laCourse.getLieu() != null ? laCourse.getLieu() : "" %>" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="date" class="col-sm-3 control-label">Date *</label>
                                <div class="col-sm-9">
                                    <input type="date" name="date" id="date" class="form-control"
                                           value="<%= laCourse.getDate() != null ? laCourse.getDate().toString() : "" %>" required>
                                </div>
                            </div>

                            <div class="form-group" style="margin-top:24px;">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-success">
                                        <span class="glyphicon glyphicon-floppy-disk"></span> Enregistrer
                                    </button>
                                    <a href="<%= request.getContextPath() %>/course-servlet/show?idCourse=<%= laCourse.getId() %>" class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove"></span> Annuler
                                    </a>
                                </div>
                            </div>
                        </form>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
