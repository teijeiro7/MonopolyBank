package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GameManager {
    private Terminal terminal;

    public void start() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Menú principal");
        System.out.println("----------------");
        System.out.println("1: Nueva partida");
        System.out.println("2: Cargar partida");
        System.out.println("3: Salir");
        System.out.println("----------------");
        System.out.print("Elija la opción que desea: ");
        int opcion = scanner.nextInt();

        Game game = new Game();
        switch (opcion) {
            case 1:
                try {
                    game.loadMonopolyCodes();
                } catch (IOException e) {
                    System.out.println("Error al cargar los códigos del Monopoly: " + e.getMessage());
                }
                break;

            case 2:
                askForResumeGame();
                break;

            case 3:
                System.out.println("Saliendo del juego...");
                System.exit(0);
                break;
        }

        scanner.close();

    }

    public void askForResumeGame() {
        System.out.print("¿Desea cargar una partida guardada? (S/N): ");
        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();

        if (opcion.equalsIgnoreCase("S")) {
            File folder = new File("config/oldGames");
            String[] listOfFiles = folder.list();

            if (listOfFiles.length == 0) {
                terminal.show("No hay partidas guardadas");
                start();
            }

            terminal.show("A continuación se mostrarán las partidas guardadas:");

            for (int i = 0; i < listOfFiles.length; i++) {
                terminal.show((i + 1) + ": " + listOfFiles[i]);
            }

            terminal.show("Introduzca el número de la partida que desea cargar: ");
            int numPartida = terminal.read();

        }

        scanner.close();
    }

}
