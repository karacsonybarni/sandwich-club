package com.udacity.sandwichclub.utils.json;

import java.util.ArrayList;
import java.util.List;

abstract class JsonParser {

    private String json;
    private int charPosition;

    JsonParser(String json) {
        this.json = json;
    }

    abstract void parseField(String fieldName);

    void parseNext() {
        if (charPosition >= json.length()) {
            return;
        }

        boolean isAtQuotes = json.charAt(charPosition) == '"';
        if (isAtQuotes) {
            onQuotes();
        } else {
            charPosition++;
            parseNext();
        }
    }

    private void onQuotes() {
        String fieldName = parseString();
        parseField(fieldName);
    }

    private String parseString() {
        int stringStart = json.indexOf('"', charPosition) + 1;
        int indexOfClosingQuotes = json.indexOf('"', stringStart);
        String string = json.substring(stringStart, indexOfClosingQuotes);
        charPosition = indexOfClosingQuotes + 1;
        return string;
    }

    String parseStringValueOfField() {
        parseColon();
        return parseString();
    }

    private void parseColon() {
        int indexOfColon = json.indexOf(':', charPosition);
        charPosition = indexOfColon + 1;
    }

    List<String> parseArrayValueOfField() {
        parseColon();
        return parseArray();
    }

    private List<String> parseArray() {
        int arrayStart = json.indexOf('[', charPosition) + 1;
        int indexOfClosingSquareBracket = json.indexOf(']', arrayStart);
        String arrayString = json.substring(arrayStart, indexOfClosingSquareBracket);
        List<String> arrayValues = new ArrayList<>();
        int commaCount = getCommaCount(arrayString);
        for (int i = 0; i <= commaCount; i++) {
            String value = parseString();
            arrayValues.add(value);
        }
        charPosition = indexOfClosingSquareBracket + 1;
        return arrayValues;
    }

    private int getCommaCount(String string) {
        return string.length() - string.replace(",", "").length();
    }
}
