/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author sio2
 */
public class Vente {
    private int id; 
    private String nom; 
    private LocalDate dateDebutVente;
    
    private Lieu lieu;
    private CategVente CategVente;
    private ArrayList<Courriel> lesCourriels; 
    
    public Vente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebutVente() {
        return dateDebutVente;
    }

    public void setDateDebutVente(LocalDate dateDebutVente) {
        this.dateDebutVente = dateDebutVente;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public CategVente getCategVente() {
        return CategVente;
    }

    public void setCategVente(CategVente CategVente) {
        this.CategVente = CategVente;
    }

}
