package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class GameManager {
    private Terminal terminal;
    private Game game = new Game();

    public void start() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Main Menu");
        System.out.println("----------------");
        System.out.println("1: New Game");
        System.out.println("2: Load Game");
        System.out.println("3: Exit");
        System.out.println("----------------");
        System.out.print("Choose the option you want: ");
        int option = scanner.nextInt();

        if (option >= 1 && option <= 3) {
            if (option == 1 || option == 2) {
                try {
                    game.loadMonopolyCodes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (option == 1) {
                game.newGame();
            } else if (option == 2) {
                askForResumeGame();
            } else {
                leaveGame();
            }
        } else {
            terminal.show("The entered number does not correspond to any of the options.");
            terminal.show("Please enter a number between 1 and 3 again");
            start();
        }

        scanner.close();

    }

    public void askForResumeGame() {
        System.out.print("Do you want to load a saved game? (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        if (option.toUpperCase().equalsIgnoreCase("Y")) {
            File folder = new File("config/oldGames");
            String[] listOfFiles = folder.list();

            if (listOfFiles.length == 0) {
                terminal.show("No saved games available");
                start();
            }

            terminal.show("The following saved games will be displayed:");

            for (int i = 0; i < listOfFiles.length; i++) {
                terminal.show((i + 1) + ": " + listOfFiles[i]);
            }

            terminal.show("Enter the number of the game you want to load: ");
            int gameNumber = terminal.read();

            try {
                String file = listOfFiles[gameNumber];
                ObjectInputStream data = new ObjectInputStream(new FileInputStream(file));
                Game loadedGame = (Game) data.readObject();
                game.loadDataGame(loadedGame);
            } catch (Exception e) {
                String errorMessage = e.toString();
                terminal.show(errorMessage);
            }

        }

        scanner.close();
    }

    public void leaveGame() {
        terminal.show("Leaving the game...");
        System.exit(0);
    }

}
