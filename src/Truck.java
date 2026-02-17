import java.awt.*;

public abstract class Truck extends Vehicle {

    public Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    // för att se om lastbilen kan köra eller ej
    protected abstract boolean isStorageSafe();

    // kan inte starta med lasten osäker (start ger speed 0.1)
    @Override
    public void startEngine() {
        if (isStorageSafe()) {
            super.startEngine();
        }
    }
    // kan inte gasa med lasten osäker
    @Override
    public void gas(double amount) {
        if (isStorageSafe()) {
            super.gas(amount);
        }
    }
}
