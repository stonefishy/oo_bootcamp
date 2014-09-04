import java.util.HashSet;
import java.util.Set;

/**
 * Created by yushi on 9/1/14.
 */
public class ParkingBoy extends ParkingMan {

    public ParkingBoy(Set<ParkingLot> parkingLots) {
        super(parkingLots);
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
