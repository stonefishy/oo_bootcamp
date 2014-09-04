import java.io.PrintStream;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by yushi on 9/4/14.
 */
public class ParkingReporter {
    private PrintStream printStream;

    public ParkingReporter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printReport(ParkingManager parkingManager) {
        parkingManager.printReport(printStream);
        for (ParkingPeople parkingPeople : parkingManager.getParkingPeoples()) {
            parkingPeople.printReport(printStream);
        }
    }

    public static void main(String[] args) throws NoMoreSpaceException, ClassNotFoundException {
        HashSet<ParkingLot> parkingLotsA = createParkingLots("A");
        HashSet<ParkingLot> parkingLotsB = createParkingLots("B");
        HashSet<ParkingLot> parkingLotsC =createParkingLots("C");
        HashSet<ParkingLot> parkingLotsD = createParkingLots("D");
        ParkingBoy pb = new ParkingBoy("ParkingBoyD",parkingLotsD);
        SmartParkingBoy spb = new SmartParkingBoy("SmartParkingBoyC", parkingLotsC);
        SuperParkingBoy sspb = new SuperParkingBoy("SuperParkingBoyB", parkingLotsB);
        ParkingManager pm = new ParkingManager("ParkingManagerA", parkingLotsA);
        pm.addParkingMan(sspb);
        pm.addParkingMan(spb);
        pm.addParkingMan(pb);

        new ParkingReporter(System.out).printReport(pm);
    }

    private static HashSet<ParkingLot> createParkingLots(String suffix) throws ClassNotFoundException, NoMoreSpaceException {
        HashSet<ParkingLot> parkingLots = new HashSet<ParkingLot>();
        for(int i = 0; i < new Random().nextInt(5); i++){
            ParkingLot parking = new ParkingLot(String.format("ParkingLot_%s",suffix),5 + new Random().nextInt(5));

            for(int j = 0; j < new Random().nextInt(5); j++){
                parking.portCar(new Car(String.format("Car_%s",suffix)));
            }
            parkingLots.add(parking);
        }

        return parkingLots;
    }
}
