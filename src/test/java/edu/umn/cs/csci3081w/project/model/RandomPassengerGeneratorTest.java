package edu.umn.cs.csci3081w.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class RandomPassengerGeneratorTest {
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
   * Tests that generatePassengers generates a reasonable amount of passengers.
   */
  @Test
  public void testGeneratePassengers(){
    List<Double> l = new ArrayList<Double>();
    List<Stop> stops = new ArrayList<Stop>();
    Stop stop1 = new Stop(0, 44.972392, -93.243774);
    Stop stop2 = new Stop(1, 44.973580, -93.235071);
    Stop stop3 = new Stop(2, 44.975392, -93.226632);
    stops.add(stop1);
    stops.add(stop2);
    stops.add(stop3);
    l.add(0.9);
    RandomPassengerGenerator rpg = new RandomPassengerGenerator(l, stops);
    assertTrue(44 > rpg.generatePassengers());
  }
}
