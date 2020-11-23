package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusDataTest {
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
   * Testing state after using constructor.
   */
  @Test
  public void testConstructorNormal() {
    String id = "test";
    Position pos = new Position(0.0, 1.0);
    int numPassengers = 0;
    int capacity = 30;
    BusData busData = new BusData(id, pos, numPassengers, capacity);
    assertEquals("test", busData.getId());
    assertEquals(0.0, busData.getPosition().getXcoordLoc());
    assertEquals(1.0, busData.getPosition().getYcoordLoc());
    assertEquals(0, busData.getNumPassengers());
    assertEquals(30, busData.getCapacity());
  }
}
