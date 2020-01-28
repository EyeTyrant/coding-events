package org.launchcode.codingevents.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class EventCategory extends AbstractEntity{

//  @Id
//  @GeneratedValue
//  private int id;   // handled by AbstractEntity

  @NotBlank(message = "Must add a name")
  @Size(min=3, max=50, message = "Name length between 3-50 characters")
  private String name;

  @OneToMany(mappedBy = "eventCategory")                // By specifying mappedBy = "eventCategory", we are telling Hibernate that for a given category object,
  private final List<Event> events = new ArrayList<>(); // someCategory, the events collection should be populated by all events for which the eventCategory field
                                                        // is set to someCategory. To determine this, Hibernate will look at the foreign key column on the events table.

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

  public List<Event> getEvents() {
    return events;
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
