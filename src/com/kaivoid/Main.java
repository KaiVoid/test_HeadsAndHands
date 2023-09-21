package com.kaivoid;

public class Main {

    public static void main(String[] args) throws Exception {
        Player player = new Player("Sam", 5, 5, 2, 6, 10);
        Monster monster = new Monster("Gargantua", 5, 5, 2, 4, 40);

        int round = 1;
        while (player.isAlive() && monster.isAlive()) {
            player.hitEnemy(monster);
            monster.hitEnemy(player);
            player.healing();
            System.out.println("++ " + round++ + " ROUND ++\n");
        }

        if (player.isAlive())
            System.out.println(player.getName() + " WIN!!");

        if (monster.isAlive())
            System.out.println(monster.getName() + " WIN!!");
    }
}