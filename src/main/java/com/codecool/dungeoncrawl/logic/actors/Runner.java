package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Runner extends Actor {

    public Runner(Cell cell) {
        super(cell, 5, 2);
    }

    @Override
    public String getTileName() {
        return "runner";
    }
}