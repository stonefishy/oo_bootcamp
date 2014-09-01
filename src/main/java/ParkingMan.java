import java.util.HashSet;
import java.util.Set;

/**
 * Created by yushi on 9/1/14.
 */
public class ParkingMan {
    private Set<ParkingLot> parkingLots;

    public ParkingMan(Set<ParkingLot> parkingLots){
        this.parkingLots = new HashSet<ParkingLot>();
        this.parkingLots.addAll(parkingLots);
    }

    public boolean hasCarSpace() {
        boolean canFindSpace = false;
        for(ParkingLot pl : parkingLots){
            canFindSpace |= pl.hasSpace();
        }
        return  canFindSpace;
    }

    public String portCar(Car car) throws NoMoreSpaceException {
        String ticket = null;

        if(hasCarSpace()){
            ParkingLot pl = getSpaceParkingLot();
            ticket = pl.portCar(car);
        } else {
            throw new NoMoreSpaceException();
        }

        return ticket;
    }

    public Car pickCar(String ticket) throws InvalidTicketException {
        for(ParkingLot pl : parkingLots){
            Car car = pl.pickCar(ticket);
            if(car != null){
                return car;
            }
        }

        return null;
    }

    private ParkingLot getSpaceParkingLot(){
        for(ParkingLot pl : parkingLots){
            if(pl.hasSpace()){
                return pl;
            }
        }

        return null;
    }
}
