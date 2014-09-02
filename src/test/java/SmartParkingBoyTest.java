import org.junit.Test;

import java.util.HashSet;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by yushi on 9/2/14.
 */
public class SmartParkingBoyTest {
    private HashSet<ParkingLot> parkingLots;

    private SmartParkingBoy smartParkingBoy;

    @Test
    public void should_choose_much_space_parking_than_other_parkings() throws NoMoreSpaceException {
        //given
        Car car = new Car("Car1");
        parkingLots = new HashSet<ParkingLot>();
        ParkingLot parkingLot1 = new ParkingLot("ParkingLot1", 1);
        ParkingLot parkingLot2 = spy(new ParkingLot("ParkingLot2", 2));
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        smartParkingBoy =new SmartParkingBoy(parkingLots);

        //when
        smartParkingBoy.portCar(car);

        //then
        verify(parkingLot2).portCar(car);
    }
}
