import java.util.Scanner;

public class TextTerminal extends Terminal {

    @Override
    public int read() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.close();
        return number;
    }

    @Override
    public String readString() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        scanner.close();
        return string;
    }

    @Override
    public void show(String s) {
        System.out.println(s);
    }
}
