package SimRace.RaceManager;

import SimRace.Evil.Accident;
import SimRace.Participants.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by dima on 07.05.15.
 */
public class RaceManager implements IRaceManager{


    @Override
    public List<String> startRace(int rounds) {

        List<String> result = new ArrayList<String>();

        Car car1 = new Car("Arnold", rounds);
        Car car2 = new Car("Donalt", rounds);
        Car car3 = new Car("Goofy", rounds);
        Car car4 = new Car("Simon", rounds);
        Car car5 = new Car("Wook", rounds);

        Car[] cars_ = {car1,car2,car3,car4,car5};
        List<Car> cars = new ArrayList<Car>(Arrays.asList(cars_));

        car1.start();
        car2.start();
        car3.start();
        car4.start();
        car5.start();

        try {
            car1.join();
            car2.join();
            car3.join();
            car4.join();
            car5.join();
        } catch (InterruptedException e) {

        }


        cars.sort(new TimeComparator());

        for(int i = 0; i < cars.size(); i++){
            result.add((i+1)+". Place "+cars.get(i).toString());
        }
        return result;
    }

    @Override
    public List<String> startRaceWithEvil(int rounds, int wickedness) throws Exception{
        List<String> result = new ArrayList<String>();

        Car car1 = new Car("Arnold", rounds);
        Car car2 = new Car("Donalt", rounds);
        Car car3 = new Car("Goofy", rounds);
        Car car4 = new Car("Simon", rounds);
        Car car5 = new Car("Wook", rounds);

        Car[] cars_ = {car1,car2,car3,car4,car5};
        List<Car> cars = new ArrayList<Car>(Arrays.asList(cars_));

        car1.start();
        car2.start();
        car3.start();
        car4.start();
        car5.start();
        
        Random rand = new Random();
        
        Thread.sleep(150);
        
        if(rand.nextInt(wickedness) == 0){
            // its accident time
            Accident accident = new Accident(cars);
            accident.execute();
            
        }else{
            // sooo luky
            try {
                car1.join();
                car2.join();
                car3.join();
                car4.join();
                car5.join();
            } catch (InterruptedException e) {

            }
        }
        
        cars.sort(new TimeComparator());

        for(int i = 0; i < cars.size(); i++){
            result.add((i+1)+". Place "+cars.get(i).toString());
        }
        
        return result;
    }
}

class TimeComparator implements Comparator<Car>{

    @Override
    public int compare(Car o1, Car o2) {
        return Long.compare(o1.getRaceTimeInMillsec(), o2.getRaceTimeInMillsec());
    }
    
}
