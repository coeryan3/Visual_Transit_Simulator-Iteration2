package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

public class ListenBusCommand extends MyWebServerCommand{
    private VisualizationSimulator mySim;

    public ListenBusCommand(VisualizationSimulator sim) {
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
        String id = command.get("id").getAsString();

        String message = mySim.listenBus(id).getInfo();
        JsonObject json = new JsonObject();
        json.addProperty("command", "observeBus");
        json.addProperty("text", message);
        session.sendJson(json);
    }
}
