package com.example.springbootgettingstarted.events;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;


import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(JUnitParamsRunner.class)
public class EventTest {

    @Test
    public void builder() throws Exception {
        Event event = Event.builder()
                .name("Inflearn Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {

        String name = "Event";
        String description = "Spring";

        Event event = new Event();

        event.setName(name);
        event.setDescription(description);

        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);

    }

    @ParameterizedTest
    @MethodSource
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        //Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();
        //When
        event.update();
        //Then
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    private Object[] testFree() {
        return new Object[][]{
                {0, 0, true},
                {100, 0, false},
                {0, 100, false}
        };

    }

    @ParameterizedTest
    @MethodSource
    public void testOffline(String location, boolean isOffline) {
        Event event = Event.builder()
                .location(location)
                .build();
        event.update();
        assertThat(event.isOffline()).isEqualTo(isOffline);
    }

    private Object[] testOffline() {
        return new Object[]{
                new Object[]{"강남역 네이버 D2", true},
                new Object[]{null, false}
        };

    }

}