package ru.egar.springcourses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Coordinates {

    int x;
    int y;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        return x == ((Coordinates) obj).x && y == ((Coordinates) obj).y;
    }

    @Override
    public int hashCode() {
        return x+y;
    }
}
