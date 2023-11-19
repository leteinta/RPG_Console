package Game;

import java.util.*;
import java.util.Scanner;

public class Map {
    private int GRID_SIZE_X = 10;
    private int GRID_SIZE_Y = 10;
    private int[][] grid;
    private Player player;
    private List<Monster> monsters;
    private List<Obstacle> obstacles;
    Scanner scanner = new Scanner(System.in);
    public Map(Player player) {
        this.player = player;
        this.monsters = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        initializeMap();
        placePlayer();
        placeExit();
        placeWall();
        placeMonsters(); // Place 5 monstres initialement
        placeObstacles(); // Place 8 obstacles initialement

    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    private void initializeMap() {
        // Initialisez la grille avec des cases vides (0)
        grid = new int[GRID_SIZE_X][GRID_SIZE_Y]; // Par exemple, une grille de 5x5
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 0;
            }
        }
    }

    private void placeExit() {
        // Placez la sortie (4) à une position initiale, ici, en bas à droite
        grid[grid.length - 1][grid[0].length - 1] = 4;
    }

    private void placePlayer() {
        // Placez le joueur (1) à une position initiale, ici, en haut à gauche
        grid[player.getX()][player.getY()] = 1;
    }

    private void placeWall() {
        // Placez les murs (5) à des positions initiale
        grid[0][2] = 5;
        grid[1][0] = 5;
        grid[1][4] = 5;
        grid[1][5] = 5;
        grid[1][7] = 5;
        grid[1][8] = 5;
        grid[1][9] = 5;
        grid[2][3] = 5;
        grid[2][4] = 5;
        grid[2][8] = 5;
        grid[2][9] = 5;
        grid[3][1] = 5;
        grid[3][3] = 5;
        grid[3][6] = 5;
        grid[4][3] = 5;
        grid[4][4] = 5;
        grid[4][5] = 5;
        grid[4][8] = 5;
        grid[5][0] = 5;
        grid[5][1] = 5;
        grid[5][7] = 5;
        grid[5][8] = 5;
        grid[6][0] = 5;
        grid[6][1] = 5;
        grid[6][3] = 5;
        grid[6][4] = 5;
        grid[6][5] = 5;
        grid[6][7] = 5;
        grid[7][3] = 5;
        grid[7][7] = 5;
        grid[7][8] = 5;
        grid[7][9] = 5;
        grid[8][1] = 5;
        grid[8][5] = 5;
        grid[8][8] = 5;
        grid[8][9] = 5;
        grid[9][1] = 5;
        grid[9][2] = 5;
        grid[9][5] = 5;
        grid[9][6] = 5;
    }


    private void placeMonsters() {
        // Placez les monstres à des positions aléatoires
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int x = random.nextInt(grid.length);
            int y = random.nextInt(grid[0].length);

            // Assurez-vous que la case est vide avant de placer le monstre
            while (grid[x][y] != 0) {
                x = random.nextInt(grid.length);
                y = random.nextInt(grid[0].length);
            }

            grid[x][y] = 2; // Placez le monstre
            monsters.add(new Monster("Monster " + (i + 1), 80, x, y)); // Ajoutez le monstre à la liste
        }
    }

    private void placeObstacles() {
        // Placez les obstacles (3) à des positions aléatoires
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int x = random.nextInt(grid.length);
            int y = random.nextInt(grid[0].length);

            // Assurez-vous que la case est vide avant de placer l'obstacle
            while (grid[x][y] != 0) {
                x = random.nextInt(grid.length);
                y = random.nextInt(grid[0].length);
            }

            grid[x][y] = 3; // Placez l'obstacle
            obstacles.add(new Obstacle("Obstacle " + (i + 1), 15, x, y)); // Ajoutez l'obstacle à la liste
        }
    }

    public void displayMap() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(getSymbol(grid[i][j]) + " ");
            }
            System.out.println(); // Nouvelle ligne pour passer à la ligne suivante
        }
    }

    private String getSymbol(int value) {
        // Convertit la valeur de la case en un symbole pour l'affichage
        switch (value) {
            case 0:
                return "."; // Case vide
            case 1:
                return "P"; // Position du joueur
            case 2:
                return "M"; // Monstre
            case 3:
                return "O"; // Obstacle
            case 4:
                return "E"; // Sortie
            case 5:
                return "#"; // Mur
            default:
                return "?"; // Symbole inconnu
        }
    }

    public List<Integer> canMove() {
        List<Integer> possibleDirections = new ArrayList<>();
        int currentX = player.getX();
        int currentY = player.getY();

        if (currentX > 0 && (grid[currentX - 1][currentY] == 0 || grid[currentX - 1][currentY] == 4)) {
            possibleDirections.add(Direction.UP);
        }

        if (currentX < grid.length - 1 && (grid[currentX + 1][currentY] == 0 || grid[currentX + 1][currentY] == 4)) {
            possibleDirections.add(Direction.DOWN);
        }

        if (currentY > 0 && (grid[currentX][currentY - 1] == 0 || grid[currentX][currentY - 1] == 4)) {
            possibleDirections.add(Direction.LEFT);
        }

        if (currentY < grid[0].length - 1 && (grid[currentX][currentY + 1] == 0 || grid[currentX][currentY + 1] == 4)) {
            possibleDirections.add(Direction.RIGHT);
        }

        return possibleDirections;
    }

    public List<Integer> canAttack() {
        List<Integer> possibleAttacks = new ArrayList<>();
        int currentX = player.getX();
        int currentY = player.getY();

        if (currentX > 0 && (grid[currentX - 1][currentY] == 2 || grid[currentX - 1][currentY] == 3)) {
            possibleAttacks.add(Direction.UP);
        }

        if (currentX < grid.length - 1 && (grid[currentX + 1][currentY] == 2 || grid[currentX + 1][currentY] == 3)) {
            possibleAttacks.add(Direction.DOWN);
        }

        if (currentY > 0 && (grid[currentX][currentY - 1] == 2 || grid[currentX][currentY - 1] == 3)) {
            possibleAttacks.add(Direction.LEFT);
        }

        if (currentY < grid[0].length - 1 && (grid[currentX][currentY + 1] == 2 || grid[currentX][currentY + 1] == 3)) {
            possibleAttacks.add(Direction.RIGHT);
        }

        return possibleAttacks;
    }

    public void movePlayer(int direction) {
        List<Integer> possibleDirections = canMove();

        if (possibleDirections.contains(direction)) {
            // Effacez la position actuelle du joueur
            grid[player.getX()][player.getY()] = 0;

            if (possibleDirections.contains(direction)) {

                // Déplacez le joueur dans la nouvelle position
                switch (direction) {
                    case Direction.UP:
                        player.setX(player.getX() - 1);
                        break;
                    case Direction.DOWN:
                        player.setX(player.getX() + 1);
                        break;
                    case Direction.LEFT:
                        player.setY(player.getY() - 1);
                        break;
                    case Direction.RIGHT:
                        player.setY(player.getY() + 1);
                        break;
                }

            } else {
                System.out.println("Mouvement invalide, veuillez choisir une direction parmi : " + possibleDirections);
            }
        }
        grid[player.getX()][player.getY()] = 1;
    }

    public Player getPlayer() {
        return player;
    }

    public void attack(int x, int y) {
        // Si la case contient un obstacle
        if (grid[x][y] == 3) {
            System.out.println("Vous allez attaquer un obstacle !");
            Obstacle obstacleToRemove = null;
            // Recherchez l'obstacle à la position actuelle dans la liste
            for (Obstacle obstacle : obstacles) {
                if (obstacle.getX() == x && obstacle.getY() == y) {
                    if(player.attack(obstacle)){
                        grid[x][y] = 0;
                        obstacleToRemove = obstacle;
                    }
                }
            }
            if(obstacleToRemove != null){
                obstacles.remove(obstacleToRemove); // Supprimez l'obstacle
            }
        }else if (grid[x][y] == 2) {
            System.out.println("Vous allez attaquer un monstre !");
            Monster monsterToRemove = null;
            // Recherchez le monstre à la position actuelle dans la liste
            for (Monster monster : monsters) {
                if (monster.getX() == x && monster.getY() == y) {
                    if(player.attack(monster)){
                        grid[x][y] = 0;
                        monsterToRemove = monster;
                    }
                }
            }
            if(monsterToRemove != null){
                monsters.remove(monsterToRemove); // Supprimez le monstre
            }
        }
    }
    public boolean isExitReached() {
        // Les coordonnées de la sortie
        int exitX = grid.length - 1;
        int exitY = grid[0].length - 1;

        // Vérifiez si les coordonnées du joueur correspondent à la sortie
        return player.getX() == exitX && player.getY() == exitY;
    }

}


