import javax.swing.*;
import java.util.ArrayList;

public abstract class Item {
    protected String description;
    protected ImageIcon sprite;
    private int cost;
    private static ArrayList<Item> items = new ArrayList<Item>();
    public Item(String d, ImageIcon s, int c) {
        description = d;
        sprite = s;
        cost = c;
        items.add(this);
    }
    public Item(String d, ImageIcon s) {
        this(d,s,2);
    }

    public static Item findItem(String s){
        for (Item i : items) {
            if (i.description.equals(s)) {
                return i;
            }
        }
        return null;
    }
    public ImageIcon getImage(){
        return sprite;
    }
    public String getDescription(){
        return description;
    }
    public int getCost(){
        return cost;
    }
    public boolean descEquals(String s){
        return s.equals(description);
    }
    public String toString(){
        return description;
    }
    public abstract boolean equals(Item other);
    public abstract ArrayList<String> getIngredientsInCup();
}
