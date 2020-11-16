package edu.umn.cs.csci3081w.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopDataTest {
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
  public void testConstructorNormal(){
    String id = "test";
    Position pos = new Position(0.0, 1.0);
    int numPeople = 6;
    StopData stopData = new StopData(id, pos, numPeople);
    assertEquals("test", stopData.getId());
    assertEquals(0.0, stopData.getPosition().getXcoordLoc());
    assertEquals(1.0, stopData.getPosition().getYcoordLoc());
    assertEquals(6, stopData.getNumPeople());
  }
}
