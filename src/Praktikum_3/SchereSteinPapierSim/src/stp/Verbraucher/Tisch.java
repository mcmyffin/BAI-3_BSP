package stp.Verbraucher;

import java.util.LinkedHashMap;
import java.util.Map;
import stp.Erzeuger.Spieler;
import stp.SpielRegeln.HandTyp;
import stp.SpielRegeln.SchiedsRichtiger;

/**
 *
 * @author dima
 */

public class Tisch {

    
    private Map<Spieler,HandTyp> spielerErgebnis;
    
    public Tisch(){
        spielerErgebnis = new LinkedHashMap();
    }
    
    public synchronized void legeHandAufTisch(Spieler spieler, HandTyp handTyp){
        
        while(spielerErgebnis.containsKey(spieler)){
            
            try {
                wait();
            } catch (InterruptedException ex) {
                System.err.println("Spieler"+spieler+" wurde beim Warten auf das Spielergebnis gestört");
                spieler.interrupt();
                
                return;
            }
        }
        
        System.err.println("Spieler "+spieler+" -> "+handTyp.getValue());
        spielerErgebnis.put(spieler, handTyp);
        notifyAll();
    }
    
    public synchronized Map<Spieler,HandTyp> getSpielErgebnis(SchiedsRichtiger schiedsRichtiger){
        
        while(!habenBeideSpielerZugGemacht()){
            
            try {
                wait();
            } catch (InterruptedException ex) {
                System.err.println("Schiedsrichter wurde beim Warten unterbrochen");
                schiedsRichtiger.interrupt();
            }
        }
        
        return this.spielerErgebnis;
    }
    
    private synchronized boolean habenBeideSpielerZugGemacht(){
        return spielerErgebnis.size() >= 2;
    }
    
    public synchronized void neueRunde(){
        spielerErgebnis.clear();
        notifyAll();
    }
    
    
}
