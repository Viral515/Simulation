package ru.egar.springcourses;
import ru.egar.springcourses.entity.*;
import ru.egar.springcourses.entity.creature.Herbivore;
import ru.egar.springcourses.entity.creature.Predator;
import ru.egar.springcourses.entity.static_objects.Grass;
import ru.egar.springcourses.entity.static_objects.Rock;
import ru.egar.springcourses.entity.static_objects.Tree;

public class Renderer {

    private static final String HERBIVORE  = " \uD83D\uDC14 ";
    private static final String PREDATOR   = " \uD83E\uDD8A ";
    private static final String ROCK       = " ⚫ ";
    private static final String TREE       = " \uD83C\uDF32 ";
    private static final String GRASS      = " \uD83C\uDF40 ";
    private static final String EMPTY      = "    ";

    private final int mapSize;
    private final Map map;

    public Renderer(Map map) {
        this.mapSize = map.GetMapSize();
        this.map = map;
    }

    public void RenderMap() {
        DrawBorder();

        for (int y = 0; y < mapSize; y++) {
            System.out.print("│");
            for (int x = 0; x < mapSize; x++) {
                Entity tile = map.GetTile(new Coordinates(x, y));

                String symbol = getString(tile);
                System.out.print(symbol);
            }
            System.out.println("│");
        }
        DrawBorder();
        System.out.flush();
    }

    private static String getString(Entity tile) {
        String symbol = EMPTY;
        if (tile != null) {
            switch (tile) {
                case Herbivore _ -> symbol = HERBIVORE;
                case Predator _ -> symbol = PREDATOR;
                case Rock _ -> symbol = ROCK;
                case Tree _ -> symbol = TREE;
                case Grass _ -> symbol = GRASS;
                default -> {
                }
            }
        }
        return symbol;
    }

    private void DrawBorder(){
        for (int i = 0; i < mapSize * 4 + 4; i++) {
            System.out.print("_");
        }
        System.out.println();
    }
}