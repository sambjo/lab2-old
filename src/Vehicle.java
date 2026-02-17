import java.awt.*;


// private på det som användaren inte behöver känna till
// public på det som användaren behöver kunna känna till / göra t.ex. starta/stoppa, ändra färg osv
// protected på det som subklasserna ska skriva över och användare ej känna till
// final på immutable

public abstract class Vehicle implements src.Movable {

    private final int nrDoors;
    private final double enginePower;
    private double currentSpeed;
    private Color color;
    private final String modelName;
    private double x;
    private double y;
    private int direction;
    private boolean started;

    public Vehicle(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.x = 0;
        this.y = 0;
        this.direction = 0; // 0 = norr, 1= öst, 2=syd, 3=väst
        this.started =false;
        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){

        currentSpeed = 0.1;
        this.started = true;
    }

    public void stopEngine(){

        currentSpeed = 0;
        this.started = false;
    }


    protected abstract double speedFactor();

    private void incrementSpeed(double amount){
        if (this.started){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);}
    }
    private void decrementSpeed(double amount){
        // har ingen check här, när bilen är av är speed 0, då kommer math.max ge 0
        // en bil kan också bromsa även om motorn är av
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    public void gas(double amount){
        if (amount >= 0 && amount <= 1) {
            incrementSpeed(amount);
        }
    }
    public void brake(double amount){
        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        }
    }

    public void move() {
        if (this.direction == 0) { //norr
            this.y +=  this.getCurrentSpeed();
        } else if (this.direction == 1) { //öst
            this.x += this.getCurrentSpeed();
        } else if (this.direction == 2) { //syd
            this.y -=  this.getCurrentSpeed();
        } else{ //väst
            this.x -= this.getCurrentSpeed();
        }
    }


    public void turnLeft() {
        this.direction = (this.direction +3) % 4;
    }


    public void turnRight() {
        this.direction = (this.direction +1) %4;
    }

    public double getY() {
        return this.y;
    }

    public double getX() {
        return this.x;
    }

    protected void setY(double value){
        this.y = value;
    }
    protected void setX(double value){
        this.x = value;
    }
//
    public String getModelName() {
        return this.modelName;
    }

    public int getDirection() {
        return direction;
    }
}