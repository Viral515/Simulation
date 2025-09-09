package ru.egar.springcourses;

import ru.egar.springcourses.entity.creature.Creature;
import ru.egar.springcourses.entity.creature.Herbivore;
import ru.egar.springcourses.entity.creature.Predator;
import ru.egar.springcourses.entity.static_objects.Grass;
import ru.egar.springcourses.entity.static_objects.Rock;
import ru.egar.springcourses.entity.static_objects.Tree;
import ru.egar.springcourses.enums.EntityType;
import ru.egar.springcourses.entity.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Map {

    private final HashMap<Coordinates, Entity> mapTiles;
    private static final Properties PROPERTIES = new Properties();
    private final int mapSize;

    static {
        try (InputStream input = Map.class.getClassLoader().getResourceAsStream(".properties")) {
            if (input == null) {
                throw new RuntimeException("Файл config.properties не найден в classpath!");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке config.properties", e);
        }
    }

    public Map() {
        mapSize = Integer.parseInt(PROPERTIES.getProperty("MapSize"));
        mapTiles = new HashMap<>();
    }

    public void AddEntity(EntityType type, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Coordinates coordinates;
            do {
                coordinates = new Coordinates(random.nextInt(mapSize), random.nextInt(mapSize));
            } while(mapTiles.containsKey(coordinates));
            mapTiles.put(coordinates, CreateEntity(type, coordinates));
        }
    }

    private Entity CreateEntity(EntityType entityType, Coordinates coordinates) {
        switch (entityType) {
            case EntityType.GRASS -> {
                return new Grass(coordinates);
            }

            case EntityType.HERBIVORE -> {
                return new Herbivore(coordinates);
            }

            case EntityType.PREDATOR -> {
                return new Predator(coordinates);
            }

            case EntityType.ROCK -> {
                return new Rock(coordinates);
            }

            case EntityType.TREE -> {
                return new Tree(coordinates);
            }
        }
        return null;
    }

    public int GetMapSize(){
        return mapSize;
    }

    public Entity GetTile(Coordinates coordinates){
        return mapTiles.get(coordinates);
    }

    public void RemoveTile(Coordinates coordinates){
        mapTiles.remove(coordinates);
    }

    public void SetTile(Coordinates coordinates, Entity tile){
        mapTiles.remove(coordinates);
        mapTiles.put(coordinates, tile);
    }

    public ArrayList<Creature> GetAllCreaturesOfType(Entity type) {
        ArrayList<Creature> currentEntities = new ArrayList<>();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                Coordinates coordinates = new Coordinates(j, i);
                if (GetTile(coordinates) == null) {
                    continue;
                }
                if (GetTile(coordinates).getClass().equals(type.getClass())){
                    currentEntities.add((Creature) GetTile(coordinates));
                }
            }
        }
        return currentEntities;
    }
}
