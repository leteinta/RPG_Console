package Game;

import Game.Wepons.Bow;

import java.util.ArrayList;
import java.util.Scanner;

public class Player implements ActionsPlayer {
    private String name;
    private Faction faction;
    private ArrayList<Weapon> weapons;
    private int healthPoints;
    private int money;
    private int experiencePoints;
    private int x; // Coordonnée X
    private int y; // Coordonnée Y
    Scanner scanner = new Scanner(System.in);

    public Player(String name, Faction faction) {
        this.name = name;
        this.faction = faction;
        this.money = 50;
        this.weapons = new ArrayList<>();
        this.weapons.add(new Bow()); // Ajoute automatiquement l'arc comme première arme
        this.experiencePoints = 0;
        this.healthPoints = getHealthPoints();
        this.x = 0; // Position initiale
        this.y = 0;
    }

    public String getName() {
        return name;
    }

    public Faction getFaction() {
        return faction;
    }

    public ArrayList<Weapon> getWeapons() {
        return this.weapons;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getExperiencePoints() {
        return experiencePoints;
    }

    public int getMoney() {
        return money;
    }
    public int getHealthPoints() {
        return healthPoints;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Méthode pour gagner de l'expérience
    public void gainExperience(int amount) {
        experiencePoints += amount;
    }

    // Méthode pour gagner de l'argent
    public void gainMoney(int amount) {
        money += amount;
    }

    @Override
    public void buyWeapon(Weapon w) {
        if (w.getPrice() <= this.money) {
            this.weapons.add(w);
            this.money -= w.getPrice();
            System.out.println("L'arme a été acheté !");
            System.out.println("Il vous reste "+this.money+" pièces d'or.");
        } else {
            System.out.println("Vous n'avez pas assez de pièces d'or pour acheter cette arme !");
        }
    }

    public boolean attack(Obstacle obstacle) {
        // Sélectionnez une arme pour attaquer
        Weapon selectedWeapon = selectWeaponForAttack();

        if (selectedWeapon != null) {
            int damage = selectedWeapon.getDamage();
            obstacle.takeDamage(damage);
            if(obstacle.getHealthPoints() <= 0){
                // Gagner de l'expérience lorsque l'obstacle est détruit
                gainExperience(5);
            }
        } else {
            System.out.println("Vous n'avez aucune arme pour attaquer.");
        }
        return obstacle.getHealthPoints() <= 0;
    }




    // Méthode pour sélectionner une arme pour l'attaque
    private Weapon selectWeaponForAttack() {
        System.out.println("Choisissez une arme pour attaquer l'obstacle :");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println(i + 1 + ". " + weapons.get(i).getName());
        }

        int weaponIndex = scanner.nextInt();
        weaponIndex--;
        if (weaponIndex >= 0 && weaponIndex < weapons.size()) {
            return weapons.get(weaponIndex);
        } else {
            System.out.println("Choix d'arme invalide. Vous aurez une arme par defaut.");
            return weapons.get(0);
        }
    }



    // Méthode pour attaquer un ennemi
    public boolean attack(Monster monster) {
        Weapon selectedWeapon = selectWeaponForAttack();
        if (selectedWeapon != null) {
            // Utilisez la force de la faction pour ajuster les dégâts de l'arme
            int damage = selectedWeapon.getDamage() + faction.getStrength();
            monster.takeDamage(damage);
            if (monster.getHealthPoints() <= 0) {
                gainExperience(10); // Gagner de l'expérience lorsque le monstre est vaincu
                gainMoney(25); // Gagner de l'argent lorsque le monstre est vaincu
            }
        } else {
            System.out.println("Vous n'avez aucune arme pour attaquer.");
        }
        return monster.getHealthPoints() <= 0;
    }
}