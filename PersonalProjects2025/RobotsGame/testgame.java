package github.PersonalProjects2025.RobotsGame;
import java.util.Scanner;
import java.util.Random;

public class testgame {
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in);
        int rows = 20, cols = 18;  // Grid size is 20 rows x 10 columns
        int playerX = 10, playerY = 9;  // Initial position of the 'player'
        Random random = new Random();
        int robot1X = random.nextInt(20);
        int robot1Y = random.nextInt(18);

        //loop for the screen
        while (true){
            System.out.print("\033[H\033[2J"); //this clears the screen somehow...
            System.out.flush(); //clear the screen

            if (robot1Y == playerY && robot1X == playerX) {break;}
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) 
                {
                    if (i == playerY && j == playerX) {System.out.print("P");}
                    if (i == robot1Y && j == robot1X) {System.out.print("#");}
                    else {System.out.print("*");} 
                }
            System.out.println(); 
        }
        System.out.println("Move with W (up), A (left), S (down), D (right), T (Teleport). Press Q to quit.");
            char move = scanner.next().charAt(0);
            //check all of the keys
            Random raandom = new Random();
            if (move == 'w' && playerY > 0) playerY--; // Up
            if (move == 's' && playerY < rows - 1) playerY++; // Down
            if (move == 'a' && playerX > 0) playerX--; // Left
            if (move == 'd' && playerX < cols - 1) playerX++; // Right
            if (move == 'q') break; // Quit the game
            if (move == 't') {playerX = raandom.nextInt(10); playerY = raandom.nextInt(9);}

            //Move the robot towards the player step by step.
            if (robot1X < playerX) {robot1X++;}
            if (robot1Y < playerY) {robot1Y++;}
            if (robot1X > playerX) {robot1X--;}
            if (robot1Y > playerY) {robot1Y--;}
    }
    scanner.close();
    System.out.println("Game Over!");
}

}
