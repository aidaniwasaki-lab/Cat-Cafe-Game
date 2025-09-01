import javax.swing.*;
import java.util.ArrayList;

public class Cup extends Item{
    private ArrayList<String> ingredientsInCup = new ArrayList<String>();
    public Cup(String d, ImageIcon s, int c){
        super(d,s,c);
    }
    public Cup(){
        super("customer", new ImageIcon("emptyCup.png"), 0);
    }
    public Cup(String d, ImageIcon s) {
        super(d, s, 1);
    }
    public Cup(ArrayList<String> a){
        super("Cup", new ImageIcon("emptyCup.png"),0);
        ingredientsInCup = a;

    }
    public static Cup randomOrder(int numLiquids, int numToppings){ //creates a random order (ArrayList of String ingredients)
        Cup c = new Cup();
        for (int i = 0; i < numLiquids; i++){
            c.ingredientsInCup.add(Liquid.randomItem());
        }
        for (int i = 0; i < numToppings; i++){
            c.ingredientsInCup.add(Topping.randomItem());
        }
        if ((int)(Math.random()*2) == 0){
            c.ingredientsInCup.add("sugar");
        }
        if ((int)(Math.random()*2) == 0){
            c.ingredientsInCup.add("ice");
        }
        return c;
    }
    public static Cup randomOrder(){ //Default random order
        return randomOrder(1,1);
    }
    public void addIngredient(String s){
        ingredientsInCup.add(s);
    }
    public void updateImage(ImageIcon i){
        sprite = i;
    }
    public boolean equals(Item c){ //Checks if 2 cups are the same and returns true or false
        Cup temp = (Cup) c;
        if (ingredientsInCup.size() == temp.ingredientsInCup.size()){
            return (ingredientsInCup.containsAll(temp.ingredientsInCup) && temp.ingredientsInCup.containsAll(ingredientsInCup));
        }
        return false;
    }
    public ArrayList<String> getIngredientsInCup(){
        return ingredientsInCup;
    }
    public String toString(){
        String temp = "Ingredients:";
        for (String s : ingredientsInCup){
            temp += " " + s;
        }
        return temp;
    }
}