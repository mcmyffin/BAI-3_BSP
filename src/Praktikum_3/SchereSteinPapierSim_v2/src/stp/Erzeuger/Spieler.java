package stp.Erzeuger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import stp.SpielRegeln.HandTyp;
import stp.Verbraucher.Tisch;

/**
 *
 * @author dima
 */

public class Spieler extends Thread{
    
    
    private int spielerNr;
    private Tisch spielTisch;
    private List<HandTyp> handTypen;
    private Random zufall;
    
    public Spieler(int spielerNr, Tisch spielTisch){
        this.spielerNr = spielerNr;
        this.spielTisch = spielTisch;
        this.handTypen = new ArrayList();
        this.zufall = new Random();
        init();
    }
    
    private void init(){
        
        handTypen.add(HandTyp.PAPIER);
        handTypen.add(HandTyp.SCHERE);
        handTypen.add(HandTyp.STEIN);
    }
    
    private HandTyp waehleZufaelligenHandTyp(){
        
        int index = zufall.nextInt(3);
        return handTypen.get(index);
    }
    
    /**
     * Run Methode
     */
    public void run(){
    
        while(!this.isInterrupted()){
            
            HandTyp handTyp = waehleZufaelligenHandTyp();
            spielTisch.legeHandAufTisch(this, handTyp);
            
        }
        
    }

    
    @Override
    public String toString() {
        return spielerNr+"";
    }
    
    
}
