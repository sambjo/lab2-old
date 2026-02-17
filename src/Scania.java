import java.awt.*;

public class Scania extends Truck implements TipAble{
    private double tipped;

    public Scania(Color color){
        super(2,770,color,"Scania");
        this.tipped = 0;}

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    protected boolean isStorageSafe() {
        return this.tipped == 0;
    }

    public void tip(double degree){
        if (getCurrentSpeed() == 0) {
            if (this.tipped + degree > 70) {
                this.tipped = 70;}
            else if (this.tipped + degree <0) {
                this.tipped = 0;
            }
            else {
                this.tipped = this.tipped + degree;

    }}
    }

    public double getTipped() {
        return tipped;
    }
}

