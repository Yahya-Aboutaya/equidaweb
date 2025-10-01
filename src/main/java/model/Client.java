/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author sio2
 */
public class Client {
    private int id; 
    private String titre; 
    private String nom;
    private String prenom; 
    private String rue;
    private int copos;
    private String ville; 
    private String adresseMessagerie;
    
    private Pays pays; 
    private Lot Lot ;
    private ArrayList<Enchere> lesEncheres ;
    
    
    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCopos() {
        return copos;
    }

    public void setCopos(int copos) {
        this.copos = copos;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresseMessagerie() {
        return adresseMessagerie;
    }

    public void setAdresseMessagerie(String adresseMessagerie) {
        this.adresseMessagerie = adresseMessagerie;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
    
    
    
}
