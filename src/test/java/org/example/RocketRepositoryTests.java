package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        rocketRepository.assignMission(rocket, mission);

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
        rocketRepository.assignMission(rocket, mission);

        // then
        assertThrows(IllegalStateException.class, () -> {
            rocketRepository.assignMission(rocket, mission2);
        });
    }

    @Test
    void shouldThrow_WhenAssigningMissionToNonExistingRocket() {
        // given
        Mission mission = new Mission();
        Rocket rocket = new Rocket();

        // when + then
        assertThrows(IllegalStateException.class, () -> {
            rocketRepository.assignMission(rocket, mission);
        });
    }

    @Test
    void shouldChangeRocketStatus_WhenMissionAssigned() {
        // given
        Rocket rocket = new Rocket();
        Mission mission = new Mission();

        // when
        rocketRepository.add(rocket);
        rocketRepository.assignMission(rocket, mission);

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
        mission.setStatus(MissionStatus.PENDING);

        // when + then
        assertThrows(IllegalArgumentException.class, () -> {
            rocketRepository.add(mission);
        });
    }
}
