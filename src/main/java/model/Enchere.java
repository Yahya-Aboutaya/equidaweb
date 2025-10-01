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
public class Enchere {
    private int numero; 
    private String montant; 
    
    private ArrayList<Client> lesClient ;
    private Lot Lot ;

    public Enchere() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public Lot getLot() {
        return Lot;
    }

    public void setLot(Lot Lot) {
        this.Lot = Lot;
    }

    public ArrayList<Client> getLesClient() {
        return lesClient;
    }

    public void setLesClient(ArrayList<Client> lesClient) {
        this.lesClient = lesClient;
    }
    
    
    
    
    
}
