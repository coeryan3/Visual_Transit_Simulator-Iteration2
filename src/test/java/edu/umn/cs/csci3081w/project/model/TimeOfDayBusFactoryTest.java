package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TimeOfDayBusFactoryTest {
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
   * Testing strategy 1 bus generation.
   */
  @Test
  public void testGetStrategy1Bus() {
    Stop stop1 = new Stop(0, 44.972392, -93.243774);
    Stop stop2 = new Stop(1, 44.973580, -93.235071);
    Stop stop3 = new Stop(2, 44.975392, -93.226632);
    List<Stop> stopsIn = new ArrayList<Stop>();
    stopsIn.add(stop1);
    stopsIn.add(stop2);
    stopsIn.add(stop3);
    List<Double> distancesIn = new ArrayList<Double>();
    distancesIn.add(0.008784);
    distancesIn.add(0.008631);
    List<Double> probabilitiesIn = new ArrayList<Double>();
    probabilitiesIn.add(.15);
    probabilitiesIn.add(0.3);
    probabilitiesIn.add(.025);
    PassengerGenerator generatorIn = new RandomPassengerGenerator(probabilitiesIn, stopsIn);
    Route testRouteIn = new Route("testRouteIn", stopsIn, distancesIn, 3, generatorIn);
    List<Stop> stopsOut = new ArrayList<>();
    stopsOut.add(stop3);
    stopsOut.add(stop2);
    stopsOut.add(stop1);
    List<Double> distancesOut = new ArrayList<>();
    distancesOut.add(0.008631);
    distancesOut.add(0.008784);
    List<Double> probabilitiesOut = new ArrayList<>();
    probabilitiesOut.add(.025);
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.15);
    PassengerGenerator generatorOut = new RandomPassengerGenerator(probabilitiesOut, stopsOut);
    Route testRouteOut = new Route("testRouteIn", stopsOut, distancesOut, 3, generatorOut);
    TimeOfDayBusFactory busFactory = new TimeOfDayBusFactory();
    Bus bus = busFactory.getStrategy1Bus("TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(30, bus.getCapacity());
    Bus bus2 = busFactory.getStrategy1Bus("TestBus2", testRouteOut, testRouteIn, 1);
    assertEquals(60, bus2.getCapacity());
  }

  /**
   * Testing strategy 2 bus generation.
   */
  @Test
  public void testGetStrategy2Bus() {
    Stop stop1 = new Stop(0, 44.972392, -93.243774);
    Stop stop2 = new Stop(1, 44.973580, -93.235071);
    Stop stop3 = new Stop(2, 44.975392, -93.226632);
    List<Stop> stopsIn = new ArrayList<Stop>();
    stopsIn.add(stop1);
    stopsIn.add(stop2);
    stopsIn.add(stop3);
    List<Double> distancesIn = new ArrayList<Double>();
    distancesIn.add(0.008784);
    distancesIn.add(0.008631);
    List<Double> probabilitiesIn = new ArrayList<Double>();
    probabilitiesIn.add(.15);
    probabilitiesIn.add(0.3);
    probabilitiesIn.add(.025);
    PassengerGenerator generatorIn = new RandomPassengerGenerator(probabilitiesIn, stopsIn);
    Route testRouteIn = new Route("testRouteIn", stopsIn, distancesIn, 3, generatorIn);
    List<Stop> stopsOut = new ArrayList<>();
    stopsOut.add(stop3);
    stopsOut.add(stop2);
    stopsOut.add(stop1);
    List<Double> distancesOut = new ArrayList<>();
    distancesOut.add(0.008631);
    distancesOut.add(0.008784);
    List<Double> probabilitiesOut = new ArrayList<>();
    probabilitiesOut.add(.025);
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.15);
    PassengerGenerator generatorOut = new RandomPassengerGenerator(probabilitiesOut, stopsOut);
    Route testRouteOut = new Route("testRouteIn", stopsOut, distancesOut, 3, generatorOut);
    TimeOfDayBusFactory busFactory = new TimeOfDayBusFactory();
    Bus bus = busFactory.getStrategy2Bus("TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(60, bus.getCapacity());
    Bus bus2 = busFactory.getStrategy2Bus("TestBus2", testRouteOut, testRouteIn, 1);
    assertEquals(90, bus2.getCapacity());
  }

  /**
   * Testing strategy 3 bus generation.
   */
  @Test
  public void testGetStrategy3Bus() {
    Stop stop1 = new Stop(0, 44.972392, -93.243774);
    Stop stop2 = new Stop(1, 44.973580, -93.235071);
    Stop stop3 = new Stop(2, 44.975392, -93.226632);
    List<Stop> stopsIn = new ArrayList<Stop>();
    stopsIn.add(stop1);
    stopsIn.add(stop2);
    stopsIn.add(stop3);
    List<Double> distancesIn = new ArrayList<Double>();
    distancesIn.add(0.008784);
    distancesIn.add(0.008631);
    List<Double> probabilitiesIn = new ArrayList<Double>();
    probabilitiesIn.add(.15);
    probabilitiesIn.add(0.3);
    probabilitiesIn.add(.025);
    PassengerGenerator generatorIn = new RandomPassengerGenerator(probabilitiesIn, stopsIn);
    Route testRouteIn = new Route("testRouteIn", stopsIn, distancesIn, 3, generatorIn);
    List<Stop> stopsOut = new ArrayList<>();
    stopsOut.add(stop3);
    stopsOut.add(stop2);
    stopsOut.add(stop1);
    List<Double> distancesOut = new ArrayList<>();
    distancesOut.add(0.008631);
    distancesOut.add(0.008784);
    List<Double> probabilitiesOut = new ArrayList<>();
    probabilitiesOut.add(.025);
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.15);
    PassengerGenerator generatorOut = new RandomPassengerGenerator(probabilitiesOut, stopsOut);
    Route testRouteOut = new Route("testRouteIn", stopsOut, distancesOut, 3, generatorOut);
    TimeOfDayBusFactory busFactory = new TimeOfDayBusFactory();
    Bus bus = busFactory.getStrategy3Bus("TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(30, bus.getCapacity());
    Bus bus2 = busFactory.getStrategy3Bus("TestBus2", testRouteOut, testRouteIn, 1);
    assertEquals(60, bus2.getCapacity());
    Bus bus3 = busFactory.getStrategy3Bus("TestBus3", testRouteOut, testRouteIn, 1);
    assertEquals(90, bus3.getCapacity());
  }

  /**
   * Testing general bus creation method: ensuring use of correct generation
   * strategy depending on the date and time.
   */
  @Test
  public void testGetBus() {
    Stop stop1 = new Stop(0, 44.972392, -93.243774);
    Stop stop2 = new Stop(1, 44.973580, -93.235071);
    Stop stop3 = new Stop(2, 44.975392, -93.226632);
    List<Stop> stopsIn = new ArrayList<Stop>();
    stopsIn.add(stop1);
    stopsIn.add(stop2);
    stopsIn.add(stop3);
    List<Double> distancesIn = new ArrayList<Double>();
    distancesIn.add(0.008784);
    distancesIn.add(0.008631);
    List<Double> probabilitiesIn = new ArrayList<Double>();
    probabilitiesIn.add(.15);
    probabilitiesIn.add(0.3);
    probabilitiesIn.add(.025);
    PassengerGenerator generatorIn = new RandomPassengerGenerator(probabilitiesIn, stopsIn);
    Route testRouteIn = new Route("testRouteIn", stopsIn, distancesIn, 3, generatorIn);
    List<Stop> stopsOut = new ArrayList<>();
    stopsOut.add(stop3);
    stopsOut.add(stop2);
    stopsOut.add(stop1);
    List<Double> distancesOut = new ArrayList<>();
    distancesOut.add(0.008631);
    distancesOut.add(0.008784);
    List<Double> probabilitiesOut = new ArrayList<>();
    probabilitiesOut.add(.025);
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.15);
    PassengerGenerator generatorOut = new RandomPassengerGenerator(probabilitiesOut, stopsOut);
    Route testRouteOut = new Route("testRouteIn", stopsOut, distancesOut, 3, generatorOut);
    TimeOfDayBusFactory busFactory = new TimeOfDayBusFactory();
    LocalDateTime testTime = LocalDateTime.of(2020, 1, 2, 5, 00);
    Bus bus = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(30, bus.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 6, 00);
    Bus bus2 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(60, bus2.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 7, 00);
    Bus bus3 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(30, bus3.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 8, 00);
    Bus bus4 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(60, bus4.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 11, 00);
    Bus bus5 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(90, bus5.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 15, 00);
    Bus bus6 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(60, bus6.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 16, 00);
    Bus bus7 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(30, bus7.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 17, 00);
    Bus bus8 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(60, bus8.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 18, 00);
    Bus bus9 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(90, bus9.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 19, 00);
    Bus bus10 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(30, bus10.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 20, 00);
    Bus bus11 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(60, bus11.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 21, 00);
    Bus bus12 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(30, bus12.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 2, 23, 00);
    Bus bus13 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(30, bus13.getCapacity());
    testTime = LocalDateTime.of(2020, 1, 3, 3, 00);
    Bus bus14 = busFactory.getBus(testTime, "TestBus", testRouteOut, testRouteIn, 1);
    assertEquals(30, bus14.getCapacity());
  }

}
