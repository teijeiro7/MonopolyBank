package src;

/**
 *
 * @author
 */
public class MonopolyBank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // The file with the codes is located at "config/MonopolyCode.txt"
        // Languages should be in the "config/languages/" folder
        // Old games should be in the "config/oldGames/" folder
        System.out.println("WELCOME TO MONOPOLY");
        GameManager gameManager = new GameManager();
        gameManager.start();
    }
}
