package database;

import model.Cheval;
import model.Race;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCheval {
    Connection cnx;
    static PreparedStatement requeteSql = null;
    static ResultSet resultatRequete = null;

    public static ArrayList<Cheval> getLesChevaux(Connection cnx) {
        ArrayList<Cheval> lesChevaux = new ArrayList<Cheval>();
        try {
            requeteSql = cnx.prepareStatement(
                "SELECT c.id as c_id, c.nom as c_nom, " +
                "r.id as r_id, r.libelle as r_libelle " +
                "FROM cheval c " +
                "INNER JOIN race r ON c.race_id = r.id"
            );
            resultatRequete = requeteSql.executeQuery();
            while (resultatRequete.next()) {
                Cheval c = new Cheval();
                c.setId(resultatRequete.getInt("c_id"));
                c.setNom(resultatRequete.getString("c_nom"));
                Race r = new Race();
                r.setId(resultatRequete.getInt("r_id"));
                r.setLibelle(resultatRequete.getString("r_libelle"));
                c.setRace(r);
                lesChevaux.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesChevaux a généré une exception SQL");
        }
        return lesChevaux;
    }

    public static Cheval getLeCheval(Connection cnx, int idCheval) {
    Cheval cheval = null;
    try {
        // La requête utilise des alias (p et m) pour faire une jointure sur la même table (cheval)
        requeteSql = cnx.prepareStatement(
            "SELECT c.*, r.libelle as r_libelle, " +
            "p.nom as nom_pere, m.nom as nom_mere " +
            "FROM cheval c " +
            "INNER JOIN race r ON c.race_id = r.id " +
            "LEFT JOIN cheval p ON c.cheval_pere = p.id " + // Jointure pour le père
            "LEFT JOIN cheval m ON c.cheval_mere = m.id " + // Jointure pour la mère
            "WHERE c.id = ?"
        );
        requeteSql.setInt(1, idCheval);
        resultatRequete = requeteSql.executeQuery();

        if (resultatRequete.next()) {
            cheval = new Cheval();
            cheval.setId(resultatRequete.getInt("id"));
            cheval.setNom(resultatRequete.getString("nom"));
            cheval.setSire(resultatRequete.getString("codeSire"));
            
            // Récupération des caractéristiques physiques
            cheval.setTaille(resultatRequete.getFloat("taille"));
            cheval.setPoids(resultatRequete.getFloat("poids"));
            cheval.setTypeRobe(resultatRequete.getString("typeRobe"));
            
            // Gestion de la race
            Race race = new Race();
            race.setId(resultatRequete.getInt("race_id"));
            race.setLibelle(resultatRequete.getString("r_libelle"));
            cheval.setRace(race);

            // Gestion du Père (si existant)
            String nomPere = resultatRequete.getString("nom_pere");
            if (nomPere != null) {
                Cheval pere = new Cheval();
                pere.setNom(nomPere);
                cheval.setPere(pere);
            }

            // Gestion de la Mère (si existante)
            String nomMere = resultatRequete.getString("nom_mere");
            if (nomMere != null) {
                Cheval mere = new Cheval();
                mere.setNom(nomMere);
                cheval.setMere(mere);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erreur dans DaoCheval.getLeCheval");
    }
    return cheval;
}

    /**
     * CORRECTION : Le champ codeSire est NOT NULL dans la BD.
     * Il faut le générer et l'inclure dans l'INSERT.
     */
    public static boolean ajouterCheval(Connection cnx, Cheval cheval) {
        try {
            // Génération d'un codeSire unique basé sur le timestamp
            String codeSire = "SIRE" + System.currentTimeMillis();

            requeteSql = cnx.prepareStatement(
                "INSERT INTO cheval (nom, date_naissance, race_id, codeSire) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );
            requeteSql.setString(1, cheval.getNom());

            if (cheval.getDateNaissance() != null) {
                requeteSql.setDate(2, java.sql.Date.valueOf(cheval.getDateNaissance()));
            } else {
                requeteSql.setNull(2, java.sql.Types.DATE);
            }

            requeteSql.setInt(3, cheval.getRace().getId());
            requeteSql.setString(4, codeSire);

            int result = requeteSql.executeUpdate();

            if (result == 1) {
                ResultSet rs = requeteSql.getGeneratedKeys();
                if (rs.next()) {
                    cheval.setId(rs.getInt(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du cheval : " + e.getMessage());
            return false;
        }
    }
}