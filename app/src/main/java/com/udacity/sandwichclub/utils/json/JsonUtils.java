package com.udacity.sandwichclub.utils.json;

import com.udacity.sandwichclub.model.Sandwich;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        return SandwichParser.parse(json);
    }
}
