package servlet;

import database.DaoVente;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lieu;
import model.Lot;
import model.Vente;

@WebServlet(name = "VenteServlet", urlPatterns = {"/vente-servlet/*"})
public class VenteServlet extends HttpServlet {

    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
        try {
            System.out.println("INIT SERVLET=" + cnx.getSchema());
        } catch (SQLException ex) {
            Logger.getLogger(VenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if ("/list".equals(path)) {
            ArrayList<Vente> lesVentes = DaoVente.getLesVentes(cnx);
            request.setAttribute("pLesVentes", lesVentes);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/list.jsp").forward(request, response);
        }

        if ("/show".equals(path)) {
            try {
                int idVente = Integer.parseInt(request.getParameter("idVente"));
                Vente laVente = DaoVente.getLaVente(cnx, idVente);
                if (laVente != null) {
                    ArrayList<Lot> lesLots = DaoVente.getLesLots(cnx, idVente);
                    request.setAttribute("pLaVente", laVente);
                    request.setAttribute("pLesLots", lesLots);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/show.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/vente-servlet/list");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/vente-servlet/list");
            }
        }

        if ("/add".equals(path)) {
            ArrayList<Lieu> lesLieux = DaoVente.getLesLieux(cnx);
            request.setAttribute("pLesLieux", lesLieux);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/add.jsp").forward(request, response);
        }

        if ("/edit".equals(path)) {
            try {
                int idVente = Integer.parseInt(request.getParameter("idVente"));
                Vente laVente = DaoVente.getLaVente(cnx, idVente);
                if (laVente != null) {
                    ArrayList<Lieu> lesLieux = DaoVente.getLesLieux(cnx);
                    request.setAttribute("pLaVente", laVente);
                    request.setAttribute("pLesLieux", lesLieux);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/vente-servlet/list");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/vente-servlet/list");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if ("/add".equals(path)) {
            String nom = request.getParameter("nom");
            String dateDebut = request.getParameter("dateDebutVente");
            int idLieu = Integer.parseInt(request.getParameter("lieu"));

            Vente uneVente = new Vente();
            uneVente.setNom(nom);
            if (dateDebut != null && !dateDebut.isEmpty()) {
                uneVente.setDateDebutVente(LocalDate.parse(dateDebut));
            }
            Lieu leLieu = new Lieu();
            leLieu.setId(idLieu);
            uneVente.setLieu(leLieu);

            if (DaoVente.ajouterVente(cnx, uneVente)) {
                response.sendRedirect(request.getContextPath() + "/vente-servlet/list");
            } else {
                request.setAttribute("message", "Erreur lors de l'enregistrement");
                request.setAttribute("pLesLieux", DaoVente.getLesLieux(cnx));
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/add.jsp").forward(request, response);
            }
        }

        if ("/edit".equals(path)) {
            try {
                int idVente = Integer.parseInt(request.getParameter("idVente"));
                String nom = request.getParameter("nom");
                String dateDebut = request.getParameter("dateDebutVente");
                int idLieu = Integer.parseInt(request.getParameter("lieu"));

                Vente uneVente = new Vente();
                uneVente.setId(idVente);
                uneVente.setNom(nom);
                if (dateDebut != null && !dateDebut.isEmpty()) {
                    uneVente.setDateDebutVente(LocalDate.parse(dateDebut));
                }
                Lieu leLieu = new Lieu();
                leLieu.setId(idLieu);
                uneVente.setLieu(leLieu);

                if (DaoVente.modifierVente(cnx, uneVente)) {
                    response.sendRedirect(request.getContextPath() + "/vente-servlet/show?idVente=" + idVente);
                } else {
                    throw new Exception("Erreur lors de la mise à jour");
                }
            } catch (Exception e) {
                request.setAttribute("message", "Erreur : " + e.getMessage());
                try {
                    int idVente = Integer.parseInt(request.getParameter("idVente"));
                    request.setAttribute("pLaVente", DaoVente.getLaVente(cnx, idVente));
                } catch (Exception ignored) {}
                request.setAttribute("pLesLieux", DaoVente.getLesLieux(cnx));
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/edit.jsp").forward(request, response);
            }
        }
    }
}