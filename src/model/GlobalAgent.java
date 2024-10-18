package model;

import java.awt.Color;
import java.util.Random;

public abstract class GlobalAgent {
    private int posX;
    private int posY;
    private int size;
    private int targetX;
    private int targetY;
    private Random random;
    private Color color;

    public GlobalAgent(int posX, int posY, int size, int targetX, int targetY) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.targetX = targetX;
        this.targetY = targetY;
        this.random = new Random();
        this.color = Color.BLUE;
    }

    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void move() { // A intégrer dans DumbAgent.java
        if (hasReachedTarget()) {
            selectRandomTarget();
        }

        // Direction vers la cible
        int dirX = targetX - posX;
        int dirY = targetY - posY;

        // Distance euclidienne entre l'agent et sa cible
        double distance = Math.sqrt(dirX * dirX + dirY * dirY);
        if (distance > 0) {
            double speed = 20 / size; // Vitesse indexée sur la taille (pas utile pour le moment)
            double stepSize = speed / distance;
            dirX = (int) (dirX * stepSize);
            dirY = (int) (dirY * stepSize);
        }

        int newX = posX + dirX;
        int newY = posY + dirY;
        setPosition(newX, newY);
    }

    public boolean collidesWith(GlobalAgent otherAgent) {
        // Collision comptée uniquement si les positions des deux agents sont strictement égales
        // A modifier en prenant en compte les tailles des deux agents
        return posX == otherAgent.getPosX() && posY == otherAgent.getPosY();
    }

    private void selectRandomTarget() {
        int mapSizeX = 1920;
        int mapSizeY = 1080;
        double stdDev = 50; // Ecart type de la distribution

        // Cible aléatoire choisie sur une distribution gaussienne
        targetX = (int) (posX + random.nextGaussian() * stdDev);
        targetY = (int) (posY + random.nextGaussian() * stdDev);

        // Vérifier que la cible se trouve dans les limites de la map
        targetX = Math.max(0, Math.min(mapSizeX, targetX));
        targetY = Math.max(0, Math.min(mapSizeY, targetY));
    }

    private boolean hasReachedTarget() {
        int threshold = 20 / size; // Flexibilité du calcul indexée sur la vitesse de déplacement
        return Math.abs(posX - targetX) <= threshold && Math.abs(posY - targetY) <= threshold;
    }
}
