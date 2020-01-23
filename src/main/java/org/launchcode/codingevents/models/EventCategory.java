package org.launchcode.codingevents.models;


import javax.persistence.Entity;

@Entity
public class EventCategory {

  private int id;
  private String name;


  public EventCategory(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }







}
