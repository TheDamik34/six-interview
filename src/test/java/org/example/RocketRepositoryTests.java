package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RocketRepositoryTests {

    @Test
    void shouldCreateEmptyRepository() {
        // given + when
        RocketRepository rocketRepository = new RocketRepository();

        // then
        assertEquals(0, rocketRepository.rocketsCount());
    }
}
