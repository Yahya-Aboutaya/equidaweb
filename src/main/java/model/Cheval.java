package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cheval {

    private int id;
    private String nom;
    private String sexe;
    private String sire;
    private LocalDate dateNaissance;

    private Race race;
    
    private ArrayList<Lot> lesLots; 
    private ArrayList<Cheval> parents; 
    private ArrayList<ChevalCourse> lesChevalCourses;
    
    public Cheval() {
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getSire() {
        return sire;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }
    

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
   
 
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public Race getRace() {
        return race;
    }
    public void setRace(Race race) {
        this.race = race;
    }

    public ArrayList<Lot> getLesLots() {
        return lesLots;
    }

    public void setLesLots(ArrayList<Lot> lesLots) {
        this.lesLots = lesLots;
    }
    

    public ArrayList<Cheval> getParents() {
        return parents;
    }

    public void setParents(ArrayList<Cheval> parents) {
        this.parents = parents;
    }

    public ArrayList<ChevalCourse> getLesChevalCourses() {
        return lesChevalCourses;
    }

    public void setLesChevalCourses(ArrayList<ChevalCourse> lesChevalCourses) {
        this.lesChevalCourses = lesChevalCourses;
    }
    

    public void addLot(Lot unLot){
        if (lesLots ==null ){
            lesLots = new ArrayList<Lot>();
        }
        lesLots.add(unLot);
    }
    
    public void addChevalCourse(ChevalCourse unChevalCourse){
        if (lesChevalCourses ==null ){
            lesChevalCourses = new ArrayList<ChevalCourse>();
        }
        lesChevalCourses.add(unChevalCourse);
    }
    
   
}
