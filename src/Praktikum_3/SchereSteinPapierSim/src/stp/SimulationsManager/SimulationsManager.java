package stp.SimulationsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import stp.Erzeuger.Spieler;
import stp.SpielRegeln.SchiedsRichtiger;
import stp.Verbraucher.Tisch;

/**
 *
 * @author dima
 */
public class SimulationsManager extends Thread{
    
    private long simulationszeitInMillSec;
    private int spilerAnzahl;
    
    private List<Spieler> spielerListe;
    private SchiedsRichtiger schiedsrichter;
    private Tisch einTisch;
    
    
    public SimulationsManager(long simulationsZeitInMillSec, int spielerAnzahl){
        
        this.simulationszeitInMillSec = simulationsZeitInMillSec;
        this.spilerAnzahl = spielerAnzahl;
        this.einTisch = new Tisch();
        this.schiedsrichter = new SchiedsRichtiger(einTisch);
        init();
    }
    
    private void init(){
        
        spielerListe = new ArrayList();
        
        for(int i = 0; i < spilerAnzahl; i++){
            
            Spieler einSpieler = new Spieler(i,einTisch);
            spielerListe.add(einSpieler);
        }
    }
    
    
    public void run(){
        
        // Starte Schiedsrichter
        schiedsrichter.start();
        
        // Starte Spieler Threads
        for(Spieler einSpieler: spielerListe){
            einSpieler.start();;
        }
        
        while(!this.isInterrupted()){
            try {
                this.sleep(simulationszeitInMillSec);
                this.interrupt();
            } catch (InterruptedException ex) {
                this.interrupt();
            }
        }
        
        System.err.println("### Simulation beendet ### ");
        schiedsrichter.interrupt();
        for(Spieler einSpieler : spielerListe){
            einSpieler.interrupt();
        }
    }
    
}
