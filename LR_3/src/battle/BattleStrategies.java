package battle;

import droids.MedicDroid;
import droids.Droid;

import java.util.List;
import java.util.Random;

public class BattleStrategies {

    public static void randomTeamFight(List<Droid> team1, List<Droid> team2) {
        Random random = new Random();

        System.out.println("Командний бій розпочався!");

        // Бій триває, поки в обох командах є живі дроїди
        while (team1.stream().anyMatch(Droid::isAlive) && team2.stream().anyMatch(Droid::isAlive)) {
            // Випадковий вибір атакуючого дроїда з команди 1
            Droid attacker1 = team1.get(random.nextInt(team1.size()));
            Droid target2 = team2.get(random.nextInt(team2.size()));

            // Якщо це MedicDroid, він хилить союзника, інакше атакує
            if (attacker1.isAlive()) {
                if (attacker1 instanceof MedicDroid) {
                    Droid teammate1 = team1.get(random.nextInt(team1.size()));
                    if (teammate1.isAlive()) {
                        System.out.println(attacker1.getName() + " з команди 1 хилить " + teammate1.getName() + "!");
                        ((MedicDroid) attacker1).heal(teammate1); // Виклик методу зцілення
                    }
                } else if (target2.isAlive()) {
                    System.out.println(attacker1.getName() + " з команди 1 атакує " + target2.getName() + " з команди 2!");
                    attacker1.attack(target2);
                }
            }

            // Перевірка, чи є живі дроїди у команді 2 після атаки
            if (team2.stream().noneMatch(Droid::isAlive)) {
                break;
            }

            // Випадковий вибір атакуючого дроїда з команди 2
            Droid attacker2 = team2.get(random.nextInt(team2.size()));
            Droid target1 = team1.get(random.nextInt(team1.size()));

            if (attacker2.isAlive()) {
                if (attacker2 instanceof MedicDroid) {
                    Droid teammate2 = team2.get(random.nextInt(team2.size()));
                    if (teammate2.isAlive()) {
                        System.out.println(attacker2.getName() + " з команди 2 хилить " + teammate2.getName() + "!");
                        ((MedicDroid) attacker2).heal(teammate2); // Виклик методу зцілення
                    }
                } else if (target1.isAlive()) {
                    System.out.println(attacker2.getName() + " з команди 2 атакує " + target1.getName() + " з команди 1!");
                    attacker2.attack(target1);
                }
            }
        }

        System.out.println("Бій завершено!");
    }
}
