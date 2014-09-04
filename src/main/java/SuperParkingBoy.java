import java.util.HashSet;
import java.util.Set;

/**
 * Created by yushi on 9/2/14.
 */
public class SuperParkingBoy extends SmartParkingBoy implements Calculator{
    public SuperParkingBoy(HashSet<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public SuperParkingBoy(String name, Set<ParkingLot> parkingLots) {
        super(name, parkingLots);
    }

    @Override
    public float calculate(int space, int availableSpace) {
        return (float)availableSpace/space;
    }
}
