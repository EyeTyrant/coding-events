package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;



@Controller
@RequestMapping("events")
public class EventController {

  @Autowired                               // @Autowired automatically creates instances (called eventRepository in this case) of the EventRepository
  private EventRepository eventRepository; // class when asked for by the code, EventRepository is the local extension of CrudRepository class which
                                           // contains the methods (i.e. findAll(), save(), and findById()) for interacting with the database.

  @GetMapping
  public String displayAllEvents(Model model){
    model.addAttribute("title", "All Events");
    model.addAttribute("events", eventRepository.findAll());
    return "events/index";
  }

  // lives at /events/create
  @GetMapping("create")
  public String renderCreateEventForm(Model model){
    model.addAttribute("title", "Create Event");
    model.addAttribute(new Event()); // It’s also allowable to pass in the Event object without a label
                                     //  In this case, Spring will implicitly create the label "event", which is the lowercase version of the class name.
    model.addAttribute("types", EventType.values()); // returns an array of the values in EventType enum list

    return "events/create";
  }


  @PostMapping("create")
  public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors err, Model model){

   if (err.hasErrors()){
     model.addAttribute("title", "Create Event");
     model.addAttribute("types", EventType.values());
     return "events/create";
   }
    eventRepository.save(newEvent);
      return "redirect:";
  }

  @GetMapping("delete")
  public String displayDeleteEventForm(Model model){
    model.addAttribute("title", "Delete Events");
    model.addAttribute("events", eventRepository.findAll());
    return "events/delete";
  }

  @PostMapping("delete")
  public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
    if (eventIds != null) {
      for (int id : eventIds) {
        eventRepository.deleteById(id);
      }
    }
    return "redirect:";
  }

  @GetMapping("edit/{eventId}")
  public String displayEditForm(@PathVariable int eventId, Model cheese) {
    cheese.addAttribute("title", "Edit Event: "+ eventRepository.findById(eventId)+" (id=" + eventId + ")");
    cheese.addAttribute("events", eventRepository.findById(eventId));
    return "events/edit";
  }

  @PostMapping("edit")
  public String processEditForm(Event event, Integer eventId, String name, String description) {
    eventRepository.findById(eventId);
    event.setName(name);
    event.setDescription(description);
    eventRepository.save(event);
    return "redirect:/events";
  }
}
