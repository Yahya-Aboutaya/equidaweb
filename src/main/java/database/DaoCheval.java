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
            requeteSql = cnx.prepareStatement(
                "SELECT c.*, r.libelle as r_libelle, " +
                "p.nom as nom_pere, p.id as id_pere, " +
                "m.nom as nom_mere, m.id as id_mere " +
                "FROM cheval c " +
                "INNER JOIN race r ON c.race_id = r.id " +
                "LEFT JOIN cheval p ON c.cheval_pere = p.id " +
                "LEFT JOIN cheval m ON c.cheval_mere = m.id " +
                "WHERE c.id = ?"
            );
            requeteSql.setInt(1, idCheval);
            resultatRequete = requeteSql.executeQuery();

            if (resultatRequete.next()) {
                cheval = new Cheval();
                cheval.setId(resultatRequete.getInt("id"));
                cheval.setNom(resultatRequete.getString("nom"));
                cheval.setSire(resultatRequete.getString("codeSire"));
                cheval.setSexe(resultatRequete.getString("sexe") != null ? resultatRequete.getString("sexe") : "");
                cheval.setTaille(resultatRequete.getFloat("taille"));
                cheval.setPoids(resultatRequete.getFloat("poids"));
                cheval.setTypeRobe(resultatRequete.getString("typeRobe"));

                // Date naissance
                java.sql.Date dateNaiss = resultatRequete.getDate("date_naissance");
                if (dateNaiss != null) cheval.setDateNaissance(dateNaiss.toLocalDate());

                Race race = new Race();
                race.setId(resultatRequete.getInt("race_id"));
                race.setLibelle(resultatRequete.getString("r_libelle"));
                cheval.setRace(race);

                // Père
                String nomPere = resultatRequete.getString("nom_pere");
                if (nomPere != null) {
                    Cheval pere = new Cheval();
                    pere.setId(resultatRequete.getInt("id_pere"));
                    pere.setNom(nomPere);
                    cheval.setPere(pere);
                }

                // Mère
                String nomMere = resultatRequete.getString("nom_mere");
                if (nomMere != null) {
                    Cheval mere = new Cheval();
                    mere.setId(resultatRequete.getInt("id_mere"));
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
     * Ajoute un nouveau cheval avec tous les champs enrichis.
     */
    public static boolean ajouterCheval(Connection cnx, Cheval cheval) {
        try {
            // Génération automatique du SIRE si non fourni
            String codeSire = (cheval.getSire() != null && !cheval.getSire().isEmpty())
                ? cheval.getSire()
                : "SIRE" + System.currentTimeMillis();

            requeteSql = cnx.prepareStatement(
                "INSERT INTO cheval (nom, date_naissance, race_id, codeSire, sexe, taille, poids, typeRobe, cheval_pere, cheval_mere) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
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

            // Sexe
            if (cheval.getSexe() != null && !cheval.getSexe().isEmpty()) {
                requeteSql.setString(5, cheval.getSexe());
            } else {
                requeteSql.setNull(5, java.sql.Types.VARCHAR);
            }

            // Taille
            if (cheval.getTaille() != 0) {
                requeteSql.setFloat(6, cheval.getTaille());
            } else {
                requeteSql.setNull(6, java.sql.Types.DECIMAL);
            }

            // Poids
            if (cheval.getPoids() != 0) {
                requeteSql.setFloat(7, cheval.getPoids());
            } else {
                requeteSql.setNull(7, java.sql.Types.DECIMAL);
            }

            // TypeRobe
            if (cheval.getTypeRobe() != null && !cheval.getTypeRobe().isEmpty()) {
                requeteSql.setString(8, cheval.getTypeRobe());
            } else {
                requeteSql.setNull(8, java.sql.Types.VARCHAR);
            }

            // Père
            if (cheval.getPere() != null && cheval.getPere().getId() != 0) {
                requeteSql.setInt(9, cheval.getPere().getId());
            } else {
                requeteSql.setNull(9, java.sql.Types.INTEGER);
            }

            // Mère
            if (cheval.getMere() != null && cheval.getMere().getId() != 0) {
                requeteSql.setInt(10, cheval.getMere().getId());
            } else {
                requeteSql.setNull(10, java.sql.Types.INTEGER);
            }

            int result = requeteSql.executeUpdate();
            if (result == 1) {
                ResultSet rs = requeteSql.getGeneratedKeys();
                if (rs.next()) cheval.setId(rs.getInt(1));
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du cheval : " + e.getMessage());
            return false;
        }
    }

    /**
     * Modifie un cheval existant.
     */
    public static boolean modifierCheval(Connection cnx, Cheval cheval) {
        try {
            requeteSql = cnx.prepareStatement(
                "UPDATE cheval SET nom=?, date_naissance=?, race_id=?, codeSire=?, sexe=?, " +
                "taille=?, poids=?, typeRobe=?, cheval_pere=?, cheval_mere=? " +
                "WHERE id=?"
            );
            requeteSql.setString(1, cheval.getNom());

            if (cheval.getDateNaissance() != null) {
                requeteSql.setDate(2, java.sql.Date.valueOf(cheval.getDateNaissance()));
            } else {
                requeteSql.setNull(2, java.sql.Types.DATE);
            }

            requeteSql.setInt(3, cheval.getRace().getId());

            String codeSire = (cheval.getSire() != null && !cheval.getSire().isEmpty())
                ? cheval.getSire()
                : "SIRE" + System.currentTimeMillis();
            requeteSql.setString(4, codeSire);

            if (cheval.getSexe() != null && !cheval.getSexe().isEmpty()) {
                requeteSql.setString(5, cheval.getSexe());
            } else {
                requeteSql.setNull(5, java.sql.Types.VARCHAR);
            }

            if (cheval.getTaille() != 0) {
                requeteSql.setFloat(6, cheval.getTaille());
            } else {
                requeteSql.setNull(6, java.sql.Types.DECIMAL);
            }

            if (cheval.getPoids() != 0) {
                requeteSql.setFloat(7, cheval.getPoids());
            } else {
                requeteSql.setNull(7, java.sql.Types.DECIMAL);
            }

            if (cheval.getTypeRobe() != null && !cheval.getTypeRobe().isEmpty()) {
                requeteSql.setString(8, cheval.getTypeRobe());
            } else {
                requeteSql.setNull(8, java.sql.Types.VARCHAR);
            }

            if (cheval.getPere() != null && cheval.getPere().getId() != 0) {
                requeteSql.setInt(9, cheval.getPere().getId());
            } else {
                requeteSql.setNull(9, java.sql.Types.INTEGER);
            }

            if (cheval.getMere() != null && cheval.getMere().getId() != 0) {
                requeteSql.setInt(10, cheval.getMere().getId());
            } else {
                requeteSql.setNull(10, java.sql.Types.INTEGER);
            }

            requeteSql.setInt(11, cheval.getId());

            return requeteSql.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la modification du cheval : " + e.getMessage());
            return false;
        }
    }
}