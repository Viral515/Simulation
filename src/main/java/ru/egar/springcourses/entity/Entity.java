package ru.egar.springcourses.entity;

import lombok.Getter;
import ru.egar.springcourses.Coordinates;

@Getter
public class Entity {
    private final Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
