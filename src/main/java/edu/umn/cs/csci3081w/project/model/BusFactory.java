package edu.umn.cs.csci3081w.project.model;

import java.util.Random;

public class BusFactory {

  private Random rand;
  private int state1 = 0;
  private int state2 = 0;
  private int state3 = 0;

  /**
   * Constructor for BusFactory.
   */
  public BusFactory() {
    this.rand = new Random();
  }

  /**
   * Creates a small bus.
   * @param name name of bus
   * @param outbound outbound route for the bus to follow
   * @param inbound inbound route for the bus to follow
   * @param speed speed of the bus
   * @return
   */
  public Bus getSmallBus(String name, Route outbound, Route inbound, double speed) {
    return new SmallBus(name, outbound, inbound, speed);
  }

  /**
   * Creates a bus according the random type.
   * @param name name of bus
   * @param outbound outbound route for the bus to follow
   * @param inbound inbound route for the bus to follow
   * @param speed speed of the bus
   * @return
   */
  public Bus getRandomBus(String name, Route outbound, Route inbound, double speed) {
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

  /**
   * Creates a bus according to Strategy 1, alternating between small and regular size buses.
   * @param name name of bus
   * @param outbound outbound route for the bus to follow
   * @param inbound inbound route for the bus to follow
   * @param speed speed of the bus
   * @return
   */
  public Bus getStrategy1Bus(String name, Route outbound, Route inbound, double speed) {
    if (this.state1 == 0) {
      this.state1 = 1;
      return new SmallBus(name, outbound, inbound, speed);
    } else if (this.state1 == 1) {
      this.state1 = 0;
      return new RegularBus(name, outbound, inbound, speed);
    }
    return null;
  }

  /**
   * Creates a bus according to Strategy 2, alternating between regular and large size buses.
   * @param name name of bus
   * @param outbound outbound route for the bus to follow
   * @param inbound inbound route for the bus to follow
   * @param speed speed of the bus
   * @return
   */
  public Bus getStrategy2Bus(String name, Route outbound, Route inbound, double speed) {
    if (this.state2 == 0) {
      this.state2 = 1;
      return new RegularBus(name, outbound, inbound, speed);
    } else if (this.state2 == 1) {
      this.state2 = 0;
      return new LargeBus(name, outbound, inbound, speed);
    }
    return null;
  }

  /**
   * Creates a bus according to Strategy 3, alternating between small, regular,
   * and large size buses.
   * @param name name of bus
   * @param outbound outbound route for the bus to follow
   * @param inbound inbound route for the bus to follow
   * @param speed speed of the bus
   * @return
   */
  public Bus getStrategy3Bus(String name, Route outbound, Route inbound, double speed) {
    if (this.state3 == 0) {
      this.state3 = 1;
      return new SmallBus(name, outbound, inbound, speed);
    } else if (this.state3 == 1) {
      this.state3 = 2;
      return new RegularBus(name, outbound, inbound, speed);
    } else if (this.state3 == 2) {
      this.state3 = 0;
      return new LargeBus(name, outbound, inbound, speed);
    }
    return null;
  }
}