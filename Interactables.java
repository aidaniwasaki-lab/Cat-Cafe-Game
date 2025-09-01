import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Interactables extends NonCollidables{ //All things player can interact with
    protected PopUp popUp;
    private boolean interactable;
    private int hitBoxX, hitBoxY;
    private Rectangle hitBox;
    private static ArrayList<Interactables> interactableArr = new ArrayList<Interactables>();
    public Interactables(ImageIcon i, int x, int y, int h, int w, int hx, int hy, String popUpDirection) {
        super(i, x, y, h, w);
        if (popUpDirection.equals("up")){
            popUp = new PopUp(new ImageIcon("counterPopUp.png"),x+w/2-25,y-50,50,50);
        } else if (popUpDirection.equals("left")){
            popUp = new PopUp(new ImageIcon("counterPopLeft.png"),x-50,y+h/2-25,50,50);
        } else if (popUpDirection.equals("down")){
            popUp = new PopUp(new ImageIcon("counterPopDown.png"),x+w/2-25,y+h,50,50);
        } else if (popUpDirection.equals("right")){
            popUp = new PopUp(new ImageIcon("counterPopRight.png"),x+w,y+h/2-25,50,50);
        }
        hitBoxX = hx;
        hitBoxY = hy;
        interactable = false;
        hitBox = new Rectangle(x-hitBoxX, y-hitBoxY,w+2*hitBoxX,h+2*hitBoxY);
        interactableArr.add(this);
    }
    public Rectangle getHitBox(){
        return hitBox;
    }
    public void setInteractable(boolean b){
        interactable = b;
    }
    public boolean getInteractable(){
        return interactable;
    }
    public static ArrayList<Interactables> getInteractables(){
        return interactableArr;
    }
    public static void paint(Graphics g){
        for (Interactables i : interactableArr){
            if (i.getInteractable()){
                i.popUp.paint(g);
            }
        }
    }
    public abstract String getTypeOfItem();
    public abstract void setHasCup(boolean b);
    public abstract void setCup(Cup c);
    public abstract Cup getCup();
    public abstract boolean getHasCup();
    public abstract void createCup(ArrayList<String> ingredientsInCup);
    public abstract void addIngredient(String s);
    public abstract boolean customerSeated();
    public abstract void removeCustomer();
    public abstract Customer getCustomer();

    public abstract int getItemsLeft();
    public abstract void setItemsLeft(int i);


}