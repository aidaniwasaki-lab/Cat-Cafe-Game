import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JLayeredPane implements KeyListener, ActionListener, MouseListener {
    private static Timer timer, customerTimer, customerMoveTimer;
    private static Timer messageTimer;
    private static boolean displayOrNot;
    private static PopUp messagePopUp;
    private static RestockingPanel restockingPanel;
    private static OrderPanel orderPanel;
    private static UpgradesPanel upgradesPanel;
    private JPanel[] buttonPanels;
    private boolean[] buttonPanelsVisible;
    private JButton restockingButton, ordersButton, upgradesButton, soundToggle, musicToggle, backButton;
    private static Player player;
    private ImageIcon background, itemBanner, ordersBanner, restockBanner, upgradesBanner, currentBanner;
    private ImageIcon soundOn, soundOff, musicOn, musicOff, backIcon, backHover;
    public Game(){
        // Initializing Variables
        player = new Player();

        background = new ImageIcon("background.png");

        itemBanner = new ImageIcon("itemBanner.png");
        ordersBanner = new ImageIcon("ordersBanner.png");
        restockBanner = new ImageIcon("restockBanner.png");
        upgradesBanner = new ImageIcon("upgradesBanner.png");
        currentBanner = itemBanner;

        backIcon = Main.getResizedImage(new ImageIcon("backBtn.png"),100,50);
        backHover = Main.getResizedImage(new ImageIcon("backBtnHover.png"),106,53);
        backButton = new JButton(backIcon);
        backButton.setBounds(870,10,100,50);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        this.add(backButton);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        // Loads sound effects and music
        Sounds.loadSounds();
        // Loads images for each customer number
        CustomerImages.loadImages();
        // Initializing all items in game and sprites (interactables and non collideables)
        Item boba = new Topping("boba", new ImageIcon("boba.png"));
        Item milk = new Liquid("milk", new ImageIcon("milk.png"));
        Item sugar = new Ingredient("sugar", new ImageIcon("sugar.png"), 1);
        Item ice = new Ingredient("ice", new ImageIcon("ice.png"), 1);

        Item grapefruit = new Liquid("grapefruit", new ImageIcon("grapefruit.png"));
        Item greentea = new Liquid("greentea", new ImageIcon("greentea.png"));
        Item jelly = new Topping("jelly", new ImageIcon("jelly.png"));
        Item lychee = new Liquid("lychee", new ImageIcon("lychee.png"));
        Item mango = new Liquid("mango", new ImageIcon("mango.png"));

        Interactables cupCounter = new Counter(new ImageIcon("cupCounter.png"),235,150,75,75,"cup",10,10, 3, "up");
        Item cup = new Cup("cup", new ImageIcon("emptyCup.png"), 1);

        Interactables bobaCounter = new Counter(new ImageIcon("bobaCounter.png"), 150, 0,75,75,"boba",0,10, "down");
        Interactables milkCounter = new Counter(new ImageIcon("milkCounter.png"),225,0,75,75,"milk",0,10, "down");
        Interactables sugarCounter = new Counter(new ImageIcon("sugarCounter.png"), 300,0,75,75,"sugar", 0, 10, 2, "down");

        Interactables iceCounter = new Counter(new ImageIcon("iceCounter.png"), 0,225,75,75,"ice", 10, 0,2, "right");
        Interactables grapefruitCounter = new Counter(new ImageIcon("grapefruitCounter.png"), 0, 75,75,75,"grapefruit",10,0, "right");
        Interactables greenteaCounter = new Counter(new ImageIcon("greenteaCounter.png"), 0, 150,75,75,"greentea",10,0, "right");

        Interactables jellyCounter = new Counter(new ImageIcon("jellyCounter.png"), 150, 300,75,75,"jelly",0,10, "up");
        Interactables lycheeCounter = new Counter(new ImageIcon("lycheeCounter.png"), 225, 300,75,75,"lychee",0,10,"up");
        Interactables mangoCounter = new Counter(new ImageIcon("mangoCounter.png"), 300, 300,75,75,"mango",0,10,"up");


        Interactables prepTable1 = new PrepTable(new ImageIcon("prepTable.png"),150,150,75,75, 10,10,"up");
        Interactables prepTable2 = new PrepTable(new ImageIcon("prepTable.png"),320,150,75,75, 10,10,"up");


        Interactables trashbin1 = new Trashbin (new ImageIcon("trashbin.png"),0,325,50,50,10,10,"right");
        Interactables trashbin2 = new Trashbin (new ImageIcon("trashbin.png"),75,0,50,50,10,10,"down");

        NonCollidables table0 = new Decorations(Main.getResizedImage(new ImageIcon("longTable.png"),400,100),575,475,100,400);
        Interactables chair1 = new Chair(new ImageIcon("chairLeft.png"),575,385,75,75,10,10, false, new int[][] {{980,180},{900,180},{900,350},{600,350},{600,380}}, "up");
        Interactables chair2 = new Chair(new ImageIcon("chairLeft.png"),675,385,75,75,10,10,false, new int[][] {{980,180},{900,180},{900,350},{700,350},{700,380}}, "up");
        Interactables chair3 = new Chair(new ImageIcon("chairRight.png"),775,385,75,75,10,10,true, new int[][] {{980,180},{900,180},{900,350},{800,350},{800,380}}, "up");
        Interactables chair4 = new Chair(new ImageIcon("chairRight.png"),875,385,75,75,10,10,true, new int[][] {{980,180},{900,180},{900,350},{900,380}},"up");

        NonCollidables table1 = new Decorations(new ImageIcon("table.png"), 700, 50, 100,100);
        Interactables chair5 = new Chair(new ImageIcon("chairLeft.png"), 625,62,75,75,10,10,false, new int[][] {{980,180},{620,180},{620,100}},"up");
        Interactables chair6 = new Chair(new ImageIcon("chairRight.png"), 800, 62,75,75,10,10,true, new int[][] {{980,180},{850,180},{850,100}},"up");

        NonCollidables table2 = new Decorations(new ImageIcon("table.png"), 700, 230, 100,100);
        Interactables chair7 = new Chair(new ImageIcon("chairLeft.png"), 625,242,75,75,10,10,false, new int[][] {{980,180},{620,180},{620,260}},"up");
        Interactables chair8 = new Chair(new ImageIcon("chairRight.png"), 800,242,75,75,10,10,true, new int[][] {{980,180},{850,180},{850,260}},"up");

        NonCollidables wall1 = new Decorations(new ImageIcon("wall.png"), 0,423,4,450);
        NonCollidables wall2 = new Decorations(new ImageIcon("wall.png"), 448,75,275,4);
        NonCollidables wall3 = new Decorations(new ImageIcon("wall.png"), 448,425,165,4);
        NonCollidables wall4 = new Decorations(new ImageIcon("wall.png"),450,588,4,550);

        // Initializing extra panels (restocking, orders, item held, upgrades panels) and respective buttons
        buttonPanels = new JPanel[3];
        buttonPanelsVisible = new boolean[3];

        restockingPanel = new RestockingPanel();
        buttonPanels[0] = restockingPanel;
        buttonPanelsVisible[0] = false;

        restockingButton = new JButton(Main.getResizedImage(new ImageIcon("restockBtn.png"), 200, 50));
        restockingButton.setBounds(20,600,200,50);
        this.add(restockingButton);
        restockingButton.addActionListener(this);
        restockingButton.addMouseListener(this);

        orderPanel = new OrderPanel();
        buttonPanels[1] = orderPanel;
        buttonPanelsVisible[1] = false;

        ordersButton = new JButton(Main.getResizedImage(new ImageIcon("ordersBtn.png"), 200,50));
        ordersButton.setBounds(240,600,200,50);
        this.add(ordersButton);
        ordersButton.addActionListener(this);
        ordersButton.addMouseListener(this);

        upgradesPanel = new UpgradesPanel();
        buttonPanels[2] = upgradesPanel;
        buttonPanelsVisible[2] = false;

        upgradesButton = new JButton(Main.getResizedImage(new ImageIcon("upgradesBtn.png"),200,50));
        upgradesButton.setBounds(460,600,200,50);
        this.add(upgradesButton);
        upgradesButton.addActionListener(this);
        upgradesButton.addMouseListener(this);

        // Initializing sound and music toggle buttons
        soundOn = Main.getResizedImage(new ImageIcon("soundOn.png"),50,50);
        soundOff = Main.getResizedImage(new ImageIcon("soundOff.png"),50,50);
        soundToggle = new JButton(soundOn);
        soundToggle.setBounds(920,600,50,50);
        soundToggle.setContentAreaFilled(false);
        this.add(soundToggle);
        soundToggle.addActionListener(this);
        soundToggle.addMouseListener(this);

        musicOn = Main.getResizedImage(new ImageIcon("musicOn.png"),50,50);
        musicOff = Main.getResizedImage(new ImageIcon("musicOff.png"),50,50);
        musicToggle = new JButton(musicOn);
        musicToggle.setBounds(850,600,50,50);
        musicToggle.setContentAreaFilled(false);
        this.add(musicToggle);
        musicToggle.addActionListener(this);
        soundToggle.addMouseListener(this);

        // Supplementary panel initialization and timers
        addKeyListener(this);
        setFocusable(true);

        // Default timer
        timer = new Timer(16,this);
        timer.start();
        // Timer for spawning customers
        customerTimer = new Timer(1000, this);
        customerTimer.start();
        // Timer for movement of customers
        customerMoveTimer = new Timer(10,this);
        customerMoveTimer.start();
        // Timer for counter empty message
        messageTimer = new Timer(1000, this);
        displayOrNot = false;

    }
    public void checkInteractables(){ // Checks if a player is in the appropriate hit-box of a counter and can interact with it
        boolean flag = false; // Flag so that only one interactable displays a pop-up at a time
        for (Interactables i : Interactables.getInteractables()){ // Loops through all interactables
            i.setInteractable(false);
            Rectangle playerRect = player.getHitBoxPlayerRect();
            Rectangle temp = i.getHitBox();
            if (playerRect.intersects(temp) && !flag){
                if (i instanceof Chair && i.customerSeated()){
                    i.setInteractable(true);
                }
                else if (!(i instanceof Chair)){
                    i.setInteractable(true);
                }
                flag = true;
            }
        }
    }
    public static int getPlayerMoney(){
        return player.getMoney();
    }
    public static void setPlayerMoney(int m){
        player.setMoney(m);
    }
    public static void addPlayerSpeed(double s){
        player.addSpeed(s);
    }
    public static void incrementTips(){
        player.incrementTips();
    }
    public static int getTips(){
        System.out.println(player.getTips());
        return player.getTips();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background.getImage(),0,0,null);
        g.drawImage(currentBanner.getImage(),375,435,null);
        player.paint(g);
        NonCollidables.paint(g);
        Customer.paint(g);
        Interactables.paint(g);
        if (displayOrNot){
            messagePopUp.paint(g);
            System.out.println("hi");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer ) { // Calls methods every timer tick
            player.updateCoord();
            checkInteractables();
            repaint();
        }
        if (e.getSource() == customerTimer){
            Chair.assignCustomer();
            Customer.updateSeatedCustomer();
        }
        if (e.getSource() == customerMoveTimer){
            Chair.updateCustomerPosition();
        }
        if (e.getSource() == restockingButton){
            currentBanner = restockBanner;
            player.stopPlayer();
            updatePanel(0);
            restockingPanel.updateSelection();
            addNotify();
        }
        if (e.getSource() == ordersButton){
            currentBanner = ordersBanner;
            player.stopPlayer();
            updatePanel(1);
            orderPanel.updateCurrentOrders();
            addNotify();
        }
        if (e.getSource() == upgradesButton){
            currentBanner = upgradesBanner;
            player.stopPlayer();
            updatePanel(2);
        }
        if (e.getSource() == backButton){
            stopTimers();
            Main.cardL.show(Main.c, "Menu");
            Main.c.getComponent(0).requestFocusInWindow();
            Sounds.stopBackgroundMusic();
        }
        if (e.getSource() == soundToggle){
            musicToggle.setIcon(Sounds.changeSoundsOn() ? musicOn : musicOff);
            soundToggle.setIcon(Sounds.getSoundsOn() ? soundOn : soundOff);
            addNotify();
        }
        if (e.getSource() == musicToggle){
            Sounds.changeMusicOn();
            musicToggle.setIcon(Sounds.getMusicOn() ? musicOn : musicOff);
            addNotify();
        }
        if (e.getSource() == messageTimer){
            messageTimer.stop();
            displayOrNot = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.move(e);
        if (e.getKeyCode() == KeyEvent.VK_E) {
            player.itemChange();
        }
        //repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.stopMove(e);
    }
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow(); // Request focus when the panel is displayed
    }
    public void updatePanel(int n){
        removeButtonPanels(n);
        buttonPanelsVisible[n] = !buttonPanelsVisible[n];
        if (buttonPanelsVisible[n]){
            this.add(buttonPanels[n]);
            buttonPanels[n].setVisible(true);
        }
        else{
            currentBanner = itemBanner;
            this.remove(buttonPanels[n]);
            addNotify(); // Requests focus from the window and lets us use keys to move again
        }
    }
    public static void largeScreenPopUp(PopUp p){
        messageTimer.start();
        messagePopUp = p;
        displayOrNot = true;
    }
    private void removeButtonPanels(int index){ // removes all panels except for the one at the specified index
        for (int i = 0; i < buttonPanels.length; i++){
            if (buttonPanelsVisible[i] && i != index){
                buttonPanelsVisible[i] = false;
                this.remove(buttonPanels[i]);
            }
        }
    }
    public static void updateCurrentOrders(){
        orderPanel.updateCurrentOrders();
    }
    public static void startTimers(){
        timer.start();
        customerMoveTimer.start();
        customerTimer.start();
    }
    public static void stopTimers(){
        timer.stop();
        customerTimer.stop();
        customerMoveTimer.stop();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Sounds.playClick();
        if (e.getSource() == backButton){
            backButton.setBounds(870,10,106,53);
            backButton.setIcon(backHover);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Sounds.stopClick();
        if (e.getSource() == backButton){
            backButton.setBounds(870,10,100,50);
            backButton.setIcon(backIcon);
        }
    }
}