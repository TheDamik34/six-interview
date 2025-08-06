package org.example;

import java.util.Objects;

public final class Mission {
    private MissionStatus missionStatus;

    public Mission() {
        this.missionStatus = MissionStatus.SCHEDULED;
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
