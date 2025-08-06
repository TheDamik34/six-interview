package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RocketRepository {
    private final List<Rocket> rocketList;
    private final List<Mission> missionList;
    private final Map<Rocket, Mission> rocketMissionMap;
    private final Map<Mission, List<Rocket>> missionToRocketsMap;

    public RocketRepository() {
        this.rocketList = new ArrayList<>();
        this.missionList = new ArrayList<>();
        this.rocketMissionMap = new HashMap<>();
        this.missionToRocketsMap = new HashMap<>();
    }

    public void add(Rocket rocket) {
        if (rocket == null) {
            throw new NullPointerException("Rocket cannot be null");
        }

        if (rocket.getStatus() != RocketStatus.ON_GROUND) {
            throw new IllegalArgumentException("Only rockets that have ON_GROUND status can be added");
        }

        if (rocketList.contains(rocket)) {
            throw new IllegalStateException("The same rocket cannot be added");
        }

        rocketList.add(rocket);
    }

    public void add(Mission mission) {
        if (mission == null) {
            throw new NullPointerException("Mission cannot be null");
        }

        if (mission.getMissionStatus() != MissionStatus.SCHEDULED) {
            throw new IllegalArgumentException("Only missions that have SCHEDULED status can be added");
        }

        missionList.add(mission);
    }

    public void assignRocketToMission(Rocket rocket, Mission mission) {
        if (rocket == null) {
            throw new NullPointerException("Rocket cannot be null");
        }

        if (mission == null) {
            throw new NullPointerException("Mission cannot be null");
        }

        if (!rocketList.contains(rocket)) {
            throw new IllegalStateException("Rocket first must be stored to then be able to assign a mission to it");
        }

        if (rocketMissionMap.containsKey(rocket)) {
            throw new IllegalStateException("Rocket already have a mission assigned");
        }

        rocket.setStatus(RocketStatus.IN_SPACE);
        rocketMissionMap.put(rocket, mission);

        if (missionToRocketsMap.containsKey(mission)) {
            missionToRocketsMap.get(mission).add(rocket);
        } else {
            List<Rocket> rockets = new ArrayList<>();
            rockets.add(rocket);
            missionToRocketsMap.put(mission, rockets);
        }
    }

    List<Mission> getMissions() {
        return missionList;
    }

    Map<Rocket, Mission> getRocketsMission() {
        return rocketMissionMap;
    }

    Map<Mission, List<Rocket>> getRocketsAssignedToMission() {
        return missionToRocketsMap;
    }

    List<Rocket> getRockets() {
        return rocketList;
    }

    int rocketsCount() {
        return rocketList.size();
    }
}
