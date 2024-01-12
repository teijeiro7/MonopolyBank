package src;

import java.io.Serializable;

public class TranslatorManager implements Serializable {

    private Translator currentIdiom;
    public Translator[] translators = new Translator[3];

    public TranslatorManager() {
        this.translators[0] = new Translator("English.txt");
        this.translators[1] = new Translator("Euskera.txt");
        this.translators[2] = new Translator("Spanish.txt");
        this.currentIdiom = this.translators[0];
    }

    public Translator getTranslator(String language) {
        for (Translator translator : translators) {
            if (translator.getLanguage().equals(language)) {
                return translator;
            }
        }
        return null;
    }

    public void changeIdiom(int languageOption) {
        if (languageOption >= 0 && languageOption < translators.length) {
            currentIdiom = translators[languageOption];
        }
    }

    public Translator getCurrentIdiom() {
        return currentIdiom;
    }

    public void setCurrentIdiom(Translator currentIdiom) {
        this.currentIdiom = currentIdiom;
    }

    public Translator[] getTranslators() {
        return translators;
    }

    public void setTranslators(Translator[] translators) {
        this.translators = translators;
    }

}
