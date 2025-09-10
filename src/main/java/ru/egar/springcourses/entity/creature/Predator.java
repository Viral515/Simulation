package ru.egar.springcourses.entity.creature;

import ru.egar.springcourses.Coordinates;
import ru.egar.springcourses.Map;


public class Predator extends Creature{

    @Override
    public void MakeMove(Map map){
        MoveToTarget(map, new Herbivore(new Coordinates(0,0)));
        if (this.getTargetTileCoordinates() != null){
            Eat(map);
        }
    }

    public Predator(Coordinates coordinates) {
        super(coordinates);
        this.setMaxHP(5);
        this.setHP(this.getMaxHP());
    }
}
