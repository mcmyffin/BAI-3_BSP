package SimRace;

import SimRace.RaceManager.IRaceManager;
import SimRace.RaceManager.RaceManager;
import java.io.InputStream;

import java.util.List;
import java.util.Scanner;

/**
 * Created by dima on 07.05.15.
 */
public class StartUp {

    public static void main(String[] args) {
        
        // Racemanager
        IRaceManager raceManager = new RaceManager();
        
        // Terminal inputstream
        Scanner scanner = new Scanner(System.in);
        
        // Output Info
        System.out.println("Welcome to Race Simulator, lets begin!");
        System.out.println("How many rounds?\n");
        int rounds_1 = scanner.nextInt();
        
        System.out.println("=== RACE 1 ===");
        
        // Start race
        List<String> result = raceManager.startRace(rounds_1);
        
        // print result
        for(String txt: result){
            System.out.println(txt);
        }
        
        // New Race
        System.out.println("==============\n");
        System.out.println("Now a race with Evil :)");
        System.out.println("How many rounds?");
        int rounds_2 = scanner.nextInt();
        
        // Ask for wickedness 
        System.out.println("Evil Wickedness?");
        System.out.println("0: 100%");
        System.out.println("1: 50%");
        System.out.println("...");
        
        int wickedness = scanner.nextInt();
        System.out.println("=== RACE 2 ==");
        
        // if accident happens, than catch Exception with message
        try{
        
            // start race
            List<String> result1 = raceManager.startRaceWithEvil(rounds_2, wickedness);
            
            // print result
            for(String txt: result1){
                System.out.println(txt);
            }
            
        }catch(Exception ex){
            // An accident happens, print which car and in which round 
            System.out.println(ex.getMessage()+" had an accident");
        }
        System.out.println("==============");
    }
}
