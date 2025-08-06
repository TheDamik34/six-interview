package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RocketRepository {
    private final List<Rocket> rocketList;
    private final Map<Rocket, Mission> rocketsMission;

    public RocketRepository() {
        this.rocketList = new ArrayList<>();
        this.rocketsMission = new HashMap<>();
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

        rocketsMission.put(rocket, mission);
    }

    Map<Rocket, Mission> getRocketsMission() {
        return rocketsMission;
    }

    List<Rocket> getRockets() {
        return rocketList;
    }

    int rocketsCount() {
        return rocketList.size();
    }
}
