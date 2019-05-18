package com.udacity.sandwichclub.utils.json;

import com.udacity.sandwichclub.model.Sandwich;

class SandwichParser {

    private static final String MAIN_NAME = "mainName";

    private String json;
    private Sandwich sandwich;
    private int charPosition;

    private SandwichParser(String json) {
        this.json = json;
        sandwich = new Sandwich();
    }

    static Sandwich parse(String json) {
        SandwichParser parser = new SandwichParser(json);
        return parser.parse();
    }

    private Sandwich parse() {
        parseNext();
        return sandwich;
    }

    private void parseNext() {
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

    private void parseField(String fieldName) {
        switch (fieldName) {
            case MAIN_NAME:
                parseMainName();
                break;
            default:
                parseNext();
        }
    }

    private void parseMainName() {
        String mainName = parseFieldValue();
        sandwich.setMainName(mainName);
        parseNext();
    }

    private String parseFieldValue() {
        int indexOfColon = json.indexOf(':', charPosition);
        charPosition = indexOfColon + 1;
        return parseString();
    }
}
