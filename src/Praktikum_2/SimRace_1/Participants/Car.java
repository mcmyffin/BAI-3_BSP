package SimRace.Participants;

import java.util.Random;

/**
 * Created by dima on 07.05.15.
 */
public class Car extends Thread implements Comparable<Car>{

    // Information
    private String name;
    private int rounds = 0;
    private int roundsDone = 0;

    // Time
    private long startTimeInMillSec = 0;
    private long raceTimeInMillsec = 0;

    // Random
    private Random randomness;
    private int maxRoundTime = 200;

    public Car(String carname, int rounds){
        this.name = carname;
        this.rounds = rounds;
        this.randomness = new Random();
    }

    private void drive(){

        startTimeInMillSec = System.currentTimeMillis();
        for(; roundsDone <= rounds && !isInterrupted(); roundsDone++){

            int roundTime = randomness.nextInt(this.maxRoundTime);

            try {
                sleep(roundTime);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
        raceTimeInMillsec = System.currentTimeMillis() - startTimeInMillSec;
    }

    public void run(){
        drive();
    }

    public String getCarName(){
        return this.name;
    }
    
    public String getDrivenRounds(){
        return roundsDone+"/"+rounds;
    }
    
    public long getRaceTimeInMillsec(){
        return this.raceTimeInMillsec;
    }

    @Override
    public String toString(){

        return getCarName()+" ["+getRaceTimeInMillsec()+"] ";
    }

    @Override
    public int compareTo(Car o) {
        return Long.compare(this.getRaceTimeInMillsec(),o.getRaceTimeInMillsec());
    }
}
