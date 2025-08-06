package org.example;

import java.util.Objects;

public final class Mission {
    private String name;
    private MissionStatus missionStatus;

    public Mission() {
        this.name = "Unknown Mission";
        this.missionStatus = MissionStatus.SCHEDULED;
    }

    public Mission(String name) {
        this.name = name;
        this.missionStatus = MissionStatus.SCHEDULED;
    }

    public String getName() {
        return name;
    }

    public MissionStatus getMissionStatus() {
        return missionStatus;
    }

    void setMissionStatus(MissionStatus missionStatus) {
        this.missionStatus = Objects.requireNonNull(missionStatus);
    }

    @Override
    public String toString() {
        return "{MissionStatus: " + missionStatus + "}";
    }
}
