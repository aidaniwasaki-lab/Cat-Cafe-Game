import javax.swing.*;
import java.awt.*;

public class PopUp {
    private ImageIcon popUpImage;
    private int x, y, w, h;

    public PopUp(ImageIcon i, int x, int y, int w, int h) {
        popUpImage = i;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void paint(Graphics g){
        g.drawImage(popUpImage.getImage(), x, y, w, h, null);
    }
}