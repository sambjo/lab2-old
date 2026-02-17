

import java.awt.*;

public class Volvo240 extends Car {
    private final static double trimFactor = 1.25;


    // assuming enginepower is fixed 100
    public Volvo240(Color color) {
        super(4, 100, color,"Volvo240");


    }
    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }}

