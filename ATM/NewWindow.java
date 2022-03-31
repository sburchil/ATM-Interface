package ATM;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class NewWindow extends JFrame implements PropertyChangeListener {
    private static JFormattedTextField principleTextField;
    private static JFormattedTextField amtTotal;
    private static JButton completeTransaction;
    private static ButtonGroup buttonGroup1, buttonGroup2;
    private static JRadioButton creditOption, debitOption, cashOption, checkOption;
    private static JRadioButton deposit, withdraw;
    private static Payment payOption;


    public static void createWindow(NewWindow tester) {
        JFrame frame = new JFrame("ATM Transactions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame, tester);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame, NewWindow tester) {
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        LayoutManager layout = new GridLayout(8, 2);
        panel.setLayout(layout);
        panel2.setLayout(new FlowLayout());
        panel.setSize(300, 200);
        panel2.setSize(200, 100);
        panel.setBorder(BorderFactory.createTitledBorder("ATM"));
        panel2.setBorder(BorderFactory.createTitledBorder("Transaction Type"));

        NumberFormat principleFormat = NumberFormat.getNumberInstance();
        principleTextField = new JFormattedTextField(principleFormat);
        principleTextField.setName("Amount");
        principleTextField.setColumns(10);
        JLabel principleLabel = new JLabel("Amount:");
        principleLabel.setLabelFor(principleTextField);
        principleTextField.setValue(Double.valueOf(100.00));
        principleTextField.addPropertyChangeListener("value", tester);

        NumberFormat amountFormat = NumberFormat.getCurrencyInstance();
        amtTotal = new JFormattedTextField(amountFormat);
        amtTotal.setName("Total Balance");
        amtTotal.setColumns(10);
        amtTotal.setEditable(false);
        JLabel amountLabel = new JLabel("Total Balance: ");
        amountLabel.setLabelFor(amtTotal);

        // NumberFormat amountFormat = NumberFormat.getCurrencyInstance();
        // amountTextField = new JFormattedTextField(amountFormat);
        // amountTextField.setName("Amount");
        // amountTextField.setColumns(10);
        // amountTextField.setEditable(false);
        // JLabel amountLabel = new JLabel("Amount:");
        // amountLabel.setLabelFor(amountTextField);
        // amountTextField.setValue(new Double(110000));

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
        
        completeTransaction = new JButton("Complete Transaction");
        completeTransaction.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String textValue = principleTextField.getText().replaceAll(",", "");
                try {
                    switch (buttonGroup1.getSelection().getActionCommand()) {
                        case "Credit Card":
                            payOption = Payment.CREDIT;

                            if(deposit.isSelected()){
                                payOption.amtDeposited += Double.parseDouble(textValue);
                                payOption.balanceTotal += Double.parseDouble(textValue);
                                amtTotal.setValue(payOption.balanceTotal);
                                JOptionPane.showMessageDialog(null, "Total Balance is Now: " 
                                + amtTotal.getText());
                            }
                            else if(withdraw.isSelected()){
                                if(payOption.balanceTotal <= 0.0){
                                    JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n"+
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
                            
                            if(deposit.isSelected()){
                                payOption.amtDeposited += Double.parseDouble(textValue);
                                payOption.balanceTotal += Double.parseDouble(textValue);
                                amtTotal.setValue(payOption.balanceTotal);
                                JOptionPane.showMessageDialog(null, "Total Balance is Now: " 
                                + amtTotal.getText());
                            }
                            else if(withdraw.isSelected()){
                                if(payOption.balanceTotal <= 0.0){
                                    JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n"+
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
                            
                            if(deposit.isSelected()){
                                payOption.amtDeposited += Double.parseDouble(textValue);
                                payOption.balanceTotal += Double.parseDouble(textValue);
                                if(payOption.balanceTotal < 0.0){
                                    JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n"+
                                    "NO MORE MONEY CAN BE WITHDRAWN!");
                                    amtTotal.setValue(0.0);
                                }
                                amtTotal.setValue(payOption.balanceTotal);
                                JOptionPane.showMessageDialog(null, "Total Balance is Now: " 
                                + amtTotal.getText());
                            }
                            else if(withdraw.isSelected()){
                                if(payOption.balanceTotal <= 0.0){
                                    JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n"+
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
                            
                            if(deposit.isSelected()){
                                payOption.amtDeposited += Double.parseDouble(textValue);
                                payOption.balanceTotal += Double.parseDouble(textValue);
                                amtTotal.setValue(payOption.balanceTotal);
                                JOptionPane.showMessageDialog(null, "Total Balance is Now: " 
                                + amtTotal.getText());
                            }
                            else if(withdraw.isSelected()){
                                if(payOption.balanceTotal <= 0.0){
                                    JOptionPane.showMessageDialog(null, "ACCOUNT BALANCE IS $0.0!\n"+
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
        });

        panel.add(principleLabel);
        panel.add(principleTextField);
        panel.add(amountLabel);
        panel.add(amtTotal);
        panel.add(todayLabel);
        panel.add(today);
        panel.add(completeTransaction);
        panel.add(new JLabel());
        panel.add(creditOption);
        panel.add(debitOption);
        panel.add(cashOption);
        panel.add(checkOption); 
        panel2.add(new JLabel("Would you like to withdraw or deposit?"));
        panel2.add(deposit);
        panel2.add(withdraw);
        // panel.updateUI();
        
        JPanel mainPanel = new JPanel();
        mainPanel.add(panel);
        mainPanel.add(panel2);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        
    }

    // @Override
    // public void propertyChange(PropertyChangeEvent evt) {

    //     principle = ((Number) principleTextField.getValue()).doubleValue();
    //     // rate = ((Number) rateTextField.getValue()).doubleValue() * 100;
    //     // years = ((Number) yearsTextField.getValue()).doubleValue();

    //     amount += principle;
    //     System.out.println(amount);
    //     amtTotal.setValue(new Double(amount));
    // }

}
