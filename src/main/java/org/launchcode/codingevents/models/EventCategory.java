package org.launchcode.codingevents.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity{

//  @Id
//  @GeneratedValue
//  private int id;   // handled by AbstractEntity

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

//  public int getId() {
//    return id;
//  }                         // handled by AbstractEntity

  @Override
  public String toString() {
    return name;
  }

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (!(o instanceof EventCategory)) return false;
//    EventCategory that = (EventCategory) o;
//    return getId() == that.getId();
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(getId());
//  }                                   // handled by AbstractEntity
}
