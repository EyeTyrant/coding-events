package org.launchcode.codingevents.models;

import javax.validation.constraints.*;
import java.util.Objects;

public class Event {

  private int id;
  private static int nextId = 1;

  @NotBlank(message = "Must add a name")
  @Size(min=3, max=50, message = "Name length between 3-50 characters")
  private String name;
  @Size(max=500, message = "Description too long")
  private String description;
  @NotBlank(message = "Must add email")
  @Email(message="Invalid email.")
  private String contactEmail;
  @NotNull(message = "Must not be blank")
  @NotBlank(message = "Must add a location")
  @Size(min=3, max=50, message = "Location length between 3-50 characters")
  private String location;
  @Positive(message = "Must have at least 1 attendee")
  private int attendees;
  @NotBlank(message = "Must register to attend")
  private String regReq;

  private EventType type;

  public Event(String name, String description, String contactEmail, String location, EventType type, int attendees, String regReq) {
    this();
    this.name = name;
    this.description = description;
    this.contactEmail = contactEmail;
    this.location = location;
    this.type = type;
    this.attendees = attendees;
    this.regReq = regReq;
  }

  public Event() {
    this.id = nextId;
    nextId++;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public EventType getType() {
    return type;
  }

  public void setType(EventType type) {
    this.type = type;
  }

  public String getRegReq() {
    return regReq;
  }

  public void setRegReq(String regReq) {
    this.regReq = regReq;
  }

  public int getAttendees() {
    return attendees;
  }

  public void setAttendees(int attendees) {
    this.attendees = attendees;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Event)) return false;
    Event event = (Event) o;
    return getId() == event.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
