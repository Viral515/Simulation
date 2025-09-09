package ru.egar.springcourses;

import ru.egar.springcourses.action.Action;
import ru.egar.springcourses.action.init_actions.SpawnEntities;
import ru.egar.springcourses.action.turn_actions.StepByAllCreatures;

import java.util.ArrayList;

public class Simulation {
    private final Map map = new Map();
    private int MovesCounter = 1;
    private final Renderer renderer = new Renderer(map);
    private static final boolean running = true;
    private final ArrayList<Action> initActions = new ArrayList<>();
    private final ArrayList<Action> turnActions = new ArrayList<>();

    public Simulation() {
        initActions.add(new SpawnEntities());
        turnActions.add(new StepByAllCreatures());
    }

    public void StartSimulation() {
        for (Action action : initActions) {
            action.Execute(map);
        }
        renderer.RenderMap();
        while(running) {
            System.out.println("Step number " + MovesCounter);
            nextTurn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            MovesCounter++;
        }
    }

    private void nextTurn(){
        for (Action action : turnActions) {
            action.Execute(map);
            renderer.RenderMap();
        }
    }
}
