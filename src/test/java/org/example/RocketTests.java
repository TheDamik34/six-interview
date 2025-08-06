package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RocketTests {

    @Test
    void shouldCreateNewRocket() {
        // given + when
        Rocket rocket = new Rocket();
        
        // then
        assertEquals(RocketStatus.ON_GROUND, rocket.getStatus());
    }

    @Test
    void shouldChangeRocketStatus() {
        // given
        Rocket rocket = new Rocket();

        // when
        rocket.setStatus(RocketStatus.IN_SPACE);

        // then
        assertEquals(RocketStatus.IN_SPACE, rocket.getStatus());
    }

    @Test
    void shouldChangeRocketStatusToInRepair() {
        // given
        Rocket rocket = new Rocket();

        // when
        rocket.setStatus(RocketStatus.IN_REPAIR);

        // then
        assertEquals(RocketStatus.IN_REPAIR, rocket.getStatus());
    }

    @Test
    void shouldCreateRocketWithName() {
        // given
        String name = "Red Dragon";

        // when
        Rocket rocket = new Rocket(name);

        // then
        assertEquals(name, rocket.getName());
    }

    @Test
    void shouldThrow_WhenNameIsInvalid() {
        // given
        String name = "";

        // when + then
        assertThrows(IllegalArgumentException.class, () -> {
            Rocket rocket = new Rocket(name);
        });
    }
}
