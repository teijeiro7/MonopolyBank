package src;

public abstract class Terminal {

    private TranslatorManager translatorManager;

    public Terminal() {
        this.translatorManager = new TranslatorManager();
    }

    public TranslatorManager getTranslatorManager() {
        return this.translatorManager;
    }

    public abstract int read();

    public abstract String readString();

    public abstract void show(String message);

    public abstract void closeScanner();

}
