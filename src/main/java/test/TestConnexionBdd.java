package test;

import java.sql.Connection;
import java.util.ArrayList;

import database.Connexionbdd;
import database.DaoCheval;
import database.DaoVente;

public class TestConnexionBdd {

    public static void main (String args[]) {

        Connection cnx = Connexionbdd.ouvrirConnexion();
        System.out.println ("nombre de chevaux = " + DaoCheval.getLesChevaux(cnx).size());
        System.out.println ("nombre de ventes = " + DaoVente.getLesVentes(cnx).size());

            }

}
