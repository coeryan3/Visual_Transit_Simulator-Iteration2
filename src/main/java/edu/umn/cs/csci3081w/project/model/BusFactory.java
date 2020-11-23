package edu.umn.cs.csci3081w.project.model;

public class BusFactory {
  /**
   * Creates a bus according the random type.
   * @param type type of bus from 0-2. 0 for SmallBus, 1 for RegularBus, 2 for LargeBus
   * @param name name of bus
   * @param outbound outbound route for the bus to follow
   * @param inbound inbound route for the bus to follow
   * @param speed speed of the bus
   * @return
   */
  public Bus getBus(int type, String name, Route outbound, Route inbound, double speed) {
    if (type == 0) {
      return new SmallBus(name, outbound, inbound, speed);
    } else if (type == 1) {
      return new RegularBus(name, outbound, inbound, speed);
    } else if (type == 2) {
      return new LargeBus(name, outbound, inbound, speed);
    }
    return null;
  }
}
