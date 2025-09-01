import javax.swing.*;
import java.util.*;

public class Ingredient extends Item{ // Items that can be put into cups (are orderable)
    public Ingredient(String d, ImageIcon s, int c) {
        super(d, s, c);
    }
    public Ingredient(String d, ImageIcon s) {
        super(d, s, 2);
    }
    @Override
    public boolean equals(Item other) {
        return this.descEquals(other.description);
    }

    @Override
    public ArrayList<String> getIngredientsInCup() {
        return null;
    }
}
