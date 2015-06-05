package mk;

import mk.Simulation.SimulationsManager;

/**
 *
 * @author dima
 */
public class StartUp {
        
    public static void main(String[] args) {
        
        mk.Simulation.SimulationsManager simulation = new SimulationsManager(5,3,3000);
        simulation.start();
        
    }

}
