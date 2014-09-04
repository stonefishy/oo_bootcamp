import java.util.HashSet;

/**
 * Created by yushi on 9/2/14.
 */
public class SuperParkingBoy extends SmartParkingBoy implements Calculator{
    public SuperParkingBoy(HashSet<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public float calculate(int space, int availableSpace) {
        return (float)availableSpace/space;
    }
}
