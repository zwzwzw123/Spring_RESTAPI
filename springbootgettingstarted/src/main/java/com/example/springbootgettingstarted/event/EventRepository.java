package com.example.springbootgettingstarted.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EventRepository extends JpaRepository<Event, Integer> {
}
