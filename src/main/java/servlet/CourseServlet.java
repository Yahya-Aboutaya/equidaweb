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
import java.time.LocalDate;
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        // --- LISTE ---
        if ("/list".equals(path)) {
            ArrayList<Course> lesCourses = DaoCourse.getLesCoures(cnx);
            request.setAttribute("pLesCourses", lesCourses);
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/course/list.jsp").forward(request, response);
        }

        // --- DÉTAILS ---
        else if ("/show".equals(path)) {
            try {
                int idCourse = Integer.parseInt(request.getParameter("idCourse"));
                Course laCourse = DaoCourse.getLaCourse(cnx, idCourse);
                if (laCourse != null) {
                    request.setAttribute("pLaCourse", laCourse);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/course/show.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/course-servlet/list");
                }
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/course-servlet/list");
            }
        }

        // --- FORMULAIRE AJOUT ---
        else if ("/add".equals(path)) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/course/add.jsp").forward(request, response);
        }

        // --- FORMULAIRE MODIFICATION (C'est ici que ça bloquait !) ---
        else if ("/edit".equals(path)) {
            try {
                int idCourse = Integer.parseInt(request.getParameter("idCourse"));
                Course laCourse = DaoCourse.getLaCourse(cnx, idCourse);
                
                if (laCourse != null) {
                    request.setAttribute("pLaCourse", laCourse);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/course/edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/course-servlet/list");
                }
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/course-servlet/list");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getPathInfo();

        // --- ACTION AJOUTER ---
        if ("/add".equals(path)) {
            try {
                String nom = request.getParameter("nom");
                String lieu = request.getParameter("lieu");
                String dateStr = request.getParameter("date");

                Course nouvelleCourse = new Course();
                nouvelleCourse.setNom(nom);
                nouvelleCourse.setLieu(lieu);
                if (dateStr != null && !dateStr.isEmpty()) {
                    nouvelleCourse.setDate(LocalDate.parse(dateStr));
                }

                if (DaoCourse.ajouterCourse(cnx, nouvelleCourse)) {
                    response.sendRedirect(request.getContextPath() + "/course-servlet/list");
                }
            } catch (Exception e) {
                request.setAttribute("message", "Erreur : " + e.getMessage());
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/course/add.jsp").forward(request, response);
            }
        }

        // --- ACTION MODIFIER ---
        else if ("/edit".equals(path)) {
            try {
                int idCourse = Integer.parseInt(request.getParameter("idCourse"));
                String nom = request.getParameter("nom");
                String lieu = request.getParameter("lieu");
                String dateStr = request.getParameter("date");

                Course courseModifiee = new Course();
                courseModifiee.setId(idCourse);
                courseModifiee.setNom(nom);
                courseModifiee.setLieu(lieu);
                if (dateStr != null && !dateStr.isEmpty()) {
                    courseModifiee.setDate(LocalDate.parse(dateStr));
                }

                if (DaoCourse.modifierCourse(cnx, courseModifiee)) {
                    response.sendRedirect(request.getContextPath() + "/course-servlet/list");
                } else {
                    throw new Exception("Erreur BDD");
                }
            } catch (Exception e) {
                request.setAttribute("message", "Erreur : " + e.getMessage());
                // En cas d'erreur on réaffiche le formulaire via la JSP
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/course/edit.jsp").forward(request, response);
            }
        }
    }
}