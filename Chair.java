import javax.swing.*;
import java.util.*;

public class Chair extends Interactables{
    private Customer customer;
    private boolean customerAssigned, customerSeated;
    private static ArrayList<Chair> chairs = new ArrayList<Chair>();
    private static ArrayList<Integer> availableIndex = new ArrayList<Integer>();
    private static int chance = 10; // Chance to seat customer
    private static double chanceDivisor = 1;
    private boolean chairRight; // Chair is on the right or left side (changes image)
    private int[][] coordinates;
    public Chair(ImageIcon i, int x, int y, int h, int w, int hx, int hy, boolean c, int[][] coords, String s) {
        super(i, x, y, h, w, hx, hy, s);
        chairRight = c;
        customerAssigned = false;
        chairs.add(this);
        availableIndex.add(chairs.indexOf(this));
        coordinates = coords;
        setRect(x+10, y+13,w-20,h-15);
    }

    public static boolean assignCustomer(){ // Seats a customer if a random integer from 0 to 9 is equal to 0, and returns true if a customer is now seated, false if no changes are made
        if (!availableIndex.isEmpty()) {
            int randInt = (int) (Math.random() * (chance / chanceDivisor));
            chance = (int)(chance/1.08);
            if (randInt == 0) {
                chance = 100*((Customer.getCustomers().size()+1));
                Customer c = new Customer(1);
                int randomIndex = (int) (Math.random() * availableIndex.size());
                Chair randChair = chairs.get(availableIndex.remove(randomIndex));
                randChair.customer = c;
                randChair.customerAssigned = true;
                c.changeImageIcon(randChair.chairRight);
                c.setPos(980,180,40,40);
                return true;
            }
        }
        return false;
    }
    public void seatCustomer(){ //Make the customer go in the seat
        customerSeated = true;
        customer.changeImageIcon(chairRight);
        customer.setPos(x,y,w,h);
        customer.setSeated(true);
        Game.updateCurrentOrders();
    }
    public void removeCustomer(){ //Remove customer out of seat
        availableIndex.add(chairs.indexOf(this));
        customerSeated = false;
        customer.setLeaving(true);
        customer.changeCurrentTarget();
        customer.setPos(coordinates[coordinates.length-1][0], coordinates[coordinates.length-1][1] ,40,40);
        customer.setSeated(false);
        Customer.updateSeatedCustomer();
        Game.updateCurrentOrders();
    }
    public void deleteCustomer(){ //Customer walks away, is now gone
        Customer.deleteCustomer(customer);
        customerAssigned = false;
    }
    public static void setChanceDivisor(int i){ //For upgrades panel, chance divisor used to increase chances of customer spawning
        chanceDivisor = 1 + Math.pow(i, 1.1)/10.0;
    }
    public static void updateCustomerPosition(){ //Updates customer position based on chair's predestined route and customer current position
        for (Chair c : chairs){
            if (c.customerAssigned && !c.customerSeated) {
                if (c.customer.getCurrentTarget() < c.coordinates.length) {
                    int[] currentCoords = c.customer.getCurrentCoords();
                    int[] targetCoords = c.coordinates[c.customer.getCurrentTarget()];
                    int dx = (int) Math.signum(targetCoords[0] - currentCoords[0]);
                    int dy = (int) Math.signum(targetCoords[1] - currentCoords[1]);
                    c.customer.moveCustomer(dx, dy);
                    currentCoords = c.customer.getCurrentCoords();
                    if (Arrays.equals(currentCoords, targetCoords)) {
                        if (c.customer.getCurrentTarget() == c.coordinates.length) {
                            Customer.updateSeatedCustomer();
                            c.seatCustomer();
                        }
                        else if (c.customer.getCurrentTarget() == 0 && c.customer.getLeaving()){
                            c.deleteCustomer();
                        }
                        else {
                            c.customer.changeCurrentTarget();
                        }
                    }
                } else {
                    Customer.updateSeatedCustomer();
                    c.seatCustomer();
                }
            }
        }
    }
    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public int getItemsLeft() {
        return 0;
    }

    @Override
    public void setItemsLeft(int i) {

    }

    public boolean customerSeated(){
        return customerSeated;
    }
    @Override
    public void setHasCup(boolean b) {

    }
    @Override
    public void setCup(Cup c) {

    }
    @Override
    public Cup getCup() {
        return customer.getOrder();
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
    public String getTypeOfItem() {
        return "";
    }
}
