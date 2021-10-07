package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Settings;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Bullet;
import com.codecool.dungeoncrawl.logic.actors.Direction;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.LinkedList;

public class AutomaticMovement implements Runnable {
    private LinkedList<Actor> monsters;
    private final Player player;
    private final Main main;

    public AutomaticMovement(LinkedList<Actor> monsters, Player player, Main main) {
        this.monsters = monsters;
        this.player = player;
        this.main = main;
    }

    @Override
    public void run() {
        int frag = 1;
        while (true) {
            if(player.getInventory().getActiveGun() != null) {
                LinkedList<Bullet> bullets = player.getInventory().getActiveGun().getBullets();
                bullets.removeIf(bullet -> !bullet.isAlive());
                for (Bullet bullet : bullets) {
                    bullet.move(bullet.getDirection().getX(), bullet.getDirection().getY());
                }
            }
            monsters.removeIf(monster -> !monster.isAlive());
            if (monsters.size() > 0) {
                for (Actor monster : monsters) {
                    if (monster.canAttackPlayer()) {
                        monster.attack(player);
                    } else {
                        monster.autoMove(frag, player);
                    }
                }
            }
            main.refresh();
            frag ++;
            try {
                Thread.sleep(210 - (Settings.GAME_SPEED.getValue()* 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
