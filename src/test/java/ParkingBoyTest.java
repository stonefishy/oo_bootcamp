import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.util.HashSet;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by yushi on 9/1/14.
 */
public class ParkingBoyTest {
    private ParkingBoy parkingBoy;

    @Before
    public void setUp() {
        HashSet<ParkingLot> parkingLots = new HashSet<ParkingLot>();
        parkingLots.add(new ParkingLot("parkinglot1", 1));
        parkingLots.add(new ParkingLot("parkinglot2", 2));
        this.parkingBoy = new ParkingBoy(parkingLots);
    }

    @Ignore
    @Test(expected = NoMoreSpaceException.class)
    public void should_throw_exception_when_has_no_space() throws NoMoreSpaceException {
        //given
        ParkingBoy mockParkingBoy = mock(ParkingBoy.class);
        given(mockParkingBoy.hasCarSpace()).willReturn(false);

        //when
        parkingBoy.portCar(new Car("Car1"));

        //then
    }

    @Test(expected = NoMoreSpaceException.class)
    public void should_throw_exception_when_has_no_space2() throws NoMoreSpaceException {
        //given
        parkingBoy.portCar(new Car("Car1"));
        parkingBoy.portCar(new Car("Car2"));
        parkingBoy.portCar(new Car("Car3"));

        //when
        parkingBoy.portCar(new Car("Car4"));

        //then
    }

    @Test(expected = NoMoreSpaceException.class)
    public void should_throw_exception_when_has_no_space3() throws NoMoreSpaceException {
        //given
        final ParkingLot pl1 = mock(ParkingLot.class);

        HashSet<ParkingLot> parkingLots = new HashSet<ParkingLot>();
        parkingLots.add(pl1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        given(pl1.hasSpace()).willReturn(false);

        //when
        parkingBoy.portCar(new Car("Car1"));

        //then
    }


    @Test
    public void should_return_ticket_when_has_space() throws NoMoreSpaceException {
        //given
        Car car = new Car("Car1");
        String result = String.valueOf(car.getCarName().hashCode());

        //when
        String ticket = parkingBoy.portCar(car);
        //then
        assertThat(ticket).isEqualTo(result);
    }

    @Test
    public void should_return_car_when_ticket_valid() throws InvalidTicketException, NoMoreSpaceException {
        //given
        Car car = new Car("Car1");
        parkingBoy.portCar(car);
        String ticket = String.valueOf(car.getCarName().hashCode());

        //when
        Car pickCar = parkingBoy.pickCar(ticket);

        //then
        assertThat(pickCar).isEqualTo(car);
    }

    @Test
    public void should_return_ticket_when_one_parking_full_but_another_parking_has_space() throws NoMoreSpaceException {
        //given
        Car car = new Car("Car1");
        parkingBoy.portCar(new Car("Car2"));
        String result = String.valueOf(car.getCarName().hashCode());

        //when
        String ticket = parkingBoy.portCar(car);

        //then
        assertThat(ticket).isEqualTo(result);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_exception_when_ticket_invalid() throws NoMoreSpaceException, InvalidTicketException {
        //given
        String ticket = "adc";

        //when
        parkingBoy.portCar(new Car("Car1"));
        parkingBoy.pickCar(ticket);

        //then
    }
}
