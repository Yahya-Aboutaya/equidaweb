/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author sio2
 */
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
                v.setNom(resultatRequete.getString("V_nom"));
                Lieu l = new Lieu();
                l.setId(resultatRequete.getInt("l_id"));
                l.setVille(resultatRequete.getString("l_ville"));
                v.setLieu(l);
                lesVentes.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("La requête de getLesChevaux a généré une exception SQL");
        }
        return lesVentes;
    }
    
    
    public static Vente getLaVente(Connection cnx, int idVente){
        Vente v = null;
        try{
            requeteSql = cnx.prepareStatement(
                "SELECT v.id as v_id, v.nom as v_nom, " +
                "l.id as l_id, l.ville as l_ville " +
                "FROM vente v " +
                "INNER JOIN lieu l ON v.idLieu = l.id " + 
                "WHERE v.id = ?"
            );

            requeteSql.setInt(1, idVente);
            resultatRequete = requeteSql.executeQuery();
            System.out.println("requete "+ requeteSql);
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
            System.out.println("La requête de getLesChevaux a généré une exception SQL");
        }
        return v;
    }
    
    public static ArrayList<Lot> getLesLots(Connection cnx, int idVente) {
        ArrayList<Lot> lesLots = new ArrayList<Lot>();
        try {
            requeteSql = cnx.prepareStatement(
                "SELECT l.id as l_id, l.prixDepart as l_prixDepart , " +
                "c.id as c_id, c.nom as c_nom " +
                "FROM Lot l " +
                "INNER JOIN Cheval c ON l.idCheval = c.id"
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
            System.out.println("La requête de getLesChevaux a généré une exception SQL");
        }
        return lesLots;
    }
}
