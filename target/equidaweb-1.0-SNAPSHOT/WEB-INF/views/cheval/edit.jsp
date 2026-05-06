<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Race" %>
<%@ page import="model.Cheval" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equida - Modifier un cheval</title>
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
                padding: 30px;
                margin-top: 20px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }
            .section-title {
                font-size: 1rem;
                font-weight: bold;
                color: #555;
                border-bottom: 2px solid #b8860b;
                padding-bottom: 6px;
                margin: 24px 0 16px 0;
            }
        </style>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/common/navbar.jsp" %>

        <%
            Cheval leCheval = (Cheval) request.getAttribute("pLeCheval");
            ArrayList<Race> lesRaces = (ArrayList<Race>) request.getAttribute("pLesRaces");
            ArrayList<Cheval> lesChevaux = (ArrayList<Cheval>) request.getAttribute("pLesChevaux");
        %>

        <div class="container special">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="form-container">
                        <h2>Modifier le cheval : <%= leCheval != null ? leCheval.getNom() : "" %></h2>

                        <% if(request.getAttribute("message") != null) { %>
                            <div class="alert alert-danger"><%= request.getAttribute("message") %></div>
                        <% } %>

                        <% if (leCheval != null) { %>
                        <form class="form-horizontal" action="<%= request.getContextPath() %>/cheval-servlet/edit" method="POST">
                            <input type="hidden" name="idCheval" value="<%= leCheval.getId() %>">

                            <!-- ===== INFORMATIONS PRINCIPALES ===== -->
                            <div class="section-title">Informations principales</div>

                            <div class="form-group">
                                <label for="nom" class="col-sm-3 control-label">Nom *</label>
                                <div class="col-sm-9">
                                    <input type="text" name="nom" id="nom" class="form-control" value="<%= leCheval.getNom() %>" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="codeSire" class="col-sm-3 control-label">Code SIRE</label>
                                <div class="col-sm-9">
                                    <input type="text" name="codeSire" id="codeSire" class="form-control" value="<%= leCheval.getSire() != null ? leCheval.getSire() : "" %>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="sexe" class="col-sm-3 control-label">Sexe</label>
                                <div class="col-sm-9">
                                    <select name="sexe" id="sexe" class="form-control">
                                        <option value="">-- Sélectionnez --</option>
                                        <option value="M" <%= "M".equals(leCheval.getSexe()) ? "selected" : "" %>>Mâle</option>
                                        <option value="F" <%= "F".equals(leCheval.getSexe()) ? "selected" : "" %>>Femelle</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="dateNaissance" class="col-sm-3 control-label">Date de naissance</label>
                                <div class="col-sm-9">
                                    <input type="date" name="dateNaissance" id="dateNaissance" class="form-control"
                                           value="<%= leCheval.getDateNaissance() != null ? leCheval.getDateNaissance().toString() : "" %>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="race" class="col-sm-3 control-label">Race *</label>
                                <div class="col-sm-9">
                                    <select name="race" id="race" class="form-control" required>
                                        <option value="">Sélectionnez une race</option>
                                        <% if (lesRaces != null) {
                                               for(Race race : lesRaces) {
                                                   boolean selected = leCheval.getRace() != null && leCheval.getRace().getId() == race.getId();
                                        %>
                                                   <option value="<%= race.getId() %>" <%= selected ? "selected" : "" %>><%= race.getLibelle() %></option>
                                        <% }} %>
                                    </select>
                                </div>
                            </div>

                            <!-- ===== CARACTERISTIQUES PHYSIQUES ===== -->
                            <div class="section-title">Caractéristiques physiques</div>

                            <div class="form-group">
                                <label for="taille" class="col-sm-3 control-label">Taille (m)</label>
                                <div class="col-sm-9">
                                    <input type="number" step="0.01" min="0" max="3" name="taille" id="taille" class="form-control"
                                           value="<%= leCheval.getTaille() != 0 ? leCheval.getTaille() : "" %>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="poids" class="col-sm-3 control-label">Poids (kg)</label>
                                <div class="col-sm-9">
                                    <input type="number" step="0.1" min="0" name="poids" id="poids" class="form-control"
                                           value="<%= leCheval.getPoids() != 0 ? leCheval.getPoids() : "" %>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="typeRobe" class="col-sm-3 control-label">Robe</label>
                                <div class="col-sm-9">
                                    <select name="typeRobe" id="typeRobe" class="form-control">
                                        <option value="">-- Sélectionnez --</option>
                                        <% String[] robes = {"Alezan","Bai","Gris","Isabelle","Noir","Pie"};
                                           for(String robe : robes) { %>
                                            <option value="<%= robe %>" <%= robe.equals(leCheval.getTypeRobe()) ? "selected" : "" %>><%= robe %></option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>

                            <!-- ===== GENEALOGIE ===== -->
                            <div class="section-title">Généalogie</div>

                            <div class="form-group">
                                <label for="idPere" class="col-sm-3 control-label">Père</label>
                                <div class="col-sm-9">
                                    <select name="idPere" id="idPere" class="form-control">
                                        <option value="">-- Inconnu --</option>
                                        <% if (lesChevaux != null) {
                                               for(Cheval ch : lesChevaux) {
                                                   if (ch.getId() == leCheval.getId()) continue; // ne pas s'auto-sélectionner
                                                   boolean selPere = leCheval.getPere() != null && leCheval.getPere().getId() == ch.getId();
                                        %>
                                                   <option value="<%= ch.getId() %>" <%= selPere ? "selected" : "" %>><%= ch.getNom() %></option>
                                        <% }} %>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="idMere" class="col-sm-3 control-label">Mère</label>
                                <div class="col-sm-9">
                                    <select name="idMere" id="idMere" class="form-control">
                                        <option value="">-- Inconnue --</option>
                                        <% if (lesChevaux != null) {
                                               for(Cheval ch : lesChevaux) {
                                                   if (ch.getId() == leCheval.getId()) continue;
                                                   boolean selMere = leCheval.getMere() != null && leCheval.getMere().getId() == ch.getId();
                                        %>
                                                   <option value="<%= ch.getId() %>" <%= selMere ? "selected" : "" %>><%= ch.getNom() %></option>
                                        <% }} %>
                                    </select>
                                </div>
                            </div>

                            <!-- ===== BOUTONS ===== -->
                            <div class="form-group" style="margin-top:24px;">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-success">
                                        <span class="glyphicon glyphicon-floppy-disk"></span> Enregistrer
                                    </button>
                                    <a href="<%= request.getContextPath() %>/cheval-servlet/show?idCheval=<%= leCheval.getId() %>" class="btn btn-default">
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
