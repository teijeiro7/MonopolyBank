
public abstract class Terminal {

    private String defaultMessage;

    public Terminal() {
        // Constructor predeterminado
    }

    public Terminal(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public abstract int read();

    public abstract String readString();

    public void show(String message) {
        if (defaultMessage != null) {
            System.out.println(defaultMessage + ": " + message);
        } else {
            System.out.println(message);
        }
    }

    public abstract void closeScanner();
}
