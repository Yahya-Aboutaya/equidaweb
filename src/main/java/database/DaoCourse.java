package database;

import model.Cheval;
import model.ChevalCourse;
import model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCourse {

    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    /**
     * Récupère toutes les courses de la base de données.
     */
    public static ArrayList<Course> getLesCoures(Connection cnx) {
        ArrayList<Course> lesCourses = new ArrayList<>();
        try {
            requeteSql = cnx.prepareStatement(
                "SELECT id, nom, lieu, date_ FROM course ORDER BY date_ DESC"
            );
            resultatRequete = requeteSql.executeQuery();
            while (resultatRequete.next()) {
                Course c = new Course();
                c.setId(resultatRequete.getInt("id"));
                c.setNom(resultatRequete.getString("nom"));
                c.setLieu(resultatRequete.getString("lieu"));
                String dateStr = resultatRequete.getString("date_");
                if (dateStr != null) {
                    c.setDate(java.time.LocalDate.parse(dateStr));
                }
                lesCourses.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans DaoCourse.getLesCoures");
        }
        return lesCourses;
    }

    /**
     * Récupère une course par son id, avec la liste de ses participants.
     */
    public static Course getLaCourse(Connection cnx, int idCourse) {
        Course course = null;
        try {
            // 1. Infos de la course
            requeteSql = cnx.prepareStatement(
                "SELECT id, nom, lieu, date_ FROM course WHERE id = ?"
            );
            requeteSql.setInt(1, idCourse);
            resultatRequete = requeteSql.executeQuery();
            if (resultatRequete.next()) {
                course = new Course();
                course.setId(resultatRequete.getInt("id"));
                course.setNom(resultatRequete.getString("nom"));
                course.setLieu(resultatRequete.getString("lieu"));
                String dateStr = resultatRequete.getString("date_");
                if (dateStr != null) {
                    course.setDate(java.time.LocalDate.parse(dateStr));
                }
            }

            if (course == null) return null;

            // 2. Participants (cheval + position)
            requeteSql = cnx.prepareStatement(
                "SELECT c.id as c_id, c.nom as c_nom, p.position " +
                "FROM participer p " +
                "INNER JOIN cheval c ON p.idCheval = c.id " +
                "WHERE p.idCourse = ? " +
                "ORDER BY p.position ASC"
            );
            requeteSql.setInt(1, idCourse);
            resultatRequete = requeteSql.executeQuery();

            ArrayList<ChevalCourse> participations = new ArrayList<>();
            while (resultatRequete.next()) {
                ChevalCourse cc = new ChevalCourse();
                Cheval ch = new Cheval();
                ch.setId(resultatRequete.getInt("c_id"));
                ch.setNom(resultatRequete.getString("c_nom"));
                cc.setCheval(ch);
                cc.setCourse(course);
                // position peut être NULL en base → getInt retourne 0 si null
                cc.setPosition(resultatRequete.getInt("position"));
                participations.add(cc);
            }
            course.setParticipations(participations);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans DaoCourse.getLaCourse");
        }
        return course;
    }
    
    public static boolean ajouterCourse(Connection cnx, Course course) {
    try {
        // On précise RETURN_GENERATED_KEYS pour récupérer l'ID après l'insertion
        PreparedStatement requeteSql = cnx.prepareStatement(
            "INSERT INTO course (nom, lieu, date_) VALUES (?, ?, ?)",
            java.sql.Statement.RETURN_GENERATED_KEYS
        );
        
        requeteSql.setString(1, course.getNom());
        requeteSql.setString(2, course.getLieu());
        
        if (course.getDate() != null) {
            requeteSql.setDate(3, java.sql.Date.valueOf(course.getDate()));
        } else {
            requeteSql.setNull(3, java.sql.Types.DATE);
        }

        int result = requeteSql.executeUpdate();
        
        if (result == 1) {
            // Récupération de l'ID auto-incrémenté
            java.sql.ResultSet rs = requeteSql.getGeneratedKeys();
            if (rs.next()) {
                course.setId(rs.getInt(1));
            }
            return true;
        }
        return false;
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    /**
     * Récupère les courses auxquelles un cheval a participé.
     * Utilisé dans la fiche détail du cheval.
     */
    public static ArrayList<ChevalCourse> getLesCoursesDuCheval(Connection cnx, int idCheval) {
        ArrayList<ChevalCourse> lesChevalCourses = new ArrayList<>();
        try {
            requeteSql = cnx.prepareStatement(
                "SELECT co.id as co_id, co.nom as co_nom, co.lieu as co_lieu, " +
                "co.date_ as co_date, p.position " +
                "FROM participer p " +
                "INNER JOIN course co ON p.idCourse = co.id " +
                "WHERE p.idCheval = ? " +
                "ORDER BY co.date_ DESC"
            );
            requeteSql.setInt(1, idCheval);
            resultatRequete = requeteSql.executeQuery();

            while (resultatRequete.next()) {
                Course co = new Course();
                co.setId(resultatRequete.getInt("co_id"));
                co.setNom(resultatRequete.getString("co_nom"));
                co.setLieu(resultatRequete.getString("co_lieu"));
                String dateStr = resultatRequete.getString("co_date");
                if (dateStr != null) {
                    co.setDate(java.time.LocalDate.parse(dateStr));
                }

                ChevalCourse cc = new ChevalCourse();
                cc.setCourse(co);
                cc.setPosition(resultatRequete.getInt("position"));
                lesChevalCourses.add(cc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans DaoCourse.getLesCoursesDuCheval");
        }
        return lesChevalCourses;
    }
}