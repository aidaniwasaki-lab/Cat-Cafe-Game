import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player{
    private BufferedImage rollRightImage, rollLeftImage, rollUpImage, rollDownImage, stationaryImage;
    private static Animations rollRight, rollLeft, rollUp, rollDown, stationary;
    private BufferedImage currentImage;
    private Item item;
    private ImageIcon itemHeld, currentItemBackground;
    private double x, y, dx, dy;
    private double speed = 2.2; //1.6;
    private final int WIDTH = 40;
    private final int HEIGHT = 40;
    private boolean wHeld, aHeld, sHeld, dHeld;
    private int money, moneyGained;
    private int tips;
    private static long currentTime;
    public Player(){
        // Load Images
        try{
            stationaryImage = ImageIO.read(new File("stationary.png"));
        }catch (Exception e){
            System.out.println("Error" + e);
        }
        stationary = new Animations(stationaryImage, 600,200,200,200);

        try{
            rollRightImage = ImageIO.read(new File("rollRight.png"));
        }catch (Exception e){
            System.out.println("Error " + e);
        }
        rollRight = new Animations(rollRightImage, 1400, 400, 200, 200);

        try{
            rollLeftImage = ImageIO.read(new File("rollLeft.png"));
        }catch (Exception e){
            System.out.println("Error " + e);
        }
        rollLeft = new Animations(rollLeftImage, 1400, 400, 200, 200);

        try{
            rollDownImage = ImageIO.read(new File("rollDown.png"));
        }catch (Exception e){
            System.out.println("Error " + e);
        }
        rollDown = new Animations(rollDownImage, 1400, 800, 200, 200);

        try{
            rollUpImage = ImageIO.read(new File("rollUp.png"));
        }catch (Exception e){
            System.out.println("Error " + e);
        }
        rollUp = new Animations(rollUpImage, 1400, 800, 200, 200);

        currentItemBackground = new ImageIcon("currentItemBackground.png");
        // Declare variables
        x = 100;
        y = 100;
        dx = 0;
        dy = 0;

        wHeld = false;
        aHeld = false;
        sHeld = false;
        dHeld = false;

        item = null;
        money = 1000;
        tips = 0;
    }
    public void updateMove(){ // Checks booleans and moves accordingly
        dy = (wHeld ? -speed :0) + (sHeld ? speed :0);
        dx = (aHeld ? -speed :0) + (dHeld ? speed :0);
        System.out.println("Current Item: " + item);
    }
    public void itemChange(){
        for (Interactables c : Interactables.getInteractables()) {
            if (c.getInteractable()) {
                String temp = c.getTypeOfItem();
                if (item == null) { // Deals with cases for a player retrieving items
                    if (c instanceof PrepTable &&  c.getHasCup()) { // Deals with cases for a player taking a cup off of a Prep OtherNonCollidables
                        item = c.getCup();
                        c.setHasCup(false);
                        c.setCup(null);
                        break;
                    } else if (c instanceof Counter){ // Taking from counters
                        if (c.getItemsLeft() > 0) { // If there are items left, take an item and reduce count of items in counter
                            if (temp.equals("cup")) { // Taking from cup bin
                                item = new Cup(temp, Item.findItem(temp).getImage());
                            }
                            else { // Taking from ingredient counters
                                item = new Ingredient(Item.findItem(temp).getDescription(), Item.findItem(temp).getImage());
                            }
                            c.setItemsLeft(c.getItemsLeft()-1); // items left--
                            break;
                        } else {
                            Game.largeScreenPopUp(new PopUp(new ImageIcon("insufficientMessage.png"),Main.getWindowWidth()/2 - 250, Main.getWindowHeight()/2 - 50, 500,100));
                        }
                    }
                } else { // Deals with cases for a player setting/discarding items
                    if (c instanceof PrepTable) {
                        if (item instanceof Cup && !(c.getHasCup())) {
                            c.setHasCup(true);
                            c.createCup(item.getIngredientsInCup());
                            if (item.getIngredientsInCup().size() == 0) {
                                c.getCup().updateImage(new ImageIcon("emptyCup.png"));
                            }
                            else{
                                c.getCup().updateImage(new ImageIcon("filledCup.png"));
                            }
                            item = null;
                        } else if (item instanceof Ingredient && c.getHasCup()) {
                            c.addIngredient(item.getDescription());
                            c.getCup().updateImage(new ImageIcon("filledCup.png"));
                            item = null;
                        }
                    } else if (c instanceof Trashbin) { // Deals with cases for a player setting items
                        item = null;
                    } else if (c instanceof Chair && item instanceof Cup) {
                        if (c.getCup().equals((Cup)item)){
                            Sounds.playMeowHappy();
                            moneyGained = (int)(c.getCustomer().getOrderCost()*1.5) + tips;
                            money += moneyGained;
                        } else {
                            Sounds.playMeowAngry();
                            moneyGained = -3;
                            money -= 3;
                        }
                        currentTime = System.currentTimeMillis();
                        c.removeCustomer();
                        item = null;
                    }
                }
            }
        }
        itemHeld = item == null ? null : item.getImage(); // Updates what player is holding

    }
    public void move(KeyEvent e){ // Sets booleans for movement to avoid delay
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W : wHeld = true; break;
            case KeyEvent.VK_A : aHeld = true; break;
            case KeyEvent.VK_S : sHeld = true; break;
            case KeyEvent.VK_D : dHeld = true; break;
        }
        updateMove();
    }
    public void stopMove(KeyEvent e){
        switch (e.getKeyCode()) { // Sets booleans to false
            case KeyEvent.VK_W : wHeld = false; break;
            case KeyEvent.VK_A : aHeld = false; break;
            case KeyEvent.VK_S : sHeld = false; break;
            case KeyEvent.VK_D : dHeld = false; break;
        }
        updateMove();
    }
    public Rectangle getPlayerRect(){
        return new Rectangle((int)Math.round(x)+5, (int)Math.round(y)+5 , WIDTH-10, HEIGHT-10);
    }
    public Rectangle getHitBoxPlayerRect(){
        return new Rectangle((int)Math.round(x) + 2, (int)Math.round(y) + 2, WIDTH + 4, HEIGHT + 4);
    }
    public void updateCoord(){
        if (dx != 0 && dy != 0){ // We normalize the vector by finding the intersection of the graphs: y = sqrt(2)*x and y = SPEED; derived from distance formula
            dx = (int)Math.signum(dx) * speed / Math.sqrt(2);
            dy = (int)Math.signum(dy) * speed / Math.sqrt(2);
        }
        x += dx;
        y += dy;
        for (NonCollidables n : NonCollidables.getObjectList()){ // If player collides with an object, player stops moving
            Rectangle playerRect = getPlayerRect();
            Rectangle temp = n.getRect();
            if (playerRect.intersects(temp)){
                x -= dx;
                y -= dy;
                break;
            }
        }
        if (x <= 0 || x >= Main.getWindowWidth()-WIDTH || y <= 0 || y >= Main.getWindowHeight()-HEIGHT*2){
            x -= dx;
            y -= dy;
        }
    }
    public void stopPlayer(){
        dx = 0;
        dy = 0;
        wHeld = false;
        aHeld = false;
        sHeld = false;
        dHeld = false;
    }
    public int getMoney(){
        return money;
    }
    public void setMoney(int m){
        money = m;
    }
    public void addSpeed(double s){
        speed += s;
    }
    public void incrementTips(){
        tips++;
    }
    public int getTips(){
        return tips;
    }
    public static BufferedImage getPlayerStatic(){
        return stationary.getNextImage();
    }
    public void paint(Graphics g){
        if (dHeld && !aHeld){
            currentImage = rollRight.getNextImage();
        }
        else if (aHeld && !dHeld){
            currentImage = rollLeft.getNextImage();
        }
        else if (wHeld && !sHeld){
            currentImage = rollUp.getNextImage();
        }
        else if (sHeld && !wHeld){
            currentImage = rollDown.getNextImage();
        }
        else{
            currentImage = stationary.getNextImage();
        }
        g.setColor(Color.decode("#5097B9"));
        g.drawImage(currentItemBackground.getImage(),0,435,375,150,null);
        g.drawImage(currentImage, (int)Math.round(x), (int)Math.round(y), null);
        if (itemHeld != null){ // if the player has an item, we display it
            g.setFont(new Font(Main.getCustomFont().getFontName(),Font.PLAIN,48));
            g.drawImage(itemHeld.getImage(),(int)Math.round(x)+WIDTH, (int)Math.round(y)+HEIGHT/2, 25, 25,null);
            g.drawString("Current Item: ", 20,480);
            g.drawString(item.getDescription(), 20,540);
            g.drawImage(itemHeld.getImage(), 280,460,90,90,null);
            if (item instanceof Cup) {
                g.setFont(new Font(Main.getCustomFont().getFontName(),Font.PLAIN,36));
                g.drawString("Ingredients: ", 120,520);
                g.setFont(new Font(Main.getCustomFont().getFontName(),Font.PLAIN,24));
                int temp = 0;
                int xCoord = 100;
                for (String s : (item).getIngredientsInCup()){
                    g.drawString(s, xCoord,550+temp++*25);
                    if (temp == 2){
                        temp = 0;
                        xCoord = 200;
                    }
                }
            }
        }
        else{
            g.setFont(new Font(Main.getCustomFont().getFontName(),Font.PLAIN,48));
            g.drawString("Current Item: ", 20,480);
            g.drawString("Nothing", 20,540);
            g.setFont(new Font(Main.getCustomFont().getFontName(),Font.PLAIN,24));
        }
        g.setFont(new Font(Main.getCustomFont().getFontName(),Font.PLAIN,24));
        g.setColor(new Color(21,120,17));
        g.drawString("Money: $" + money, 700, 650);
        if (System.currentTimeMillis() < currentTime + 2000){
            boolean plus = moneyGained >= 0;
            g.setColor(plus ? new Color(21,120,17) : Color.RED);
            g.drawString((plus ? "+" : "-") + ("$" + Math.abs(moneyGained)), 780, 620 + (int)(System.currentTimeMillis() - currentTime)/150);
        }
        g.setFont(Main.getCustomFont());
        g.setColor(Color.BLACK);
    }
}