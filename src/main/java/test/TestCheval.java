package test;

import model.Cheval;
import model.Race;

public class TestCheval {

    public static void main (String args[]){

        // création d'une instance de cheval nommée c
        Cheval c = new Cheval();
        c.setId(2);
        c.setNom("Houri");

        // création d'une instance de race nommée r
        Race r = new Race();
        r.setId(1);
        r.setLibelle("pur-sang");

        //affectation de  la race au cheval grâce à la relation ManyToOne
        c.setRace(r);
        
        // Création de 2 parents
        Cheval parent1 = new Cheval();
        parent1.setId(10);
        parent1.setNom("Lightning");
        parent1.setSexe("F");

        Cheval parent2 = new Cheval();
        parent2.setId(11);
        parent2.setNom("Thunder");
        parent2.setSexe("M");

        
        //c.addParent(parent1);
        //c.addParent(parent2);


        System.out.println("Cheval : " + c.getId() + " " + c.getNom() + " "
                + c.getRace().getId() + " " + c.getRace().getLibelle());
        System.out.println("Parents : ");
        for (Cheval p : c.getParents()) {
            System.out.println(" - " + p.getId() + " " + p.getNom() + " " + p.getSexe());
        }
        
    }
}
