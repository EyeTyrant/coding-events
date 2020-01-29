package org.launchcode.codingevents.models.dto;

import com.sun.istack.NotNull;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.Tag;

public class EventTagDTO { // This Data Transfer Objects class allows Event and Tag objects to be
                           // bundled together for passing

  @NotNull
  private Event event;

  @NotNull
  private Tag tag;

  public EventTagDTO() {}

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }
}
