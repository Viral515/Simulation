package ru.egar.springcourses.entity.creature;

import ru.egar.springcourses.Coordinates;
import ru.egar.springcourses.Map;
import ru.egar.springcourses.entity.static_objects.Grass;

public class Herbivore extends Creature {

    @Override
    public void MakeMove(Map map){
        MoveToTarget(map, new Grass(new Coordinates(0,0)));
        if (this.getTargetTileCoordinates() != null){
            Eat(map);
        }
    }

    public Herbivore(Coordinates coordinates) {
        super(coordinates);
        this.setMaxHP(2);
        this.setHP(this.getMaxHP());
    }
}
