package org.launchcode.codingevents.models;


import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
public class EventDetails extends AbstractEntity {

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


  public EventDetails(String description, String contactEmail, String location, int attendees, String regReq) {
    this.description = description;
    this.contactEmail = contactEmail;
    this.location = location;
    this.attendees = attendees;
    this.regReq = regReq;
  }

  public EventDetails() {}


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public int getAttendees() {
    return attendees;
  }

  public void setAttendees(int attendees) {
    this.attendees = attendees;
  }

  public String getRegReq() {
    return regReq;
  }

  public void setRegReq(String regReq) {
    this.regReq = regReq;
  }
}
