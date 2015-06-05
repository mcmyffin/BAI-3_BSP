package stp.Verbraucher;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import stp.Erzeuger.Spieler;
import stp.SpielRegeln.HandTyp;
import stp.SpielRegeln.SchiedsRichtiger;

/**
 *
 * @author dima
 */

public class Tisch {

    
    private Map<Spieler,HandTyp> spielerErgebnis;
    private ReentrantLock   zugriffMutex;
    private Condition       spielerConditionQueue;
    private Condition       schiedsrichterConditionQueue;
    
    public Tisch(){
        spielerErgebnis = new LinkedHashMap();
        this.zugriffMutex = new ReentrantLock();
        this.spielerConditionQueue = zugriffMutex.newCondition();
        this.schiedsrichterConditionQueue = zugriffMutex.newCondition();
    }
    
    public void legeHandAufTisch(Spieler spieler, HandTyp handTyp){
        zugriffMutex.lock();
        
            try {

                System.err.println("Spieler "+spieler+" -> "+handTyp.getValue());
                spielerErgebnis.put(spieler, handTyp);
                schiedsrichterConditionQueue.signal();

                spielerConditionQueue.await();

            } catch (InterruptedException ex) {
                System.err.println("Spieler"+spieler+" wurde beim Warten auf das Spielergebnis gestört");
                spieler.interrupt();
            }
        
        zugriffMutex.unlock();
    }
    
    public Map<Spieler,HandTyp> getSpielErgebnis(SchiedsRichtiger schiedsRichtiger){
        
        zugriffMutex.lock();
            while(!habenBeideSpielerZugGemacht()){

                try {
                    schiedsrichterConditionQueue.await();
                } catch (InterruptedException ex) {
                    System.err.println("Schiedsrichter wurde beim Warten unterbrochen");
                    schiedsRichtiger.interrupt();
                    break;
                }
            }
        zugriffMutex.unlock();
        return this.spielerErgebnis;
    }
    
    private boolean habenBeideSpielerZugGemacht(){
        zugriffMutex.lock();
            boolean spielerFertig = spielerErgebnis.size() >= 2;
        zugriffMutex.unlock();
        
        return spielerFertig;
    }
    
    public void neueRunde(){
        zugriffMutex.lock();
            spielerErgebnis.clear();
            spielerConditionQueue.signalAll();
        zugriffMutex.unlock();
    }
    
    
}
