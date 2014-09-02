import java.util.HashMap;
import java.util.Map;

/**
 * Created by yushi on 9/1/14.
 */
public class ParkingLot {
    private int spaceCount;
    private String parkingLotName;
    private Map<String, Car> mapCar;
    private int maxSpace;

    public ParkingLot(String parkingLotName, int spaceCount){
        this.parkingLotName = parkingLotName;
        this.spaceCount = spaceCount;
        this.maxSpace = spaceCount;
        mapCar = new HashMap<String, Car>(spaceCount);
    }

    public float calculate(Calculator calculator) {
        return calculator.calculate(maxSpace, spaceCount);
    }

    public boolean hasSpace() {
        return spaceCount > 0;
    }

    public String portCar(Car car) throws NoMoreSpaceException {
        String tickets;

        if (hasSpace()) {
            tickets = getTicket(car);
            mapCar.put(tickets, car);
            spaceCount--;
        } else {
            throw new NoMoreSpaceException();
        }

        return tickets;
    }

    public Car pickCar(String ticket) throws InvalidTicketException {
        Car car;
        if(isTicketValid(ticket)){
            car = mapCar.get(ticket);
            mapCar.remove(ticket);
            spaceCount++;
        }else {
            throw new InvalidTicketException();
        }

        return car;
    }

    public String getTicket(Car car) {
        return String.valueOf(car.getCarName().hashCode());
    }

    private boolean isTicketValid(String ticket){
        if(ticket != null && mapCar.containsKey(ticket)){
            return true;
        }

        return false;
    }
}
