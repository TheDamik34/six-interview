package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RocketRepository {
    private final List<Rocket> rocketList;
    private final Map<Rocket, Mission> rocketMissionMap;

    public RocketRepository() {
        this.rocketList = new ArrayList<>();
        this.rocketMissionMap = new HashMap<>();
    }

    public void add(Rocket rocket) {
        if (rocket == null) {
            throw new NullPointerException("Rocket cannot be null");
        }

        if (rocket.getStatus() != RocketStatus.ON_GROUND) {
            throw new IllegalArgumentException("Only rockets that have ON_GROUND status can be added");
        }

        rocketList.add(rocket);
    }

    public void assignMission(Rocket rocket, Mission mission) {
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
    }

    Map<Rocket, Mission> getRocketsMission() {
        return rocketMissionMap;
    }

    List<Rocket> getRockets() {
        return rocketList;
    }

    int rocketsCount() {
        return rocketList.size();
    }
}
