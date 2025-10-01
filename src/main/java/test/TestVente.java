/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;


import model.CategVente;
import model.Lieu;
import model.Vente;

/**
 *
 * @author sio2
 */
public class TestVente {
    public static void main (String args[]){
        Vente v = new Vente ();
        v.setId(2);
        v.setNom("Fevrier");
        
        
        Lieu l = new Lieu ();
        l.setId(1);
        l.setVille("Caen");
       
        CategVente cv = new CategVente();
        cv.setCode(9);
        cv.setLibelle("dbdbk ");
        
        v.setLieu(l);
        v.setCategVente(cv);
        
        System.out.println("Vente: " + v.getId() + " " + v.getNom() + " " + v.getLieu().getId() + " " + v.getLieu().getVille()); 
       
        System.out.println("Vente: " + v.getId() + " " + v.getNom() + " " + v.getCategVente().getCode() + " " + v.getCategVente().getLibelle()); 
    }
}
