package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AgentView extends JPanel {
    private List<GlobalAgent> agents;

    public AgentView() {
        agents = new ArrayList<>();
        initializeAgents();
    }

    private void initializeAgents() {
        int mapSizeX = 1920;
        int mapSizeY = 1080;
        int numAgents = 100;

        for (int i = 0; i < numAgents; i++) {
            int posX = (int) (Math.random() * mapSizeX);
            int posY = (int) (Math.random() * mapSizeY);
            int size = 10; // Taille minimale = 5 car taille de la nourriture = 4
            GlobalAgent agent = new GlobalAgent(posX, posY, size, posX, posY) {
                /* Faire bouger les agents une première fois si besoin
                @Override
                public void move() {
                    super.move();
                }

                */
            };
            agents.add(agent);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GlobalAgent agent : agents) {
            g.setColor(agent.getColor());
            g.fillOval(agent.getPosX(), agent.getPosY(), agent.getSize(), agent.getSize());
        }
    }

    public void updateAgents() {
        for (GlobalAgent agent : agents) {
            agent.move();

            // Vérification des collisions
            // A remplacer par "collision vraie si rayon du grand agent >= (distance  + rayon du petit agent)"
            for (GlobalAgent otherAgent : agents) {
                if (agent != otherAgent && agent.collidesWith(otherAgent)) {
                    agent.setColor(Color.RED);
                    otherAgent.setColor(Color.RED);
                }
            }
        }
        repaint();
    }
}
