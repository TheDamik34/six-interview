package org.example;

import java.util.Objects;

public final class Rocket {
    private String name;
    private RocketStatus status;

    public Rocket() {
        this.name = "Unnamed Rocket";
        this.status = RocketStatus.ON_GROUND;
    }

    public Rocket(String name) {
        if (name == null) {
            throw new NullPointerException("Supplied name cannot be null");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Supplied name cannot be empty");
        }

        this.name = name;
        this.status = RocketStatus.ON_GROUND;
    }

    Rocket(RocketStatus rocketStatus) {
        this.status = Objects.requireNonNull(rocketStatus);
    }

    public RocketStatus getStatus() {
        return status;
    }

    void setStatus(RocketStatus status) {
        this.status = Objects.requireNonNull(status);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{RocketName: " + name + ", RocketStatus: " + status + "}";
    }
}
