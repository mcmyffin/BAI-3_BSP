package Erzeuger;

import Verraucher.Kasse;
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
    private boolean habBezahlt = false;
    private boolean bereitsAngestellt = false;
    
    public Student(int matrikelNummer, List<Kasse> kassenListe){
        
        this.matrikelNummer = matrikelNummer;
        this.kassenListe = kassenListe;
    }
    
    public void hatBezahlt(){
        this.habBezahlt = true;
    }
    
    private void habGegessen(){
        this.bereitsAngestellt = false;
        this.habBezahlt = false;
    }
    
    public void anstellen(){
        this.bereitsAngestellt = true;
    }
    
    
    public void run(){
     
        while(!this.isInterrupted()){
         
            
            if(!bereitsAngestellt){
                // 1) kasse wählen
                Kasse kasseMitKleinsterSchlange = null;

                for(Kasse kasse : kassenListe){

                    if(kasseMitKleinsterSchlange == null) kasseMitKleinsterSchlange = kasse;
                    if(kasse.getQueueSize() < kasseMitKleinsterSchlange.getQueueSize()) kasseMitKleinsterSchlange = kasse;
                }

                // 2) an kasse anstellen
                kasseMitKleinsterSchlange.anKasseAnstellen(this);
            }    

            // 3) essen
            if(habBezahlt){
                try {
                    System.err.println(" Student "+matrikelNummer+" isst");
                    sleep(2000);
                    habGegessen();
                } catch (InterruptedException ex) {
                    System.err.println(" Student wird beim Essen unterbrochen :(");
                    this.interrupt();
                }
            }
        }
    }
    
    public void gehEssen(){
        this.start();
    }

    @Override
    public String toString() {
        return matrikelNummer+"";
    }
    
    
    
}
