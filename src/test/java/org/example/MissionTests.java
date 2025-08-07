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
        assertEquals(0, mission.getAssignedRocketsCount());
    }

    @Test
    void shouldCreateNewMissionWithName() {
        // given
        String name = "Transit";

        // when
        Mission mission = new Mission(name);

        // then
        assertEquals(name, mission.getName());
        assertEquals(0, mission.getAssignedRocketsCount());
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

    @Test
    void shouldIncrementAssignedRocketsCount() {
        // given
        String name = "Transit";
        Mission mission = new Mission(name);

        // when
        mission.incrementRocketsCount();

        // then
        assertEquals(name, mission.getName());
        assertEquals(1, mission.getAssignedRocketsCount());
    }
}
