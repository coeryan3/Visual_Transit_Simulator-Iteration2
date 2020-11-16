package edu.umn.cs.csci3081w.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteDataTest {
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
   * Test that RouteData constructor works normally.
   */
  @Test
  public void testConstructorNormal(){
    RouteData routeData = new RouteData();
    assertEquals("", routeData.getId());
    assertEquals(0, routeData.getStops().size());
  }

}
