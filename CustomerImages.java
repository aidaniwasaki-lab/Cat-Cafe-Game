import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CustomerImages { //For customer animations
    private static BufferedImage up, down, left, right, seatedLeft, seatedRight;
    private static Animations upAnimation, downAnimation, leftAnimation, rightAnimation;
    public static void loadImages(){
        try{
            up = ImageIO.read(new File("customerUp.png"));
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        upAnimation = new Animations(up, 1400, 800, 200, 200);

        try{
            down = ImageIO.read(new File("customerDown.png"));
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        downAnimation = new Animations(down, 1400, 800, 200, 200);

        try{
            left = ImageIO.read(new File("customerLeft.png"));
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        leftAnimation = new Animations(left, 1400, 600, 200, 200);

        try{
            right = ImageIO.read(new File("customerRight.png"));
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        rightAnimation = new Animations(right, 1400, 600, 200, 200);

        try{
            seatedLeft = ImageIO.read(new File("chairSeatedLeft.png"));
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        try{
            seatedRight = ImageIO.read(new File("chairSeatedRight.png"));
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }
    public static BufferedImage getNextImage(String s){ //Returns next image based on which direction customer going in
        if (s.equals("up")){
            return upAnimation.getNextImage();
        } else if (s.equals("down")){
            return downAnimation.getNextImage();
        } else if (s.equals("left")){
            return leftAnimation.getNextImage();
        } else if (s.equals("right")){
            return rightAnimation.getNextImage();
        }
        return null;
    }
    public static BufferedImage getCustomerSeatedImage(boolean b){ //Returns which way customer is seated
        return b ? seatedRight : seatedLeft;
    }
}
