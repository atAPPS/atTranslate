package com.example.arek.attranslate_ver10;

/**
 * Created by Arek on 2015-01-15.
 */
public class Word {
    private String source;
    private String translated;

    public Word(String source, String translated) {
        this.source = source;
        this.translated = translated;
    }

    public String getSource() {
        return source;
    }

    public String getTranslated() {
        return translated;
    }
}
