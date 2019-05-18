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
        int indexOfClosingQuotes = indexOfUnescapedQuotesFrom(stringStart);
        String string = json.substring(stringStart, indexOfClosingQuotes);
        charPosition = indexOfClosingQuotes + 1;
        return string;
    }

    private int indexOfUnescapedQuotesFrom(int fromIndex) {
        if (isUnescapedQuotesAt(fromIndex)) {
            return fromIndex;
        } else {
            int indexOfQuotes = json.indexOf('"', fromIndex + 1);
            return indexOfUnescapedQuotesFrom(indexOfQuotes);
        }
    }

    private boolean isUnescapedQuotesAt(int charIndex) {
        return json.charAt(charIndex) == '"' && json.charAt(charIndex - 1) != '\\';
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

        List<String> list;
        int elemCount = getElemCount(arrayString);
        if (elemCount > 0) {
            list = parseArray(elemCount);
        } else {
            list = new ArrayList<>();
        }
        charPosition = indexOfClosingSquareBracket + 1;
        return list;
    }

    private int getElemCount(String arrayString) {
        boolean hasElems = arrayString.indexOf('"') != -1;
        if (!hasElems) {
            return 0;
        }

        String splittingString = "\",";
        int numberOfRemovedCharacters =
                arrayString.length() - arrayString.replace(splittingString, "").length();
        int splitterCount = numberOfRemovedCharacters / splittingString.length();
        return splitterCount + 1;
    }

    private List<String> parseArray(int elemCount) {
        List<String> arrayValues = new ArrayList<>();
        for (int i = 0; i < elemCount; i++) {
            String value = parseString();
            arrayValues.add(value);
        }
        return arrayValues;
    }
}
