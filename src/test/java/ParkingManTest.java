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
public class ParkingManTest {
    private ParkingMan parkingMan;

    @Before
    public void setUp() {
        HashSet<ParkingLot> parkingLots = new HashSet<ParkingLot>();
        parkingLots.add(new ParkingLot("parkinglot1", 1));
        parkingLots.add(new ParkingLot("parkinglot2", 2));
        this.parkingMan = new ParkingMan(parkingLots);
    }

    @Ignore
    @Test(expected = NoMoreSpaceException.class)
    public void should_throw_exception_when_has_no_space() throws NoMoreSpaceException {
        //given
        ParkingMan mockParkingMan = mock(ParkingMan.class);
        given(mockParkingMan.hasCarSpace()).willReturn(false);

        //when
        parkingMan.portCar(new Car("Car1"));

        //then
    }

    @Test(expected = NoMoreSpaceException.class)
    public void should_throw_exception_when_has_no_space2() throws NoMoreSpaceException {
        //given
        parkingMan.portCar(new Car("Car1"));
        parkingMan.portCar(new Car("Car2"));
        parkingMan.portCar(new Car("Car3"));

        //when
        parkingMan.portCar(new Car("Car4"));

        //then
    }

    @Test(expected = NoMoreSpaceException.class)
    public void should_throw_exception_when_has_no_space3() throws NoMoreSpaceException {
        //given
        final ParkingLot pl1 = mock(ParkingLot.class);

        HashSet<ParkingLot> parkingLots = new HashSet<ParkingLot>();
        parkingLots.add(pl1);
        ParkingMan parkingMan = new ParkingMan(parkingLots);
        given(pl1.hasSpace()).willReturn(false);

        //when
        parkingMan.portCar(new Car("Car1"));

        //then
    }


    @Test
    public void should_return_ticket_when_has_space() throws NoMoreSpaceException {
        //given
        Car car = new Car("Car1");
        String result = String.valueOf(car.getCarName().hashCode());

        //when
        String ticket = parkingMan.portCar(car);
        //then
        assertThat(ticket).isEqualTo(result);
    }

    @Test
    public void should_return_car_when_ticket_valid() throws InvalidTicketException, NoMoreSpaceException {
        //given
        Car car = new Car("Car1");
        parkingMan.portCar(car);
        String ticket = String.valueOf(car.getCarName().hashCode());

        //when
        Car pickCar = parkingMan.pickCar(ticket);

        //then
        assertThat(pickCar).isEqualTo(car);
    }

    @Test
    public void should_return_ticket_when_one_parking_full_but_another_parking_has_space() throws NoMoreSpaceException {
        //given
        Car car = new Car("Car1");
        parkingMan.portCar(new Car("Car2"));
        String result = String.valueOf(car.getCarName().hashCode());

        //when
        String ticket = parkingMan.portCar(car);

        //then
        assertThat(ticket).isEqualTo(result);
    }

    @Test(expected = InvalidTicketException.class)
    public void should_throw_exception_when_ticket_invalid() throws NoMoreSpaceException, InvalidTicketException {
        //given
        String ticket = "adc";

        //when
        parkingMan.portCar(new Car("Car1"));
        parkingMan.pickCar(ticket);

        //then
    }
}
