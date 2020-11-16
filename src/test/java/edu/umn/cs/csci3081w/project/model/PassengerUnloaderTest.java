package edu.umn.cs.csci3081w.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
  public void testUnloadPassengers(){
    PassengerUnloader unloader = new PassengerUnloader();
    List<Passenger> pList = new ArrayList<Passenger>();
    Passenger p1 = PassengerFactory.generate(0, 10);
    Passenger p2 = PassengerFactory.generate(0, 10);
    Passenger p3 = PassengerFactory.generate(0, 10);
    pList.add(p1);
    pList.add(p2);
    pList.add(p3);
    Stop stop = new Stop(0,0,0);
    assertEquals(3, unloader.unloadPassengers(pList, stop));
  }
}
