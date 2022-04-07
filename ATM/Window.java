package ATM;

import javax.swing.*;
import java.awt.*;

class Window extends JFrame {

    private static ButtonGroup bg = new ButtonGroup();
    private static JFrame frame;
    private static JButton checkDay = new JButton("Check Day");
    private static String[] daysOfWeek = { "Sunday", "Monday", "Tuesday",
     "Wednesday", "Thursday", 
     "Friday", "Saturday" };
    private static DAY day;
    protected static boolean isOpen = false;

    protected static void createWindow() {
        JFrame frame = new JFrame("What Day is It?");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(400, 250);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    private static void createUI(JFrame frame) {
        Window.frame = frame;
        JPanel mainPanel = new JPanel();

        LayoutManager layout = new GridLayout(3, 1);
        mainPanel.setLayout(layout);
        mainPanel.setSize(300, 200);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel words = new JLabel("Is the bank open? Check! What day of the week is it?");
        words.setFont(new Font("Sans Serif", Font.BOLD, 14));
        
        checkDay.setBackground(Color.LIGHT_GRAY);
        for (int i = 0; i < 7; i++) {
            JRadioButton day;
            day = new JRadioButton(daysOfWeek[i]);
            day.setActionCommand(daysOfWeek[i]);
            day.setFont(new Font("Sans Serif", Font.BOLD, 14));
            bg.add(day);
            mainPanel.add(day);
        }
        checkDay.addActionListener(e -> checkDay(bg));
        checkDay.setFont(new Font("Sans Serif", Font.BOLD, 20));
        
        frame.getContentPane().add(words);
        frame.getContentPane().add(mainPanel);
        frame.getContentPane().add(checkDay);

    }

    private static void checkDay(ButtonGroup bg) {
        JFrame err = new JFrame();
        err.setSize(1000, 200);

        try {
            switch (bg.getSelection().getActionCommand()) {
                case "Sunday":
                    day = DAY.SUNDAY;
                    break;
                case "Monday":
                    day = DAY.MONDAY;
                    break;
                case "Tuesday":
                    day = DAY.TUESDAY;
                    break;
                case "Wednesday":
                    day = DAY.WEDNESDAY;
                    break;
                case "Thursday":
                    day = DAY.THURSDAY;
                    break;
                case "Friday":
                    day = DAY.FRIDAY;
                    break;
                case "Saturday":
                    day = DAY.SATURDAY;
                    break;
            }
            isOpen = day.open;
            if(isOpen){
                JOptionPane.showMessageDialog(err, "It is Currently " + 
                    bg.getSelection().getActionCommand() + "!\n" 
                    + "The Bank is Open!\nYou can proceed to transactions.");
                    frame.setVisible(false);
                NewWindow.createWindow(); 
            }
            else{
                JOptionPane.showMessageDialog(err, "The Bank is Not Open On " + 
                    bg.getSelection().getActionCommand() + "." +
                    "\nPlease come back at a later date.");
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(new JFrame(), "You need to choose a day");
        }
    }

}
