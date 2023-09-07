package com.example.springbootgettingstarted.events;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class EventResource extends EntityModel<Event> {
    public EventResource(Event event, Link... links) {
        super(event, List.of(links));
//      add(Link.of("http://localhost:8080/api/events/" + event.getId())); // 같은 의미
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }

}
