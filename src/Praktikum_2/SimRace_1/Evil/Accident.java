package SimRace.Evil;

import SimRace.Participants.Car;
import java.util.List;
import java.util.Random;

/**
 * Created by dima on 07.05.15.
 */
public class Accident {
    
    private List<Car> cars;
    
    public Accident(List<Car> cars){
        this.cars = cars;
    }
    
    public void execute() throws Exception{
        
        if(cars.isEmpty()) return;
        Random rand = new Random();
        int index = rand.nextInt((cars.size()-1));
        
        Car aCar = cars.get(index);
        
        // hahaha
        aCar.interrupt();
        throw new Exception(aCar.getCarName()+" in round "+aCar.getDrivenRounds());
        
    }
}
