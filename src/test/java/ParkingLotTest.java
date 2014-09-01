import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;

/**
 * Created by yushi on 9/1/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParkingLotTest {
    private ParkingLot parkingLot;

    @Before
    public void setUp() {
        parkingLot = new ParkingLot("parkingLot", 2);
    }

    @Test
    public void should_return_ticket_when_has_space() throws Exception {
        //given

        //when
        String message = parkingLot.portCar(new Car("Car1"));

        //then
        assertNotNull(message);
    }

    @Test(expected = NoMoreSpaceException.class)
    public void should_throw_exception_when_has_no_space() throws Exception {
        //given
        parkingLot.portCar(new Car("Car1"));
        parkingLot.portCar(new Car("Car2"));
        //when
        parkingLot.portCar(new Car("Car1"));

        //then
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_exception_when_ticket_invalid() throws Exception {
        //given
        String tickets = "abc";

        //when
        parkingLot.pickCar(tickets);

        //then

    }

    @Test(expected = InvalidTicketException.class)
    public void shoul_throw_exception_when_has_no_ticket() throws Exception {
        //given

        //when
        parkingLot.pickCar(null);

        //then

    }

    @Test
    public void should_return_car_when_ticket_valid() throws Exception {
        //given
        Car car = new Car("Car1");
        String ticket = String.valueOf(car.getCarName().hashCode());
        parkingLot.portCar(car);

        //when
        Car pickCar = parkingLot.pickCar(ticket);

        //then
        assertNotNull(pickCar);
    }
}
