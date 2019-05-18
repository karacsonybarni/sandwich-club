package com.udacity.sandwichclub.utils.json;

import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

class SandwichParser extends JsonParser {

    private static final String MAIN_NAME = "mainName";
    private static final String INGREDIENTS = "ingredients";

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
            case INGREDIENTS:
                parseIngredients();
                break;
            default:
                parseNext();
        }
    }

    private void parseMainName() {
        String mainName = parseStringValueOfField();
        sandwich.setMainName(mainName);
        parseNext();
    }

    private void parseIngredients() {
        List<String> ingredients = parseArrayValueOfField();
        sandwich.setIngredients(ingredients);
        parseNext();
    }
}
