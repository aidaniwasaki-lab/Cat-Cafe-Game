import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class OrderPanel extends MyPanel{
    private final static int WIDTH = 375;
    private final static int HEIGHT = 150;
    private JPanel ordersDisplay;
    private JScrollPane scrollPane;
    public OrderPanel(){
        this.setLayout(new BorderLayout());
        setBounds(0,435,WIDTH,HEIGHT);
        ordersDisplay = new JPanel();
        scrollPane = new JScrollPane(ordersDisplay);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        ordersDisplay.setLayout(new GridLayout(2,1));
        add(scrollPane, BorderLayout.CENTER);
        ordersDisplay.setBackground(Color.decode("#FFE2DF"));


    }
    public void updateCurrentOrders(){
        int temp = Customer.getCustomers().size();
        ordersDisplay.setLayout(new GridLayout(Math.max(temp, 2),1));
        ordersDisplay.removeAll();
        int customerNum = 1;
        for (int i = 0; i < temp; i++){
            if (Customer.getCustomers().get(i).getSeated()) {
                ordersDisplay.add(new OrderDisplayPanel(customerNum++, Customer.getCustomers().get(i)));
            }
        }
        ordersDisplay.revalidate();
        ordersDisplay.repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
