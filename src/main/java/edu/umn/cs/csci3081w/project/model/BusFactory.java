package edu.umn.cs.csci3081w.project.model;

import java.time.LocalDateTime;

public abstract class BusFactory {

  public abstract Bus getBus(LocalDateTime now, String name,
                             Route outbound, Route inbound, double speed);

}