import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Customer{
    private BufferedImage customerImage;
    private int x, y, w, h;
    private Cup order;
    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    private int customerNumber;
    private int currentTarget; //index of coordinate that the customer is currently traveling to
    private boolean leaving, seated;
    private static ImageIcon[] customerPopUpImages = {new ImageIcon("customer1PopUp.png"),new ImageIcon("customer2PopUp.png"),new ImageIcon("customer3PopUp.png"),new ImageIcon("customer4PopUp.png"),new ImageIcon("customer5PopUp.png"),new ImageIcon("customer6PopUp.png"),new ImageIcon("customer7PopUp.png"),new ImageIcon("customer8PopUp.png")};
    public Customer(int n){
        customerImage = CustomerImages.getNextImage("left");

        x = 100;
        y = 0;
        w = 50;
        h = 50;
        order = Cup.randomOrder();
        customers.add(this);
        customerNumber = customers.size();
        currentTarget = 0;
        leaving = false;
        seated = false;
    }
    public Cup getOrder(){
        return order;
    }
    public void setPos(int newX, int newY, int newW, int newH){
        x = newX;
        y = newY;
        h = newH;
        w = newW;
    }
    public boolean getLeaving(){
        return leaving;
    }
    public static void deleteCustomer(Customer c){
        customers.remove(c);
    }
    public static void updateSeatedCustomer(){ //Used to update seated customer numbers
        int temp = 1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).seated) {
                customers.get(i).customerNumber = temp++;
            }
        }
    }

    public static void paint(Graphics g){
        for (Customer c : customers) {
            g.drawImage(c.customerImage, c.x, c.y, c.w, c.h, null);
            if (c.seated) {
                g.drawImage(customerPopUpImages[c.customerNumber - 1].getImage(), c.x, c.y - 50, 50, 50, null);
            }
        }
    }
    public int getOrderCost(){ //Gets cost of an order by going through ArrayList of items and adding their prices
        int temp = 1; // Starts at 1 because the cup costs a dollar by default
        for (String s : order.getIngredientsInCup()){
            try{
                temp += Item.findItem(s).getCost();
            } catch(Exception e){ // In case we are unable to find item.
                temp += 0;
                System.out.println("Error " + e);
            }
        }
        return temp;
    }
    public int getCurrentTarget(){
        return currentTarget;
    }
    public void changeCurrentTarget(){
        currentTarget += leaving ? -1 : 1;
    }
    public int[] getCurrentCoords(){
        return new int[] {x,y};
    }
    public void moveCustomer(int dx, int dy){ //Moves customer and updates their image based on direction
        if (dx > 0){
            customerImage = CustomerImages.getNextImage("right");
        } else if (dx < 0){
            customerImage = CustomerImages.getNextImage("left");
        } else if (dy < 0){
            customerImage = CustomerImages.getNextImage("up");
        } else if (dy > 0){
            customerImage = CustomerImages.getNextImage("down");
        }
        x += dx;
        y += dy;
    }
    public void setLeaving(boolean b){
        leaving = b;
    }
    public void setSeated(boolean b){
        seated = b;
    }
    public boolean getSeated(){
        return seated;
    }
    public static ArrayList<Customer> getCustomers(){
        return customers;
    }
    public void changeImageIcon(boolean b){
        customerImage = CustomerImages.getCustomerSeatedImage(b);
    }
}