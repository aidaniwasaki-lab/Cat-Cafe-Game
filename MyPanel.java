import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public abstract class MyPanel extends JPanel implements MouseListener {
    public MyPanel(){

    }
    public static void makeButton(JButton b, int x, int y, int w, int h, JPanel p){
        b.setContentAreaFilled(false);
        b.setBounds(x,y,w,h);
        p.add(b);
    }
}
