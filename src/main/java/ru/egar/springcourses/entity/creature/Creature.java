package ru.egar.springcourses.entity.creature;


import lombok.Getter;
import lombok.Setter;
import ru.egar.springcourses.BreadthFirstSearch;
import ru.egar.springcourses.Coordinates;
import ru.egar.springcourses.Map;
import ru.egar.springcourses.entity.Entity;

@Getter
@Setter
public abstract class Creature extends Entity {
    private int HP;
    private int maxHP;
    private int moveSpeed;
    private Coordinates targetTileCoordinates = null;

    public abstract void MakeMove(Map map);

    public Creature(Coordinates coordinates) {
        super(coordinates);
    }

    protected void MoveToTarget(Map map, Entity targetType) {
        Coordinates nextStepCoordinates = BreadthFirstSearch.SearchFirstStepToTarget(map, this, targetType);
        if (nextStepCoordinates == null) {
            return;
        }
        if (map.GetTile(nextStepCoordinates) != null){
            if (map.GetTile(nextStepCoordinates).getClass().equals(targetType.getClass())) {
                targetTileCoordinates = nextStepCoordinates;
                return;
            }
        }
        map.RemoveTile(this.getCoordinates());
        this.getCoordinates().setX(nextStepCoordinates.getX());
        this.getCoordinates().setY(nextStepCoordinates.getY());
        map.SetTile(nextStepCoordinates, this);
    }

    protected void Eat(Map map) {
        this.HP+= 1;
        if (this.HP > this.maxHP) {
            this.HP = this.maxHP;
        }
        Entity entity = map.GetTile(targetTileCoordinates);
        if ((entity instanceof Creature) && ((Creature) entity).getHP()-1 > 0) {
            ((Creature) entity).setHP(((Creature) entity).getHP()-1);
        }
        else {
            map.RemoveTile(targetTileCoordinates);
        }
        targetTileCoordinates = null;
    }
}
