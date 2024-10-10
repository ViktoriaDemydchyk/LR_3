// WarriorDroid.java
package droids;

public class WarriorDroid extends Droid {
    public WarriorDroid(String name) {
        super(name, 100, 20); // Високе здоров'я, середні ушкодження
    }

    @Override
    public void attack(Droid opponent) {
        System.out.println(this.name + " атакує " + opponent.getName() + "!");
        opponent.takeDamage(this.damage);
    }
}
