package src;

public abstract class Terminal {

    private String defaultMessage;

    public Terminal() {
        // Constructor predeterminado
    }

    public Terminal(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public void cleanScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public abstract int read();

    public abstract String readString();

    public abstract void show(String message);

    public abstract void closeScanner();
}
