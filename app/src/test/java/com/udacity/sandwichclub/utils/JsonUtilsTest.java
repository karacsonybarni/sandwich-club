package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.json.JsonUtils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonUtilsTest {

    private static final String hamAndCheeseSandwich =
            "{" +
                    "\"name\":{" +
                        "\"mainName\":\"Ham and cheese sandwich\"," +
                        "\"alsoKnownAs\":[]" +
                    "}," +
                    "\"placeOfOrigin\":\"\"," +
                    "\"description\":\"A ham and cheese sandwich is a common type of sandwich. It is made by putting cheese and sliced ham between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables like lettuce, tomato, onion or pickle slices can also be included. Various kinds of mustard and mayonnaise are also common.\"," +
                    "\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG\"," +
                    "\"ingredients\":[\"Sliced bread\",\"Cheese\",\"Ham\"]" +
            "}";

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

    private static final String medianoche =
            "{" +
                    "\"name\":{" +
                        "\"mainName\":\"Medianoche\"," +
                        "\"alsoKnownAs\":[\"Cuban Sandwich\"]" +
                    "}," +
                    "\"placeOfOrigin\":\"Cuba\"," +
                    "\"description\":\"Medianoche (\\\"midnight\\\" in Spanish) is a type of sandwich which originated in Cuba. It is served in many Cuban communities in the United States. It is so named because of the sandwich's popularity asa staple served in Havana's night clubs right around or after midnight.\"," +
                    "\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Sandwich_de_Medianoche.jpg/800px-Sandwich_de_Medianoche.jpg\"," +
                    "\"ingredients\":[\"Egg bread\",\"Roast pork\",\"Ham\",\"Mustard\",\"Swiss cheese\",\"Dill pickles\"]" +
            "}";

    private static final String pljeskavica =
            "{\"name\":{" +
                        "\"mainName\":\"Pljeskavica\"," +
                        "\"alsoKnownAs\":[]" +
                    "}," +
                    "\"placeOfOrigin\":\"Serbia\"," +
                    "\"description\":\"Pljeskavica, a grilled dish of spiced meat patty mixture of pork, beef and lamb, is a national dish of Serbia, also popular in Bosnia and Herzegovina and Croatia. It is a main course served with onions, kajmak (milk cream), ajvar (relish), and urnebes (spicy cheese salad), either on plate with side dishes, or with lepinja (flatbread, as a type of hamburger). Recently, Pljeskavica has gained popularity elsewhere in Europe and is served in a few speciality fast food restaurants in Germany, Sweden, and Austria.\"," +
                    "\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Pljeskavica_%286883073017%29.jpg/800px-Pljeskavica_%286883073017%29.jpg\"," +
                    "\"ingredients\":[\"Two or more of beef, lamb, pork, veal\",\"Onions\",\"Bread crumbs\",\"Lard\"]" +
            "}";

    private static final String shawarma =
            "{" +
                    "\"name\":{" +
                        "\"mainName\":\"Shawarma\"," +
                        "\"alsoKnownAs\":[]" +
                    "}," +
                    "\"placeOfOrigin\":\"Middle East, Levant\"," +
                    "\"description\":\"Shawarma also spelled shawurma or shawerma, is a Levantine meat preparation, where lamb, chicken, turkey, beef, veal, or mixed meats are placed on a spit (commonly a vertical spit in restaurants), and may be grilled for as long as a day. Shavings are cut off the block of meat for serving, and the remainder of the block of meat is kept heated on the rotating spit. Shawarma can be served on a plate (generally with accompaniments), or as a sandwich or wrap. Shawarma is usually eaten with tabbouleh, fattoush, taboon bread, tomato, and cucumber. Toppings include tahini, hummus, pickled turnips, and amba.\"," +
                    "\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Shawarmafood.jpg/800px-Shawarmafood.jpg\"," +
                    "\"ingredients\":[\"Shawarma meat (lamb, chicken, turkey, beef) or shawarma falafel\",\"Pita or wrap bread\",\"Chopped or shredded vegetables\",\"Pickles\",\"Assorted condiments\"]" +
            "}";

    @Test
    public void parseBosna() {
        Sandwich sandwich = JsonUtils.parseSandwichJson(bosnaJson);
        assertThat(sandwich.getMainName()).isEqualTo("Bosna");
        assertThat(sandwich.getAlsoKnownAs()).containsExactly("Bosner");
        assertThat(sandwich.getPlaceOfOrigin()).isEqualTo("Austria");
        assertThat(sandwich.getDescription()).isEqualTo("Bosna is a spicy Austrian fast food dish, said to have originated in either Salzburg or Linz. It is now popular all over western Austria and southern Bavaria.");
        assertThat(sandwich.getImage()).isEqualTo("https://upload.wikimedia.org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg");
        assertThat(sandwich.getIngredients()).containsExactly("White bread", "Bratwurst", "Onions", "Tomato ketchup", "Mustard", "Curry powder");
    }

    @Test
    public void parseHamAndCheese() {
        Sandwich sandwich = JsonUtils.parseSandwichJson(hamAndCheeseSandwich);
        assertThat(sandwich.getAlsoKnownAs()).isEmpty();
    }

    @Test
    public void parseMedianoche() {
        Sandwich sandwich = JsonUtils.parseSandwichJson(medianoche);
        assertThat(sandwich.getDescription()).isEqualTo("Medianoche (\\\"midnight\\\" in Spanish) is a type of sandwich which originated in Cuba. It is served in many Cuban communities in the United States. It is so named because of the sandwich's popularity asa staple served in Havana's night clubs right around or after midnight.");
    }

    @Test
    public void parsePljeskavica() {
        Sandwich sandwich = JsonUtils.parseSandwichJson(pljeskavica);
        assertThat(sandwich.getIngredients()).containsExactly("Two or more of beef, lamb, pork, veal","Onions","Bread crumbs","Lard");
    }

    @Test
    public void parseShawarma() {
        Sandwich sandwich = JsonUtils.parseSandwichJson(shawarma);
        assertThat(sandwich.getImage()).isEqualTo("https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/Shawarmafood.jpg/800px-Shawarmafood.jpg");
    }
}