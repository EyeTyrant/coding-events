package org.launchcode.codingevents.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class EventCategory {

  @Id
  @GeneratedValue
  private int id;

  @NotBlank(message = "Must add a name")
  @Size(min=3, max=50, message = "Name length between 3-50 characters")
  private String name;

  public EventCategory(String name) {
    this.name = name;
  }

  public EventCategory() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }







}
