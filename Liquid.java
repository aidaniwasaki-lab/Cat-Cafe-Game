import javax.swing.*;
import java.util.ArrayList;

public class Liquid extends Ingredient{
    private static ArrayList<String> liquidsArrayList = new ArrayList<String>();
    public Liquid(String d, ImageIcon s, int c) {
        super(d, s, c);
        liquidsArrayList.add(d);
    }
    public Liquid(String d, ImageIcon s) {
        this(d,s,2);
    }
    public static String randomItem(){
        return liquidsArrayList.get((int)(Math.random()*liquidsArrayList.size()));
    }
    @Override
    public boolean equals(Item other) {
        return super.equals(other);
    }
}
