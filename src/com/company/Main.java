package com.company;

import java.util.Random;
import java.lang.Math;

public class Main {
    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {260, 250, 270,350};
    public static int[] heroesDamage = {15, 20, 10,0};
    public static String[] heroesAttackType = {
            "Physical", "Magical", "Kinetic", "Medical"};

    public static int MedicSupport = randHealth();



    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        isMedicDie();
        System.out.println("++++++++++++++");
        if (bossHealth > 0) {
            chooseBossDefence();
            bossHits();

        }

        heroesHit();
        printStatistics();

    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefence);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(
                            "Critical Damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }

    }



    public static void printStatistics() {
        System.out.println("++++++++++++++");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("++++++++++++++");
    }

    public static int randHealth() {
        int min = 20;
        int max = 40;
        int randomHealth = min + (int) (Math.random() * max);
        return randomHealth;
    }

    public static boolean isMedicDie(){
        if(heroesHealth[3] <= 0){
            System.out.println("Medic is die");
            return true;
        }
        MedicWorks();
        return false;
    }

    public static void MedicWorks() {
        Random random = new Random();
        int a = random.nextInt(2);

        for (int i = 0; i < heroesHealth[a]; i++) {
            if (heroesHealth[a] < 100 && heroesHealth[a] > 0) {
                heroesHealth[a] = heroesHealth[a] + MedicSupport;
                System.out.println("Medic healed the " + heroesAttackType[a] + " on " + MedicSupport + " health point");
            }
        }
    }


    /*
    public static int randHero() {
        int min = 0;
        int max = 2;
        int randomHero = min + (int) (Math.random() * max);
        return randomHero;
    }
    */
    /*
    public static int MedicWorks() {
        int min = 1;
        int max = 80;
        int randomHealth = min + (int) (Math.random() * max);

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth.length <= 100 && heroesHealth.length > 1) {
                heroesHealth[i] = heroesHealth[i] + MedicWorks();
                System.out.println("Support added " + randomHealth + " health point");
            } else {
                System.out.println("All the heroes are fine");
            }
        }
        return randomHealth;
    }
    */
}
