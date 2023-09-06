package com.example.springbootgettingstarted.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.file.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    //입력값이 제대로 들어온 경우
    @Test
    public void createEvent() throws Exception {
        EventDto event = EventDto.builder()
                .name("test")
                .description("Rest API")
                .beginEventDateTime(LocalDateTime.of(2020, 1, 1, 12, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2020, 1, 2, 12, 0))
                .beginEventDateTime(LocalDateTime.of(2020, 1, 3, 1, 0))
                .endEventDateTime(LocalDateTime.of(2020, 1, 4, 1, 0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 d2 스타텁 팩토리")
                .build();

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("id").value(Matchers.not(100)))
                .andExpect(jsonPath("free").value(Matchers.not(true)))
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()));
    }


    //입력값이 제대로 들어오지 않은 경우
    @Test
    public void createEvent_Bad_Request() throws Exception {
        Event event = Event.builder()
                .id(10)
                .name("test")
                .description("Rest API")
                .beginEventDateTime(LocalDateTime.of(2020, 1, 1, 12, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2020, 1, 2, 12, 0))
                .beginEventDateTime(LocalDateTime.of(2020, 1, 3, 1, 0))
                .endEventDateTime(LocalDateTime.of(2020, 1, 4, 1, 0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 d2 스타텁 팩토리")
                .free(true)
                .offline(false)
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }


}
