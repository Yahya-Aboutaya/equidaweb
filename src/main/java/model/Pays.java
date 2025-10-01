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
class Pays {
    private int code; 
    private String nom;
    
    private ArrayList<Client> lesClients ; 

    public Pays() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Client> getLesClients() {
        return lesClients;
    }

    public void setLesClients(ArrayList<Client> lesClients) {
        this.lesClients = lesClients;
    }
    
    
}
