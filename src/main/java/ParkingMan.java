import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yushi on 9/4/14.
 */
public abstract class ParkingMan implements ParkingPeople {

    private String name;
    protected Set<ParkingLot> parkingLots;

    public ParkingMan(Set<ParkingLot> parkingLots) {
        this.parkingLots = new HashSet<ParkingLot>();
        this.parkingLots.addAll(parkingLots);
    }

    public ParkingMan(String name, Set<ParkingLot> parkingLots) {
        this.name = name;
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

    @Override
    public void printReport(PrintStream printStream) {
        String info = String.format("%s:%s, %d parkinglots", getClass().getName(), name, parkingLots.size());
        printStream.println(info);
        printStream.println();

        for (ParkingLot pl : parkingLots) {
            pl.printReport(printStream);
        }
        printStream.println();
    }

    protected abstract ParkingLot getSpaceParkingLot();

}
