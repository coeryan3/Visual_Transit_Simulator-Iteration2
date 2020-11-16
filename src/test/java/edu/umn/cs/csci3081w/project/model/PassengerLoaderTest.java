package edu.umn.cs.csci3081w.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
  public void testLoadPassenger(){
    PassengerLoader pLoader = new PassengerLoader();
    Passenger p1 = PassengerFactory.generate(0, 10);
    List<Passenger> pList = new ArrayList<Passenger>();
    assertTrue(pLoader.loadPassenger(p1, 1, pList));
    Passenger p2 = PassengerFactory.generate(0, 10);
    assertFalse(pLoader.loadPassenger(p1, 1, pList));
  }
}
