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

        if (missionList.contains(mission)) {
            throw new IllegalStateException("The same mission cannot be added");
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

        if (!missionList.contains(mission)) {
            throw new IllegalStateException("Mission first must be stored to then be able to be assigned to a rocket");
        }

        if (rocketMissionMap.containsKey(rocket)) {
            throw new IllegalStateException("Rocket already have a mission assigned");
        }

        if (mission.getMissionStatus() == MissionStatus.ENDED) {
            throw new IllegalStateException("New rockets cannot be assigned to ENDED missions");
        }

        if (rocket.getStatus() == RocketStatus.IN_SPACE) {
            throw new IllegalStateException("Rocket already on the mission");
        }

        handleMissionStatus(rocket, mission);
        mission.incrementRocketsCount();

        if (rocket.getStatus() == RocketStatus.ON_GROUND) {
            rocket.setStatus(RocketStatus.IN_SPACE);
        }

        rocketMissionMap.put(rocket, mission);

        if (missionToRocketsMap.containsKey(mission)) {
            missionToRocketsMap.get(mission).add(rocket);
        } else {
            List<Rocket> rockets = new ArrayList<>();
            rockets.add(rocket);
            missionToRocketsMap.put(mission, rockets);
        }
    }

    private void handleMissionStatus(Rocket rocket, Mission mission) {
        if (rocket == null) {
            throw new NullPointerException("Rocket is null");
        }

        if (mission == null) {
            throw new NullPointerException("Mission is null");
        }

        if (missionToRocketsMap.containsKey(mission)) {
            boolean hasAnyRocketInRepair = missionToRocketsMap.get(mission)
                    .stream()
                    .anyMatch(rocket1 -> rocket1.getStatus() == RocketStatus.IN_REPAIR);

            if (hasAnyRocketInRepair) {
                mission.setMissionStatus(MissionStatus.PENDING);
                return;
            }
        }

        if (rocket.getStatus() == RocketStatus.IN_REPAIR) {
            mission.setMissionStatus(MissionStatus.PENDING);
        } else {
            mission.setMissionStatus(MissionStatus.IN_PROGRESS);
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
