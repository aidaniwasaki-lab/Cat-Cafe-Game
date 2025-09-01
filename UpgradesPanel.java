import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class UpgradesPanel extends MyPanel implements ActionListener { // Panel for all upgrades
    private final static int WIDTH = 400;
    private final static int HEIGHT = 400;
    private ImageIcon background;
    private JButton advertisementButton, energyButton, cutenessButton, meowmaxButton;
    private int advertisementLevel, energyLevel, cutenessLevel, meowmaxLevel;
    private int advertisementCost, energyCost, cutenessCost, meowmaxxedCost;
    private ImageIcon upgrade, maxed, insufficient;
    private int maxLevel;
    public UpgradesPanel(){
        setLayout(null);
        setBackground(Color.GREEN);
        setBounds(Main.getWindowWidth()/2-WIDTH/2, Main.getWindowHeight()/2-HEIGHT/2,WIDTH,HEIGHT);
        background = new ImageIcon("upgradesBackground.png");

        upgrade = new ImageIcon("upgrade.png");
        maxed = new ImageIcon("max.png");
        insufficient = new ImageIcon("insufficient.png");

        advertisementButton = new JButton(upgrade);
        energyButton = new JButton(upgrade);
        cutenessButton = new JButton(upgrade);
        meowmaxButton = new JButton(upgrade);

        MyPanel.makeButton(advertisementButton,63,183,100,25,this);
        MyPanel.makeButton(energyButton,237,183,100,25,this);
        MyPanel.makeButton(cutenessButton,63,331,100,25,this);
        MyPanel.makeButton(meowmaxButton,237,331,100,25,this);

        advertisementButton.addActionListener(this);
        energyButton.addActionListener(this);
        cutenessButton.addActionListener(this);
        meowmaxButton.addActionListener(this);

        advertisementButton.addMouseListener(this);
        energyButton.addMouseListener(this);
        cutenessButton.addMouseListener(this);
        meowmaxButton.addMouseListener(this);

        advertisementLevel = 0;
        energyLevel = 0;
        cutenessLevel = 0;
        meowmaxLevel = 0;

        advertisementCost = 20;
        energyCost = 10;
        cutenessCost = 50;
        meowmaxxedCost = 100;

        maxLevel = 5;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == advertisementButton){ // Adds cost and level to advertisement if conditions met
            if (Game.getPlayerMoney() >= advertisementCost && advertisementLevel < maxLevel) {
                Chair.setChanceDivisor(advertisementLevel++);
                Game.setPlayerMoney(Game.getPlayerMoney() - advertisementCost);
                advertisementCost += 5;
            }
        }
        else if (e.getSource() == energyButton){ // Adds cost and level to energy if conditions met
            if (Game.getPlayerMoney() >= energyCost && energyLevel < maxLevel) {
                Game.addPlayerSpeed(0.3);
                Game.setPlayerMoney(Game.getPlayerMoney() - energyCost);
                energyCost += 5;
                energyLevel++;
            }
        }
        else if (e.getSource() == cutenessButton){ // Adds cost and level to cuteness if conditions met
            if (Game.getPlayerMoney() >= cutenessCost && cutenessLevel < maxLevel) {
                Game.incrementTips();
                Game.setPlayerMoney(Game.getPlayerMoney() - cutenessCost);
                cutenessCost += 15;
                cutenessLevel++;
            }
        }
        else if (e.getSource() == meowmaxButton){ // Adds cost and level to meowmax if conditions met
            if (Game.getPlayerMoney() >= meowmaxxedCost && meowmaxLevel < 2) {
                maxLevel += 3;
                Game.setPlayerMoney(Game.getPlayerMoney() - meowmaxxedCost);
                meowmaxxedCost += 100;
                meowmaxLevel++;
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background.getImage(),0,0,400,400,null);
        g.setFont(new Font(Main.getCustomFont().getFontName(),Font.PLAIN,12));
        g.setColor(Color.WHITE);
        drawSection(g, advertisementLevel, advertisementCost, maxLevel, 47, 159, advertisementButton);
        drawSection(g, energyLevel, energyCost, maxLevel, 220,159, energyButton);
        drawSection(g, cutenessLevel, cutenessCost, maxLevel, 47, 306, cutenessButton);
        drawSection(g, meowmaxLevel, meowmaxxedCost,2,220,306, meowmaxButton);
        g.setFont(Main.getCustomFont());
        g.setColor(Color.BLACK);
    }
    public void drawSection(Graphics g, int level, int cost, int max, int x, int y, JButton b){ // Quick method to draw and removes mouse listener if max level/insufficient funds
        if (level == max){
            b.removeMouseListener(this);
            b.setIcon(maxed);
        }
        else if (Game.getPlayerMoney() < cost){
            b.removeMouseListener(this);
            b.setIcon(insufficient);
        }
        else{
            b.addMouseListener(this);
            b.setIcon(upgrade);
        }
        g.drawString("Upgrade Level: " + level + "/" + max, x,y);
        g.drawString(level == max ? "MAX LEVEL" : "Cost to Upgrade: " + cost,x,y+15);
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
