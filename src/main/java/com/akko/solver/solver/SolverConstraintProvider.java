package com.akko.solver.solver;

import com.akko.solver.domain.Order;
import com.akko.solver.domain.OrderCount;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import java.util.concurrent.atomic.AtomicReference;

public class SolverConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                visitOnlyOnce(constraintFactory),
                calculateRoute(constraintFactory),
                //alwaysComeHome(constraintFactory)
        };
    }

    private Constraint visitOnlyOnce(ConstraintFactory constraintFactory){
        return constraintFactory.from(Order.class)
                .join(Order.class)
                .filter((o1, o2) -> o1.getLocation().equals(o2.getLocation()))
                .penalize("visitOnlyOnce", HardSoftScore.ONE_HARD);
    }
    private Constraint calculateRoute(ConstraintFactory constraintFactory) {
        AtomicReference<Integer> distance = new AtomicReference<>(0);
        return constraintFactory.from(Order.class)
                .join(Order.class)
                .filter((o1,o2)->o1.getNumber() + 1 == o2.getNumber())
                .filter((o1,o2) -> {
                    distance.set((int) Math.sqrt(Math.pow((o1.getLocation().getLatitude() - o2.getLocation().getLatitude()), 2) + Math.pow(o1.getLocation().getLongitude() - o2.getLocation().getLongitude(), 2)));
                    return true;
                })
                .penalize("calculateRoute", HardSoftScore.ofSoft(distance.get()));
    }
    private Constraint alwaysComeHome(ConstraintFactory constraintFactory){
        return constraintFactory.from(Order.class)
                .join(Order.class)
                .filter((o1,o2) -> {
                    if(o1.getNumber() == OrderCount.getCount() && o2.getNumber() == 1)
                        o1.setLocation(o2.getLocation());
                    return true;
                }).penalize("alwaysComeHome", HardSoftScore.ZERO);
    }
/*
    private Constraint visitOnlyOnce(ConstraintFactory constraintFactory){
        return constraintFactory.from(Location.class)
                .join(Location.class)
                .filter((l1,l2) -> l1.getNextLocation().equals(l2.getNextLocation()))
                .penalize("visitOnlyOnce", HardSoftScore.ONE_HARD);
    }
    private Constraint calculateRoute(ConstraintFactory constraintFactory){
        AtomicReference<Integer> distance = new AtomicReference<>(0);
        return constraintFactory.from(Location.class)
                .filter(location -> {
                    distance.set((int) Math.sqrt(Math.pow((location.getNextLocation().getLatitude() - location.getLatitude()), 2) + Math.pow(location.getNextLocation().getLongitude() - location.getLongitude(), 2)));
                    return true;
                })
                .penalize("calculateRoute",HardSoftScore.ofSoft(distance.get()));
    }
    private Constraint alwaysComeHome(ConstraintFactory constraintFactory){
        return constraintFactory.from(Location.class)
                .filter(location -> {
                    if(location.getNextLocation() == null) {
                        Location l = new Location();
                        l.setLatitude(StartLocation.getLatitude());
                        l.setLongitude(StartLocation.getLongitude());
                        location.setNextLocation(l);
                    }
                    return true;
                }).penalize("alwaysComeHome",HardSoftScore.ofHard(0));
    }

 */
}
