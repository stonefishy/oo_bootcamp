/**
 * Created by yushi on 9/2/14.
 */
public interface ParkingPeople {
    String portCar(Car car) throws NoMoreSpaceException;

    Car pickCar(String ticket) throws InvalidTicketException;
}
