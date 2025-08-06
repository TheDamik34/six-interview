package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
