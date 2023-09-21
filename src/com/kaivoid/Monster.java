package com.kaivoid;

public class Monster extends Creature {

    public Monster(String name, int attack, int protection,
                   int damageMin, int damageMax, int healthMax) throws Exception {
        super(name, attack, protection, damageMin, damageMax, healthMax);
    }
}