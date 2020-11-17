package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerLoaderTest {
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
   * Testing loading a passenger.
   */
  @Test
  public void testLoadPassenger() {
    PassengerLoader passengerLoader = new PassengerLoader();
    Passenger p1 = PassengerFactory.generate(0, 10);
    List<Passenger> passengerList = new ArrayList<Passenger>();
    assertTrue(passengerLoader.loadPassenger(p1, 1, passengerList));
    Passenger p2 = PassengerFactory.generate(0, 10);
    assertFalse(passengerLoader.loadPassenger(p1, 1, passengerList));
  }
}
