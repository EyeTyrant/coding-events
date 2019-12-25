package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.data.EventData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {

//  private static List<Event> events = new ArrayList<>();

  @GetMapping
  public String displayAllEvents(Model model){
    model.addAttribute("title", "All Events");
    model.addAttribute("events", EventData.getAll());
    return "events/index";
  }

  // lives at /events/create
  @GetMapping("create")
  public String renderCreateEventForm(Model model){
    model.addAttribute("title", "Create Event");
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
     model.addAttribute("errMsg", "Bad data entered");
     return "events/create";
   }
      EventData.add(newEvent);
    return "redirect:";
  }

  @GetMapping("delete")
  public String displayDeleteEventForm(Model model){
    model.addAttribute("title", "Delete Events");
    model.addAttribute("events", EventData.getAll());
    return "events/delete";
  }

  @PostMapping("delete")
  public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
    if (eventIds != null) {
      for (int id : eventIds) {
        EventData.remove(id);
      }
    }
    return "redirect:";
  }

  @GetMapping("edit/{eventId}")
  public String displayEditForm(@PathVariable int eventId, Model cheese) {
    cheese.addAttribute("title", "Edit Event: "+ EventData.getById(eventId)+" (id=" + eventId + ")");
    cheese.addAttribute("events", EventData.getById(eventId));
    return "events/edit";
  }

  @PostMapping("edit")
  public  String processEditForm(int eventId, String name, String description) {
    EventData.getById(eventId).setName(name);
    EventData.getById(eventId).setDescription(description);
    return "redirect:/events";
  }
}
