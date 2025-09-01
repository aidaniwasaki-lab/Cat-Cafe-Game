import javax.swing.*;
import java.util.ArrayList;

public class Counter extends Interactables{
    private String typeOfItem;
    private int itemsLeft;
    private static ArrayList<Counter> counterArr = new ArrayList<Counter>();
    private static ArrayList<Integer> itemsLeftArr = new ArrayList<Integer>();
    public Counter(ImageIcon img, int x, int y, int h, int w, String item, int hx, int hy, int i, String s){
        super(img, x, y, h, w, hx, hy,s);
        typeOfItem = item;
        itemsLeft = i;
        addObject(this);
    }
    public Counter(ImageIcon img, int x, int y, int h, int w, String item, int hx, int hy, String s){
        this(img,x,y,h,w,item,hx,hy,1,s);
    }
    public static void addObject(Counter c){ //Adds object to arraylist
        NonCollidables.addObject(c);
        counterArr.add(c);
        itemsLeftArr.add(c.itemsLeft);
    }
    public static ArrayList<Counter> getCounters(){
        return counterArr;
    }
    public static Counter findCounter(String s) { // Finds instance of counter given the name of an item
        for (Counter c : counterArr) {
            if (c.typeOfItem.equals(s)) {
                return c;
            }
        }
        return null;
    }
    public String getTypeOfItem(){
        return typeOfItem;
    }

    @Override
    public void setHasCup(boolean b) {

    }
    @Override
    public void setCup(Cup c) {

    }
    @Override
    public Cup getCup() {
        return null;
    }

    @Override
    public boolean getHasCup() {
        return false;
    }
    @Override
    public void createCup(ArrayList<String> ingredientsInCup) {

    }
    @Override
    public void addIngredient(String s) {

    }

    @Override
    public boolean customerSeated() {
        return false;
    }

    @Override
    public void removeCustomer() {

    }

    @Override
    public Customer getCustomer() {
        return null;
    }

    @Override
    public int getItemsLeft(){
        return itemsLeft;
    }

    @Override
    public void setItemsLeft(int i) {
        itemsLeft = i;
    }
}
