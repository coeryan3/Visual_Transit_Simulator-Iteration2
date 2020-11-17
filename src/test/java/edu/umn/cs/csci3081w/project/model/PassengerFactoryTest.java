package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerFactoryTest {
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
   * Testing passenger generation.
   */
  @Test
  public void testGenerate() {
    //expect destination = 2 given currStop = 0 and lastStop = 10
    Passenger p = PassengerFactory.generate(0, 10);
    assertEquals(2, p.getDestination());
  }

  /**
   * Testing name generation for passengers.
   */
  @Test
  public void testNameGeneration() {
    String name = PassengerFactory.nameGeneration();
    assertEquals("Goldy", name);
  }

}
