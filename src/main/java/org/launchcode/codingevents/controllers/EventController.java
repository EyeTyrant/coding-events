package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.EventCategory;
//import org.launchcode.codingevents.models.EventType; // Now handled by EventCategory
import org.launchcode.codingevents.models.EventDetails;
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

//  private static List<Event> events = new ArrayList<>(); // Now stored in EventData class

  @Autowired                               // @Autowired automatically creates instance (called eventRepository in this case) of the EventRepository
  private EventRepository eventRepository; // class when asked for by the code, EventRepository is the local extension of CrudRepository class which
                                           // contains the methods (i.e. findAll(), save(), and findById()) for interacting with the database.
                                           // Sort of a temoprary storage area between the controller and the database

  @Autowired
  private EventCategoryRepository eventCategoryRepository; // Creates instance of EventCategoryRepository to hold the categories created

  @GetMapping
  public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model){

    if (categoryId == null){
    model.addAttribute("title", "All Events");
//    model.addAttribute("events", EventData.getAll()); // EventData class no longer needed, data is now stored in database and handled with EventRepository class.
    model.addAttribute("events", eventRepository.findAll());
    } else {
      Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
      if (result.isEmpty()) {
        model.addAttribute("title", "Invalid Category ID: " + categoryId);
      } else {
        EventCategory category = result.get();
        model.addAttribute("title", "Events In Category: " + category.getName());
        model.addAttribute("events", category.getEvents());
      }
    }
    return "events/index";
  }

  // lives at /events/create
  @GetMapping("create")
  public String renderCreateEventForm(Model model){
    model.addAttribute("title", "Create Event");
    model.addAttribute(new Event()); // It’s also allowable to pass in the Event object without a label
                                     //  In this case, Spring will implicitly create the label "event", which is the lowercase version of the class name.
//    model.addAttribute("types", EventType.values()); // returns an array of the values in EventType enum list
    model.addAttribute("categories", eventCategoryRepository.findAll()); // returns the values in eventCategoryRepository

    return "events/create";
  }

//  @PostMapping("create")
//  public String processCreateEventForm(@RequestParam String eventName,
//                                       @RequestParam String eventDescription){ // For each piece of data that needs to be retrieved from the form,
//                                                            // declare a parameter of the appropriate type.
//                                                            // @RequestParam matches the parameters to the submitted data.
//                                                            // For this to work, the parameter names MUST match the name="attributes"
//                                                            // used in each of the input elements.
//      EventData.add(new Event(eventName, eventDescription));
//    return "redirect:";
//  }

  @PostMapping("create")
  public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors err, Model model){ // For each piece of data that needs to be retrieved from the form,
                                                            // there is no need to declare a parameter for each data field with a @RequestParam for each
                                                            // @ModelAttribute matches the parameters to the submitted data automatically.
                                                            // For this to work, the parameter names which, in this case,
                                                            // are taken from the Event object fields MUST match the name="attributes"
                                                            // used in each of the input elements.
                                                            // Errors err used with @Valid to handle invalid input values

   if (err.hasErrors()){
     model.addAttribute("title", "Create Event");
//     model.addAttribute("types", EventType.values()); // enum attribute must also be added here to appear on page when redirected because of input errors
     model.addAttribute("categories", eventRepository.findAll()); // eventRepository attribute must also be added here to appear on page when redirected because of input errors
     return "events/create";
   }
//      EventData.add(newEvent); // EventData class no longer needed, data is now stored in database and handled with EventRepository class.
    eventRepository.save(newEvent);
      return "redirect:";
  }

  @GetMapping("delete")
  public String displayDeleteEventForm(Model model){
    model.addAttribute("title", "Delete Events");
//    model.addAttribute("events", EventData.getAll()); // EventData class no longer needed, data is now stored in database and handled with EventRepository class.
    model.addAttribute("events", eventRepository.findAll());
    return "events/delete";
  }

  @PostMapping("delete")
  public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
    if (eventIds != null) {
      for (int id : eventIds) {
//        EventData.remove(id); // EventData class no longer needed, data is now stored in database and handled with EventRepository class.
        eventRepository.deleteById(id);
      }
    }
    return "redirect:";
  }

  @GetMapping("edit/{eventId}")
  public String displayEditForm(@PathVariable int eventId, Model cheese) {
//    cheese.addAttribute("title", "Edit Event: "+ EventData.getById(eventId)+" (id=" + eventId + ")"); // EventData class no longer needed, data is now stored in database and handled with EventRepository class.
    cheese.addAttribute("title", "Edit Event: "+ eventRepository.findById(eventId)+" (id=" + eventId + ")");
//    cheese.addAttribute("events", EventData.getById(eventId)); // EventData class no longer needed, data is now stored in database and handled with EventRepository class.
    cheese.addAttribute("events", eventRepository.findById(eventId));
    return "events/edit";
  }

  @PostMapping("edit")
//  public String processEditForm(int eventId, String name, String description) {
  public String processEditForm(int eventId, String name, String description, Event event, EventDetails eventDetails) {
//    EventData.getById(eventId).setName(name); // EventData class no longer needed, data is now stored in database and handled with EventRepository class.
//    EventData.getById(eventId).setDescription(description); // EventData class no longer needed, data is now stored in database and handled with EventRepository class.
    eventRepository.findById(eventId);
    event.setName(name);
    eventDetails.setDescription(description);
    eventRepository.save(event);


    return "redirect:/events";
  }

  @GetMapping("detail")
  public String displayEventDetails(@RequestParam Integer eventId, Model model) {

    Optional<Event> result = eventRepository.findById(eventId);

    if (result.isEmpty()) {
      model.addAttribute("title", "Invalid Event ID: " + eventId);
    } else {
      Event event = result.get();
      model.addAttribute("title", event.getName() + " Details");
      model.addAttribute("event", event);
    }

    return "events/detail";
  }



}
