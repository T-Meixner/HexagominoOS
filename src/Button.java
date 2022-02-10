import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class Button extends JButton
{
	public Button (String text)
	{
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		
		this.setForeground (Color.white);
		this.setBackground (Color.decode ("#5D4037") );
		
		this.setFont (new Font ("Fixedsys", Font.BOLD, 16));
		this.setBorder(raisedbevel);
		this.setFocusPainted (false);
		this.setText(text);
	}
}
