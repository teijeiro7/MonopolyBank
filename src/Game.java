package src;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import utils.*;

public class Game implements Serializable {
    private MonopolyCode[] monopolyCodeArray;
    private Player[] players;
    private /* transient */ Terminal terminal;
    private boolean finished;

    public Game() {
        this.terminal = new TextTerminal();
        this.monopolyCodeArray = new MonopolyCode[81];
        this.players = new Player[4];
    }

    public void newGame() {
        createPlayers();
        String gameName = setGameName();
        askForLanguage();
        saveGame(gameName);
        gameMenu(gameName, false);
    }

    public Game loadGame(String gameName) {
        Game loadedGame = null;
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(String.format("config/oldGames/" + gameName))));
            loadedGame = (Game) decoder.readObject();
            decoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loadedGame;
    }

    public void gameMenu(String gameName, boolean finished) {
        int gameMenuOption = 0;
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        while (!finished) {
            do {
                for (int i = 0; i < Constants.gameMenu.length; i++) {
                    String translatedString = translator.translate(Constants.gameMenu[i]);
                    terminal.show(translatedString);

                }
                gameMenuOption = terminal.read();

                while (gameMenuOption < 1 || gameMenuOption > 3) {
                    String translated = translator.translate("Please enter a valid number between 1 and 3.");
                    terminal.show(translated);
                    gameMenuOption = terminal.read();
                }

                if (gameMenuOption == 1) {
                    play(gameName);
                } else if (gameMenuOption == 2) {
                    showGameStatus();
                } else {
                    saveGame(gameName);
                    String translatedLeavingGame = translator.translate(Constants.leavingGame);
                    terminal.show(translatedLeavingGame);
                    System.exit(0);
                }
            } while (gameMenuOption < 1 || gameMenuOption > 3);
        }
    }

    public void showGameStatus() {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                terminal.show(players[i].toString());
            } else {
                String translatedErrorPlayerNull = translator.translate(Constants.errorPlayerNull);
                terminal.show(String.format(translatedErrorPlayerNull, i));
            }
        }
    }

    public void play(String gameName) {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();
        int idCard = -1;
        do {
            String translatedEnterID = translator.translate(Constants.enterID);
            terminal.show(translatedEnterID);
            idCard = terminal.read();
            if (idCard < 0 || idCard > 81) {
                String translatedErrorNumberID = translator.translate(Constants.errorNumberID);
                terminal.show(translatedErrorNumberID);
            }
        } while (idCard < 0 || idCard > 80);

        int playerTurn = 0;

        do {
            String translatedWhoseTurn = translator.translate(Constants.whoseTurn);
            terminal.show(translatedWhoseTurn);
            playerTurn = terminal.read();
            String translatedErrorNumberPlayers = translator.translate(Constants.errorNumberPlayers);
            terminal.show(String.format(translatedErrorNumberPlayers, players.length));
        } while (playerTurn < 1 || playerTurn > players.length);

        Player player = players[playerTurn - 1];
        terminal.show(String.format(Constants.turnSummary, player.getName()));
        monopolyCodeArray[idCard].doOperation(player);

        saveGame(gameName);
    }

    public void createPlayers() {
        TranslatorManager translatorManager = terminal.getTranslatorManager();
        Translator translator = translatorManager.getCurrentIdiom();

        String translatedNumberPlayers = translator.translate(Constants.numberPlayers);
        terminal.show(translatedNumberPlayers);
        int numPlayers = terminal.read();

        if (numPlayers < 2 || numPlayers > 4) {
            String translatedNumberWarning = translator.translate(Constants.numberWarning);
            terminal.show(translatedNumberWarning);
            createPlayers();
        }

        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i, terminal);
            terminal.show(players[i].toString());
        }

    }

    public void loadMonopolyCodes() throws IOException {
        String file = Constants.monopolyCodeFile;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                switch (parts[1]) {
                    case "STREET":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new Street(parts, terminal);
                        break;
                    case "REPAIRS_CARD":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new RepairsCard(parts, terminal);
                        break;
                    case "TRANSPORT":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new Transport(parts, terminal);
                        break;
                    case "PAYMENT_CHARGE_CARD":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new PaymentCharge(parts, terminal);
                        break;
                    case "SERVICE":
                        monopolyCodeArray[Integer.parseInt(parts[0])] = new Service(parts, terminal);
                        break;
                }
            }
        } catch (IOException e) {
            throw new IOException(Constants.errorReadingFile + file, e);
        }
    }

    public static void removePlayer(Player player) {
        boolean isBankrupt = player.getBankrupt();
        if (isBankrupt) {
            player = null;
        }
    }

    public int getId(int id) {
        return id;
    }

    public void setMonopolyCode(int id, MonopolyCode monopolyCode) {
        monopolyCodeArray[id] = monopolyCode;
    }

    public String setGameName() {
        terminal.show(Constants.enterGameName);
        String gameName = terminal.readString();
        return gameName;
    }

    public void saveGame(String gameName) {
        try {
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream("config/oldGames/" + gameName + ".xml")));
            encoder.writeObject(this);
            encoder.close();
        } catch (FileNotFoundException fileNotFound) {
            System.out.println(Constants.errorSavedGame);
        }
    }

    public void askForLanguage() {
        terminal.show(Constants.selectLanguage);

        File languagesFolder = new File(Constants.languagesFolder);

        String[] listOfLanguages = languagesFolder.list();

        if (listOfLanguages == null || listOfLanguages.length == 0) {
            terminal.show(Constants.noLanguages);
            return;
        }

        for (int i = 0; i < listOfLanguages.length; i++) {
            terminal.show((i + 1) + ": " + listOfLanguages[i]);
        }

        terminal.show(Constants.selectNumberLanguage);
        int languageNumber = terminal.read();

        TranslatorManager translatorManager = terminal.getTranslatorManager();
        translatorManager.changeIdiom(languageNumber - 1);
        Translator translator = translatorManager.getCurrentIdiom();
        String translated = translator.translate(Constants.confirmationLanguage);
        terminal.show(translated + listOfLanguages[languageNumber - 1]);
    }

    public MonopolyCode[] getMonopolyCodeArray() {
        return monopolyCodeArray;
    }

    public void setMonopolyCodeArray(MonopolyCode[] monopolyCodeArray) {
        this.monopolyCodeArray = monopolyCodeArray;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
