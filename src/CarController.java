

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    Workshop<Volvo240> volvoWorkshop = new Workshop<Volvo240>(10);
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.vehicles.add(new Volvo240(Color.BLACK));
        cc.vehicles.add(new Saab95(Color.BLACK));
        cc.vehicles.add(new Scania(Color.BLACK));
        int i=0;
        for (Vehicle vehicle : cc.vehicles) {
            vehicle.setX(300);
            vehicle.setY(i);
            i = i+100;
        }

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {
                vehicle.move();
                int x = (int) Math.round(vehicle.getX());
                int y = (int) Math.round(vehicle.getY());
                if (x > 800 || x < 0 || y > 500 || y < 0) {
                    vehicle.turnLeft();
                    vehicle.turnLeft();
                    vehicle.move(); // flytta bort från "danger zone"
                    vehicle.stopEngine();
                    vehicle.startEngine();
                }
                frame.drawPanel.moveit(vehicle,x, y);
                //repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles
        ) {
            vehicle.gas(gas);
        }
    }

    void startEngine() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    void stopEngine() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    void brake(int amount) {
        for (Vehicle vehicle : vehicles) {
            vehicle.brake((double) amount / 100);
        }
    }

    void turboOn() {
        for (Vehicle vehicle: vehicles) {
            if (vehicle instanceof hasTurbo) {
                ((hasTurbo) vehicle).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Vehicle car : vehicles) {
            if (car instanceof hasTurbo) {
                ((hasTurbo) car).setTurboOff();
            }
        }
    }

    // koppla på detta till amount knappen
    void liftBed(int amount) {
        for (Vehicle vehicle: vehicles)
        { if (vehicle instanceof TipAble)
            ((TipAble) vehicle).tip(amount);
        }
    }

    void lowerBed(int amount) {
        for (Vehicle vehicle: vehicles)
        { if (vehicle instanceof TipAble)
            ((TipAble) vehicle).tip(-amount);
        }
    }

}