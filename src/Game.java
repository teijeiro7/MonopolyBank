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
        // Lógica del flujo del juego
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
    }

    public void loadMonopolyCodes() throws IOException {
        String archivo = "config/MonopolyCode.txt";
        monopolyCodeArray = new ArrayList<MonopolyCode>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                monopolyCodeArray.add(linea);
            }
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo " + archivo, e);
        }
    }
}
