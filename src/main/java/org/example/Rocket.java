package org.example;

import java.util.Objects;

public final class Rocket {
    private RocketStatus status;

    public Rocket() {
        this.status = RocketStatus.ON_GROUND;
    }

    Rocket(RocketStatus rocketStatus) {
        this.status = Objects.requireNonNull(rocketStatus);
    }

    public RocketStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{RocketStatus: " + status + "}";
    }
}
