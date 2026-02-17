public interface CanLoad<Model extends Car> {
    void LoadCar(Model car);
    Model DropCar();
    double getLoaded_capacity();
}

