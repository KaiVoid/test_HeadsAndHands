package com.kaivoid;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Creature {

    private final String name;
    protected final int attack;
    protected final int protection;
    protected final int damageMin;
    protected final int damageMax;
    protected final int healthMax;

    private int health;

    public Creature(String name, int attack, int protection, int damageMin, int damageMax, int healthMax) throws Exception {
        if (name == null)
            throw new Exception(String.format("Wrong value: name=%s value=%s", "Name", null));
        validateInt(attack, 1, 30, "attack");
        validateInt(protection, 1, 30, "protection");
        validateInt(damageMin, 1, null, "damage_min");
        validateInt(damageMax, damageMin, null, "damage_max");
        validateInt(healthMax, 1, null, "health_max");
        this.name = name;
        this.attack = attack;
        this.protection = protection;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.healthMax = healthMax;
        this.health = healthMax;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int getHealth() {
        return health;
    }

    protected void addHealth(int addHealth) {
        if (this.health == 0) {
            System.out.println("Can't heal a dead creature.");
        }
        if (this.health < healthMax) {
            int tempHealth = this.health + addHealth;
            if (tempHealth > healthMax) {
                System.out.println(this.name + " has regained " + (healthMax - this.health) + "Health=" + healthMax);
                this.health = healthMax;
            } else {
                System.out.println(this.name + " has regained " + (addHealth) + "Health=" + tempHealth);
                this.health = tempHealth;
            }
        }
    }

    public void hitEnemy(Creature enemy) {
        int attackModifier = 1 + this.attack - enemy.protection;
        if (isDiceThrownSuccessfully(attackModifier)) {
            int damage = ThreadLocalRandom.current().nextInt(damageMin, damageMax);
            enemy.takeTheDamage(damage);
            System.out.printf("%s dealt %s damage to %s%n", name, damage, enemy.name);
        } else {
            System.out.printf("%s missed.%n", name);
        }
    }

    private void takeTheDamage(int damage) {
        int tempHealth = this.health - damage;
        this.health = Math.max(tempHealth, 0);
        String message = String.format("%s received %s hit.", this.name, damage)
                + ((this.health == 0) ? (this.name + " is death.") : ("Health=" + this.health));
        System.out.println(message);
    }

    public static boolean isDiceThrownSuccessfully(int numberOfDice) {
        if (numberOfDice < 0) {
            numberOfDice = 1;
        }
        for (int i = 0; i < numberOfDice; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
            if (randomNum >= 5) {
                return true;
            }
        }
        return false;
    }

    private void validateInt(int val, int min, Integer max, String fieldName) throws Exception {
        if (val < min || (max != null && val > max))
            throw new Exception(String.format("Wrong value: name=%s value=%s", fieldName, val));
    }
}