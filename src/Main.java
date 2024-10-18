import model.*;
import view.*;
import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    public static void main(String[] args) {
        AgentView agentView = new AgentView();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    agentView.updateAgents();
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayGUI(agentView);
            }
        });
    }

    private static void displayGUI(AgentView agentView) {
        JFrame frame = new JFrame("Agar Window");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(agentView, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
