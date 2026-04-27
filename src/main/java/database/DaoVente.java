package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cheval;
import model.Lieu;
import model.Lot;
import model.Vente;

public class DaoVente {

    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<Vente> getLesVentes(Connection cnx) {
        ArrayList<Vente> lesVentes = new ArrayList<Vente>();
        try {
            requeteSql = cnx.prepareStatement(
                "SELECT v.id as v_id, v.nom as v_nom, " +
                "l.id as l_id, l.ville as l_ville " +
                "FROM vente v " +
                "INNER JOIN lieu l ON v.idLieu = l.id"
            );
            resultatRequete = requeteSql.executeQuery();
            while (resultatRequete.next()) {
                Vente v = new Vente();
                v.setId(resultatRequete.getInt("v_id"));
                v.setNom(resultatRequete.getString("v_nom"));
                Lieu l = new Lieu();
                l.setId(resultatRequete.getInt("l_id"));
                l.setVille(resultatRequete.getString("l_ville"));
                v.setLieu(l);
                lesVentes.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesVentes a généré une exception SQL");
        }
        return lesVentes;
    }

    public static Vente getLaVente(Connection cnx, int idVente) {
        Vente v = null;
        try {
            requeteSql = cnx.prepareStatement(
                "SELECT v.id as v_id, v.nom as v_nom, " +
                "l.id as l_id, l.ville as l_ville " +
                "FROM vente v " +
                "INNER JOIN lieu l ON v.idLieu = l.id " +
                "WHERE v.id = ?"
            );
            requeteSql.setInt(1, idVente);
            resultatRequete = requeteSql.executeQuery();
            while (resultatRequete.next()) {
                v = new Vente();
                v.setId(resultatRequete.getInt("v_id"));
                v.setNom(resultatRequete.getString("v_nom"));
                Lieu l = new Lieu();
                l.setId(resultatRequete.getInt("l_id"));
                l.setVille(resultatRequete.getString("l_ville"));
                v.setLieu(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLaVente a généré une exception SQL");
        }
        return v;
    }

    /**
     * CORRECTION : ajout du WHERE idVente = ? qui manquait,
     * sinon setInt(1, idVente) plantait avec une SQLException.
     */
    public static ArrayList<Lot> getLesLots(Connection cnx, int idVente) {
        ArrayList<Lot> lesLots = new ArrayList<Lot>();
        try {
            requeteSql = cnx.prepareStatement(
                "SELECT l.id as l_id, l.prixDepart as l_prixDepart, " +
                "c.id as c_id, c.nom as c_nom " +
                "FROM lot l " +
                "INNER JOIN cheval c ON l.idCheval = c.id " +
                "WHERE l.idVente = ?"          // ← la ligne qui manquait
            );
            requeteSql.setInt(1, idVente);
            resultatRequete = requeteSql.executeQuery();
            while (resultatRequete.next()) {
                Lot l = new Lot();
                l.setId(resultatRequete.getInt("l_id"));
                l.setPrixDepart(resultatRequete.getString("l_prixDepart"));
                Cheval c = new Cheval();
                c.setId(resultatRequete.getInt("c_id"));
                c.setNom(resultatRequete.getString("c_nom"));
                l.setCheval(c);
                lesLots.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesLots a généré une exception SQL");
        }
        return lesLots;
    }

    /**
     * Ajoute une nouvelle vente dans la base de données.
     * @return true si l'ajout a réussi, false sinon
     */
    public static boolean ajouterVente(Connection cnx, Vente vente) {
    try {
        // On ne définit que 3 colonnes : nom, idLieu, dateDebutVente
        requeteSql = cnx.prepareStatement(
            "INSERT INTO vente (nom, idLieu, dateDebutVente) VALUES (?, ?, ?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        
        // Paramètre 1 : Nom
        requeteSql.setString(1, vente.getNom());
        
        // Paramètre 2 : ID du Lieu
        requeteSql.setInt(2, vente.getLieu().getId());

        // Paramètre 3 : Date de début
        if (vente.getDateDebutVente() != null) {
            requeteSql.setDate(3, java.sql.Date.valueOf(vente.getDateDebutVente()));
        } else {
            requeteSql.setNull(3, java.sql.Types.DATE);
        }

        // EXECUTION
        int result = requeteSql.executeUpdate();
        
        if (result == 1) {
            // Récupération de l'ID auto-généré
            ResultSet rs = requeteSql.getGeneratedKeys();
            if (rs.next()) {
                vente.setId(rs.getInt(1));
            }
            return true;
        }
        return false;

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
        return false;
    }
}

    /**
     * Récupère tous les lieux disponibles.
     */
    public static ArrayList<Lieu> getLesLieux(Connection cnx) {
        ArrayList<Lieu> lesLieux = new ArrayList<>();
        try {
            requeteSql = cnx.prepareStatement("SELECT id, ville FROM lieu ORDER BY ville");
            resultatRequete = requeteSql.executeQuery();
            while (resultatRequete.next()) {
                Lieu l = new Lieu();
                l.setId(resultatRequete.getInt("id"));
                l.setVille(resultatRequete.getString("ville"));
                lesLieux.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesLieux a généré une exception SQL");
        }
        return lesLieux;
    }
}