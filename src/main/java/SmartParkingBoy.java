import java.util.HashSet;
import java.util.Set;

/**
 * Created by yushi on 9/2/14.
 */
public class SmartParkingBoy extends ParkingMan implements Calculator{
    public SmartParkingBoy(HashSet<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public SmartParkingBoy(String name, Set<ParkingLot> parkingLots) {
        super(name, parkingLots);
    }

    @Override
    protected ParkingLot getSpaceParkingLot(){
        ParkingLot parkingLot = parkingLots.iterator().next();
        for(ParkingLot pl : parkingLots){
            if(pl.calculate(this) >= parkingLot.calculate(this)){
                parkingLot = pl;
            }
        }
        return parkingLot;
    }

    @Override
    public float calculate(int space, int availableSpace) {
        return availableSpace;
    }
}
