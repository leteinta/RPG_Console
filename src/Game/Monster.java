package Game;

public class Monster extends Destructible{

    public Monster(String name, int healthPoints, int x, int y) {
        super(name, healthPoints, x, y);
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage); // Appel de la méthode takeDamage de la classe Destructible

        if (getHealthPoints() <= 0) {
            System.out.println(getName() + " a été vaincu !");
        } else {
            System.out.println(getName() + " a subi " + damage + " points de dégâts. Points de vie restants : " + getHealthPoints());
        }
    }
}
