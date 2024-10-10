// MedicDroid.java
package droids;

public class MedicDroid extends Droid {
    private int healAmount;

    public MedicDroid(String name) {
        super(name, 80, 10); // Середнє здоров'я, низькі ушкодження
        this.healAmount = 15; // Лікування на 15 очок
    }

    @Override
    public void attack(Droid opponent) {
        System.out.println(this.name + " атакує " + opponent.getName() + "!");
        opponent.takeDamage(this.damage);
    }

    public void heal(Droid ally) {
        System.out.println(this.name + " лікує " + ally.getName() + " на " + healAmount + " очок здоров'я!");
        ally.takeDamage(-healAmount);
    }
}
