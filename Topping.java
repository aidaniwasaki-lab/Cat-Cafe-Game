import javax.swing.*;
import java.util.ArrayList;

public class Topping extends Ingredient{ // All toppings that can be put in cup
    private static ArrayList<String> toppingsArrayList = new ArrayList<String>();
    public Topping(String d, ImageIcon s, int c) {
        super(d, s, c);
        toppingsArrayList.add(d);
    }
    public Topping(String d, ImageIcon s) {
        this(d,s,2);
    }
    public static String randomItem(){ // Returns a random topping
        return toppingsArrayList.get((int)(Math.random()*toppingsArrayList.size()));
    }
    @Override
    public boolean equals(Item other) {
        return super.equals(other);
    }
}
