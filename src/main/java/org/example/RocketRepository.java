package org.example;

import java.util.ArrayList;
import java.util.List;

public final class RocketRepository {
    private List<Rocket> rockets;

    public RocketRepository() {
        this.rockets = new ArrayList<>();
    }

    public void add(Rocket rocket) {
        rockets.add(rocket);
    }

    List<Rocket> getRockets() {
        return rockets;
    }

    int rocketsCount() {
        return rockets.size();
    }
}
