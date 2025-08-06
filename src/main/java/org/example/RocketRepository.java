package org.example;

import java.util.ArrayList;
import java.util.List;

public final class RocketRepository {
    private final List<Rocket> rockets;

    public RocketRepository() {
        this.rockets = new ArrayList<>();
    }

    public void add(Rocket rocket) {
        if (rocket == null) {
            throw new NullPointerException("Rocket cannot be null");
        }

        if (rocket.getStatus() != RocketStatus.ON_GROUND) {
            throw new IllegalArgumentException("Only rockets that have ON_GROUND status can be added");
        }

        rockets.add(rocket);
    }

    List<Rocket> getRockets() {
        return rockets;
    }

    int rocketsCount() {
        return rockets.size();
    }
}
