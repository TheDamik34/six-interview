package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MissionTests {

    @Test
    void shouldCreateNewMission() {
        // given + when
        Mission mission = new Mission();

        // then
        assertEquals(MissionStatus.SCHEDULED, mission.getMissionStatus());
    }

    @Test
    void shouldCreateNewMissionWithName() {
        // given
        String name = "Transit";

        // when
        Mission mission = new Mission(name);

        // then
        assertEquals(name, mission.getName());
    }

    @Test
    void shouldThrow_WhenNameIsInvalid() {
        // given
        String name = "";

        // when + then
        assertThrows(IllegalArgumentException.class, () -> {
            Mission rocket = new Mission(name);
        });
    }

    @Test
    void shouldThrow_WhenNameIsTooLong() {
        // given
        String name = "This Name Is Too Long This Name Is Too Long This Name Is Too Long This Name Is Too Long This Name Is Too Long This Name Is Too Long";

        // when + then
        assertThrows(IllegalArgumentException.class, () -> {
            Mission rocket = new Mission(name);
        });
    }
}
