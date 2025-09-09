package ru.egar.springcourses.action.turn_actions;

import ru.egar.springcourses.Coordinates;
import ru.egar.springcourses.Map;
import ru.egar.springcourses.action.Action;
import ru.egar.springcourses.entity.creature.Creature;
import ru.egar.springcourses.entity.creature.Herbivore;
import ru.egar.springcourses.entity.creature.Predator;

import java.util.ArrayList;

public class StepByAllCreatures extends Action {

    @Override
    public void Execute(Map map){
        ArrayList<Creature> creatures = map.GetAllCreaturesOfType(new Predator(new Coordinates(0,0)));
        creatures.addAll(map.GetAllCreaturesOfType(new Herbivore(new Coordinates(0,0))));
        for(Creature creature : creatures){
            creature.MakeMove(map);
        }
    }

}
