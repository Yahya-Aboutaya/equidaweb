package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cheval {

    private int id;
    private String nom;
    private String sexe;
    private String sire;
    private LocalDate dateNaissance;
    
    // Nouveaux attributs physiques
    private float taille;
    private float poids;
    private String typeRobe;

    // Objets parents (Généalogie)
    private Cheval pere;
    private Cheval mere;

    private Race race;
    private ArrayList<Lot> lesLots; 
    private ArrayList<ChevalCourse> lesChevalCourses;

    public Cheval() {
    }

    // --- Getters et Setters existants ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    public String getSire() { return sire; }
    public void setSire(String sire) { this.sire = sire; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public Race getRace() { return race; }
    public void setRace(Race race) { this.race = race; }

    // --- Nouveaux Getters et Setters pour la taille, poids et robe ---
    public float getTaille() { return taille; }
    public void setTaille(float taille) { this.taille = taille; }
    public float getPoids() { return poids; }
    public void setPoids(float poids) { this.poids = poids; }
    public String getTypeRobe() { return typeRobe; }
    public void setTypeRobe(String typeRobe) { this.typeRobe = typeRobe; }

    // --- Getters et Setters pour les parents ---
    public Cheval getPere() { return pere; }
    public void setPere(Cheval pere) { this.pere = pere; }
    public Cheval getMere() { return mere; }
    public void setMere(Cheval mere) { this.mere = mere; }

    // --- Gestion des listes ---
    public ArrayList<Lot> getLesLots() { return lesLots; }
    public void setLesLots(ArrayList<Lot> lesLots) { this.lesLots = lesLots; }
    public ArrayList<ChevalCourse> getLesChevalCourses() { return lesChevalCourses; }
    public void setLesChevalCourses(ArrayList<ChevalCourse> lesChevalCourses) { this.lesChevalCourses = lesChevalCourses; }

    public void addLot(Lot unLot){
        if (lesLots == null) { lesLots = new ArrayList<Lot>(); }
        lesLots.add(unLot);
    }
    
    public void addChevalCourse(ChevalCourse unChevalCourse){
        if (lesChevalCourses == null) { lesChevalCourses = new ArrayList<ChevalCourse>(); }
        lesChevalCourses.add(unChevalCourse);
    }
}