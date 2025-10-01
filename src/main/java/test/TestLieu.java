/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import model.Lieu;
import model.Vente;

/**
 *
 * @author sio2
 */
public class TestLieu {
    public static void main(String args[]){
        
        Lieu l = new Lieu(); 
        l.setId(5);
        l.setVille("Rouen");
        
        Vente v1 = new Vente(); 
        v1.setId(6);
        v1.setNom("Février");
        l.addVente(v1);
        
        Vente v2 =new Vente();
        v2.setId(9);
        v2.setNom("été");
        l.addVente(v2);
        
        
        
        System.out.println("Le lieu est " + l.getId() + " " + l.getVille() + " et contient " + l.getLesVentes().size() + " ventes.");
        for (Vente v : l.getLesVentes()) {
            System.out.println("Vente numéro : " + v.getId() + " - " + v.getNom());
        }        
    }
    
}
