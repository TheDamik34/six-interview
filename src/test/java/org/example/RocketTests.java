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
}
