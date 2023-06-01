package ClientWindow;

import javax.swing.*;

public class ClientRunner {
    public static void main(String[] args) {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Wilboar's Crypt");

        SwingWindow gamePanel = new SwingWindow();
        gameWindow.add(gamePanel);
        gameWindow.pack();  //Sizes the window to the preferred panel size in the SwingWindow class
        gameWindow.setLocationRelativeTo(null); //puts the window in the center of screen
        gameWindow.setVisible(true);

        startGame(gamePanel);
    }

    public static void startGame(SwingWindow gamePanel)
    {
        gamePanel.setUp();
        gamePanel.startThread();  //begins the game loop timer
    }
}
