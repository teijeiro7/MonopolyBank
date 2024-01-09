package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import utils.Constants;

public class GameManager {
    private Game game = new Game();
    Terminal terminal = new TextTerminal();

    public GameManager() {
    }

    public void start() {
        for (int i = 1; i < Constants.menuInterface.length; i++) {
            System.out.println(Constants.menuInterface[i]);
        }

        int option = 0;

        while (option < 1 || option > 3) {
            option = 0;
            System.out.print(Constants.chooseOption);
            option = terminal.read();
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
                System.out.println("Please enter a valid number.");
            }

            terminal.closeScanner();
        }

    }

    public void askForResumeGame() {
        System.out.print("Do you want to load a saved game? (Y/N): ");
        String option = terminal.readString();

        if (option.toUpperCase().equalsIgnoreCase("Y")) {
            File folder = new File("config/oldGames");
            String[] listOfFiles = folder.list();

            if (listOfFiles.length == 0) {
                terminal.show("No saved games available");
                start();
            }

            terminal.show("The following saved games will be displayed:");

            for (int i = 0; i < listOfFiles.length; i++) {
                terminal.show((i) + ": " + listOfFiles[i]);
            }

            terminal.show("Enter the number of the game you want to load: ");
            int gameNumber = terminal.read();
            String loadedGame = listOfFiles[gameNumber];

            try {
                game.loadGame(loadedGame);
            } catch (Exception e) {
                String errorMessage = e.toString();
                terminal.show(errorMessage);
            }

            game.gameMenu(loadedGame, false);
        }

        terminal.closeScanner();
    }

    public void leaveGame() {
        System.out.println("Leaving the game...");
        System.exit(0);
    }

}
