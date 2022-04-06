package ATM;

import javax.swing.*;
import java.awt.*;

class Window extends JFrame{

    private static ButtonGroup bg = new ButtonGroup();
    private static String[] daysOfWeek = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
    private static DAY day;
    protected static boolean isOpen = false;
    
    public static void createWindow() {
        JFrame frame = new JFrame("ATM Transactions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(500, 300);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    private static void createUI(JFrame frame){
        JPanel mainPanel = new JPanel();
        
        LayoutManager layout = new GridLayout(6, 2);
        mainPanel.setLayout(layout);
        mainPanel.setSize(300, 200);
        mainPanel.setBorder(BorderFactory.createTitledBorder("ATM"));

        for(int i = 0; i < 7; i++){
            JRadioButton day;
            day = new JRadioButton(daysOfWeek[i].toUpperCase());
            bg.add(day);
            mainPanel.add(day);
        }

        checkDay(bg);
        frame.getContentPane().add(new JLabel("Is the bank open? Check! What day of the week is it?"));
        frame.getContentPane().add(mainPanel);

    }
    private static boolean checkDay(ButtonGroup bg){
        day = DAY.MONDAY;

        if(bg.getSelection().getActionCommand() == "monday"){
            day = DAY.MONDAY;
            System.out.println("this works");
        }


        return DAY.FRIDAY.open;
    }
    
}
