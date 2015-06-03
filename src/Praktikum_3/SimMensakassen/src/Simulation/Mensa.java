package Simulation;

import Verraucher.Kasse;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dima
 */
public class Mensa {
    
    private List<Kasse> kassen;
    
    public Mensa(){
        
        kassen = new LinkedList();
    }
    
    public List<Kasse> init(int kassenAnzahl){
        
        for(int i = 0; i < kassenAnzahl; i++){
            
            Kasse kasse = new Kasse(i);
            System.err.println("Kasse "+i+" hat geöffnet");
            kasse.start();
            kassen.add(kasse);
        }
        return kassen;
    }
    
}
