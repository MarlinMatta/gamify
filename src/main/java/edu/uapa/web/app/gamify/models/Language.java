package edu.uapa.web.app.gamify.models;

import java.io.IOException;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Language {

    private static Language instance = null;
    public static final Locale ENGLISH = Locale.ENGLISH;
    public static final Locale SPANISH = new Locale("es");

    private Language() {
    }

    public static Language getInstance() {
        if (instance == null)
            instance = new Language();
        return instance;
    }

    public ResourceBundle getI18N(Locale locale) throws IOException {
        if (SPANISH.equals(locale)) {
            return new PropertyResourceBundle(getClass().getResourceAsStream("/messagesReport_es.properties"));
        } else {
            return new PropertyResourceBundle(getClass().getResourceAsStream("/messagesReport.properties"));
        }
    }
}
