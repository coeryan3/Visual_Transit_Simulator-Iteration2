package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

public class UpdateCommand extends MyWebServerCommand {

  private VisualizationSimulator mySim;

  public UpdateCommand(VisualizationSimulator sim) {
    this.mySim = sim;
  }

  /**
   * Updates the state of the simulation.
   *
   * @param session current simulation session
   * @param command the update simulation command content
   * @param state the state of the simulation session
   */
  @Override
  public void execute(MyWebServerSession session, JsonObject command,
                      MyWebServerSessionState state) {
    mySim.update();
    if(mySim.getBusObserved() != null) {
      //updates BusObserved data on webserver display
      String busMessage = mySim.getBusObserved().getInfo();
      JsonObject jsonBus = new JsonObject();
      jsonBus.addProperty("command", "observeBus");
      jsonBus.addProperty("text", busMessage);
      session.sendJson(jsonBus);
    }

    if(mySim.getStopObserved() != null) {
      //updates stopObserved data on webserver display
      String stopMessage = mySim.getStopObserved().getInfo();
      JsonObject jsonStop = new JsonObject();
      jsonStop.addProperty("command", "observeStop");
      jsonStop.addProperty("text", stopMessage);
      session.sendJson(jsonStop);
    }
  }

}
