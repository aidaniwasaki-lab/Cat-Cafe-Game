import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RestockingPanel extends MyPanel implements ActionListener, ItemListener {
    private final static int WIDTH = 375;
    private final static int HEIGHT = 150;
    private String currentCounter;
    private ImageIcon image, background;
    private JButton plus1, minus1, plus10, minus10, restockBtn;
    private int numberToRestock;
    private int numIngredientsLeft, cost;
    private JComboBox counterDropdown;
    public RestockingPanel(){
        setLayout(null);
        setBounds(0,435,WIDTH,HEIGHT);
        background = new ImageIcon("restockBackground.png");
        String[] counterNames = new String[Counter.getCounters().size()];
        for (int i = 0; i < counterNames.length; i++) {
            counterNames[i] = Counter.getCounters().get(i).getTypeOfItem() + " counter";
        }
        Arrays.sort(counterNames);
        counterDropdown = new JComboBox(counterNames);
        counterDropdown.setBounds(10,10,130,20);
        counterDropdown.setMaximumRowCount(5);
        //counterDropdown.get
        counterDropdown.addItemListener(this);
        add(counterDropdown);
        counterDropdown.addMouseListener(this);

        restockBtn = new JButton(Main.getResizedImage(new ImageIcon("restockActionBtn.png"),120,40));
        MyPanel.makeButton(restockBtn,15,105,120,40,this);
        restockBtn.addActionListener(this);
        restockBtn.addMouseListener(this);

        plus1 = new JButton(Main.getResizedImage(new ImageIcon("plus1.png"),50,50));
        minus1 = new JButton(Main.getResizedImage(new ImageIcon("minus1.png"),50,50));
        plus10 = new JButton(Main.getResizedImage(new ImageIcon("plus10.png"),50,50));
        minus10 = new JButton(Main.getResizedImage(new ImageIcon("minus10.png"),50,50));

        MyPanel.makeButton(plus1,170,10,50,50,this);
        MyPanel.makeButton(minus1,170,70,50,50,this);
        MyPanel.makeButton(plus10,230,10,50,50,this);
        MyPanel.makeButton(minus10,230,70,50,50,this);

        plus1.addActionListener(this);
        minus1.addActionListener(this);
        plus10.addActionListener(this);
        minus10.addActionListener(this);

        plus1.addMouseListener(this);
        minus1.addMouseListener(this);
        plus10.addMouseListener(this);
        minus10.addMouseListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == plus1){
            numberToRestock++;
        }
        else if (e.getSource() == minus1 && numberToRestock >= 1){
            numberToRestock--;
        }
        else if (e.getSource() == plus10){
            numberToRestock += 10;
        }
        else if (e.getSource() == minus10 && numberToRestock >= 10){
            numberToRestock -= 10;
        }
        if (e.getSource() == restockBtn && Game.getPlayerMoney() >= cost * numberToRestock && numberToRestock != 0){
            Counter c = Counter.findCounter(currentCounter);
            c.setItemsLeft(numberToRestock + c.getItemsLeft());
            numIngredientsLeft = c.getItemsLeft();
            Game.setPlayerMoney(Game.getPlayerMoney() - cost * numberToRestock);
            numberToRestock = 0;
        }
        this.getParent().addNotify();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            updateSelection();
        }
    }
    public void updateSelection(){ // Updates which selection of item to restock, with values
        String temp = counterDropdown.getSelectedItem().toString();
        currentCounter = temp.substring(0,temp.length()-8);
        numIngredientsLeft = Counter.findCounter(currentCounter).getItemsLeft();
        image = Item.findItem(currentCounter).getImage();
        cost = Item.findItem(currentCounter).getCost();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background.getImage(),0,0,WIDTH,HEIGHT,null);
        int money = Game.getPlayerMoney();
        g.drawImage(image.getImage(), 40, 32, 70,70,null);
        g.drawString("Buying: " + numberToRestock, 290, 20);
        g.drawString("Remaining: " + numIngredientsLeft, 290, 80);
        g.drawString("Cost: " + cost * numberToRestock, 290, 50);
        if (money < cost * numberToRestock){
            g.drawString("Insufficient money", 170, 140);
            restockBtn.setIcon(Main.getResizedImage(new ImageIcon("restockActionBtnGrey.png"),120,40));
        }
        else{
            g.drawString("Money after purchase: " + (money - cost * numberToRestock), 170, 140);
            restockBtn.setIcon(Main.getResizedImage(new ImageIcon("restockActionBtn.png"),120,40));
        }
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
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
