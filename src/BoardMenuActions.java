import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class BoardMenuActions implements ActionListener
{
	public BoardMenuActions ()
	{
		Board.boardMenuGraphics.btnBoardMenuNext.addActionListener (this);
		Board.boardMenuGraphics.btnBoardMenuNewHxg.addActionListener (this);
		Board.boardMenuGraphics.btnBoardMenuManual.addActionListener (this);
		Board.boardMenuGraphics.btnBoardMenuQuit.addActionListener (this);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == Board.boardMenuGraphics.btnBoardMenuNext)
		{
			nextPlayerTurn ();
		}
		
		if (e.getSource() == Board.boardMenuGraphics.btnBoardMenuNewHxg)
		{
			getNewHexagon ();
		}
		
		if (e.getSource() == Board.boardMenuGraphics.btnBoardMenuManual)
		{
			showManual ();
		}
		
		if (e.getSource() == Board.boardMenuGraphics.btnBoardMenuQuit)
		{
			quitGame ();
		}
	}
	
	private void nextPlayerTurn ()
	{
		 Board.boardMenuGraphics.clBoardMenuCardLayout.next (Board.boardMenuGraphics.pnlBoardMenuCardPanel);
		 
		 if ( (Game.iCurrentPlayer +1) == Game.getPlayerCount ())
		 {
			 /* End the game if there have been no moves this round */
			 if (Game.iMoves == 0)
			 {
				 Game.missingMoves();
			 }
			 Game.iCurrentPlayer = 0;
			 Game.iRoundCount += 1;
			 Game.iMoves = 0;
		 }
		 else
		 {
			 Game.iCurrentPlayer += 1;
		 }
		 
		 Game.setPlayerPlacedHexagon (false);
		 Game.setPlayerDrawHexagon (false);
	}
	
	private void getNewHexagon ()
	{
		Game.drawNewHexagon();
		
		/* Set the possibility to draw another hexagon to locked */
		Game.setPlayerDrawHexagon (true);
	}
	
	private void showManual ()
	{
		JLabel lblManual = new JLabel (MenuTexts.manual);
		lblManual.setFont (new Font ("Fixedsys", Font.PLAIN, 14));
		
		JScrollPane spManual = new JScrollPane (lblManual);
		spManual.setPreferredSize (new Dimension (600, 400));
		
		JOptionPane.showOptionDialog (null, spManual, "Spielanleitung", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
				null, null, null);
	}
	
	private void quitGame ()
	{
		int iQuit = JOptionPane.showConfirmDialog (null, "Möchten Sie das Spiel beenden?", 
				"Spiel beenden", JOptionPane.YES_NO_OPTION);
		
		if (iQuit == 0)
		{
			System.exit(0);
		}
	}
}
