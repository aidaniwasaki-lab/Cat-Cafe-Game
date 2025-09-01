/* Class for animations so that we can increment the current index
* for each respective group of BufferedImages */
import java.awt.image.BufferedImage;
import java.util.*;

public class Animations { //Easy access to animations
    private int current;
    private BufferedImage[] animation;
    public Animations(BufferedImage b, int x, int y, int w, int h){
        int numOfPictures = (x/w) * (y/h); // Divides length and width of spritesheet by single sprite size and multiplies (LW = A)
        animation = new BufferedImage[numOfPictures];
        int rows = y/h;
        int cols = x/w;
        int temp = 0;
        System.out.println(rows + " " + cols);
        for (int i = 0; i < rows; i++){ //Splits the sprite sheet into subimages
            for (int j = 0; j < cols; j++){
                animation[temp++] = Main.getResizedImage(b.getSubimage(j*200,i*200,200,200),40,40);
            }
        }
    }
    public BufferedImage getNextImage(){ //returns next image
        return animation[++current%animation.length];
    }
}
