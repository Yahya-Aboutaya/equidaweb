/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import database.DaoRace;
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
import model.Race;
import model.Vente;

/**
 *
 * @author sio2
 */
@WebServlet(name = "VenteServlet", urlPatterns = {"/vente-servlet/*"})
public class VenteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VenteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VenteServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    Connection cnx;
    
    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection)servletContext.getAttribute("connection");
        try {
            System.out.println("INIT SERVLET=" + cnx.getSchema());
        } catch (SQLException ex) {
            Logger.getLogger(VenteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String path = request.getPathInfo();
        System.out.println("PathInfo: " + path);

        
        if ("/list".equals(path)){
            ArrayList<Vente> lesVentes = DaoVente.getLesVentes(cnx);
            request.setAttribute("pLesVentes", lesVentes); 
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/list.jsp").forward(request, response); 
        }
        if ("/show".equals(path)) {
            
            System.out.println("SHOW VENTE");
            try {
                int idVente = Integer.parseInt(request.getParameter("idVente"));
                Vente laVente = DaoVente.getLaVente(cnx,idVente);
                System.out.println("laV" + laVente.getNom());
                
                if(laVente != null) {
                    ArrayList<Lot> lesLots = DaoVente.getLesLots(cnx, idVente);
                    request.setAttribute("pLaVente", laVente);
                    request.setAttribute("pLesLots", lesLots);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/show.jsp").forward(request, response);
                
                } else {
                    response.sendRedirect(request.getContextPath() + "/vente-servlet/lister");         
                }
            } catch (NumberFormatException e){
                System.out.println("Erreur:l'id de la Vente n'est pas un nombre valide");
               response.sendRedirect(request.getContextPath() + "/vente-servlet/lister");
            }
        }
        if ("/add".equals(path)) {
            // 1. On va chercher les lieux pour remplir la liste déroulante du formulaire
            ArrayList<Lieu> lesLieux = DaoVente.getLesLieux(cnx);

            // 2. On les donne à la JSP
            request.setAttribute("pLesLieux", lesLieux);

            // 3. On affiche enfin la page
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/add.jsp").forward(request, response);   
        }
        
        
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        // On convertit le texte reçu du formulaire en véritable date Java
        LocalDate laDate = LocalDate.parse(dateDebut);
        // On donne cette date à l'objet vente
        uneVente.setDateDebutVente(laDate);
        
        Lieu leLieu = new Lieu();
        leLieu.setId(idLieu);
        uneVente.setLieu(leLieu);

        boolean ok = DaoVente.ajouterVente(cnx, uneVente);

        if (ok) {
            // Si c'est bon, on retourne à la liste
            response.sendRedirect(request.getContextPath() + "/vente-servlet/list");
        } else {
            // Sinon on réaffiche le formulaire avec un message d'erreur
            request.setAttribute("message", "Erreur lors de l'enregistrement");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vente/add.jsp").forward(request, response);
        }
    }
}

}
