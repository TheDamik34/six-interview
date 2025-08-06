package org.example;

public final class Mission {
    private MissionStatus missionStatus;

    public Mission() {
        this.missionStatus = MissionStatus.SCHEDULED;
    }

    public MissionStatus getMissionStatus() {
        return missionStatus;
    }
}
