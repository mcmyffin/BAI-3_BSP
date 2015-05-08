package SimRace.RaceManager;

import java.util.List;

/**
 * Created by dima on 07.05.15.
 */
public interface IRaceManager {


    public List<String> startRace(int rounds);

    public List<String> startRaceWithEvil(int rounds, int wickedness) throws Exception;
}
