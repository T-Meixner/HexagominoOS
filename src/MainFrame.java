import java.awt.Color;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	public MainFrame ()
	{
		
		this.setTitle("Hexagomino");
		
//		this.setBackground( new Color (0xE57373FF) );
//		this.setBackground(Color.decode ("#E57373") );
//		this.setBackground(Color.black);
		
//		(Color.decode("#FFFF00"))
		
		/* 600px height + 38px for windows task bar */
		this.setSize (800, 638);
		
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}

}
