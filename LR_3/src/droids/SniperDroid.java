// SniperDroid.java
package droids;

public class SniperDroid extends Droid {
    public SniperDroid(String name) {
        super(name, 60, 40); // Низьке здоров'я, високі ушкодження
    }

    @Override
    public void attack(Droid opponent) {
        System.out.println(this.name + " стріляє по " + opponent.getName() + "!");
        opponent.takeDamage(this.damage);
    }
}
