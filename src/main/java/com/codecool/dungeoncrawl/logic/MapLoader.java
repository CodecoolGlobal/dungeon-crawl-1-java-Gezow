package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class MapLoader {
    private static LinkedList<Actor> monsters;
    public static GameMap loadMap() {
        monsters = new LinkedList<>();
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            Walker walker = new Walker(cell);
                            monsters.add(walker);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            Runner runner = new Runner(cell);
                            monsters.add(runner);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            Bulky bulky = new Bulky(cell);
                            monsters.add(bulky);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    public static LinkedList<Actor> getMonsters() {
        return monsters;
    }
}
