import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class TestMenu {

    public static void main(String[] args) 
    {
        new TestMenu();
    }

    public TestMenu() 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                JFrame frame = new JFrame("Test");
                frame.add(new MenuPane());
                frame.setSize(800,600);
//                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

public class MenuPane extends JPanel {

        public MenuPane() {
//            setBorder(new EmptyBorder(10, 10, 10, 10));
//            setLayout (new GridLayout (3,3));
            GridBagLayout gbl = new GridBagLayout();
            
            gbl.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
            gbl.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
            setLayout(gbl);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            add(new JLabel("<html><h1><strong><i>HEXAGOMINO</i></strong></h1><hr></html>"), gbc);

            /*Ausrichtung des Button-Panel in Fenster-Mitte*/
            gbc.anchor = GridBagConstraints.CENTER;
            
            
            
//            gbc.gridwidth = 1;
//            gbc.gridheight = 3;
//            gbc.gridx = 3;
//            gbc.gridy = 5;
            
            /* Veränderung der Größe der Buttons über internal padding */
            gbc.ipadx = 100;
            gbc.ipady = 20;
            
            
//            gbc.gridwidth = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            /*Abstände der Elemente (Buttons) zueinander */
            gbc.insets = new Insets (5, 5, 5, 5);
            
            /*Größe der einzelnen Zellen*/
//          gbc.weightx = 1;
//        	gbc.weighty = 1;

            JPanel buttons = new JPanel(new GridBagLayout());
            
            /*Abstandstest*/
//            GridLayout layout = new GridLayout(0,3);
//            layout.setHgap(10);
//            layout.setVgap(10);
//            JPanel buttons2 = new JPanel ();
//            buttons2.setLayout(layout);
//            
//            JButton btn1 = new JButton ("Spiel beginnen");
//            JButton btn2 = new JButton ("Spielanleitung");
//            JButton btn3 = new JButton ("Spiel beenden");
//            
//            buttons2.add(btn1);
//            buttons2.add(btn2);
//            buttons2.add(btn3);
//            
//            add(buttons2, gbc);
            /*Ende Abstandstest*/
            
            buttons.add(new JButton("Spiel beginnen"), gbc);
            buttons.add(new JButton("Spielanleitung"), gbc);
            buttons.add(new JButton("Spiel beenden"), gbc);
   
            gbc.weighty = 1;
            add(buttons, gbc);
        }

    }

}

