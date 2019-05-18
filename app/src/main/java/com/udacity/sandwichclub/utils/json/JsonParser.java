package com.udacity.sandwichclub.utils.json;

abstract class JsonParser {

    private String json;
    private int charPosition;

    JsonParser(String json) {
        this.json = json;
    }

    abstract void parseField(String fieldName);

    void parseNext() {
        switch (json.charAt(charPosition)) {
            case '{':
                onOpenCurlyBracket();
                break;
            case '}':
                onCloseCurlyBracket();
                break;
            case '"':
                onQuotes();
                break;
            case ':':
                onColon();
                break;
            case ',':
                onComma();
                break;
            case ' ':
                onSpace();
                break;
        }
    }

    private void onOpenCurlyBracket() {
        skipAndParseNext();
    }

    private void onCloseCurlyBracket() {

    }

    private void onQuotes() {
        String fieldName = parseString();
        parseField(fieldName);
    }

    private void onColon() {
        skipAndParseNext();
    }

    private void onComma() {

    }

    private void onSpace() {
        skipAndParseNext();
    }

    private void skipAndParseNext() {
        charPosition++;
        parseNext();
    }

    private String parseString() {
        int stringStart = json.indexOf('"', charPosition) + 1;
        int indexOfClosingQuotes = json.indexOf('"', stringStart);
        String string = json.substring(stringStart, indexOfClosingQuotes);
        charPosition = indexOfClosingQuotes + 1;
        return string;
    }

    String parseFieldValue() {
        int indexOfColon = json.indexOf(':', charPosition);
        charPosition = indexOfColon + 1;
        return parseString();
    }
}
