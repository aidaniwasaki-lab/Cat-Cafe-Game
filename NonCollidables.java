import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class NonCollidables { // Abstract class for all sprites that cannot be walked into
    private ImageIcon image;
    protected int x, y, h, w;
    private Rectangle rect;
    private static ArrayList<NonCollidables> objects = new ArrayList<NonCollidables>();
    private static int numObjects;

    public NonCollidables(ImageIcon i, int x, int y, int h, int w) {
        image = i;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        rect = new Rectangle(x, y, w, h);
        objects.add(this);
        numObjects++;
    }


    public static ArrayList<NonCollidables> getObjectList() {
        return objects;
    }

    public Rectangle getRect() {
        return rect;
    }
    public void setRect(int x, int y, int w, int h){ // manually set hitboxes
        rect = new Rectangle(x,y,w,h);
    }

    public static void addObject(NonCollidables object) {
        objects.add(object);
    }

    public static void paint(Graphics g) {
        for (NonCollidables n : objects) {
            g.drawImage(n.image.getImage(), n.x, n.y, n.w, n.h, null);
            if (n instanceof PrepTable && ((PrepTable) n).getHasCup()) {
                g.drawImage(((PrepTable) n).getCupImage(), n.x + 15, n.y-10, 50, 50, null);
            }
        }
    }
}