
package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("events")
public class EventController {

//  @GetMapping
//  public String displayAllEvents(Model model) {
//
//    List<String> events = new ArrayList<>();

//    events.add("Code With Pride");
//    events.add("Strange Loop");
//    events.add("Apple WWDC");
//    events.add("SpringOne Platform");

//    model.addAttribute("events", events);
//
//    return "events/index";
//  }

  @GetMapping
  public String displayAllEvents(Model model) {
    Map<String, String> events = new HashMap<>();

    events.put("Strange Loop", "Fun event with many speakers");
    events.put("Apple WWDC", "Informative events with many apple snobs");
    events.put("SpringOne Platform", "Overpriced snoozefest with one speaker and no booze");

    model.addAttribute("events", events);

    return "events/index";
  }

}
