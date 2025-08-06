package org.example;

public final class Rocket {
    private RocketStatus status;

    public Rocket() {
        status = RocketStatus.ON_GROUND;
    }

    public RocketStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{RocketStatus: " + status + "}";
    }
}
