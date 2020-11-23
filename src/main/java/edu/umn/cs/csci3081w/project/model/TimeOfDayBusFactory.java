package edu.umn.cs.csci3081w.project.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeOfDayBusFactory extends BusFactory {

  private int state1 = 0;
  private int state2 = 0;
  private int state3 = 0;
  private LocalTime fiveAM = LocalTime.of(5, 0);
  private LocalTime eightAM = LocalTime.of(8, 0);
  private LocalTime fourPM = LocalTime.of(16, 0);
  private LocalTime ninePM = LocalTime.of(21, 0);

  /**
   * Creates a bus according to Strategy 1, alternating between small and regular size buses.
   * @param name name of bus
   * @param outbound outbound route for the bus to follow
   * @param inbound inbound route for the bus to follow
   * @param speed speed of the bus
   * @return alternates between returning a SmallBus and a RegularBus
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
   * @return alternates between returning a RegularBus and a LargeBus
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
   * @return alternates between returning a SmallBus, a RegularBus, and a LargeBus
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

  /**
   * Create bus either randomly or using the correct time of day strategy,
   * depending on the day of the month.
   *
   * @param now parameter for the current local date and time
   * @param name parameter for the name of the bus
   * @param outbound parameter for outbound route
   * @param inbound parameter for inbound route
   * @param speed parameter for bus speed
   * @return created bus
   */
  public Bus getBus(LocalDateTime now, String name, Route outbound, Route inbound, double speed) {
    LocalTime currentTime = now.toLocalTime();
    if (currentTime.equals(fiveAM) || (currentTime.isAfter(fiveAM)
        && currentTime.isBefore(eightAM))) {
      return getStrategy1Bus(name, outbound, inbound, speed);
    } else if (currentTime.equals(eightAM) || (currentTime.isAfter(eightAM)
        && currentTime.isBefore(fourPM))) {
      return getStrategy2Bus(name, outbound, inbound, speed);
    } else if (currentTime.equals(fourPM) || (currentTime.isAfter(fourPM)
        && currentTime.isBefore(ninePM))) {
      return getStrategy3Bus(name, outbound, inbound, speed);
    } else {
      return new SmallBus(name, outbound, inbound, speed);
    }
  }
}
