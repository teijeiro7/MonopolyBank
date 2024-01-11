package src;

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

    public abstract void show(String message);

    public abstract void closeScanner();

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
