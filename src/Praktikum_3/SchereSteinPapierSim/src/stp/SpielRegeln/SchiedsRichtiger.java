package stp.SpielRegeln;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.util.Pair;
import stp.Erzeuger.Spieler;
import stp.Verbraucher.Tisch;

/**
 *
 * @author dima
 */

public class SchiedsRichtiger extends Thread{

    private Tisch tisch;
    private Random zufall;
    
    public SchiedsRichtiger(Tisch tisch){
        this.tisch = tisch;
        this.zufall = new Random();
    }
    
    
    private void spielrundeAuswertung(List<Pair<Spieler,HandTyp>> spielErgebnis){
        
        Pair<Spieler,HandTyp> spieler1Ergebnis = spielErgebnis.get(0);
        Pair<Spieler,HandTyp> spieler2Ergebnis = spielErgebnis.get(1);
        
        HandTyp sp1Hand = spieler1Ergebnis.getValue();
        HandTyp sp2Hand = spieler2Ergebnis.getValue();
        
        String auswetungErgebnis = "";
        String ergebnisGrund = "Grund: ";
        
        if(sp1Hand.equals(sp2Hand)){
            auswetungErgebnis = "Unendschieden";
            ergebnisGrund += sp1Hand.getValue()+" == "+sp2Hand.getValue();
            
        }else if(HandTyp.PAPIER.equals(sp1Hand)){
            
            if(HandTyp.SCHERE.equals(sp2Hand)){
                // sp2 hat gewonnen
                auswetungErgebnis = "Spieler "+spieler2Ergebnis.getKey()+" hat gewonnen";
                ergebnisGrund += sp2Hand.getValue()+" > "+sp1Hand.getValue();
                
            }else{
                // sp2 hat stein
                // und somit sp1 gewonnen
                auswetungErgebnis = "Spieler "+spieler1Ergebnis.getKey()+" hat gewonnen";
                ergebnisGrund += sp1Hand.getValue()+" > "+sp2Hand.getValue();
            }
        }else if(HandTyp.SCHERE.equals(sp1Hand)){
            
            if(HandTyp.PAPIER.equals(sp2Hand)){
                // sp2 hat verloren
                auswetungErgebnis = "Spieler "+spieler1Ergebnis.getKey()+" hat gewonnen";
                ergebnisGrund += sp1Hand.getValue()+" > "+sp2Hand.getValue();
                
            }else{
                // sp2 hat stein
                // und somit gewonnen
                auswetungErgebnis = "Spieler "+spieler2Ergebnis.getKey()+" hat gewonnen";
                ergebnisGrund += sp2Hand.getValue()+" > "+sp1Hand.getValue();
            }
        }else{
            // sp1HandTyp == Stein
            if(sp2Hand.PAPIER.equals(sp2Hand)){
                // sp2 hat gewonnen
                auswetungErgebnis = "Spieler "+spieler2Ergebnis.getKey()+" hat gewonnen";
                ergebnisGrund += sp2Hand.getValue()+" > "+sp1Hand.getValue();
            }else{
                // sp2 hat Schere
                // und somit verloren
                auswetungErgebnis = "Spieler "+spieler1Ergebnis.getKey()+" hat gewonnen";
                ergebnisGrund += sp1Hand.getValue()+" > "+sp2Hand.getValue();
            }
            
        }
        
        System.err.println("==== Schiedsrichterauswertung ====");
        System.err.println(auswetungErgebnis);
        System.err.println(ergebnisGrund);
        System.err.println("==================================");
        
    }
    
    
    
    public void run(){
        
        while(!this.isInterrupted()){
            
            Map<Spieler,HandTyp> spielErgebnis = tisch.getSpielErgebnis(this);
            List<Pair<Spieler,HandTyp>> ergebnisAlsListPair = new ArrayList();
            
            for(Spieler spieler : spielErgebnis.keySet()){
                ergebnisAlsListPair.add(new Pair<Spieler,HandTyp>(spieler,spielErgebnis.get(spieler)));
            }
            spielrundeAuswertung(ergebnisAlsListPair);
            
            try {
                this.sleep(zufall.nextInt(500));
            } catch (InterruptedException ex) {
                System.err.println("Schiedsrichter wurde bei der Ergebnisauswertung unterbrochen");
                this.interrupt();
            }
            
            tisch.neueRunde();
            
        }
    }
    
}
