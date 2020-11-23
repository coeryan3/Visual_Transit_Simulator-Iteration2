package edu.umn.cs.csci3081w.project.model;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomBusFactory extends BusFactory {

  private Random rand;

  /**
   * Constructor for BusFactory.
   */
  public RandomBusFactory() {
    this.rand = new Random();
  }

  /**
   * Creates a bus of a random type.
   *
   * @param now current date and time locally
   * @param name name of bus
   * @param outbound outbound route for the bus to follow
   * @param inbound inbound route for the bus to follow
   * @param speed speed of the bus
   * @return returns a SmallBus, a RegularBus, or a LargeBus at random
   */
  public Bus getBus(LocalDateTime now, String name, Route outbound, Route inbound, double speed) {
    int type = rand.nextInt(3);
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
