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
        if (name == null) {
            throw new NullPointerException("Supplied name cannot be null");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Supplied name cannot be empty");
        }

        if (name.length() > 20) {
            throw new IllegalArgumentException("Supplied name is too long");
        }

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
        return "{MissionName: " + name + ", MissionStatus: " + missionStatus + "}";
    }
}
