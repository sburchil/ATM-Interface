package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

class Window extends JFrame {

    Container container;
    Payment payOption;

    final static int height = 400;
    final static int width = 500;

    JFormattedTextField depositField = new JFormattedTextField();
    JTextField t2 = new JTextField();
    JLabel l1 = new JLabel("Amount you want to deposit: ");
    JLabel l2 = new JLabel("$");

    ButtonGroup bg = new ButtonGroup();
    JRadioButton option1 = new JRadioButton("Credit Card");
    JRadioButton option2 = new JRadioButton("Debit Card");
    JRadioButton option3 = new JRadioButton("Cash");
    JRadioButton option4 = new JRadioButton("Check");

    JButton b1 = new JButton("Complete Transaction");    

    public Window(){
        
        container = getContentPane();
        container.setLayout(null);

        l1.setForeground(new Color(233, 100, 0));
        l1.setBounds(10, -85, 250, 250);
        l2.setForeground(Color.WHITE);
        l2.setBounds(10, 50, 100, 25);
        depositField.setBounds(25, 50, 200, 25);
        depositField.setValue(Double.valueOf(100.00));
        depositField.setForeground(Color.WHITE);
        depositField.setBackground(Color.BLACK);


        container.add(l1);
        container.add(l2);
        container.add(depositField);

        option1.setBackground(Color.DARK_GRAY);
        option1.setForeground(Color.WHITE);
        option1.setBounds(10, 80, 100, 25);
        option1.setActionCommand(option1.getText());
        option2.setBackground(Color.DARK_GRAY);
        option2.setForeground(Color.WHITE);
        option2.setBounds(10, 100, 100, 25);
        option2.setActionCommand(option2.getText());
        option3.setBackground(Color.DARK_GRAY);
        option3.setForeground(Color.WHITE);
        option3.setBounds(110, 80, 100, 25);
        option3.setActionCommand(option3.getText());
        option4.setBackground(Color.DARK_GRAY);
        option4.setForeground(Color.WHITE);
        option4.setBounds(110, 100, 100, 25);
        option4.setActionCommand(option4.getText());


        b1.setBounds(10, 140, 175, 50);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                String textValue = depositField.getText();
                try{
                    switch (bg.getSelection().getActionCommand()){
                        case "Credit Card":
                            payOption = Payment.CREDIT;
                            payOption.amtDeposited += Double.parseDouble(textValue);
                            JOptionPane.showMessageDialog(new JFrame(), payOption.amtDeposited);
                            break;
                        case "Debit Card": 
                            payOption = Payment.DEBIT;
                            break;
                        case "Cash":
                            payOption = Payment.CASH;
                            break;
                        case "Check":
                            payOption = Payment.CHECK;    
                            break;
                    }
                } catch(NullPointerException err){
                    System.err.println("caught null");
                }
            }
        });

        bg.add(option1);
        bg.add(option2);
        bg.add(option3);
        bg.add(option4);


        container.add(option1);
        container.add(option2);
        container.add(option3);
        container.add(option4);
        container.add(b1);
        
    }

}
