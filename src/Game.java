import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.awt.Color;
import java.util.Objects;

public class Game implements Serializable {
    private MonopolyCode[] monopolyCodeArray;
    static Player[] players;

    public void setMonopolyCodeArray(int size) {
        monopolyCodeArray = new MonopolyCode[size];

        for (int i = 0; i < size; i++) {
            monopolyCodeArray[i] = new MonopolyCode();
        }
    }

    public void play() {
        // Lógica del flujo del juego
    }

    // Game.java
    public static void createPlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduzca el número de jugadores: ");
        int numPlayers = scanner.nextInt();

        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            Color color = Color.values()[i];

            System.out.print("Introduzca el nombre del jugador " + (i + 1) + ": ");
            String name = scanner.next();
            players[i] = new Player(color, name, 1500, false, new Property[0]);
        }
        scanner.close();
    }

    public static void loadMonopolyCodes() throws IOException {
        String archivo = "config/MonopolyCode.txt";

        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(";");

                for (String parte : partes) {
                    System.out.println(parte);
                }
                br.close();
            }
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo " + archivo, e);
        }
    }
}