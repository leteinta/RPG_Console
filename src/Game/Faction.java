package Game;

public class Faction {
    private static String nameF;
    private  int strength;
    private int mana;



    public Faction(String nameF, int strength, int mana) {
        this.nameF = nameF;
        this.mana = mana;
        this.strength = strength;

    }

    //METHODES
    //--Getters
    public String getNameF() {
        return nameF;
    }

    public  int getStrength() {
        return strength;
    }

    public int getMana() {
        return mana;
    }
}
