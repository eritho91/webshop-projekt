package se.iths.erikthorell.webshopprojekt.util;

import java.util.List;

public class ProductCategory {

    public static final String FOOD = "Food";
    public static final String PROTEIN = "Protein";
    public static final String DIET = "Diet";
    public static final String VITAMINMINERAL = "Vitamin & Mineral";

    public static List<String> getCategories() {
        return List.of(FOOD, PROTEIN, DIET, VITAMINMINERAL);
    }
}
