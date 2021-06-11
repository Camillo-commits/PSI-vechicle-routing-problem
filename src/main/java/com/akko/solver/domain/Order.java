package com.akko.solver.domain;

import com.akko.solver.models.LocationEntity;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Order {
    private Integer number;
    private Integer distance;

    @PlanningVariable(valueRangeProviderRefs = "location")
    private Location location;

    public Order() {
    }

    public Order(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Location getLocation() {
        return location;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
