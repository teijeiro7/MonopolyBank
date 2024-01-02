import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class Game implements Serializable {
    private MonopolyCode[] monopolyCodeArray;
    private Player[] players;
    private Terminal terminal;

    public Game() {
        this.terminal = new TextTerminal();
        this.monopolyCodeArray = new MonopolyCode[81];
        this.players = new Player[4];
    }

    public void play() {
        terminal.show("Introduzca el id de la carta que ha tocado:");
        int idCard = terminal.read();

        terminal.show("¿De quién es el turno? (1-RED, 2-GREEN, 3-BLUE, 4-BLACK) \n");
        int playerTurn = terminal.read();
        Player player = players[playerTurn - 1];

        monopolyCodeArray[idCard].doOperation(player); // llamamos al doOperation de la carta que ha tocado en el turno
                                                       // del jugador

        terminal.show("Es el turno de " + player.getName());
    }

    public void createPlayers() {
        System.out.print("Introduzca el número de jugadores: ");
        int numPlayers = terminal.read();

        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i, terminal);
            terminal.show(players[i].toString());
        }

        terminal.closeScanner();

        play();
    }

    public void loadMonopolyCodes() throws IOException {
        String archivo = "config/MonopolyCode.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                switch (partes[1]) {
                    case "STREET":
                        monopolyCodeArray[Integer.parseInt(partes[0])] = new Street(partes, terminal);
                        break;
                    case "REPAIRS_CARD":
                        monopolyCodeArray[Integer.parseInt(partes[0])] = new RepairsCard(partes, terminal);
                        break;
                    case "TRANSPORT":
                        monopolyCodeArray[Integer.parseInt(partes[0])] = new Transport(partes, terminal);
                        break;
                    case "PAYMENT_CHARGE_CARD":
                        monopolyCodeArray[Integer.parseInt(partes[0])] = new PaymentCharge(partes, terminal);
                        break;
                    case "SERVICE":
                        monopolyCodeArray[Integer.parseInt(partes[0])] = new Service(partes, terminal);
                        break;
                }
            }
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo " + archivo, e);
        }
        createPlayers();
    }

    public static void removePlayer(Player player) {
        boolean pBankrupt = player.getBankrupt();
        if (pBankrupt == true) {
            player = null;
        }
    }

    public int getId(int id) {
        return id;
    }

    public void setMonopolyCode(int id, MonopolyCode monopolyCode) {
        monopolyCodeArray[id] = monopolyCode;
    }

}
