package servlet;

import database.DaoCourse;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate; // Import manquant ajouté
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CourseServlet", urlPatterns = {"/course-servlet/*"})
public class CourseServlet extends HttpServlet {

    Connection cnx;

    @Override
    public void init() {
        ServletContext servletContext = getServletContext();
        cnx = (Connection) servletContext.getAttribute("connection");
        try {
            System.out.println("INIT CourseServlet=" + cnx.getSchema());
        } catch (SQLException ex) {
            Logger.getLogger(CourseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();
        System.out.println("CourseServlet PathInfo: " + path);

        if ("/list".equals(path)) {
            ArrayList<Course> lesCourses = DaoCourse.getLesCoures(cnx);
            request.setAttribute("pLesCourses", lesCourses);
            this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/course/list.jsp")
                .forward(request, response);
        }

        if ("/show".equals(path)) {
            try {
                int idCourse = Integer.parseInt(request.getParameter("idCourse"));
                Course laCourse = DaoCourse.getLaCourse(cnx, idCourse);

                if (laCourse != null) {
                    request.setAttribute("pLaCourse", laCourse);
                    this.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/course/show.jsp")
                        .forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/course-servlet/list");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : id de course invalide");
                response.sendRedirect(request.getContextPath() + "/course-servlet/list");
            }
        }
        
        // CORRECTION : Ajout de la gestion du formulaire d'ajout en GET
        if ("/add".equals(path)) {
            this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/course/add.jsp")
                .forward(request, response);
        }
    } // Accolade fermante de doGet qui manquait

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String path = request.getPathInfo();

        if ("/add".equals(path)) {
            try {
                // Récupération des données du formulaire
                String nom = request.getParameter("nom");
                String lieu = request.getParameter("lieu");
                String dateStr = request.getParameter("date");

                // Création d'une nouvelle course
                Course nouvelleCourse = new Course();
                nouvelleCourse.setNom(nom);
                nouvelleCourse.setLieu(lieu);

                // Gestion de la date
                if (dateStr != null && !dateStr.isEmpty()) {
                    LocalDate laDate = LocalDate.parse(dateStr);
                    nouvelleCourse.setDate(laDate);
                }

                // Tentative d'ajout en base de données via le DaoCourse
                if (DaoCourse.ajouterCourse(cnx, nouvelleCourse)) {
                    // Redirection vers la liste des courses après succès
                    response.sendRedirect(request.getContextPath() + "/course-servlet/list");
                } else {
                    throw new Exception("Erreur lors de l'enregistrement de la course");
                }

            } catch (Exception e) {
                request.setAttribute("message", "Erreur : " + e.getMessage());
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/course/add.jsp").forward(request, response);
            }
        }
    }
}