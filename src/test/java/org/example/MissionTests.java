package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void shouldPreserveOrder_WhenMissionsHaveDifferentRocketsAssignedCount() {
        // given
        Mission mission1 = new Mission("aa");
        mission1.incrementRocketsCount();

        Mission mission2 = new Mission("ab");
        mission2.incrementRocketsCount();
        mission2.incrementRocketsCount();

        Mission mission3 = new Mission("ac");
        mission3.incrementRocketsCount();
        mission3.incrementRocketsCount();
        mission3.incrementRocketsCount();

        List<Mission> missionList = Arrays.asList(mission2, mission1, mission3);

        // when
        Collections.sort(missionList);

        // then
        List<Mission> expectedList = Arrays.asList(mission3, mission2, mission1);
        assertThat(missionList).hasSize(3);
        assertThat(missionList).isSortedAccordingTo(Mission::compareTo);
        assertThat(missionList).hasSameElementsAs(expectedList);
    }
}
