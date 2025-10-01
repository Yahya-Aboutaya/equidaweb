/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import model.Cheval;
import model.ChevalCourse;
import model.Course;

/**
 *
 * @author sio2
 */
public class TestChevalCourse {
    
    public static void main (String args[]){
        Cheval c = new Cheval();
        c.setId(2);
        c.setNom("Houri");
        
        Course cs = new Course();
        cs.setId(5);
        cs.setNom("zvufex");
        
        ChevalCourse cc = new ChevalCourse();
        cc.setCheval(c);
        cc.setCourse(cs);
        
        //c.addChevalCourse(cc);
        
         System.out.println("Cheval : " + c.getId() + " " + c.getNom() + " "
                + cs.getId() + " " + cs.getNom());
        
    }
    
    
}
