import java.awt.*;
import java.util.Stack;


public class CarTransport extends Truck implements CanLoad<Car>{
    private boolean ramp_up;
    private double loaded_capacity; // antal 4-dörrar bilar lastade
    private final double maxCapacity; // antal 4-dörrar bilar som får plats
    private final Stack<Car> loadedCars = new Stack<>();

    public CarTransport(Color color, int maxCapacity){
        super(2,700, color,"Transporter");
        this.ramp_up = true;
        this.maxCapacity = maxCapacity;
    }

    // en bil med 2 dörrar räknas som en halv, en bil med 4 räknas som 1
    @Override
    public void LoadCar(Car car) {
        if (loaded_capacity + car.getNrDoors()/4.0 < maxCapacity){
            double dx = (this.getX() - car.getX());

            double dy = (this.getY() - car.getY());
            double distance = Math.sqrt(dx*dx + dy*dy);
            double max_distance=2.0;

            if (!ramp_up && distance<max_distance) {
                loadedCars.push(car);
                loaded_capacity += car.getNrDoors()/4.0;
                car.setX(this.getX());
                car.setY(this.getY());
        }}
    }

    @Override
    public Car DropCar(){
        if (!ramp_up && !loadedCars.isEmpty()) {
            Car mycar = loadedCars.pop();
            loaded_capacity -= mycar.getNrDoors()/4.0;
            int dir = this.getDirection();

            // 0 = Norr, 1 = öst, 2 = syd, 3 = väst
            if (dir == 0) { // direction norr, drop Syd
                mycar.setY(this.getY() - 1.0);
                mycar.setX(this.getX());
            }
            else if (dir == 1) { // direction öst, drop väst
                mycar.setX(this.getX() - 1.0);
                mycar.setY(this.getY());
            }
            else if (dir == 2) { // direction syd, drop norr
                mycar.setY(this.getY() + 1.0);
                mycar.setX(this.getX());
            }
            else if (dir == 3) { // direction väst, drop öst
                mycar.setX(this.getX() + 1.0);
                mycar.setY(this.getY());
            }
            return mycar;
        }
        return null;
    }

    @Override
    public void move() {
        super.move();
        for (Vehicle myCar : loadedCars) {
            myCar.setX(this.getX());
            myCar.setY(this.getY());
        }
    }


    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    protected boolean isStorageSafe() {
        return ramp_up;
    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            ramp_up = false;
        }
    }

    public void raiseRamp() {
        if (getCurrentSpeed() == 0) {
            ramp_up = true;
        }
    }

    public double getLoaded_capacity() {
        return loaded_capacity;
    }

    // för testning
    public String getTopLoadedModel() {
        if (loadedCars.isEmpty()){
            return "";
        }
        else{
            return loadedCars.peek().getModelName();
        }
    }
}
