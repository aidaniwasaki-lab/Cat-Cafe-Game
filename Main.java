/*
ICS3U7 Final Project
Aidan and Nimo
Cat Cafe
 */
// importing packages
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class Main extends JFrame {
    static CardLayout cardL;
    static Container c;

    private Menu m;
    private Game g;
    private Instructions i;

    private static Font font;

    private final static int GAMEHEIGHT = 700;
    private final static int GAMEWIDTH = 1000;

    public Main() {
        c = getContentPane();
        cardL = new CardLayout();
        c.setLayout(cardL);

        m = new Menu();
        g = new Game();
        i = new Instructions();

        Game.stopTimers();


        c.add("Menu", m);
        c.add("Game", g);
        c.add("Instructions", i);

    }
    public static void main(String[] args){
        Main.setFont();
        Main.setUIFont((new javax.swing.plaf.FontUIResource(font)));
        Main a = new Main();
        a.setSize(GAMEWIDTH, GAMEHEIGHT);
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setResizable(false);
    }
    public static int getWindowHeight(){
        return GAMEHEIGHT;
    }
    public static int getWindowWidth(){
        return GAMEWIDTH;
    }
    public static ImageIcon getResizedImage(ImageIcon icon, int w, int h){
        return new ImageIcon(icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
    }
    public static BufferedImage getResizedImage(BufferedImage image, int w, int h){
        Image temp = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tempGraphic = output.createGraphics();
        tempGraphic.drawImage(temp, 0, 0, null);
        tempGraphic.dispose();
        return output;
    }
    public static void setFont(){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Waterlily Script.ttf")).deriveFont(14f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        // Register the font with the GraphicsEnvironment
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
    }
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }
    public static Font getCustomFont(){
        return font;
    }
}
