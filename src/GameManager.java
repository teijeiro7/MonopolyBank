import java.io.IOException;
import java.util.Scanner;

public class GameManager {
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
                game.createPlayers();
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

        if (opcion.equals("S") || opcion.equals("s")) {
            System.out.println("Cargando partida...");

            Game game = new Game(); // Create an instance of the Game class
            try {
                game.loadMonopolyCodes(); // Call the loadMonopolyCodes() method on the instance
            } catch (IOException e) {
                System.out.println("Error al cargar los códigos del Monopoly: " + e.getMessage());
            }
        } else {
            System.out.println("Volviendo al menú principal...");
            start();
        }

        scanner.close();
    }

}
