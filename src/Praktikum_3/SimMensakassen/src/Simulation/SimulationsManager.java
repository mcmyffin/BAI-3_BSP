package Simulation;

import Erzeuger.Student;
import Verraucher.Kasse;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dima
 */
public class SimulationsManager extends Thread{
    
    
    private int studentenAnzahl;
    private int kassenAnzahl;
    private long simDauerInMillSec;
    
    public SimulationsManager(int studentenAnzahl, int kassenAnzahl, long simDauer){
        
        this.studentenAnzahl = studentenAnzahl;
        this.kassenAnzahl = kassenAnzahl;
        this.simDauerInMillSec = simDauer;
    }
    
    public void run(){
        
        Mensa mensa = new Mensa();
        List<Kasse> kassenListe = mensa.init(kassenAnzahl);
        List<Student> studenten = new LinkedList();
        
        
        
        for(int i = 0; i < studentenAnzahl; i++){
            Student student = new Student(i, kassenListe);
            studenten.add(student);
            student.gehEssen();
        }
        
        
        while(!this.isInterrupted()){
            
            try {
                sleep(simDauerInMillSec);
                
                for(Student einStudent : studenten){
                    einStudent.interrupt();
                }
                
                for(Kasse eineKasse : kassenListe){
                    eineKasse.interrupt();
                }
                
            } catch (InterruptedException ex) {
                
            }
            
            System.err.println("=== Simulation beendet ===");
            this.interrupt();
        }
        
        
    }
}
