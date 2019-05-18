package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.json.JsonUtils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonUtilsTest {

    private static final String bosnaJson =
            "{" +
                    "\"name\":{" +
                        "\"mainName\":\"Bosna\"," +
                        "\"alsoKnownAs\":[\"Bosner\"]" +
                    "}," +
                    "\"placeOfOrigin\":\"Austria\"," +
                    "\"description\":\"Bosna is a spicy Austrian fast food dish, said to have originated in either Salzburg or Linz. It is now popular all over western Austria and southern Bavaria.\"," +
                    "\"image\":\"https://upload.wikimedia.org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg\"," +
                    "\"ingredients\":[\"White bread\",\"Bratwurst\",\"Onions\",\"Tomato ketchup\",\"Mustard\",\"Curry powder\"]" +
            "}";

    @Test
    public void parseSandwichJson() {
        Sandwich sandwich = JsonUtils.parseSandwichJson(bosnaJson);
        assertThat(sandwich.getMainName()).isEqualTo("Bosna");
        assertThat(sandwich.getIngredients()).containsExactly("White bread", "Bratwurst", "Onions", "Tomato ketchup", "Mustard", "Curry powder");
    }
}