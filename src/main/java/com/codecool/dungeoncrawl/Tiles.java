package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("medkit10", new Tile(12, 26));
        tileMap.put("medkit50", new Tile(11, 26));
        tileMap.put("ammo", new Tile(12, 31));
        tileMap.put("key", new Tile(18,23));
        tileMap.put("crystal", new Tile(18, 22));
        tileMap.put("rocket0", new Tile(15, 23));
        tileMap.put("rocket1", new Tile(15, 22));
        tileMap.put("rocket2", new Tile(15, 21));
        tileMap.put("rocket3", new Tile(15, 20));
        tileMap.put("pistol", new Tile(6, 31));
        tileMap.put("shotgun", new Tile(7, 31));
        tileMap.put("automaticrifle", new Tile(8, 31));
        tileMap.put("bfg", new Tile(11, 31));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
