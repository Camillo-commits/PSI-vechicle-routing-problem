package com.akko.solver.domain;

import com.akko.solver.models.LocationEntity;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Order {
    private Integer number;

    @PlanningVariable(valueRangeProviderRefs = "location")
    private LocationEntity location;

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

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }
}
