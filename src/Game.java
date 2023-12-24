import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Provider.Service;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    private MonopolyCode[] monopolyCodeArray;
    private static Player[] players;
    private static Terminal terminal = new Terminal();

    public void setMonopolyCodeArray(int size) {
        monopolyCodeArray = new MonopolyCode[size];
        for (int i = 0; i < size; i++) {
            monopolyCodeArray[i] = new MonopolyCode();
        }
    }

    public void play() {
        // Lógica del flujo del juego
    }

    public static void createPlayers() {
        System.out.print("Introduzca el número de jugadores: ");
        int numPlayers = terminal.read();

        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i, terminal);
            terminal.show(players[i].toString());
        }
    }

    public static void loadMonopolyCodes() throws IOException {
        String archivo = "config/MonopolyCode.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                System.out.println(partes[0] + " " + partes[1]);
            }
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo " + archivo, e);
        }
    }

}