import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JPanel implements ActionListener, MouseListener {
    private static JButton play, instructions;
    private static ImageIcon backgroundImage;
    private static ImageIcon playImg, playHoverImg, instructionsImg, instructionsHoverImg;
    public Menu(){
        setLayout(null);
        backgroundImage = new ImageIcon("menuBackground.png");

        playImg = Main.getResizedImage(new ImageIcon("playImg.png"), 150,50);
        playHoverImg = Main.getResizedImage(new ImageIcon("playHoverImg.png"),150,50);

        play = new JButton(playImg);
        play.setBounds(430,535,150,50);
        add(play);
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);
        play.addActionListener(this);
        play.addMouseListener(this);

        instructionsImg = Main.getResizedImage(new ImageIcon("instructionsImg.png"),150,50);
        instructionsHoverImg = Main.getResizedImage(new ImageIcon("instructionsHoverImg.png"),150,50);

        instructions = new JButton(instructionsImg);
        instructions.setBounds(430,600,150,50);
        add(instructions);
        instructions.setContentAreaFilled(false);
        instructions.setBorderPainted(false);
        instructions.addActionListener(this);
        instructions.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        g.drawImage(backgroundImage.getImage(),0,0,null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play){
            if (Sounds.getSoundsOn() && Sounds.getMusicOn()) {
                Sounds.startBackgroundMusic();
            }
            Main.cardL.show(Main.c, "Game");
            Main.c.getComponent(1).requestFocusInWindow();
            Game.startTimers();
        }
        if (e.getSource() == instructions){
            Main.cardL.show(Main.c, "Instructions");
            Main.c.getComponent(2).requestFocusInWindow();
            Instructions.startAnimation();
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
        if (e.getSource() == play){
            play.setIcon(playHoverImg);
        }
        if (e.getSource() == instructions){
            instructions.setIcon(instructionsHoverImg);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == play){
            play.setIcon(playImg);
        }
        if (e.getSource() == instructions){
            instructions.setIcon(instructionsImg);
        }
    }
}