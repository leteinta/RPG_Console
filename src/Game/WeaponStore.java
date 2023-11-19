package Game;

import Game.Wepons.Axe;
import Game.Wepons.Bow;
import Game.Wepons.Hammer;
import java.util.ArrayList;
public class WeaponStore {
    private ArrayList<Weapon> weaponsList;
    public WeaponStore(){
        this.weaponsList = new ArrayList<>();
        this.weaponsList.add(new Bow());
        this.weaponsList.add(new Hammer());
        this.weaponsList.add(new Axe());
    }

    public ArrayList<Weapon> getWeaponsList() {
        return weaponsList;
    }

    public void printWeaponsList() {
        System.out.println("Armes disponibles dans le store:");
        for (Weapon w : this.weaponsList) {
            System.out.println("[" + this.weaponsList.indexOf(w) + "] " + w.getName() +" (Dégats : "+w.getDamage()+", Prix : "+w.getPrice()+ " pièces d'or)" + "\n" + w.ascii_art());
        }
    }
    public Weapon getWeapon(int index) {
        return this.weaponsList.get(index);
    }

}
