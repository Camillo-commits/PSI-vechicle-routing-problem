package com.akko.solver.solver;

import com.akko.solver.domain.Location;
import com.akko.solver.domain.Order;
import com.akko.solver.domain.Problem;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

public class RoutingEasyScoreCalculator implements EasyScoreCalculator<Problem> {
    @Override
    public Score calculateScore(Problem problem) {
        int hard = 0;
        int soft = 0;

        problem.getOrders().get(0).setLocation(problem.getLocations().get(0));
        problem.getOrders().get(problem.getOrders().size() -1).setLocation(problem.getLocations().get(0));

        for(int i = 0; i < problem.getOrders().size(); ++i){
            if(problem.getOrders().get(i).getLocation() == null){
                hard--;
            }
            else{
                //jesli sa po sobie
                if(i + 1 != problem.getOrders().size())
                    if(problem.getOrders().get(i).getNumber() + 1 == problem.getOrders().get(i + 1).getNumber() && problem.getOrders().get(i + 1).getLocation() != null){
                        Order o1 = problem.getOrders().get(i);
                        Order o2 = problem.getOrders().get(i + 1);
                        soft -= (int) Math.sqrt(Math.pow((o1.getLocation().getLatitude() - o2.getLocation().getLatitude()), 2) + Math.pow(o1.getLocation().getLongitude() - o2.getLocation().getLongitude(), 2));
                        //if(o1.getLocation() == o2.getLocation()){
                        //    hard--;
                        //}
                    }
            }

        }
        for(Location l : problem.getLocations()) {
            boolean set = false; boolean setMoreThanOnce = false;
            for (Order o : problem.getOrders()) {
                if(o.getLocation() != null){
                    if(o.getLocation().equals(l)){
                        if(set){
                            setMoreThanOnce = true;
                        }
                        set = true;
                    }
                }
            }
            if(!set){
                hard--;
            }
            if(setMoreThanOnce){
                hard--;
            }
        }
        return HardSoftScore.valueOf(hard,soft);
    }
}
