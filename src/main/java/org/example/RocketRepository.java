package org.example;

import java.util.ArrayList;
import java.util.List;

public final class RocketRepository {
    private List<Rocket> rockets;

    public RocketRepository() {
        this.rockets = new ArrayList<>();
    }

    public int rocketsCount() {
        return rockets.size();
    }
}
