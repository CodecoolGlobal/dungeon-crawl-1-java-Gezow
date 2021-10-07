package com.codecool.dungeoncrawl.logic.items.guns;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Direction;

public class AutomaticRifle extends Gun{
    public AutomaticRifle(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        if(isActive) return "automaticrifle-active";
        else return "automaticrifle";
    }

    @Override
    public void shoot(Cell cell, Direction direction) {
        super.shoot(cell, direction, 8);
        switch (direction){
            case EAST:
            case WEST:
                super.shoot(cell.getNeighbor(0, 1), direction, 8);
                super.shoot(cell.getNeighbor(0, -1), direction, 8);
                break;
            case SOUTH:
            case NORTH:
                super.shoot(cell.getNeighbor(1, 0), direction, 8);
                super.shoot(cell.getNeighbor(-1, 0), direction, 8);
                break;
        }
    }
}
