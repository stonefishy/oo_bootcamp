import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by yushi on 9/3/14.
 */
public class ParkingManagerTest {
    private HashSet<ParkingLot> parkingLots;

    private Car car;

    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;

    @Before
    public void setUp(){
        car = new Car("Car1");
        parkingLots = new HashSet<ParkingLot>();
        parkingLot1 = spy(new ParkingLot("ParkingLot2", 2));
        parkingLot2 = new ParkingLot("ParkingLot1", 1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
    }
    @Test
    public void should_park_car_when_no_other_parking() throws NoMoreSpaceException {
        //given
        ParkingManager pm = new ParkingManager(parkingLots);

        //when
        pm.portCar(car);

        //then
        verify(parkingLot1).portCar(car);
    }

    @Test
    public void should_park_car_by_other_parking_man() throws NoMoreSpaceException {
        //given
        HashSet<ParkingLot> pls = new HashSet<ParkingLot>();
        ParkingLot pl = new ParkingLot("ParkingLot", 1);
        pls.add(pl);
        ParkingPeople pp = spy(new ParkingBoy(pls));
        ParkingManager pm = new ParkingManager(parkingLots);

        //when
        pm.portCar(car);

        //then
        verify(pp).portCar(car);
    }

    @Test
    public void should_pick_car_by_himself() throws NoMoreSpaceException, InvalidTicketException {
        //given
        ParkingManager pm = new ParkingManager(parkingLots);
        String ticket = String.valueOf(car.getCarName().hashCode());
        pm.portCar(car);

        //when
        pm.pickCar(ticket);

        //then
        verify(parkingLot1).pickCar(ticket);
    }

    @Test
    public void should_pick_car_by_other_parking_man() throws NoMoreSpaceException, InvalidTicketException {
        //given
        HashSet<ParkingLot> pls = new HashSet<ParkingLot>();
        ParkingLot pl = new ParkingLot("ParkingLot", 1);
        pls.add(pl);
        ParkingPeople pp = spy(new ParkingBoy(pls));
        ParkingManager pm = new ParkingManager(parkingLots);
        pm.addParkingMan(pp);
        String ticket = String.valueOf(car.getCarName().hashCode());
        pm.portCar(car);

        //when
        pm.pickCar(ticket);
        //then
        verify(pp).pickCar(ticket);
    }
}
