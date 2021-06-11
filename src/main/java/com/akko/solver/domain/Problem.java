package com.akko.solver.domain;

import com.akko.solver.models.LocationEntity;
import org.optaplanner.core.api.domain.solution.*;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class Problem {

    @ValueRangeProvider(id = "location")
    @ProblemFactCollectionProperty //elements for assignment
    private List<Location> locations;

    @PlanningEntityCollectionProperty   //elements that are selecting
    private List<Order> orders;

    @PlanningScore
    private static HardSoftScore score;

    public Problem() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public static HardSoftScore getScore() {
        return score;
    }

    public static void setScore(HardSoftScore score) {
        Problem.score = score;
    }
}
