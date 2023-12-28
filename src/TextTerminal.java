import java.util.Scanner;

public class TextTerminal extends Terminal {

    private Scanner scanner;

    public TextTerminal() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int read() {
        return scanner.nextInt();
    }

    @Override
    public String readString() {
        return scanner.next();
    }

    @Override
    public void show(String s) {
        System.out.println(s);
    }

    public void closeScanner() {
        scanner.close();
    }
}
