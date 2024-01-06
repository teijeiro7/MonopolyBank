package src;

import java.beans.XMLEncoder;
import java.io.*;
import utils.Constants;

public class Game implements Serializable {
    private MonopolyCode[] monopolyCodeArray;
    private Player[] players;
    private Terminal terminal;
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

    public void loadDataGame(Game game) {

    }

    public void gameMenu(String gameName, boolean finished) {
        while (!finished) {
            for (int i = 0; i < Constants.gameMenu.length; i++) {
                terminal.show(Constants.gameMenu[i]);
            }
            int gameMenuOption = terminal.read();
            while (gameMenuOption > 1 || gameMenuOption < 3) {
                if (gameMenuOption == 1) {
                    play(gameName);
                } else if (gameMenuOption == 2) {
                    showGameStatus();
                } else {
                    saveGame(gameName);
                    terminal.show(Constants.leavingGame);
                    System.exit(0);
                }
            }
        }
    };

    public void showGameStatus() {
        for (int i = 0; i < players.length; i++) {
            terminal.show(players[i].toString());
        }
    }

    public void play(String gameName) {
        terminal.show(Constants.enterID);
        int idCard = terminal.read();

        terminal.show(Constants.whoseTurn);
        int playerTurn = terminal.read();
        Player player = players[playerTurn - 1];
        terminal.show(String.format(Constants.turnSummary, player.getName()));

        monopolyCodeArray[idCard].doOperation(player);

        terminal.closeScanner();
        saveGame(gameName);
    }

    public void createPlayers() {
        terminal.show(Constants.numberPlayers);
        int numPlayers = terminal.read();

        if (numPlayers < 2 || numPlayers > 4) {
            terminal.show(Constants.numberWarning);
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
            String filePath = String.format(Constants.oldGamesPath, gameName);
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(filePath)));
            encoder.writeObject(this);
            encoder.close();
            terminal.show(Constants.savedGame + gameName);
        } catch (FileNotFoundException fileNotFound) {
            terminal.show(Constants.errorSavedGame);
        }
    }

    public void askForLanguage() {
        terminal.show(Constants.selectLanguage);

        File languagesFolder = new File(Constants.languagesFolder);

        if (!languagesFolder.exists() || !languagesFolder.isDirectory()) {
            terminal.show("Languages folder does not exist or is not a directory");
            return;
        }

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

        terminal.show(Constants.confirmationLanguage + listOfLanguages[languageNumber]);
    }

}
