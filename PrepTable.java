import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PrepTable extends Interactables{
    private boolean hasCup;
    private Cup cup;
    public PrepTable(ImageIcon img, int x, int y, int h, int w, int hx, int hy, String s) {
        super(img, x, y, h, w, hx, hy, s);
        hasCup = false;
    }

    @Override
    public String getTypeOfItem() {
        return "";
    }

    public void createCup(ArrayList<String> ingredientsInCup){
        cup = new Cup(ingredientsInCup);
    }
    public boolean getHasCup() {
        return hasCup;
    }
    public void setHasCup(boolean b) {
        hasCup = b;
    }
    public Cup getCup() {
        return cup;
    }
    public void setCup(Cup c){
        cup = c;
    }
    public void addIngredient(String s){
        cup.addIngredient(s);
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

    public Image getCupImage(){
        return cup.sprite.getImage();
    }
}