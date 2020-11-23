package edu.umn.cs.csci3081w.project.webserver;

import edu.umn.cs.csci3081w.project.model.Bus;
import edu.umn.cs.csci3081w.project.model.BusFactory;
import edu.umn.cs.csci3081w.project.model.Route;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VisualizationSimulator {

  private WebInterface webInterface;
  private ConfigManager configManager;
  private List<Integer> busStartTimings;
  private List<Integer> timeSinceLastBus;
  private int numTimeSteps = 0;
  private int simulationTimeElapsed = 0;
  private List<Route> prototypeRoutes;
  private List<Bus> busses;
  private int busId = 1000;
  private boolean paused = false;
  private BusFactory busFactory;
  private LocalTime currentTime;
  private LocalTime fiveAM = LocalTime.of(5, 0);
  private LocalTime eightAM = LocalTime.of(8, 0);
  private LocalTime fourPM = LocalTime.of(16, 0);
  private LocalTime ninePM = LocalTime.of(21, 0);


  /**
   * Constructor for Simulation.
   * @param webI MWS object
   * @param configM config object
   */
  public VisualizationSimulator(MyWebServer webI, ConfigManager configM) {
    this.webInterface = webI;
    this.configManager = configM;
    //initialize these lists so that we do not get a null pointer
    this.busStartTimings = new ArrayList<Integer>();
    this.prototypeRoutes = new ArrayList<Route>();
    this.busses = new ArrayList<Bus>();
    this.timeSinceLastBus = new ArrayList<Integer>();
    this.busFactory = new BusFactory();
  }

  /**
   * Starts the simulation.
   * @param busStartTimingsParam start timings of bus
   * @param numTimeStepsParam number of time steps
   */
  public void start(List<Integer> busStartTimingsParam, int numTimeStepsParam) {
    this.busStartTimings = busStartTimingsParam;
    this.numTimeSteps = numTimeStepsParam;
    for (int i = 0; i < busStartTimings.size(); i++) {
      this.timeSinceLastBus.add(i,  0);
    }
    simulationTimeElapsed = 0;
    prototypeRoutes = configManager.getRoutes();
    for (int i = 0; i < prototypeRoutes.size(); i++) {
      prototypeRoutes.get(i).report(System.out);
      prototypeRoutes.get(i).updateRouteData();
      webInterface.updateRoute(prototypeRoutes.get(i).getRouteData(), false);
    }
  }

  /**
   * Toggles the pause state of the simulation.
   */
  public void togglePause() {
    paused = !paused;
  }

  /**
   * Create bus randomly.
   *
   * @param name parameter for the name of the bus
   * @param outbound parameter for outbound route
   * @param inbound parameter for inbound route
   * @param speed parameter for bus speed
   * @return created bus
   */
  public Bus createRandomBus(String name, Route outbound, Route inbound, double speed) {
    return busFactory.getRandomBus(name, outbound, inbound, speed);
  }

  /**
   * Create bus using correct strategy according to the time of day.
   *
   * @param name parameter for the name of the bus
   * @param outbound parameter for outbound route
   * @param inbound parameter for inbound route
   * @param speed parameter for bus speed
   * @return created bus
   */
  public Bus createTimeOfDayBus(String name, Route outbound, Route inbound, double speed) {
    if (currentTime.equals(fiveAM) || (currentTime.isAfter(fiveAM)
        && currentTime.isBefore(eightAM))) {
      return busFactory.getStrategy1Bus(name, outbound, inbound, speed);
    } else if (currentTime.equals(eightAM) || (currentTime.isAfter(eightAM)
        && currentTime.isBefore(fourPM))) {
      return busFactory.getStrategy2Bus(name, outbound, inbound, speed);
    } else if (currentTime.equals(fourPM) || (currentTime.isAfter(fourPM)
        && currentTime.isBefore(ninePM))) {
      return busFactory.getStrategy3Bus(name, outbound, inbound, speed);
    } else {
      return busFactory.getSmallBus(name, outbound, inbound, speed);
    }
  }

  /**
   * Updates the simulation at each step.
   */
  public void update() {
    if (!paused) {
      simulationTimeElapsed++;
      System.out.println("~~~~The simulation time is now"
          + " at time step "
          + simulationTimeElapsed + "~~~~");
      // Check if we need to generate new busses
      for (int i = 0; i < timeSinceLastBus.size(); i++) {
        // Check if we need to make a new bus
        if (timeSinceLastBus.get(i) <= 0) {
          Route outbound = prototypeRoutes.get(2 * i);
          Route inbound = prototypeRoutes.get(2 * i + 1);
          currentTime = LocalTime.now();
          busses
              .add(createRandomBus(String.valueOf(busId),
                  outbound.shallowCopy(), inbound.shallowCopy(), 1));
          busId++;
          timeSinceLastBus.set(i, busStartTimings.get(i));
          timeSinceLastBus.set(i, timeSinceLastBus.get(i) - 1);
        } else {
          timeSinceLastBus.set(i, timeSinceLastBus.get(i) - 1);
        }
      }
      // Update busses
      for (int i = busses.size() - 1; i >= 0; i--) {
        busses.get(i).update();
        if (busses.get(i).isTripComplete()) {
          webInterface.updateBus(busses.get(i).getBusData(), true);
          busses.remove(i);
          continue;
        }
        webInterface.updateBus(busses.get(i).getBusData(), false);
        busses.get(i).report(System.out);
      }
      // Update routes
      for (int i = 0; i < prototypeRoutes.size(); i++) {
        prototypeRoutes.get(i).update();
        webInterface.updateRoute(prototypeRoutes.get(i).getRouteData(), false);
        prototypeRoutes.get(i).report(System.out);
      }
    }
  }
}
