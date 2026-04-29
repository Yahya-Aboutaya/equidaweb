<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Course" %>
<%@ page import="model.ChevalCourse" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equida - Détails de la course</title>
        <link rel="stylesheet"
              href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
              crossorigin="anonymous">
        <style>
            body { padding-top: 50px; }
            .special { padding-top: 50px; }
            .form-container {
                background-color: #f8f9fa;
                border-radius: 5px;
                padding: 20px;
                margin-top: 20px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }
            .detail-row { margin-bottom: 15px; }
            .detail-label { font-weight: bold; color: #555; }
            .detail-value { padding-top: 7px; }
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

        <%
            Course laCourse = (Course) request.getAttribute("pLaCourse");
        %>

        <div class="container special">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="form-container">
                        <% if (laCourse != null) { %>
                            <h2>Détails de la course : <%= laCourse.getNom() %></h2>
                            <hr>

                            <div class="row detail-row">
                                <div class="col-sm-3 detail-label">Identifiant</div>
                                <div class="col-sm-9 detail-value"><%= laCourse.getId() %></div>
                            </div>

                            <div class="row detail-row">
                                <div class="col-sm-3 detail-label">Nom</div>
                                <div class="col-sm-9 detail-value"><%= laCourse.getNom() %></div>
                            </div>

                            <div class="row detail-row">
                                <div class="col-sm-3 detail-label">Lieu</div>
                                <div class="col-sm-9 detail-value">
                                    <%= laCourse.getLieu() != null ? laCourse.getLieu() : "Non renseigné" %>
                                </div>
                            </div>

                            <div class="row detail-row">
                                <div class="col-sm-3 detail-label">Date</div>
                                <div class="col-sm-9 detail-value">
                                    <%= laCourse.getDate() != null ? laCourse.getDate() : "Non renseignée" %>
                                </div>
                            </div>

                            <div class="row" style="margin-top: 20px;">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <a href="<%= request.getContextPath() %>/course-servlet/list" class="btn btn-default">
                                        <span class="glyphicon glyphicon-arrow-left"></span> Retour à la liste
                                    </a>
                                </div>
                            </div>
                        <% } else { %>
                            <div class="alert alert-danger">La course demandée n'existe pas.</div>
                            <a href="<%= request.getContextPath() %>/course-servlet/list" class="btn btn-default">
                                <span class="glyphicon glyphicon-arrow-left"></span> Retour à la liste
                            </a>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>

        <!-- Participants -->
        <% if (laCourse != null) { %>
        <div class="container special">
            <div class="header-actions">
                <h2>Chevaux participants</h2>
            </div>

            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th>Cheval</th>
                            <th>Position</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<ChevalCourse> participations = laCourse.getParticipations();
                            if (participations != null && !participations.isEmpty()) {
                                for (ChevalCourse cc : participations) {
                        %>
                            <tr>
                                <td>
                                    <a href="<%= request.getContextPath() %>/cheval-servlet/show?idCheval=<%= cc.getCheval().getId() %>">
                                        <%= cc.getCheval().getNom() %>
                                    </a>
                                </td>
                                <td>
                                    <% if (cc.getPosition() != 0) { %>
                                        <%= cc.getPosition() %>
                                    <% } else { %>
                                        <em>Non classé</em>
                                    <% } %>
                                </td>
                            </tr>
                        <%  }
                            } else { %>
                            <tr>
                                <td colspan="2" class="text-center text-muted">Aucun participant enregistré</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <% } %>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
