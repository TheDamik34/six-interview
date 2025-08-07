package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RocketRepositoryTests {

    private RocketRepository rocketRepository;

    @BeforeEach
    void setup() {
        this.rocketRepository = new RocketRepository();
    }

    @Test
    void shouldCreateEmptyRepository() {
        // given + when + then
        assertEquals(0, rocketRepository.rocketsCount());
    }

    @Test
    void shouldAddRocketToRepository() {
        // given
        Rocket rocket = new Rocket();

        // when
        rocketRepository.add(rocket);

        // then
        assertTrue(rocketRepository.getRockets().contains(rocket));
    }

    @Test
    void shouldThrow_WhenAddingRocketWithStateDifferentThanOnGround() {
        // given
        Rocket rocket = new Rocket(RocketStatus.IN_SPACE);

        // when + then
        assertThrows(IllegalArgumentException.class, () -> {
            rocketRepository.add(rocket);
        });
    }

    @Test
    void shouldAssignMissionToRocket() {
        // given
        Mission mission = new Mission();
        Rocket rocket = new Rocket();

        // when
        rocketRepository.add(rocket);
        rocketRepository.add(mission);
        rocketRepository.assignRocketToMission(rocket, mission);

        // then
        assertTrue(rocketRepository.getRocketsMission().containsKey(rocket));
        assertEquals(mission, rocketRepository.getRocketsMission().get(rocket));
    }

    @Test
    void shouldThrow_WhenRocketIsAlreadyAssignToAMission() {
        // given
        Mission mission = new Mission();
        Mission mission2 = new Mission();
        Rocket rocket = new Rocket();

        // when
        rocketRepository.add(rocket);
        rocketRepository.add(mission);
        rocketRepository.assignRocketToMission(rocket, mission);

        // then
        assertThrows(IllegalStateException.class, () -> {
            rocketRepository.assignRocketToMission(rocket, mission2);
        });
    }

    @Test
    void shouldThrow_WhenAssigningMissionToNonExistingRocket() {
        // given
        Mission mission = new Mission();
        Rocket rocket = new Rocket();

        // when + then
        assertThrows(IllegalStateException.class, () -> {
            rocketRepository.assignRocketToMission(rocket, mission);
        });
    }

    @Test
    void shouldChangeRocketStatus_WhenMissionAssigned() {
        // given
        Rocket rocket = new Rocket();
        Mission mission = new Mission();

        // when
        rocketRepository.add(rocket);
        rocketRepository.add(mission);
        rocketRepository.assignRocketToMission(rocket, mission);

        // then
        assertEquals(RocketStatus.IN_SPACE, rocket.getStatus());
    }

    @Test
    void shouldAddNewMission() {
        // given
        Mission mission = new Mission();

        // when
        rocketRepository.add(mission);

        // then
        assertEquals(1, rocketRepository.getMissions().size());
        assertTrue(rocketRepository.getMissions().contains(mission));
    }

    @Test
    void shouldThrow_WhenAddedMissionIsNotInScheduledStatus() {
        // given
        Mission mission = new Mission();
        mission.setMissionStatus(MissionStatus.PENDING);

        // when + then
        assertThrows(IllegalArgumentException.class, () -> {
            rocketRepository.add(mission);
        });
    }

    @Test
    void shouldAssignRocketToMission() {
        // given
        Rocket rocket = new Rocket();
        Mission mission = new Mission();

        // when
        rocketRepository.add(rocket);
        rocketRepository.add(mission);
        rocketRepository.assignRocketToMission(rocket, mission);

        // then
        assertTrue(rocketRepository.getRocketsAssignedToMission().containsKey(mission));
        assertEquals(List.of(rocket), rocketRepository.getRocketsAssignedToMission().get(mission));
    }

    @Test
    void shouldAssignTwoRocketsToSameMission() {
        // given
        Rocket rocket = new Rocket();
        Rocket rocket2 = new Rocket();
        Mission mission = new Mission();

        // when
        rocketRepository.add(rocket);
        rocketRepository.add(rocket2);
        rocketRepository.add(mission);
        rocketRepository.assignRocketToMission(rocket, mission);
        rocketRepository.assignRocketToMission(rocket2, mission);

        // then
        assertTrue(rocketRepository.getRocketsAssignedToMission().containsKey(mission));
        assertEquals(List.of(rocket, rocket2), rocketRepository.getRocketsAssignedToMission().get(mission));
    }

    @Test
    void shouldThrow_WhenAddingTheSameRocket() {
        // given
        Rocket rocket = new Rocket();

        // when
        rocketRepository.add(rocket);

        // then
        assertThrows(IllegalStateException.class, () -> {
            rocketRepository.add(rocket);
        });
    }

    @Test
    void shouldThrow_WhenAddingTheSameMission() {
        // given
        Mission mission = new Mission();

        // when
        rocketRepository.add(mission);

        // then
        assertThrows(IllegalStateException.class, () -> {
            rocketRepository.add(mission);
        });
    }

    @Test
    void shouldThrow_WhenRocketIsAssignedToNonExistingMission() {
        // given
        Rocket rocket = new Rocket();
        Mission mission = new Mission();

        // when
        rocketRepository.add(rocket);

        // then
        assertThrows(IllegalStateException.class, () -> {
            rocketRepository.assignRocketToMission(rocket, mission);
        });
    }

    @Test
    void shouldChangeMissionStatusToPending() {
        // given
        Rocket rocket = new Rocket();
        Mission mission = new Mission();
        rocketRepository.add(mission);
        rocketRepository.add(rocket);
        rocket.setStatus(RocketStatus.IN_REPAIR);

        // when
        rocketRepository.assignRocketToMission(rocket, mission);

        // then
        assertEquals(MissionStatus.PENDING, mission.getMissionStatus());
    }

    @Test
    void shouldChangeMissionStatusToInProgress() {
        // given
        Rocket rocket = new Rocket();
        Mission mission = new Mission();
        rocketRepository.add(mission);
        rocketRepository.add(rocket);

        // when
        rocketRepository.assignRocketToMission(rocket, mission);

        // then
        assertEquals(MissionStatus.IN_PROGRESS, mission.getMissionStatus());
    }

    @Test
    void shouldPreventAssigningNewRocketsToEndedMission() {
        // given
        Rocket rocket = new Rocket();
        Mission mission = new Mission();

        // when
        rocketRepository.add(mission);
        mission.setMissionStatus(MissionStatus.ENDED);
        rocketRepository.add(rocket);

        // then
        assertThrows(IllegalStateException.class, () -> {
            rocketRepository.assignRocketToMission(rocket, mission);
        });
    }

    @Test
    void shouldChangeMissionStatusToPending_WhenAtLeastOneRocketIsInRepair() {
        // given
        Rocket rocket = new Rocket();
        Rocket rocketInRepair = new Rocket();
        Rocket rocket3 = new Rocket();

        Mission mission = new Mission();

        rocketRepository.add(mission);
        rocketRepository.add(rocket);
        rocketRepository.add(rocketInRepair);
        rocketRepository.add(rocket3);
        rocketInRepair.setStatus(RocketStatus.IN_REPAIR);

        // when
        rocketRepository.assignRocketToMission(rocket, mission);
        rocketRepository.assignRocketToMission(rocketInRepair, mission);
        rocketRepository.assignRocketToMission(rocket3, mission);

        // then
        assertEquals(MissionStatus.PENDING, mission.getMissionStatus());
    }

    @Test
    void shouldChangeMissionStatusToInProgress_WhenMultipleRocketsAreAssign() {
        // given
        Rocket rocket = new Rocket();
        Rocket rocket2 = new Rocket();
        Rocket rocket3 = new Rocket();

        Mission mission = new Mission();

        rocketRepository.add(mission);
        rocketRepository.add(rocket);
        rocketRepository.add(rocket2);
        rocketRepository.add(rocket3);

        // when
        rocketRepository.assignRocketToMission(rocket, mission);
        rocketRepository.assignRocketToMission(rocket2, mission);
        rocketRepository.assignRocketToMission(rocket3, mission);

        // then
        assertEquals(MissionStatus.IN_PROGRESS, mission.getMissionStatus());
    }

    @Test
    void shouldThrow_WhenAssigningRocketInSpaceToMission() {
        // given
        Rocket rocket = new Rocket();
        Rocket rocket2 = new Rocket();

        Mission mission = new Mission();

        rocketRepository.add(mission);

        rocketRepository.add(rocket);
        rocketRepository.add(rocket2);
        rocket2.setStatus(RocketStatus.IN_SPACE);

        rocketRepository.assignRocketToMission(rocket, mission);

        // when + then
        assertThrows(IllegalStateException.class, () -> {
            rocketRepository.assignRocketToMission(rocket2, mission);
        });
    }

    @Test
    void shouldIncrementRocketsCountOnMissionObject_WhenRocketIsAssignedToMission() {
        // given
        Rocket rocket = new Rocket();
        Rocket rocket2 = new Rocket();
        rocketRepository.add(rocket);
        rocketRepository.add(rocket2);

        Mission mission = new Mission();
        rocketRepository.add(mission);

        // when
        rocketRepository.assignRocketToMission(rocket, mission);
        rocketRepository.assignRocketToMission(rocket2, mission);

        // then
        assertEquals(2, mission.getAssignedRocketsCount());
    }
}
