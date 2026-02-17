import java.util.ArrayList;

public class Workshop<Model extends Car> implements CanLoad<Model>{

    private final ArrayList<Model> loadedCars = new ArrayList<>();
    private final int maxCapacity;
    public Workshop(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void LoadCar(Model car) {
        if (maxCapacity > loadedCars.size()) {
            loadedCars.add(car);
        }
    }

    @Override
    public Model DropCar() {
        Model car = loadedCars.getLast();
        loadedCars.removeLast();
        return car;
    }

    @Override
    public double getLoaded_capacity() {
        return loadedCars.size();
    }
}
