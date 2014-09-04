import java.util.HashSet;
import java.util.Set;

/**
 * Created by yushi on 9/1/14.
 */
public class ParkingBoy extends ParkingMan {

    public ParkingBoy(Set<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingBoy(String name, Set<ParkingLot> parkingLots) {
        super(name, parkingLots);
    }

    @Override
    protected ParkingLot getSpaceParkingLot() {
        for (ParkingLot pl : parkingLots) {
            if (pl.hasSpace()) {
                return pl;
            }
        }

        return null;
    }
}
