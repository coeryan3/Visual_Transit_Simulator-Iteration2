package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
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
    double x = 1.0;
    double y = 2.0;
    Position position = new Position(x, y);
    assertEquals(1.0, position.getXcoordLoc());
    assertEquals(2.0, position.getYcoordLoc());
    Position position1 = new Position();
    assertEquals(0.0, position1.getXcoordLoc());
    assertEquals(0.0, position1.getYcoordLoc());

  }
}
