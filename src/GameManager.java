package src;

import java.io.File;
import java.io.IOException;

import utils.Constants;

public class GameManager {
    private Game game = new Game();
    Terminal terminal = new TextTerminal();

    public GameManager() {
    }

    public void start() {
        int option = 0;
        do {
            for (int i = 1; i < Constants.menuInterface.length; i++) {
                System.out.println(Constants.menuInterface[i]);
            }

            System.out.print(Constants.chooseOption);
            option = terminal.read();

            while (option < 1 || option > 3) {
                terminal.show(Constants.errorChooseOption);
                terminal.show(Constants.chooseOption);
                option = terminal.read();
            }

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

            terminal.closeScanner();

        } while (option < 1 || option > 3);
    }

    public void askForResumeGame() {
        System.out.print(Constants.askForLoadFile);
        int option = terminal.read();

        do {
            if (option == 1) {
                File folder = new File("config/oldGames");
                String[] listOfFiles = folder.list();

                if (listOfFiles.length == 0) {
                    terminal.show(Constants.noFilesAvailable);
                    start();
                }

                terminal.show(Constants.listFiles);

                for (int i = 0; i < listOfFiles.length; i++) {
                    terminal.show((i) + ": " + listOfFiles[i]);
                }

                terminal.show(Constants.askNumberFile);
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
        } while (option < 1 || option > 3);

        terminal.closeScanner();
    }

    public void leaveGame() {
        System.out.println(Constants.leavingGame);
        System.exit(0);
    }

}
