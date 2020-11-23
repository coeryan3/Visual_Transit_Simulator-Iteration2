package edu.umn.cs.csci3081w.project.webserver;

import edu.umn.cs.csci3081w.project.model.Bus;

public class BusObserver implements Observer {

    private Bus bus;

    public BusObserver(Bus b){
        this.bus = b;
    }


    public String getID(){
        return bus.getBusData().getId();
    }

    /**
     * Gives the busObservers specific information as a string.
     * @return a String about the busObserver's position, id, and number of passengers and capacity.
     */
    public String getInfo(){
        String busInfo =
                "Bus " + this.getID() + System.lineSeparator()
                        + "-----------------------------" + System.lineSeparator()
                        + "* Position: (" + bus.getBusData().getPosition().getXcoordLoc() + ", "
                        + bus.getBusData().getPosition().getYcoordLoc() + ")" + System.lineSeparator()
                        + "* Passengers: " + bus.getBusData().getNumPassengers() + System.lineSeparator()
                        + "* Capacity: " + bus.getBusData().getCapacity() + System.lineSeparator()
                        + System.lineSeparator();
        return busInfo;
    }
    public void hello(){
        System.out.println("hello");
    }
}
