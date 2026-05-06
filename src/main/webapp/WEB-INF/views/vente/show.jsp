<%@page import="java.util.ArrayList"%>
<%@page import="model.Lot"%>
<%@page import="model.Vente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equida - Détails de la vente</title>
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
            .header-actions { margin-bottom: 20px; display: flex; justify-content: space-between; align-items: center; }
        </style>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>

        <div class="container special">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="form-container">
                        <% 
                            Vente laVente = (Vente)request.getAttribute("pLaVente");
                            if(laVente != null) {
                        %>
                            <h2>Détails de la vente : <%= laVente.getNom() %></h2>

                            <div class="row detail-row">
                                <div class="col-sm-3 detail-label">Identifiant</div>
                                <div class="col-sm-9 detail-value"><%= laVente.getId() %></div>
                            </div>
                            <div class="row detail-row">
                                <div class="col-sm-3 detail-label">Nom</div>
                                <div class="col-sm-9 detail-value"><%= laVente.getNom() %></div>
                            </div>
                            <div class="row detail-row">
                                <div class="col-sm-3 detail-label">Date de début</div>
                                <div class="col-sm-9 detail-value">
                                    <%= laVente.getDateDebutVente() != null ? laVente.getDateDebutVente() : "Non renseignée" %>
                                </div>
                            </div>
                            <div class="row detail-row">
                                <div class="col-sm-3 detail-label">Lieu</div>
                                <div class="col-sm-9 detail-value">
                                    <%= laVente.getLieu() != null ? laVente.getLieu().getVille() : "Non renseigné" %>
                                </div>
                            </div>

                            <div class="row" style="margin-top: 30px;">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <a href="<%= request.getContextPath() %>/vente-servlet/edit?idVente=<%= laVente.getId() %>" class="btn btn-warning">
                                        <span class="glyphicon glyphicon-pencil"></span> Modifier
                                    </a>
                                    <a href="<%= request.getContextPath() %>/vente-servlet/list" class="btn btn-default">
                                        <span class="glyphicon glyphicon-arrow-left"></span> Retour à la liste
                                    </a>
                                </div>
                            </div>
                        <% } else { %>
                            <div class="alert alert-danger">La vente demandée n'existe pas.</div>
                            <a href="<%= request.getContextPath() %>/vente-servlet/list" class="btn btn-default">Retour</a>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>

        <!-- Lots -->
        <div class="container special">
            <div class="header-actions">
                <h2 class="h2">Liste des lots de cette vente :</h2>
            </div>
            <div class="table-responsive">
                <% ArrayList<Lot> lesLots = (ArrayList)request.getAttribute("pLesLots"); %>
                <table class="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>Cheval</th>
                            <th>Prix de départ</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (lesLots != null) { for (Lot l : lesLots) { %>
                            <tr>
                                <td><%= l.getId() %></td>
                                <td><a href="<%= request.getContextPath() %>/cheval-servlet/show?idCheval=<%= l.getCheval().getId() %>"><%= l.getCheval().getNom() %></a></td>
                                <td><%= l.getPrixDepart() %></td>
                            </tr>
                        <% }} %>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
