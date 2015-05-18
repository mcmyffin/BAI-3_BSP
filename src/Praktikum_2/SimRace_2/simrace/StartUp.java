package simrace;

import simrace.RaceManager.RaceManager;

import java.util.List;
import java.util.Scanner;

/**
 * Created by dima on 07.05.15.
 */
public class StartUp {

    public static void main(String[] args) {
        
        // Terminal inputstream
        Scanner scanner = new Scanner(System.in);
        
        // Output Info
        System.out.println("Welcome to Race Simulator, lets begin!");
        System.out.println("How many rounds?\n");
        int rounds = scanner.nextInt();
        
        System.out.println("=== RACE 1 ===");
        
        // Start race
        
        // Racemanager
        RaceManager raceManager = new RaceManager(rounds);
        List<String> result = raceManager.startRace();
        
        // print result
        for(String txt: result){
            System.out.println(txt);
        }
        
        
        
        // New Race
        System.out.println("==============\n");
        System.out.println("=== RACE 2 ==");
        
        raceManager = new RaceManager(rounds);
        // if accident happens, than catch Exception with message
        try{
            // start race
            List<String> result1 = raceManager.startRaceAccident();
            
            // print result
            for(String txt: result1){
                System.out.println(txt);
            }
            
        }catch(InterruptedException ex){
            System.out.println("Race stop");
        }
        System.out.println("==============");
    }
}
