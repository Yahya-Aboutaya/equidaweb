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
public class Course {
    private int id; 
    private String nom; 
    private String lieu; 
    private LocalDate date; 
    
    private ArrayList<ChevalCourse> participations;

    public Course() {
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<ChevalCourse> getParticipations() {
        return participations;
    }

    public void setParticipations(ArrayList<ChevalCourse> participations) {
        this.participations = participations;
    }
    
    
    
}
