package com.udacity.sandwichclub.utils.json;

import com.udacity.sandwichclub.model.Sandwich;

class SandwichParser extends JsonParser {

    private static final String MAIN_NAME = "mainName";

    private Sandwich sandwich;

    private SandwichParser(String json) {
        super(json);
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

    @Override
    void parseField(String fieldName) {
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
}
