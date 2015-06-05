package mk.Simulation;

import mk.Erzeuger.Student;
import mk.Verraucher.Kasse;
import java.util.LinkedList;
import java.util.List;

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
        
        Mensa mensa = new Mensa(kassenAnzahl);
        List<Student> studenten = new LinkedList();
        
        for(Kasse eineKasse : mensa.getVerfuegbareKassen()){
            eineKasse.start();
            System.err.println("Kasse "+eineKasse+" hat geöffnet");
        }
        
        for(int i = 0; i < studentenAnzahl; i++){
            Student student = new Student(i, mensa.getVerfuegbareKassen());
            studenten.add(student);
            student.gehEssen();
            System.err.println("Student "+student+" geht zur Mensa");
        }
        
        
        while(!this.isInterrupted()){
            
            try {
                sleep(simDauerInMillSec);
                this.interrupt();
                
            } catch (InterruptedException ex) {
                this.interrupt();
            }
            
            System.err.println("=== Simulation beendet ===");
            
        }
        // Studenten Threads stoppen
        for(Student einStudent : studenten){
                    einStudent.interrupt();
        }
        
        // Kassen Threads stoppen
        for(Kasse eineKasse : mensa.getVerfuegbareKassen()){
                    eineKasse.interrupt();
        }
        
        
    }
}
