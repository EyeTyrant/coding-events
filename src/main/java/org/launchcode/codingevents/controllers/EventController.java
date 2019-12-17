package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.controllers.event.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {


  private static List<Event> events = new ArrayList<>();

  @GetMapping
  public String displayAllEvents(Model model){
    model.addAttribute("title", "All Events");
    model.addAttribute("events", events);
    return "events/index";
  }

  // lives at /events/create
  @GetMapping("create")
  public String renderCreateEventForm(){
    return "events/create";
  }

  @PostMapping("create")
  public String processCreateEventForm(@RequestParam String eventName,
                            @RequestParam String eventDescription){ // For each piece of data that needs to be retrieved from the form,
                                                            // declare a parameter of the appropriate type.
                                                            // @RequestParam matches the parameters to the submitted data.
                                                            // For this to work, the parameter names MUST match the name attributes
                                                            // used in each of the input elements.
    events.add(new Event(eventName, eventDescription));
    return "redirect:";
  }




}
