package ATM;

import javax.swing.JFrame;
import java.awt.Color;

public class Runner{
    public static void main(String[] args) {
        NewWindow newWindow = new NewWindow();
        NewWindow.createWindow(newWindow); 
        
        // Window jFrame = new Window();

        // jFrame.setTitle("ATM Interfacce");
        // jFrame.setSize(500, 400);
        
        // jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // jFrame.getContentPane().setBackground(Color.DARK_GRAY);
        // jFrame.setResizable(false);
        // jFrame.setVisible(true);
    }    
}
