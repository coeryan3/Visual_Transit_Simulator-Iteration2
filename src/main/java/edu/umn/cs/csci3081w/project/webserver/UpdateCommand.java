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

    //updated BusObserved data on webserver display
    String message = mySim.getBusObserved().getInfo();
    JsonObject json = new JsonObject();
    json.addProperty("command", "observeBus");
    json.addProperty("text", message);
    session.sendJson(json);
  }

}
