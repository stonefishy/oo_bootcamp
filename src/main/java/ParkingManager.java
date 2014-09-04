import java.util.HashSet;
import java.util.Set;

/**
 * Created by yushi on 9/3/14.
 */
public class ParkingManager extends ParkingBoy {
    private Set<ParkingPeople> parkingPeoples = new HashSet<ParkingPeople>();

    public ParkingManager(Set<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public void addParkingMan(ParkingPeople parkingPeople) {
        this.parkingPeoples.add(parkingPeople);
    }

    @Override
    public String portCar(Car car) throws NoMoreSpaceException {
        for (ParkingPeople parkingPeople : parkingPeoples) {
            try {
                return parkingPeople.portCar(car);
            } catch (Exception e) {
                continue;
            }
        }

        return super.portCar(car);
    }

    @Override
    public Car pickCar(String ticket) throws InvalidTicketException {
        for (ParkingPeople parkingPeople : parkingPeoples) {
            try {
                return parkingPeople.pickCar(ticket);
            } catch (Exception e) {
                continue;
            }
        }

        return super.pickCar(ticket);
    }
}
