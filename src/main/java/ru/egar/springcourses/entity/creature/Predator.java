package ru.egar.springcourses.entity.creature;

import ru.egar.springcourses.Coordinates;
import ru.egar.springcourses.Map;


public class Predator extends Creature{

    @Override
    public void MakeMove(Map map){
        if (this.getTargetTileCoordinates() == null){
            MoveToTarget(map, new Herbivore(new Coordinates(0,0)));
        }
        else {
            Eat(map);
        }
    }

    public Predator(Coordinates coordinates) {
        super(coordinates);
        this.setHP(5);
    }
}
