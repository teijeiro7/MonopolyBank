package src;

import java.io.Serializable;
import java.util.Scanner;

public class TextTerminal extends Terminal implements Serializable {

    public TextTerminal() {
    }

    @Override
    public int read() {
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }

    @Override
    public String readString() {
        Scanner s = new Scanner(System.in);
        return s.next();
    }

    @Override
    public void show(String s) {
        System.out.println(s);
    }

    public void closeScanner() {
        Scanner s = new Scanner(System.in);
        s.close();
    }

}
