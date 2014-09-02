import org.junit.Test;

import java.util.HashSet;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by yushi on 9/2/14.
 */
public class SuperParkingBoyTest {
    private HashSet<ParkingLot> parkingLots;
    private SuperParkingBoy superParkingBoy;

    @Test
    public void should_choose_max_space_rate() throws NoMoreSpaceException {
        //given
        Car car = new Car("Car1");
        parkingLots = new HashSet<ParkingLot>();
        ParkingLot parkingLot1 = new ParkingLot("ParkingLot1", 3);
        parkingLot1.portCar(new Car("CarA"));
        ParkingLot parkingLot2 = spy(new ParkingLot("ParkingLot2", 2));
        parkingLot2.portCar(new Car("CarB"));
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        superParkingBoy =new SuperParkingBoy(parkingLots);

        //when
        superParkingBoy.portCar(car);

        //then
        verify(parkingLot1).portCar(car);
    }
}
