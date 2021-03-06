package edu.umn.cs.csci3081w.project.webserver;

import edu.umn.cs.csci3081w.project.model.Bus;
import edu.umn.cs.csci3081w.project.model.BusFactory;
import edu.umn.cs.csci3081w.project.model.RandomBusFactory;
import edu.umn.cs.csci3081w.project.model.Route;
import edu.umn.cs.csci3081w.project.model.Stop;
import edu.umn.cs.csci3081w.project.model.TimeOfDayBusFactory;
import java.time.LocalDateTime;
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
  private LocalDateTime currentDate;
  private Observer busObserved;
  private Observer stopObserved;

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
  }

  /**
   * Starts the simulation.
   * @param busStartTimingsParam start timings of bus.
   * @param numTimeStepsParam number of time steps.
   */
  public void start(List<Integer> busStartTimingsParam, int numTimeStepsParam) {
    currentDate = LocalDateTime.now();
    if (currentDate.getDayOfMonth() == 1 || currentDate.getDayOfMonth() == 15) {
      this.busFactory = new RandomBusFactory();
    } else {
      this.busFactory = new TimeOfDayBusFactory();
    }
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
   * Create a new bus.
   *
   * @param name parameter for the name of the bus
   * @param outbound parameter for outbound route
   * @param inbound parameter for inbound route
   * @param speed parameter for bus speed
   * @return created bus
   */
  public Bus createBus(String name, Route outbound, Route inbound, double speed) {
    return busFactory.getBus(currentDate, name, outbound, inbound, speed);
  }

  /**
   * Find the bus associated with the given id.
   * @param id bus id.
   * @return the bus that is to be observed as a Observer.
   */
  public Observer listenBus(String id) {
    for (int i = busses.size() - 1; i >= 0; i--) {
      if (busses.get(i).getBusData().getId().equals(id)) {
        busObserved = new BusObserver(busses.get(i));
      }
    }
    return busObserved;

  }

  public Observer getBusObserved() {
    return busObserved;
  }

  /**
   * Find the stop associated with the given id.
   * @param id stop id as a String and not an integer.
   * @return the stop that is to be observed as a Observer.
   */
  public Observer listenStop(String id) {
    List<Stop> stops;
    for (int i = 0; i < prototypeRoutes.size(); i++) {
      stops = prototypeRoutes.get(i).getStops();
      for (int j = 0; j < stops.size(); j++) {
        if (Integer.toString(stops.get(j).getId()).equals(id)) {
          stopObserved = new StopObserver(stops.get(j));
        }
      }
    }
    return stopObserved;
  }

  public Observer getStopObserved() {
    return stopObserved;
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
          currentDate = LocalDateTime.now();
          busses
              .add(createBus(String.valueOf(busId),
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