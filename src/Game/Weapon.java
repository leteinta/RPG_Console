package Game;

public abstract class Weapon {
    //ATTRIBUTS/PROPRIETES
    private String name;
    private int price;
    private int damage;

    //CONSTRUCTEUR
    public Weapon(String varName, int varPrice, int varDamage){
        this.setNom(varName);
        this.setPrix(varPrice);
        this.setDomage(varDamage);
    }

    //METHODES
    //--Getters
    public int getPrice() {
        return price;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    //--Setters

    public void setPrix(int price) {
        this.price = price;
    }

    public void setDomage(int damage) {
        this.damage = damage;
    }

    public void setNom(String name) {
        this.name = name;
    }

    public abstract String ascii_art();
}
