package Game;
import Game.Factions.Elf;
import Game.Factions.Warrior;
import Game.Factions.Witch;

import java.util.List;
import java.util.Scanner;

public class MainGame {
       public static void main(String[] args) {
              Scanner scanner = new Scanner(System.in);
              System.out.print("Choisissez votre nom :");
              String name = scanner.nextLine();

              // Affichez les castes disponibles et demandez au joueur de choisir
              System.out.println("Choisissez votre caste :");
              System.out.println("1. Sorcier");
              System.out.println("2. Elfe");
              System.out.println("3. Guerrier");
              System.out.print("Entrez le numéro de la caste : ");
              int casteChoice = scanner.nextInt();

              Player p = createPlayer(name, casteChoice);

              WeaponStore store = new WeaponStore();

              Map map= new Map(p);

              boolean gameOver = false;
              map.displayMap();
              while (!gameOver) {
                     // Demandez au joueur de choisir une direction parmi les directions possibles
                     System.out.println("Choisissez de se déplacer, d'attaquer, d'ouvrir le shop ou voir votre inventaire : ");
                     System.out.println("1. Se déplacer");
                     System.out.println("2. Attaquer");
                     System.out.println("3. Ouvrir le shop");
                     System.out.println("4. Voir l'inventaire");
                     System.out.print("Entrez le numéro de l'action à effectuer : ");
                     int action = scanner.nextInt();
                     if(action == 1){
                            List<Integer> possibleDirections = map.canMove();
                            if (!possibleDirections.isEmpty()) {
                                   System.out.println("Choisissez une direction en entrant le numéro correspondant :");

                                   // Affichez les descriptions correspondantes aux directions possibles
                                   for (Integer direction : possibleDirections) {
                                          switch (direction) {
                                                 case Direction.UP:
                                                        System.out.println(Direction.UP + ". " + Direction.UP_DESCRIPTION);
                                                        break;
                                                 case Direction.DOWN:
                                                        System.out.println(Direction.DOWN + ". " + Direction.DOWN_DESCRIPTION);
                                                        break;
                                                 case Direction.LEFT:
                                                        System.out.println(Direction.LEFT + ". " + Direction.LEFT_DESCRIPTION);
                                                        break;
                                                 case Direction.RIGHT:
                                                        System.out.println(Direction.RIGHT + ". " + Direction.RIGHT_DESCRIPTION);
                                                        break;
                                          }
                                   }
                                   int chosenDirection = -1; // Initialisation à une valeur invalide

                                   // S'assurez que l'entrée est un entier valide et une direction possible
                                   while (chosenDirection == -1 || !possibleDirections.contains(chosenDirection)) {
                                          // Vérifiez si l'entrée est un entier
                                          if (scanner.hasNextInt()) {
                                                 chosenDirection = scanner.nextInt();
                                          } else {
                                                 scanner.next();
                                                 System.out.println("Veuillez entrer un nombre entier.");
                                          }

                                          // Vérifiez si la direction est possible
                                          if (!possibleDirections.contains(chosenDirection)) {
                                                 System.out.println("Direction invalide. Veuillez choisir parmi : " + possibleDirections);
                                          }
                                   }
                                   map.movePlayer(chosenDirection);
                            } else {
                                   System.out.println("Aucune direction possible !");
                            }
                            map.displayMap();
                            // Vérifiez si le joueur a atteint la sortie (ou une condition de fin)
                            if (map.isExitReached()) {
                                   System.out.println("Félicitations, vous avez atteint la sortie !");
                                   gameOver = true;
                            }
                     }else if(action == 2){
                            List<Integer> possibleAttacks = map.canAttack();
                            if (!possibleAttacks.isEmpty()) {
                                   System.out.println("Choisissez une case à attaquer :");
                                   for (Integer attack : possibleAttacks) {
                                          switch (attack) {
                                                 case Direction.UP:
                                                        System.out.println(Direction.UP + ". " + Direction.UP_DESCRIPTION);
                                                        break;
                                                 case Direction.DOWN:
                                                        System.out.println(Direction.DOWN + ". " + Direction.DOWN_DESCRIPTION);
                                                        break;
                                                 case Direction.LEFT:
                                                        System.out.println(Direction.LEFT + ". " + Direction.LEFT_DESCRIPTION);
                                                        break;
                                                 case Direction.RIGHT:
                                                        System.out.println(Direction.RIGHT + ". " + Direction.RIGHT_DESCRIPTION);
                                                        break;
                                          }
                                   }
                                   int chosenDirection = -1; // Initialisation à une valeur invalide

                                   // S'assurez que l'entrée est un entier valide et une direction possible
                                   while (chosenDirection == -1 || !possibleAttacks.contains(chosenDirection)) {
                                          // Vérifiez si l'entrée est un entier
                                          if (scanner.hasNextInt()) {
                                                 chosenDirection = scanner.nextInt();
                                          } else {
                                                 // Nettoyez le buffer de scanner en cas d'une entrée invalide
                                                 scanner.next();
                                                 System.out.println("Veuillez entrer un nombre entier.");
                                          }

                                          // Vérifiez si la direction est possible
                                          if (!possibleAttacks.contains(chosenDirection)) {
                                                 System.out.println("Direction invalide. Veuillez choisir parmi : " + possibleAttacks);
                                          }
                                   }
                                   int x = map.getPlayer().getX();
                                   int y = map.getPlayer().getY();
                                   if(chosenDirection == Direction.UP){
                                          x = map.getPlayer().getX()-1;
                                   }else if(chosenDirection == Direction.DOWN){
                                          x = map.getPlayer().getX()+1;
                                   }else if(chosenDirection == Direction.RIGHT){
                                          y = map.getPlayer().getY()+1;
                                   }else if(chosenDirection == Direction.LEFT){
                                          y = map.getPlayer().getY()-1;
                                   }
                                   map.attack(x, y);
                            } else {
                                   System.out.println("Aucune attaque possible !");
                            }
                            map.displayMap();
                     }else if(action == 3){
                            store.printWeaponsList();
                            System.out.println("Vous avez " + p.getMoney() + " pièces d'or.");
                            int weaponSelected = -1;
                            while (weaponSelected < 0 || weaponSelected >= store.getWeaponsList().size()) {
                                   System.out.println("Quelle arme voulez-vous acheter : ");
                                   // Vérifiez si l'entrée est un entier
                                   if (scanner.hasNextInt()) {
                                          weaponSelected = scanner.nextInt();
                                   } else {
                                          scanner.next();
                                          System.out.println("Veuillez entrer un nombre entier.");
                                   }

                                   if (weaponSelected < 0 || weaponSelected >= store.getWeaponsList().size()) {
                                          System.out.println("Numéro d'arme inconnu !");
                                   }
                            }
                            Weapon weapon = store.getWeapon(weaponSelected);
                            map.getPlayer().buyWeapon(weapon);
                     }else if(action == 4){
                            // Affichez la quantité d'argent du joueur
                            System.out.println("Vous avez " + p.getMoney() + " pièces d'or.");

                            System.out.println("Vous possédez les armes suivantes :");
                            for (Weapon weapon : p.getWeapons()) {
                                   int totalDamage = weapon.getDamage() + p.getFaction().getStrength();
                                   System.out.println("- " + weapon.getName() + " (Dégâts : " + totalDamage + ")");
                            }
                     }
              }
       }
       private static Player createPlayer(String playerName, int casteChoice) {
              Faction faction;

              switch (casteChoice) {
                     case 1:
                            faction = new Witch();
                            break;
                     case 2:
                            faction = new Elf();
                            break;
                     case 3:
                            faction = new Warrior();
                            break;
                     default:
                            System.out.println("Choix de caste invalide. Création d'un joueur par défaut.");
                            faction = new Faction(playerName, 100, 100); // Valeurs par défaut pour une faction générique
                            break;
              }

              return new Player(playerName, faction);
       }
}



