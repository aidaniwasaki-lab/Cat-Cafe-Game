import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Instructions extends JPanel implements ActionListener, MouseListener {
    private JButton backButton;
    private ImageIcon backIcon, backHover;
    private ImageIcon instructionsBackground, instructionsImage, chairImage, seatedImage, currentChair;
    private ImageIcon restockingButton, ordersButton;
    private static Timer animationTimer;
    private BufferedImage currentImage;
    private static int x, y;
    private static long currentTime;
    public Instructions(){
        setLayout(null);

        instructionsBackground = new ImageIcon("instructionsBackground.png");
        instructionsImage = new ImageIcon("instructionsImage.png");

        backIcon = Main.getResizedImage(new ImageIcon("backBtn.png"),100,50);
        backHover = Main.getResizedImage(new ImageIcon("backBtnHover.png"),106,53);
        backButton = new JButton(backIcon);
        backButton.setBounds(870,10,100,50);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        this.add(backButton);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);


        x = 440;
        y = 200;
        currentImage = CustomerImages.getNextImage("down");
        chairImage = Main.getResizedImage(new ImageIcon("chairRight.png"),75,75);
        seatedImage = Main.getResizedImage(new ImageIcon("chairSeatedRight.png"),75,75);
        currentChair = chairImage;

        restockingButton = Main.getResizedImage(new ImageIcon("restockBtn.png"),120,30);
        ordersButton = Main.getResizedImage(new ImageIcon("ordersBtn.png"),120,30);

        animationTimer = new Timer(30, this);
    }
    public void paintComponent(Graphics g){
        g.drawImage(instructionsBackground.getImage(),0,0,null);
        g.drawImage(instructionsImage.getImage(),90,100,null);
        g.drawImage(Player.getPlayerStatic(), 650,190,null);
        g.drawImage(restockingButton.getImage(),480,375,null);
        g.drawImage(ordersButton.getImage(),750,375,null);
        if (System.currentTimeMillis() - currentTime > 1000) {
            g.drawImage(currentImage, x, y, null);
        }
        g.drawImage(currentChair.getImage(),420,300,null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            Main.cardL.show(Main.c, "Menu");
            Main.c.getComponent(0).requestFocusInWindow();
            animationTimer.stop();
        }
        if (e.getSource() == animationTimer) {
            if (System.currentTimeMillis() - currentTime > 1000) {
                y += 2;
                currentImage = CustomerImages.getNextImage("down");
                if (y > 300) {
                    y = 200;
                    currentChair = seatedImage;
                    currentTime = System.currentTimeMillis();
                }
                else{
                    currentChair = chairImage;
                }
                repaint();
            }
        }
    }
    public static void startAnimation(){
        animationTimer.start();
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
        backButton.setIcon(backHover);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        backButton.setIcon(backIcon);
    }
}
