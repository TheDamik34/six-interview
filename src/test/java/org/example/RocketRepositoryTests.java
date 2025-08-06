package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RocketRepositoryTests {

    @Test
    void shouldCreateEmptyRepository() {
        // given + when
        RocketRepository rocketRepository = new RocketRepository();

        // then
        assertEquals(0, rocketRepository.rocketsCount());
    }

    @Test
    void shouldAddRocketToRepository() {
        // given
        Rocket rocket = new Rocket();
        RocketRepository rocketRepository = new RocketRepository();

        // when
        rocketRepository.add(rocket);

        // then
        assertTrue(rocketRepository.getRockets().contains(rocket));
    }
}
