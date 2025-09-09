package ru.egar.springcourses.action.init_actions;

import ru.egar.springcourses.Map;
import ru.egar.springcourses.action.Action;
import ru.egar.springcourses.enums.EntityType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SpawnEntities extends Action {
    private static final Properties PROPERTIES = new Properties();

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

    @Override
    public void Execute(Map map){
        map.AddEntity(EntityType.HERBIVORE, Integer.parseInt(PROPERTIES.getProperty("HerbivoreCount")));
        map.AddEntity(EntityType.PREDATOR, Integer.parseInt(PROPERTIES.getProperty("PredatorCount")));
        map.AddEntity(EntityType.GRASS, Integer.parseInt(PROPERTIES.getProperty("GrassCount")));
        map.AddEntity(EntityType.TREE, Integer.parseInt(PROPERTIES.getProperty("TreeCount")));
        map.AddEntity(EntityType.ROCK, Integer.parseInt(PROPERTIES.getProperty("RockCount")));
    }

}
