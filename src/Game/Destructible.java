package Game;
public class Destructible {
    private String name;
    private int healthPoints;
    private int x; // Coordonnée X
    private int y; // Coordonnée Y

    public Destructible(String name, int healthPoints, int x, int y) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }


    public void takeDamage(int damage) {
        if (damage > 0) {
            healthPoints -= damage;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
