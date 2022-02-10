import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class MenuActions implements ActionListener
{
	
	private int iPlayerCount;
	private int iCurrentPlayer = 0;
	
	private int [] iArColBtnInUse = {6, 6, 6, 6, 6, 6};
	
	private String [] sArPlyNamesChosen = {"", "", "", "", "", ""};
	
	private Color [] colArPlyColorsChosen = new Color [6];
	
	private boolean bSetPlayerCount = false;
	private boolean bSetPlayerColor = false;
	private boolean bSetPlayerName = false;
	
	public MenuActions ()
	{
		Menu.btnMenuMainStart.addActionListener (this);
		Menu.btnMenuMainManual.addActionListener (this);
		Menu.btnMenuMainQuit.addActionListener (this);
		
		Menu.btnMenuPlyCntNext.addActionListener (this);
		Menu.btnMenuPlyCntPrev.addActionListener (this);
		
		Menu.btnMenuPlyValNext.addActionListener (this);
		Menu.btnMenuPlyValPrev.addActionListener (this);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == Menu.btnMenuMainStart)
		{
			actMenuMainStart ();
		}
		
		if (e.getSource() == Menu.btnMenuMainManual)
		{
			actMenuMainManual ();
		}
		
		if (e.getSource() == Menu.btnMenuMainQuit)
		{
			actMenuMainQuit ();
		}
		
		if (e.getSource() == Menu.btnMenuPlyCntNext)
		{
			actMenuPlyCntNext ();
		}
		
		if (e.getSource() == Menu.btnMenuPlyCntPrev)
		{
			actMenuPlyCntPrev ();
		}
		
		if (e.getSource() == Menu.btnMenuPlyValNext)
		{
			actMenuPlyValNext ();
		}
		
		if (e.getSource() == Menu.btnMenuPlyValPrev)
		{
			actMenuPlyValPrev ();
		}
	}

	private void actMenuMainStart () 
	{
		Menu.clMenuCardLayout.next (Menu.pnlMenuCardPanel);	
	}
	
	private void actMenuMainManual ()
	{
		JLabel lblManual = new JLabel (MenuTexts.manual);
		lblManual.setFont (new Font ("Fixedsys", Font.PLAIN, 14));
		
		lblManual.setOpaque (true);
		lblManual.setBackground (Color.decode ("#D7CCC8"));
		
		JScrollPane spManual = new JScrollPane (lblManual);
		spManual.setPreferredSize (new Dimension (600, 400));
		
		JOptionPane.showOptionDialog (null, spManual, "Spielanleitung", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
				null, null, null);
	}
	
	private void actMenuMainQuit ()
	{
		int iQuit = JOptionPane.showConfirmDialog (null, "Möchten Sie das Spiel beenden?", 
				"Spiel beenden", JOptionPane.YES_NO_OPTION);
		
		if (iQuit == 0)
		{
			System.exit(0);
		}
	}
	
	private void actMenuPlyCntNext ()
	{		
		for (int i = 0; i < Menu.rdBtnArMenuPlyCntNumbers.length; i++)
		{
			if (Menu.rdBtnArMenuPlyCntNumbers[i].isSelected())
			{
				bSetPlayerCount = true;
				
				/* Button index 0 in ButtonArray == 2 Players */
				iPlayerCount = i +2;
				
				Menu.clMenuCardLayout.next (Menu.pnlMenuCardPanel);
			}
		}
		
		if (bSetPlayerCount == false)
		{
			JOptionPane.showMessageDialog (null, "Bitte geben Sie eine Spieleranzahl an", "Keine Spielerzahl", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void actMenuPlyCntPrev ()
	{
		Menu.clMenuCardLayout.previous (Menu.pnlMenuCardPanel);
	}
	
	private void actMenuPlyValNext ()
	{

		for (int iColNr = 0; iColNr < Menu.rdBtnArMenuPlyValColors.length; iColNr++)
		{
			if ( Menu.rdBtnArMenuPlyValColors[iColNr].isSelected() )
			{
				colArPlyColorsChosen [iCurrentPlayer] = Menu.colArPlyColors [iColNr];
				
				iArColBtnInUse [iCurrentPlayer] = iColNr;
				
				bSetPlayerColor = true;
			}
		}
		
		if (bSetPlayerColor == false) 
		{
			JOptionPane.showMessageDialog (null, "Bitte wählen Sie eine Spielerfarbe aus!", "Fehlende Spielerfarbe", JOptionPane.ERROR_MESSAGE);
		}
		
		if (Menu.txtFldMenuPlyValName.getText().length() != 0)
		{
			sArPlyNamesChosen [iCurrentPlayer] = Menu.txtFldMenuPlyValName.getText();
			
			bSetPlayerName = true;
		}
		
		if (Menu.txtFldMenuPlyValName.getText().length() == 0)
		{
			JOptionPane.showMessageDialog (null, "Bitte geben Sie einen Spielernamen ein!", "Fehlender Spielername", JOptionPane.ERROR_MESSAGE);
		}
		
		if (bSetPlayerColor == true && bSetPlayerName == true)
		{
			/* Start game if last player */
			if ((iCurrentPlayer +1) == iPlayerCount)
			{
				Game.startGame (iPlayerCount, colArPlyColorsChosen, sArPlyNamesChosen);
							
//				Main.mainFrame.remove (Main.menu);
//				Main.mainFrame.revalidate ();
//				Main.mainFrame.repaint ();
			}
			
			/* Set Value for next player */
			iCurrentPlayer = iCurrentPlayer + 1;
			
			/* Format label for next player */
			Menu.lblMenuPlyValCurrentPlayer.setText ("Spieler " + (iCurrentPlayer +1) );
			
			/* Reset radiobuttonpanel for next player */
			Menu.btnGrpMenuPlyValColors.clearSelection ();
			
			/* Format textfield for next player */
			/* Not if being last player */
			if (iCurrentPlayer < 6)
			{
				Menu.txtFldMenuPlyValName.setText (sArPlyNamesChosen [iCurrentPlayer]);
			}			
			
			/* Format button for last player, playernumber 0-5 <-> playercount 2-6*/
			if ((iCurrentPlayer +1) == iPlayerCount)
			{
				Menu.btnMenuPlyValNext.setText ("Spiel starten");
			}
			
			/* Disable all used RadioButtons: iArColBtnInUse index = playernumber, iArColBtnInUse value = buttonnumber */
			for (int iPlyNr = 0; iPlyNr < iArColBtnInUse.length; iPlyNr++)
			{
				/* Disable only if valid value 0-5 in iArColBtnInUse*/
				if (iArColBtnInUse[iPlyNr] < Menu.rdBtnArMenuPlyValColors.length)
				{
					Menu.rdBtnArMenuPlyValColors[iArColBtnInUse[iPlyNr]].setEnabled (false);
				}
			}
			
			/* Enable the RadioButton used by the player himself in previous choices*/
			/* Not if being last player */
			if (iCurrentPlayer < 6) 
			{
				if (iArColBtnInUse[iCurrentPlayer] < Menu.rdBtnArMenuPlyValColors.length) {
					Menu.rdBtnArMenuPlyValColors[iArColBtnInUse[iCurrentPlayer]].setEnabled(true);
					Menu.rdBtnArMenuPlyValColors[iArColBtnInUse[iCurrentPlayer]].setSelected(true);
				} 
			}
			/* Reset values for next Player */
			bSetPlayerColor = false;
			bSetPlayerName = false;
		}
	}
	
	private void actMenuPlyValPrev ()
	{
		/* Return to page MenuPlayerCount if player is first player */
		if (iCurrentPlayer == 0)
		{
			int iBackToMenuPlyCnt = JOptionPane.showConfirmDialog (null, "Möchten Sie zum Menü der Spieleranzahl?", 
					"Zum Menü Spieleranzahl", JOptionPane.YES_NO_OPTION);
			
			if (iBackToMenuPlyCnt == 0)
			{
				Menu.clMenuCardLayout.previous (Menu.pnlMenuCardPanel);
			}
			return;
		}
		
		/* Store the name of the current player */
		if (Menu.txtFldMenuPlyValName.getText().length() != 0)
		{
			sArPlyNamesChosen [iCurrentPlayer] = Menu.txtFldMenuPlyValName.getText();
		}
		
		/* Store the chosen radiobutton / color of the current player */
		for (int iColNr = 0; iColNr < Menu.rdBtnArMenuPlyValColors.length; iColNr++)
		{
			if ( Menu.rdBtnArMenuPlyValColors[iColNr].isSelected() )
			{
				colArPlyColorsChosen [iCurrentPlayer] = Menu.colArPlyColors [iColNr];
				
				iArColBtnInUse [iCurrentPlayer] = iColNr;
			}
		}
		
		/* Disable all used RadioButtons: iArColBtnInUse index = playernumber, iArColBtnInUse value = buttonnumber */
		for (int iPlyNr = 0; iPlyNr < iArColBtnInUse.length; iPlyNr++)
		{
			/* Disable only if valid value 0-5 in iArColBtnInUse*/
			if (iArColBtnInUse[iPlyNr] < Menu.rdBtnArMenuPlyValColors.length)
			{
				Menu.rdBtnArMenuPlyValColors[iArColBtnInUse[iPlyNr]].setEnabled (false);
			}
		}
		
		/* Change the current player to the previous player */
		iCurrentPlayer = iCurrentPlayer - 1;
				
		/* Format label for previous player */
		Menu.lblMenuPlyValCurrentPlayer.setText ("Spieler " + (iCurrentPlayer +1) );
			
		/* Format button for previous player */
		Menu.btnMenuPlyValNext.setText ("nächster Spieler");
		
		/* Format textfield for previous player */
		Menu.txtFldMenuPlyValName.setText (sArPlyNamesChosen[iCurrentPlayer]);
		
		/* Enable Button of previous player */
		Menu.rdBtnArMenuPlyValColors[iArColBtnInUse[iCurrentPlayer]].setEnabled (true);
		
		/* Select Button of previous player */
		Menu.rdBtnArMenuPlyValColors[iArColBtnInUse[iCurrentPlayer]].setSelected(true);
		
		/* Remove number of selected button/color of previous player */
		iArColBtnInUse[iCurrentPlayer] = 6;
	}
}
