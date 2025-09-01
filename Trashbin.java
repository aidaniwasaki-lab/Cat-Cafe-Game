import javax.swing.*;
import java.util.ArrayList;

public class Trashbin extends Interactables{ // All trashbins

    public Trashbin(ImageIcon i, int x, int y, int h, int w, int hx, int hy, String s) {
        super(i, x, y, h, w, hx, hy,s);
    }

    @Override
    public String getTypeOfItem() {
        return "";
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
    public int getItemsLeft() {
        return 0;
    }

    @Override
    public void setItemsLeft(int i) {

    }
}
