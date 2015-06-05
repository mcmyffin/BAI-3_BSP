package stp;

import stp.SimulationsManager.SimulationsManager;

/**
 *
 * @author dima
 */
public class StartUp {

    public static void main(String[] args) {
        SimulationsManager simulation = new SimulationsManager(20000, 2);
        simulation.start();
    }
    
    
}
