package mk.Erzeuger;

import mk.Verraucher.Kasse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dima
 */
public class Student extends Thread{
    
    private int matrikelNummer;
    private List<Kasse> kassenListe;
    private boolean hatBezahl = false;
    private boolean hatSichAngestellt = false;
    
    public Student(int matrikelNummer, List<Kasse> kassenListe){
        
        this.matrikelNummer = matrikelNummer;
        this.kassenListe = kassenListe;
    }
    
    public boolean hatBezahlt(){
        return hatBezahl;
    }
    
    public void bezahlen(){
        this.hatBezahl = true;
    }
    
    public void anstellen(){
        this.hatSichAngestellt = true;
    }
    
    private Kasse sucheKasseMitKleinsterWarteschlage(){
        
        Kasse kasseMitKleinsterSchlange = null;
        for(Kasse kasse : kassenListe){

            if(kasseMitKleinsterSchlange == null) kasseMitKleinsterSchlange = kasse;
            if(kasse.getQueueSize() < kasseMitKleinsterSchlange.getQueueSize()) kasseMitKleinsterSchlange = kasse;
        }
        return kasseMitKleinsterSchlange;
    }
    
    
    public void run(){
     
        while(!this.isInterrupted()){

            Kasse eineKasse = sucheKasseMitKleinsterWarteschlage();
            eineKasse.anKasseAnstellen(this);
           
            try {
                System.err.println(this+" isst");
                sleep(2000);
            } catch (InterruptedException ex) {
                System.err.println(this+" wird beim Essen unterbrochen :(");
                this.interrupt();
            }
        }
    }
    
    public void gehEssen(){
        this.start();
    }

    @Override
    public String toString() {
        return "Student["+matrikelNummer+"]";
    }
    
    
    
}
