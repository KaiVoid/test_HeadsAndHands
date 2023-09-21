package com.kaivoid;

public class Player extends Creature {

    private int healingCount = 4;

    public Player(String name, int attack, int protection,
                  int damageMin, int damageMax, int healthMax) throws Exception {
        super(name, attack, protection, damageMin, damageMax, healthMax);
    }

    public void healing() {
        if (healingCount > 0) {
            if (getHealth() < healthMax) {
                int addHp = (int) Math.round(healthMax * 0.3);
                addHealth(addHp);
                healingCount--;
            }
        } else {
            System.out.println("First aid kits are out");
        }
    }
}