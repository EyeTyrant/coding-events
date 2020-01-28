package org.launchcode.codingevents.models;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity {

  @Size(min=1, max=25)
  @NotBlank
  private String name;

  @ManyToMany(mappedBy = "tags")                        // @ManyToMany ensures that Hibernate creates a relationship from a given event to every Tag instance placed in its tags collection.
  private final List<Event> events = new ArrayList<>(); // mappedBy ensures that Hibernate populates the events collection of a given Tag object
                                                        // with every Event object that has that specific tag in its tags collection.


  public Tag() {}

  public Tag(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplayName() {
    return "#" + name + " ";
  }

}
