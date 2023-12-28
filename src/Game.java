import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private ArrayList<MonopolyCode> monopolyCodeArray;
    private Player[] players;
    private Terminal terminal;

    public Game() {
        terminal = new TextTerminal();
    }

    public void play() {
        terminal.show("Introduzca el id de la carta que ha tocado:");
        int idCard = terminal.read();
        MonopolyCode monopolyCode = monopolyCodeArray.get(idCard);
        terminal.show("¿De quién es el turno?");
        int playerTurn = terminal.read();
        Player player = players[playerTurn];
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
        monopolyCodeArray = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                
                MonopolyCode monopolyCode = new MonopolyCode(partes[1], Integer.parseInt(partes[0]), terminal); 
                monopolyCodeArray.add(monopolyCode);
            }
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo " + archivo, e);
        }
        createPlayers();
    }

    public void removePlayer(Player player) {
        boolean pBankrupt = player.getBankrupt();
        if (pBankrupt == true) {
            player = null;
        }
    }
    
}
