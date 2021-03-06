package edu.umn.cs.csci3081w.project.webserver;

import edu.umn.cs.csci3081w.project.model.Stop;

public class StopObserver implements Observer {

  private Stop stop;

  public StopObserver(Stop s) {
    this.stop = s;
  }

  public String getID() {
    String id = Integer.toString(stop.getId());
    return id;
  }

  /**
   * Gives the stopObservers specific information as a string.
   *
   * @return a String about the stopObserver's position, id, and number of people.
   */
  public String getInfo() {
    String stopInfo =
        "Stop " + this.getID() + System.lineSeparator()
            + "-----------------------------" + System.lineSeparator()
            + "* Position: (" + stop.getLatitude() + ", "
            + stop.getLongitude() + ")" + System.lineSeparator()
            + "* Number of People: " + stop.getNumPassengersPresent() + System.lineSeparator();
    return stopInfo;
  }
}
