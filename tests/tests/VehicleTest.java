
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

public class VehicleTest {

    @Test
    public void testVolvoInitialSpeedIsZero() {
        Volvo240 volvo = new Volvo240(Color.BLACK);
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    public void testGasIncreasesSpeed() {
        Volvo240 volvo = new Volvo240(Color.RED);
        volvo.startEngine();
        double initialSpeed = volvo.getCurrentSpeed();
        volvo.gas(1.0);
        assertTrue(volvo.getCurrentSpeed() > initialSpeed);
    }

    @Test
    public void testBrakeDecreasesSpeed() {
        Volvo240 volvo = new Volvo240(Color.RED);
        volvo.startEngine(); // Speed becomes 0.1
        volvo.gas(0.7);
        double initialSpeed = volvo.getCurrentSpeed();
        volvo.brake(0.3);
        assertTrue(volvo.getCurrentSpeed() < initialSpeed);
    }

    @Test
    public void testMovementAndTurning() {
        Volvo240 volvo = new Volvo240(Color.BLUE);
        volvo.startEngine(); // startar i norr i 0,0

        // rör sig i norr
        volvo.move();
        assertTrue(volvo.getY() > 0, "bilen ska ha positiv y koordinat");

        // sväng höger och rör sig
        volvo.turnRight();
        volvo.move();
        assertTrue(volvo.getX() > 0, "bilen ska ha positiv x koordinat");

        volvo.turnLeft(); // mot norr
        volvo.turnLeft(); // mot väst
        volvo.move(); // rör sig
        volvo.move(); // nu ska x vara negativ
        assertTrue(volvo.getX() < 0, "bilen ska ha negativ x koordinat");
    }

    @Test
    public void testSaabTurboFactor() {
        Saab95 saab = new Saab95(Color.WHITE);
        saab.startEngine();

        saab.setTurboOff();
        double factorOff = saab.speedFactor();

        saab.setTurboOn();
        double factorOn = saab.speedFactor();

        assertTrue(factorOn > factorOff, "när turbo är på borde speedfactor öka");
    }
    @Test
    public void testNrDoors() {
        Saab95 saab = new Saab95(Color.WHITE);
        Volvo240 volvo = new Volvo240(Color.BLUE);
        assertEquals(2, saab.getNrDoors());
        assertEquals(4, volvo.getNrDoors());

    }
    @Test
    public void testColor() {
        Saab95 saab = new Saab95(Color.WHITE);
        Volvo240 volvo = new Volvo240(Color.BLUE);
        assertEquals(Color.WHITE, saab.getColor());
        assertEquals(Color.BLUE, volvo.getColor());
    }
    @Test
    public void testEnginePower() {
        Saab95 saab = new Saab95(Color.WHITE);
        Volvo240 volvo = new Volvo240(Color.BLUE);
        assertEquals(125, saab.getEnginePower());
        assertEquals(100, volvo.getEnginePower());
    }
    @Test
    public void testStartStopEngine() {
        Saab95 saab = new Saab95(Color.WHITE);
        saab.startEngine();

        assertEquals(0.1, saab.getCurrentSpeed());
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    public void testRepaintCar() {
        Saab95 saab = new Saab95(Color.WHITE);
        Color prev_color = saab.getColor();
        saab.setColor(Color.RED);

        assertNotSame(prev_color, saab.getColor());
    }

    @Test
    public void testModelNames() {
        Volvo240 volvo = new Volvo240(Color.BLACK);
        Saab95 saab = new Saab95(Color.RED);
        assertEquals("Volvo240", volvo.getModelName());
        assertEquals("Saab95", saab.getModelName());
    }

    @Test
    public void testInitialPosition() {
        Volvo240 volvo = new Volvo240(Color.BLUE);
        assertEquals(0, volvo.getX(), "x ska vara 0");
        assertEquals(0, volvo.getY(), "y ska vara 0");
    }

    @Test
    public void testGasInvalidAmount() {
        Volvo240 volvo = new Volvo240(Color.BLUE);
        volvo.startEngine();
        double speedBefore = volvo.getCurrentSpeed();
        volvo.gas(2.0); // gasa för mycket
        assertEquals(speedBefore, volvo.getCurrentSpeed(), "hastighet ska inte ändras när man gasar för mycket");
        volvo.gas(-1); // gasa för lite
        assertEquals(speedBefore, volvo.getCurrentSpeed(), "hastighet ska inte ändras när man gasar för lite");
    }

    @Test
    public void testBrakeInvalidAmount() {
        Volvo240 volvo = new Volvo240(Color.BLUE);
        volvo.startEngine();
        double speedBefore = volvo.getCurrentSpeed();
        volvo.brake(2.0); // bromsa för mycket
        assertEquals(speedBefore, volvo.getCurrentSpeed(), "hastighet ska inte ändras när man bromsar för mycket");
        volvo.brake(-1); // bromsa för litet
        assertEquals(speedBefore, volvo.getCurrentSpeed(), "hastighet ska inte ändras när man bromsar för lite");
    }

    @Test
    public void testGasWithoutStartingEngine() {
        Volvo240 volvo = new Volvo240(Color.BLACK);
        volvo.gas(1.0);
        assertEquals(0, volvo.getCurrentSpeed(), "ska inte gå att gasa när motorn är av");
    }

    @Test
    public void testMoveInAllDirections() {
        Volvo240 volvo = new Volvo240(Color.BLACK);
        volvo.startEngine(); // så currentspeed = 0.1

        // norrut
        volvo.move();
        assertEquals(0.1, volvo.getY());
        assertEquals(0, volvo.getX());

        // österut
        volvo.turnRight();
        volvo.move();
        assertEquals(0.1, volvo.getX());
        assertEquals(0.1, volvo.getY());

        // söderut
        volvo.turnRight();
        volvo.move();
        assertEquals(0, volvo.getY());
        assertEquals(0.1, volvo.getX());

        // västerut
        volvo.turnRight();
        volvo.move();
        assertEquals(0, volvo.getX());
        assertEquals(0, volvo.getY());
    }

    @Test
    public void testBrakeAtZeroSpeed() {
        Volvo240 volvo = new Volvo240(Color.BLACK);
        volvo.stopEngine();
        volvo.brake(1.0);
        assertEquals(0, volvo.getCurrentSpeed(), "bromsa ska inte göra så man åker baklänges");
    }
    @Test
    public void testTipValueTooHigh() {
        Scania scania = new Scania(Color.BLACK);
        scania.tip(100);
        assertEquals(70, scania.getTipped(), "max 70 grader");
    }

    @Test
    public void testTipValueTooLow() {
        Scania scania = new Scania(Color.BLACK);
        scania.tip(-10);
        assertEquals(0, scania.getTipped(), "min 0 grader");
    }

    @Test
    public void testTipValue() {
        Scania scania = new Scania(Color.BLACK);
        scania.tip(10); // = 10
        scania.tip(10); // = 20
        scania.tip(-5); // = 15
        assertEquals(15, scania.getTipped());
    }

    @Test
    public void cannotTipWhileMoving() {
        Scania scania = new Scania(Color.BLACK);
        scania.startEngine();
        scania.gas(0.5);
        scania.tip(10);
        assertEquals(0, scania.getTipped());
    }

    @Test
    public void cannotStartWhileTipped() {
        Scania scania = new Scania(Color.BLACK);
        scania.tip(20);
        scania.startEngine(); // speed= 0.1
        scania.move(); // in y direction
        assertEquals(0, scania.getY());
    }

    @Test
    public void cannotMoveWhileTipped() {
        Scania scania = new Scania(Color.BLACK);
        scania.startEngine(); // speed= 0.1
        scania.brake(1); //speed=0
        scania.tip(20);
        scania.gas(0.5);
        scania.move(); // in y direction
        assertEquals(0, scania.getY());
    }

    @Test
    public void testRampCannotLowerWhileMoving() {
        CarTransport transport = new CarTransport(Color.GRAY, 6);
        transport.startEngine();
        transport.gas(1.0);

        transport.lowerRamp();
        assertTrue(transport.isStorageSafe(), "rampen ska vara uppe dvs safe när transporter kör");
    }

    @Test
    public void testCannotLoadWhileRampUp() {
        CarTransport transport = new CarTransport(Color.GRAY, 6); // ramp uppe vid initiering
        Volvo240 volvo = new Volvo240(Color.BLACK);
        transport.LoadCar(volvo);
        assertEquals(0.0, transport.getLoaded_capacity(), "kan inte fylla upp bil när rampen är uppe");
    }

    @Test
    public void testLoadAndMoveUpdatesCarPosition() {
        CarTransport transport = new CarTransport(Color.GRAY, 6);
        Volvo240 volvo = new Volvo240(Color.BLACK);
        transport.lowerRamp();
        transport.LoadCar(volvo);
        transport.raiseRamp();
        transport.startEngine();
        transport.gas(1.0);
        transport.move();

        assertEquals(transport.getX(), volvo.getX(), "bil X ska matcha Transport X");
        assertEquals(transport.getY(), volvo.getY(), "bil Y ska matcha Transport Y");
    }

    @Test
    public void testFILOUnloadingOrder() {
        CarTransport transport = new CarTransport(Color.GRAY, 6);
        Volvo240 volvo = new Volvo240(Color.BLACK);
        Saab95 saab = new Saab95(Color.RED);

        transport.lowerRamp();
        transport.LoadCar(volvo); // först
        transport.LoadCar(saab);  // sist

        transport.DropCar(); // saab droppas, volvo är kvar
        assertEquals("Volvo240", transport.getTopLoadedModel());
    }


    @Test
    public void testLoadingDistance() {
        CarTransport transport = new CarTransport(Color.GRAY, 6);
        Volvo240 volvo = new Volvo240(Color.BLACK);

        // flytta volvo långt bort
        volvo.startEngine();
        for(int i=0; i<100; i++) volvo.move();
        transport.lowerRamp();
        transport.LoadCar(volvo);

        assertEquals(0.0, transport.getLoaded_capacity(), "kan inte lasta bil långt bort");
    }


    @Test
    public void testWorkshopCapacity() {
        Workshop<Car> shop = new Workshop<>(1);
        Volvo240 volvo = new Volvo240(Color.BLACK);
        Saab95 saab = new Saab95(Color.RED);

        shop.LoadCar(volvo);
        shop.LoadCar(saab); // Denna ska inte få plats

        assertEquals(1, shop.getLoaded_capacity());
    }

    @Test
    public void testDropCarReturnsCorrectObject() {
        Workshop<Saab95> saabShop = new Workshop<>(2);
        Saab95 saab = new Saab95(Color.WHITE);
        saabShop.LoadCar(saab);

        Saab95 returned = saabShop.DropCar();

        assertSame(saab, returned, "Bilen som hämtas ut ska vara samma objekt som lämnades in");
    }

    @Test
    public void testCompileTimeSafety() {
        Workshop<Saab95> saabShop = new Workshop<>(2);
        Saab95 mySaab = new Saab95(Color.WHITE);
        saabShop.LoadCar(mySaab);
        //utkommentera detta så ser man att det blir rött
        //saabShop.LoadCar(new Volvo240(Color.BLACK));
        // ^ Compile time error, går ej att köra i IntelliJ
    }

}
//


