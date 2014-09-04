import java.util.HashSet;
import java.util.Set;

/**
 * Created by yushi on 9/4/14.
 */
public abstract class ParkingMan implements ParkingPeople {

    protected Set<ParkingLot> parkingLots;

    public ParkingMan(Set<ParkingLot> parkingLots) {
        this.parkingLots = new HashSet<ParkingLot>();
        this.parkingLots.addAll(parkingLots);
    }

    @Override
    public String portCar(Car car) throws NoMoreSpaceException {
        String ticket;
        ParkingLot pl = getSpaceParkingLot();

        if (pl != null) {
            ticket = pl.portCar(car);
        } else {
            throw new NoMoreSpaceException();
        }

        return ticket;
    }

    @Override
    public Car pickCar(String ticket) throws InvalidTicketException {
        for (ParkingLot pl : parkingLots) {
            Car car = pl.pickCar(ticket);
            if (car != null) {
                return car;
            }
        }

        return null;
    }

    protected abstract ParkingLot getSpaceParkingLot();

}
