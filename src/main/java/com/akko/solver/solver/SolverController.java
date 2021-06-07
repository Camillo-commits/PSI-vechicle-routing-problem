package com.akko.solver.solver;

import com.akko.solver.domain.Location;
import com.akko.solver.domain.Order;
import com.akko.solver.domain.OrderCount;
import com.akko.solver.domain.Problem;
import com.akko.solver.models.LocationEntity;
import com.akko.solver.repositories.LocationRepository;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/solver")
public class SolverController {

    @Autowired
    private SolverManager<Problem, UUID> solverManager;
    @Autowired LocationRepository locationRepository;
}
    @PostMapping("/solveTest")
    public Problem solveTest() {


        Problem problem = new Problem();
        LocationEntity l1=new LocationEntity(0L,0.0,0.0);
        LocationEntity l2=new LocationEntity(1L,3.0,1.0);
        LocationEntity l3=new LocationEntity(2L,1.0,1.0);
        LocationEntity l4=new LocationEntity(3L,0.0,1.0);

        List<LocationEntity> locations = new LinkedList<>();
        locations.add(l1);
        locations.add(l2);
        locations.add(l3);
        locations.add(l4);

        List<Order> orderList = new LinkedList<>();
        orderList.add(new Order());
        orderList.add(new Order());
        orderList.add(new Order());
        orderList.add(new Order());
        orderList.add(new Order());
        orderList.get(0).setNumber(1);
        orderList.get(1).setNumber(2);
        orderList.get(2).setNumber(3);
        orderList.get(3).setNumber(4);
        orderList.get(4).setNumber(5);

        problem.setLocations(locations);
        problem.setOrders(orderList);
        OrderCount.setCount(orderList.size());

        //SolverFactory<Problem> solverFactory = SolverFactory.createFromXmlResource("solver/config.xml");
        //Solver<Problem> solver = solverFactory.buildSolver();
        //Problem result;
        //result = solver.solve(problem);

        UUID problemId = UUID.randomUUID();
        SolverJob<Problem, UUID> solverJob = solverManager.solve(problemId,problem);
        Problem solution = null;
        try{
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return solution;
        //return result;
    }
    @PostMapping("/solve")
    public Problem solve() {


        Problem problem = new Problem();


        List<LocationEntity> locations = locationRepository.findAll();


        List<Order> orderList = new LinkedList<>();
        for(int i =0;i<= locations.size();i++){
            orderList.add(new Order());
            orderList.get(i).setNumber(i+1);
        }


        problem.setLocations(locations);
        problem.setOrders(orderList);
        OrderCount.setCount(orderList.size());

        //SolverFactory<Problem> solverFactory = SolverFactory.createFromXmlResource("solver/config.xml");
        //Solver<Problem> solver = solverFactory.buildSolver();
        //Problem result;
        //result = solver.solve(problem);

        UUID problemId = UUID.randomUUID();
        SolverJob<Problem, UUID> solverJob = solverManager.solve(problemId,problem);
        Problem solution = null;
        try{
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return solution;
        //return result;
    }



    public List<Location> locationList(){
        List<Location>temp = new LinkedList<>();
        for(LocationEntity e:locationRepository.findAll()){
            temp.add(new Location(e.getLongitude(), e.getLatitude()));
        }
        return temp;
    }
}
