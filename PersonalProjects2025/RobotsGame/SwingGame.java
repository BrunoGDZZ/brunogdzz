package github.PersonalProjects2025.RobotsGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SwingGame extends JFrame {
    private final int rows = 20, cols = 18;
    private int playerX = 9, playerY = 10;
    private int robot1X, robot1Y;
    private final JLabel[][] grid = new JLabel[rows][cols];
    private final Random random = new Random();

    public SwingGame() {
        setTitle("Simple Swing Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Fix: Use BorderLayout

        // ** Grid Panel **
        JPanel gridPanel = new JPanel(new GridLayout(rows, cols));
        
        robot1X = random.nextInt(cols);
        robot1Y = random.nextInt(rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new JLabel("*", SwingConstants.CENTER);
                grid[i][j].setFont(new Font("Monospaced", Font.BOLD, 18));
                grid[i][j].setOpaque(true);
                grid[i][j].setBackground(Color.CYAN);
                grid[i][j].setForeground(Color.WHITE);
                gridPanel.add(grid[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER); // Fix: Add grid panel to CENTER

        // ** "Play Again" button at the bottom **
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> resetGame());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playAgainButton);

        add(buttonPanel, BorderLayout.SOUTH); // Fix: Add button panel to SOUTH

        updateGrid();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                movePlayer(e.getKeyChar());
            }
        });

        setFocusable(true);
        setVisible(true);
    }

    private void updateGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].setText("*");
                grid[i][j].setBackground(Color.BLACK);
            }
        }

        grid[playerY][playerX].setText("P");
        grid[playerY][playerX].setForeground(Color.GREEN);
        grid[robot1Y][robot1X].setText("#");
        grid[robot1Y][robot1X].setForeground(Color.RED);

        if (playerX == robot1X && playerY == robot1Y) {
            JOptionPane.showMessageDialog(this, "Game Over!");
        }
    }

    private void movePlayer(char move) {
        switch (move) {
            case 'w' -> { if (playerY > 0) playerY--; }
            case 's' -> { if (playerY < rows - 1) playerY++; }
            case 'a' -> { if (playerX > 0) playerX--; }
            case 'd' -> { if (playerX < cols - 1) playerX++; }
            case 'q' -> { System.exit(0); }
            case 't' -> { playerX = random.nextInt(cols); playerY = random.nextInt(rows); }
        }

        // Move the robot toward the player
        if (robot1X < playerX) robot1X++;
        if (robot1Y < playerY) robot1Y++;
        if (robot1X > playerX) robot1X--;
        if (robot1Y > playerY) robot1Y--;

        updateGrid();
    }

    private void resetGame() {
        playerX = 9;
        playerY = 10;
        robot1X = random.nextInt(cols);
        robot1Y = random.nextInt(rows);
        updateGrid();
    }

    public static void main(String[] args) {
        new SwingGame();
    }
}
