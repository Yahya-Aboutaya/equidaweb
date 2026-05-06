package servlet;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DaoCheval;
import database.DaoRace;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Cheval;
import model.Race;
import java.time.LocalDate;

@WebServlet(name = "chevalServlet", value = "/cheval-servlet/*")
public class ChevalServlet extends HttpServlet {

    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
        try {
            System.out.println("INIT SERVLET=" + cnx.getSchema());
        } catch (SQLException ex) {
            Logger.getLogger(ChevalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getPathInfo();
        System.out.println("PathInfo: " + path);

        if ("/list".equals(path)) {
            ArrayList<Cheval> lesChevaux = DaoCheval.getLesChevaux(cnx);
            request.setAttribute("pLesChevaux", lesChevaux);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/list.jsp").forward(request, response);
        }

        if ("/show".equals(path)) {
            try {
                int idCheval = Integer.parseInt(request.getParameter("idCheval"));
                Cheval leCheval = DaoCheval.getLeCheval(cnx, idCheval);
                if (leCheval != null) {
                    request.setAttribute("pLeCheval", leCheval);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/show.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/cheval-servlet/list");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/cheval-servlet/list");
            }
        }

        if ("/add".equals(path)) {
            ArrayList<Race> lesRaces = DaoRace.getLesRaces(cnx);
            ArrayList<Cheval> lesChevaux = DaoCheval.getLesChevaux(cnx);
            request.setAttribute("pLesRaces", lesRaces);
            request.setAttribute("pLesChevaux", lesChevaux);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/add.jsp").forward(request, response);
        }

        if ("/edit".equals(path)) {
            try {
                int idCheval = Integer.parseInt(request.getParameter("idCheval"));
                Cheval leCheval = DaoCheval.getLeCheval(cnx, idCheval);
                if (leCheval != null) {
                    ArrayList<Race> lesRaces = DaoRace.getLesRaces(cnx);
                    ArrayList<Cheval> lesChevaux = DaoCheval.getLesChevaux(cnx);
                    request.setAttribute("pLeCheval", leCheval);
                    request.setAttribute("pLesRaces", lesRaces);
                    request.setAttribute("pLesChevaux", lesChevaux);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/cheval-servlet/list");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/cheval-servlet/list");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if ("/add".equals(path)) {
            try {
                String nom = request.getParameter("nom");
                String dateNaissanceStr = request.getParameter("dateNaissance");
                String codeSireParam = request.getParameter("codeSire");
                String sexeParam = request.getParameter("sexe");
                String tailleParam = request.getParameter("taille");
                String poidsParam = request.getParameter("poids");
                String typeRobeParam = request.getParameter("typeRobe");
                String idPereParam = request.getParameter("idPere");
                String idMereParam = request.getParameter("idMere");
                int raceId = Integer.parseInt(request.getParameter("race"));

                Cheval nouveauCheval = new Cheval();
                nouveauCheval.setNom(nom);

                if (codeSireParam != null && !codeSireParam.isEmpty()) {
                    nouveauCheval.setSire(codeSireParam);
                }
                if (sexeParam != null && !sexeParam.isEmpty()) {
                    nouveauCheval.setSexe(sexeParam);
                }
                if (dateNaissanceStr != null && !dateNaissanceStr.isEmpty()) {
                    nouveauCheval.setDateNaissance(LocalDate.parse(dateNaissanceStr));
                }
                if (tailleParam != null && !tailleParam.isEmpty()) {
                    nouveauCheval.setTaille(Float.parseFloat(tailleParam));
                }
                if (poidsParam != null && !poidsParam.isEmpty()) {
                    nouveauCheval.setPoids(Float.parseFloat(poidsParam));
                }
                if (typeRobeParam != null && !typeRobeParam.isEmpty()) {
                    nouveauCheval.setTypeRobe(typeRobeParam);
                }

                Race race = DaoRace.getRaceById(cnx, raceId);
                if (race == null) throw new Exception("La race sélectionnée n'existe pas");
                nouveauCheval.setRace(race);

                // Père
                if (idPereParam != null && !idPereParam.isEmpty()) {
                    Cheval pere = new Cheval();
                    pere.setId(Integer.parseInt(idPereParam));
                    nouveauCheval.setPere(pere);
                }
                // Mère
                if (idMereParam != null && !idMereParam.isEmpty()) {
                    Cheval mere = new Cheval();
                    mere.setId(Integer.parseInt(idMereParam));
                    nouveauCheval.setMere(mere);
                }

                if (DaoCheval.ajouterCheval(cnx, nouveauCheval)) {
                    response.sendRedirect(request.getContextPath() + "/cheval-servlet/show?idCheval=" + nouveauCheval.getId());
                } else {
                    throw new Exception("Erreur lors de l'enregistrement du cheval");
                }

            } catch (NumberFormatException e) {
                request.setAttribute("message", "Erreur : données invalides");
                request.setAttribute("pLesRaces", DaoRace.getLesRaces(cnx));
                request.setAttribute("pLesChevaux", DaoCheval.getLesChevaux(cnx));
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/add.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("message", "Erreur : " + e.getMessage());
                request.setAttribute("pLesRaces", DaoRace.getLesRaces(cnx));
                request.setAttribute("pLesChevaux", DaoCheval.getLesChevaux(cnx));
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/add.jsp").forward(request, response);
            }
        }

        if ("/edit".equals(path)) {
            try {
                int idCheval = Integer.parseInt(request.getParameter("idCheval"));
                String nom = request.getParameter("nom");
                String dateNaissanceStr = request.getParameter("dateNaissance");
                String codeSireParam = request.getParameter("codeSire");
                String sexeParam = request.getParameter("sexe");
                String tailleParam = request.getParameter("taille");
                String poidsParam = request.getParameter("poids");
                String typeRobeParam = request.getParameter("typeRobe");
                String idPereParam = request.getParameter("idPere");
                String idMereParam = request.getParameter("idMere");
                int raceId = Integer.parseInt(request.getParameter("race"));

                Cheval cheval = new Cheval();
                cheval.setId(idCheval);
                cheval.setNom(nom);

                if (codeSireParam != null && !codeSireParam.isEmpty()) cheval.setSire(codeSireParam);
                if (sexeParam != null && !sexeParam.isEmpty()) cheval.setSexe(sexeParam);
                if (dateNaissanceStr != null && !dateNaissanceStr.isEmpty()) cheval.setDateNaissance(LocalDate.parse(dateNaissanceStr));
                if (tailleParam != null && !tailleParam.isEmpty()) cheval.setTaille(Float.parseFloat(tailleParam));
                if (poidsParam != null && !poidsParam.isEmpty()) cheval.setPoids(Float.parseFloat(poidsParam));
                if (typeRobeParam != null && !typeRobeParam.isEmpty()) cheval.setTypeRobe(typeRobeParam);

                Race race = DaoRace.getRaceById(cnx, raceId);
                if (race == null) throw new Exception("La race sélectionnée n'existe pas");
                cheval.setRace(race);

                if (idPereParam != null && !idPereParam.isEmpty()) {
                    Cheval pere = new Cheval();
                    pere.setId(Integer.parseInt(idPereParam));
                    cheval.setPere(pere);
                }
                if (idMereParam != null && !idMereParam.isEmpty()) {
                    Cheval mere = new Cheval();
                    mere.setId(Integer.parseInt(idMereParam));
                    cheval.setMere(mere);
                }

                if (DaoCheval.modifierCheval(cnx, cheval)) {
                    response.sendRedirect(request.getContextPath() + "/cheval-servlet/show?idCheval=" + idCheval);
                } else {
                    throw new Exception("Erreur lors de la mise à jour");
                }

            } catch (Exception e) {
                request.setAttribute("message", "Erreur : " + e.getMessage());
                try {
                    int idCheval = Integer.parseInt(request.getParameter("idCheval"));
                    request.setAttribute("pLeCheval", DaoCheval.getLeCheval(cnx, idCheval));
                } catch (Exception ignored) {}
                request.setAttribute("pLesRaces", DaoRace.getLesRaces(cnx));
                request.setAttribute("pLesChevaux", DaoCheval.getLesChevaux(cnx));
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/cheval/edit.jsp").forward(request, response);
            }
        }
    }

    public void destroy() {}
}