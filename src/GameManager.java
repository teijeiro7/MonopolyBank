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
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        do {
            for (int i = 1; i < Constants.menuInterface.length; i++) {
                System.out.println(Constants.menuInterface[i]);
            }

            String translatedChooseOption = translator.translate(Constants.chooseOption);
            System.out.print(translatedChooseOption);
            option = terminal.read();

            while (option < 1 || option > 3) {
                String translatedErrorChooseOption = translator.translate(Constants.errorChooseOption);
                terminal.show(translatedErrorChooseOption);

                String translatedChooseOption2 = translator.translate(Constants.chooseOption);
                terminal.show(translatedChooseOption2);
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
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        String translatedAskForLoadFile = translator.translate(Constants.askForLoadFile);
        System.out.print(translatedAskForLoadFile);
        int option = terminal.read();

        do {
            if (option == 1) {
                File folder = new File("config/oldGames");
                String[] listOfFiles = folder.list();

                if (listOfFiles.length == 0) {
                    String translatedNoFilesAvailable = translator.translate(Constants.noFilesAvailable);
                    terminal.show(translatedNoFilesAvailable);
                    start();
                }

                String translatedListFiles = translator.translate(Constants.listFiles);
                terminal.show(translatedListFiles);

                for (int i = 0; i < listOfFiles.length; i++) {
                    terminal.show((i) + ": " + listOfFiles[i]);
                }

                String translatedAskNumberFile = translator.translate(Constants.askNumberFile);
                terminal.show(translatedAskNumberFile);
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
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        String translatedLeavingGame = translator.translate(Constants.leavingGame);
        System.out.println(translatedLeavingGame);
        System.exit(0);
    }

}
