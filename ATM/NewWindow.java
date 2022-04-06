package ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class NewWindow extends JFrame {
    private static JFormattedTextField principleTextField;
    private static JFormattedTextField amtTotal;
    private static JButton numBtn;
    private static ArrayList<JButton> keypadArray;
    private static ButtonGroup buttonGroup1, buttonGroup2;
    private static JRadioButton creditOption, debitOption, cashOption, checkOption;
    private static JRadioButton deposit, withdraw;
    private static Payment payOption;
    private static String keypadAmt = "";

    final private static String[] keypadContent = { "7", "8", "9", "4", "5", "6", "1", "2", "3", "Clear", "0",
            "Enter" };

    public static void createWindow(NewWindow tester) {
        JFrame frame = new JFrame("ATM Transactions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame, tester);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame, NewWindow tester) {
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        LayoutManager layout = new GridLayout(6, 2);
        panel.setLayout(layout);
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new GridLayout(4, 3));
        panel.setSize(300, 200);
        panel2.setSize(200, 100);
        panel3.setSize(600, 500);
        panel.setBorder(BorderFactory.createTitledBorder("ATM"));
        panel2.setBorder(BorderFactory.createTitledBorder("Transaction Type"));
        panel3.setBorder(BorderFactory.createTitledBorder("Keypad"));

        NumberFormat principleFormat = NumberFormat.getNumberInstance();
        principleTextField = new JFormattedTextField(principleFormat);
        principleTextField.setName("Amount");
        principleTextField.setColumns(10);
        JLabel principleLabel = new JLabel("Amount:");
        principleLabel.setLabelFor(principleTextField);
        principleTextField.setValue(Double.valueOf(100.00));

        NumberFormat amountFormat = NumberFormat.getCurrencyInstance();
        amtTotal = new JFormattedTextField(amountFormat);
        amtTotal.setName("Total Balance");
        amtTotal.setColumns(10);
        amtTotal.setEditable(false);
        JLabel amountLabel = new JLabel("Total Balance: ");
        amountLabel.setLabelFor(amtTotal);

        DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");
        JFormattedTextField today = new JFormattedTextField(dateFormat);
        today.setName("Today");
        today.setColumns(10);
        today.setEditable(false);
        JLabel todayLabel = new JLabel("Date:");
        todayLabel.setLabelFor(today);
        today.setValue(new Date());

        buttonGroup1 = new ButtonGroup();
        creditOption = new JRadioButton("Credit Card");
        creditOption.setActionCommand(creditOption.getText());
        debitOption = new JRadioButton("Debit Card");
        debitOption.setActionCommand(debitOption.getText());
        cashOption = new JRadioButton("Cash");
        cashOption.setActionCommand(cashOption.getText());
        checkOption = new JRadioButton("Check");
        checkOption.setActionCommand(checkOption.getText());

        buttonGroup1.add(creditOption);
        buttonGroup1.add(debitOption);
        buttonGroup1.add(cashOption);
        buttonGroup1.add(checkOption);

        buttonGroup2 = new ButtonGroup();
        deposit = new JRadioButton("Deposit");
        withdraw = new JRadioButton("Withdraw");

        buttonGroup2.add(deposit);
        buttonGroup2.add(withdraw);

    
        keypadArray = new ArrayList<JButton>(12);
        for (int i = 0; i < keypadContent.length; i++) {
            System.out.println(keypadContent[i]);
            numBtn = new JButton(keypadContent[i]);
            numBtn.setPreferredSize(new Dimension(70, 50));
            keypadArray.add(numBtn);
        }

        for (int n = 0; n < keypadArray.size(); n++) {
            
            final Integer innerN = new Integer(n);
            keypadArray.get(n).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                        if (keypadArray.get(innerN).getText().equals("Clear")){
                            principleTextField.setText("0.00");
                            keypadAmt = "";
                        }
                        else if (keypadArray.get(innerN).getText().equals("Enter")){
                            // JOptionPane.showMessageDialog(null, "You pressed enter, doesn't do anything rn.");
                            enterPressed();
                        }
                        else{
                            keypadAmt = keypadAmt + keypadArray.get(innerN).getText();
                            principleTextField.setText(keypadAmt);
                        }

                }
            });
            panel3.add(keypadArray.get(n));
        }

        panel.add(principleLabel);
        panel.add(principleTextField);
        panel.add(amountLabel);
        panel.add(amtTotal);
        panel.add(todayLabel);
        panel.add(today);
        panel.add(creditOption);
        panel.add(debitOption);
        panel.add(cashOption);
        panel.add(checkOption);
        panel2.add(new JLabel("Would you like to withdraw or deposit?"));
        panel2.add(deposit);
        panel2.add(withdraw);
        // panel.updateUI();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(panel);
        mainPanel.add(panel3);
        mainPanel.add(panel2, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private static void enterPressed(){
        String textValue = principleTextField.getText().replaceAll(",", "");
        try {
            switch (buttonGroup1.getSelection().getActionCommand()) {
                case "Credit Card":
                    payOption = Payment.CREDIT;

                    if (deposit.isSelected()) {
                        payOption.amtDeposited += Double.parseDouble(textValue);
                        payOption.balanceTotal += Double.parseDouble(textValue);
                        amtTotal.setValue(payOption.balanceTotal);
                        JOptionPane.showMessageDialog(null, "Total Balance is Now: "
                                + amtTotal.getText());
                    } else if (withdraw.isSelected()) {
                        if(Double.parseDouble(textValue) > payOption.balanceTotal){
                            JOptionPane.showMessageDialog(null, "CURRENT BALANCE IS $" + payOption.balanceTotal +
                             "\nA WITHDRAWL OF $" + Double.parseDouble(textValue) + " WOULD RESULT IN A NEGATIVE BALANCE!");
                             break;
                        }
                        else if (payOption.balanceTotal <= 0.0) {
                            JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n" +
                                    "NO MORE MONEY CAN BE WITHDRAWN!");
                            amtTotal.setValue(0.0);
                            break;
                        }
                        payOption.amtWithdrawn += Double.parseDouble(textValue);
                        payOption.balanceTotal -= Double.parseDouble(textValue);
                        amtTotal.setValue(payOption.balanceTotal);
                        JOptionPane.showMessageDialog(null, "Total Balance is Now: "
                                + amtTotal.getText());
                    }
                    break;
                case "Debit Card":
                    payOption = Payment.DEBIT;

                    if (deposit.isSelected()) {
                        payOption.amtDeposited += Double.parseDouble(textValue);
                        payOption.balanceTotal += Double.parseDouble(textValue);
                        amtTotal.setValue(payOption.balanceTotal);
                        JOptionPane.showMessageDialog(null, "Total Balance is Now: "
                                + amtTotal.getText());
                    } else if (withdraw.isSelected()) {
                        if(Double.parseDouble(textValue) > payOption.balanceTotal){
                            JOptionPane.showMessageDialog(null, "CURRENT BALANCE IS $" + payOption.balanceTotal +
                             "\nA WITHDRAWL OF $" + Double.parseDouble(textValue) + " WOULD RESULT IN A NEGATIVE BALANCE!");
                             break;
                        }
                        else if (payOption.balanceTotal <= 0.0) {
                            JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n" +
                                    "NO MORE MONEY CAN BE WITHDRAWN!");
                            amtTotal.setValue(0.0);
                            break;
                        }
                        payOption.amtWithdrawn += Double.parseDouble(textValue);
                        payOption.balanceTotal -= Double.parseDouble(textValue);
                        amtTotal.setValue(payOption.balanceTotal);
                        JOptionPane.showMessageDialog(null, "Total Balance is Now: "
                                + amtTotal.getText());
                    }
                    break;
                case "Cash":
                    payOption = Payment.CASH;

                    if (deposit.isSelected()) {
                        payOption.amtDeposited += Double.parseDouble(textValue);
                        payOption.balanceTotal += Double.parseDouble(textValue);
                        if (payOption.balanceTotal < 0.0) {
                            JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n" +
                                    "NO MORE MONEY CAN BE WITHDRAWN!");
                            amtTotal.setValue(0.0);
                        }
                        amtTotal.setValue(payOption.balanceTotal);
                        JOptionPane.showMessageDialog(null, "Total Balance is Now: "
                                + amtTotal.getText());
                    } else if (withdraw.isSelected()) {
                        if(Double.parseDouble(textValue) > payOption.balanceTotal){
                            JOptionPane.showMessageDialog(null, "CURRENT BALANCE IS $" + payOption.balanceTotal +
                             "\nA WITHDRAWL OF $" + Double.parseDouble(textValue) + " WOULD RESULT IN A NEGATIVE BALANCE!");
                             break;
                        }
                        else if (payOption.balanceTotal <= 0.0) {
                            JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n" +
                                    "NO MORE MONEY CAN BE WITHDRAWN!");
                            amtTotal.setValue(0.0);
                            break;
                        }
                        payOption.amtWithdrawn += Double.parseDouble(textValue);
                        payOption.balanceTotal -= Double.parseDouble(textValue);
                        amtTotal.setValue(payOption.balanceTotal);
                        JOptionPane.showMessageDialog(null, "Total Balance is Now: "
                                + amtTotal.getText());
                    }
                    break;
                case "Check":
                    payOption = Payment.CHECK;

                    if (deposit.isSelected()) {
                        payOption.amtDeposited += Double.parseDouble(textValue);
                        payOption.balanceTotal += Double.parseDouble(textValue);
                        amtTotal.setValue(payOption.balanceTotal);
                        JOptionPane.showMessageDialog(null, "Total Balance is Now: "
                                + amtTotal.getText());
                    } else if (withdraw.isSelected()) {
                        if(Double.parseDouble(textValue) > payOption.balanceTotal){
                            JOptionPane.showMessageDialog(null, "CURRENT BALANCE IS $" + payOption.balanceTotal +
                             "\nA WITHDRAWL OF $" + Double.parseDouble(textValue) + " WOULD RESULT IN A NEGATIVE BALANCE!");
                             break;
                        }
                        else if (payOption.balanceTotal <= 0.0) {
                            JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n" +
                                    "NO MORE MONEY CAN BE WITHDRAWN!");
                            amtTotal.setValue(0.0);
                            break;
                        }
                        payOption.amtWithdrawn += Double.parseDouble(textValue);
                        payOption.balanceTotal -= Double.parseDouble(textValue);
                        amtTotal.setValue(payOption.balanceTotal);
                        JOptionPane.showMessageDialog(null, "Total Balance is Now: "
                                + amtTotal.getText());
                    }
                    break;
            }
        } catch (NullPointerException err) {
            System.err.println("caught null");
        }
    }

}
