import javax.swing.*;

public class Decorations extends NonCollidables{ // Decorations that cannot be collided with (tables, walls, etc.)
    public Decorations(ImageIcon i, int x, int y, int h, int w) {
        super(i, x, y, h, w);
    }
}
