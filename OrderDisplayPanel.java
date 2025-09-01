import javax.swing.*;
import java.awt.*;
import java.util.*;

public class OrderDisplayPanel extends JPanel {
    private int orderNumber;
    private Customer customer;
    private ArrayList<String> ingredients = new ArrayList<String>();
    private JLabel draw;
    public static final ImageIcon ORDERDISPLAYIMG = Main.getResizedImage(new ImageIcon("orderDisplayBox.png"), 350, 100);
    public OrderDisplayPanel(int num, Customer c){
        setLayout(new GridLayout(1,1));
        setOpaque(false);
        orderNumber = num;
        customer = c;
        ingredients = customer.getOrder().getIngredientsInCup();
        draw = new JLabel(ORDERDISPLAYIMG);
        add(draw);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    public void paint(Graphics g) { // paint our ingredients above Java component
        super.paint(g);
        g.setFont(new Font(Main.getCustomFont().getFontName(),Font.PLAIN,48));
        g.drawString("#" + orderNumber, 30, 65);
        g.setFont(new Font(Main.getCustomFont().getFontName(),Font.PLAIN,18));
        int cost = customer.getOrderCost(); // default cup price
        for (int i = 0; i < ingredients.size(); i++){
            g.drawString(ingredients.get(i), 110, i*15+45);
        }
        g.drawString("$" + cost, 250, 45);
        g.drawString("$" + (Math.round(cost * 1.5) + Game.getTips()), 250, 75);
        g.setFont(Main.getCustomFont());
    }
}
