package mk.Simulation;

import mk.Verraucher.Kasse;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dima
 */
public class Mensa {
    
    private List<Kasse> kassen;
    
    public Mensa(int kassenAnzahl){
        kassen = new LinkedList();
        init(kassenAnzahl);
    }
    
    private void init(int kassenAnzahl){
        
        for(int i = 0; i < kassenAnzahl; i++){
            
            Kasse kasse = new Kasse(i);
            System.err.println("Kasse "+i+" wurde erstellt");
            kassen.add(kasse);
        }
    }
    
    public List<Kasse> getVerfuegbareKassen(){
        return this.kassen;
    }
    
}
