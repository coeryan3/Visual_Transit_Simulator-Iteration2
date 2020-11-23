package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerUnloaderTest {
  /**
   * Setup deterministic operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    PassengerFactory.DETERMINISTIC = true;
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 0;
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 0;
    RandomPassengerGenerator.DETERMINISTIC = true;
  }

  /**
   * Testing unloading a passenger.
   */
  @Test
  public void testUnloadPassengers() {
    PassengerUnloader unloader = new PassengerUnloader();
    List<Passenger> passengerList = new ArrayList<Passenger>();
    Passenger p1 = PassengerFactory.generate(0, 10);
    Passenger p2 = PassengerFactory.generate(0, 10);
    Passenger p3 = PassengerFactory.generate(0, 10);
    passengerList.add(p1);
    passengerList.add(p2);
    passengerList.add(p3);
    Stop stop = new Stop(2, 0, 0);
    assertEquals(1, unloader.unloadPassengers(passengerList, stop));
  }
}
