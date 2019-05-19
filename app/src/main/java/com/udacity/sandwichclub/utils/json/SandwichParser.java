package com.udacity.sandwichclub.utils.json;

import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

class SandwichParser extends JsonParser {

    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
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
        parseJson();
        return sandwich;
    }

    @Override
    void parseField(String fieldName) {
        switch (fieldName) {
            case MAIN_NAME:
                parseMainName();
                break;
            case ALSO_KNOWN_AS:
                parseAlsoKnownAs();
                break;
            case PLACE_OF_ORIGIN:
                parsePlaceOfOrigin();
                break;
            case DESCRIPTION:
                parseDescription();
                break;
            case IMAGE:
                parseImage();
                break;
            case INGREDIENTS:
                parseIngredients();
                break;
        }
    }

    private void parseMainName() {
        String mainName = parseStringValueOfField();
        sandwich.setMainName(mainName);
    }

    private void parseAlsoKnownAs() {
        List<String> alsoKnownAs = parseArrayValueOfField();
        sandwich.setAlsoKnownAs(alsoKnownAs);
    }

    private void parsePlaceOfOrigin() {
        String placeOfOrigin = parseStringValueOfField();
        sandwich.setPlaceOfOrigin(placeOfOrigin);
    }

    private void parseDescription() {
        String description = parseStringValueOfField();
        sandwich.setDescription(description);
    }

    private void parseImage() {
        String image = parseStringValueOfField();
        sandwich.setImage(image);
    }

    private void parseIngredients() {
        List<String> ingredients = parseArrayValueOfField();
        sandwich.setIngredients(ingredients);
    }
}
