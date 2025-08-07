package org.example;

import java.util.Objects;

public final class Mission implements Comparable<Mission> {
    private String name;
    private MissionStatus missionStatus;

    private int assignedRocketsCount = 0;

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

    public int getAssignedRocketsCount() {
        return assignedRocketsCount;
    }

    void setMissionStatus(MissionStatus missionStatus) {
        this.missionStatus = Objects.requireNonNull(missionStatus);
    }

    void incrementRocketsCount() {
        assignedRocketsCount++;
    }

    @Override
    public String toString() {
        return "{MissionName: " + name + ", MissionStatus: " + missionStatus + ", Dragons: " + assignedRocketsCount + "}";
    }

    @Override
    public int compareTo(Mission o) {
        int result = Integer.compare(this.assignedRocketsCount, o.assignedRocketsCount);

        if (result == 0) {
            result = String.CASE_INSENSITIVE_ORDER.compare(this.name, o.name);
        }

        return result * -1;
    }
}
