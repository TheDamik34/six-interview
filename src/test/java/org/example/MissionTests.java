package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissionTests {

    @Test
    void shouldCreateNewMission() {
        // given + when
        Mission mission = new Mission();

        // then
        assertEquals(MissionStatus.SCHEDULED, mission.getMissionStatus());
    }
}
