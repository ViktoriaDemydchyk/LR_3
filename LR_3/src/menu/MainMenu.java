// MainMenu.java
package menu;

import battle.BattleStrategies;
import droids.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static List<Droid> droids = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== Головне меню ===");
            System.out.println("1. Створити дроїда");
            System.out.println("2. Показати список дроїдів");
            System.out.println("3. Бій 1 на 1");
            System.out.println("4. Командний бій");
            System.out.println("5. Вийти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createDroid(scanner);
                case 2 -> showDroids();
                case 3 -> fightOneOnOne(scanner);
                case 4 -> teamFight(scanner);
                case 5 -> running = false;
                default -> System.out.println("Неправильний вибір!");
            }
        }
    }

    private static void createDroid(Scanner scanner) {
        System.out.println("Виберіть тип дроїда:");
        System.out.println("1. Warrior");
        System.out.println("2. Medic");
        System.out.println("3. Sniper");

        int choice = scanner.nextInt();
        System.out.println("Введіть ім'я дроїда:");
        String name = scanner.next();

        switch (choice) {
            case 1 -> droids.add(new WarriorDroid(name));
            case 2 -> droids.add(new MedicDroid(name));
            case 3 -> droids.add(new SniperDroid(name));
            default -> System.out.println("Неправильний вибір!");
        }
    }

    private static void showDroids() {
        System.out.println("=== Список дроїдів ===");
        for (int i = 0; i < droids.size(); i++) {
            Droid droid = droids.get(i);
            System.out.println((i + 1) + ". " + droid.getName() + " (Здоров'я: " + droid.getHealth() + ")");
        }
    }

    private static void fightOneOnOne(Scanner scanner) {
        System.out.println("Виберіть двох дроїдів для бою:");
        showDroids();
        int droid1Index = scanner.nextInt() - 1;
        int droid2Index = scanner.nextInt() - 1;

        Droid droid1 = droids.get(droid1Index);
        Droid droid2 = droids.get(droid2Index);

        System.out.println("Бій між " + droid1.getName() + " і " + droid2.getName() + " розпочався!");

        while (droid1.isAlive() && droid2.isAlive()) {
            if (droid1 instanceof MedicDroid) {
                System.out.println(droid1.getName() + " хилить сам себе!");
                ((MedicDroid) droid1).heal(droid1);
            } else {
                droid1.attack(droid2);
            }

            if (droid2.isAlive()) {
                if (droid2 instanceof MedicDroid) {
                    System.out.println(droid2.getName() + " хилить сам себе!");
                    ((MedicDroid) droid2).heal(droid2);
                } else {
                    droid2.attack(droid1);
                }
            }
        }

        if (droid1.isAlive()) {
            System.out.println(droid1.getName() + " переміг!");
        } else {
            System.out.println(droid2.getName() + " переміг!");
        }
    }


    private static void teamFight(Scanner scanner) {
        List<Droid> team1 = new ArrayList<>();
        List<Droid> team2 = new ArrayList<>();

        System.out.println("Виберіть дроїдів для команди 1:");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName());
        }

        System.out.println("Введіть кількість дроїдів у команді 1:");
        int team1Size = scanner.nextInt();
        for (int i = 0; i < team1Size; i++) {
            int droidIndex = scanner.nextInt() - 1;
            team1.add(droids.get(droidIndex));
        }

        System.out.println("Виберіть дроїдів для команди 2:");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName());
        }

        System.out.println("Введіть кількість дроїдів у команді 2:");
        int team2Size = scanner.nextInt();
        for (int i = 0; i < team2Size; i++) {
            int droidIndex = scanner.nextInt() - 1;
            team2.add(droids.get(droidIndex));
        }

        System.out.println("Бій між командами розпочався!");

        BattleStrategies.randomTeamFight(team1, team2);

        // Після бою оголошуємо переможця
        if (team1.stream().anyMatch(Droid::isAlive)) {
            System.out.println("Команда 1 перемогла!");
        } else {
            System.out.println("Команда 2 перемогла!");
        }
    }
}
