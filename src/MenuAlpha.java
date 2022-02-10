import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;





public class MenuAlpha extends JPanel
{
	
	
	public int iCurrentPlayerNr = 0;
	int iPlayerCount = 0;
	int [] iAPlayerColors = {6, 6, 6, 6, 6, 6};
	boolean bHasChosenColor = false;
	boolean bHasChosenName = false;
	
	String [] sAPlayerNames = {"Spieler 1", "Spieler 2", "Spieler 3", "Spieler 4", "Spieler 5", "Spieler 6"};
	
	private final Action actPlayerCount = new ActionPlayerCount ();
	
	JRadioButton btnRadioBtn2Players;
	JRadioButton btnRadioBtn3Players;
	JRadioButton btnRadioBtn4Players;
	JRadioButton btnRadioBtn5Players;
	JRadioButton btnRadioBtn6Players;
	
	JRadioButton [] bAColorButtons;
	
	CardLayout cl = new CardLayout();
	JPanel pnlMenuCard = new JPanel ();
	
	public MenuAlpha ()
	{
		JPanel testPanel = new JPanel();
		this.add(testPanel);
		
		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		/*Einstellungen für die Platzierung des Titels*/
		/*Einstellung für nur 1 Element pro Zeile*/
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        /*Einstellung für Anordnung in Mitte-Oben*/
        gbc.anchor = GridBagConstraints.NORTH;
        /*Einstellung für ausreichend Breite für Textdarstellung headline*/
        gbc.ipadx = 10;
        
        JLabel lblheadline = new JLabel ("<html><h1><strong><i>HEXAGOMINO</i></strong></h1><hr></html>");
        lblheadline.setFont (new Font ("Fixedsys", Font.PLAIN, 28) );
        lblheadline.setForeground(Color.BLUE);
//        lblheadline.setBackground(Color.RED);
        lblheadline.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.add(lblheadline, gbc);
        
//        JPanel pnlMenuCard = new JPanel ();
//        CardLayout cl = new CardLayout();
        pnlMenuCard.setLayout(cl);
        pnlMenuCard.setBackground(Color.cyan);
//        pnlMenuCard.setSize(300,400);
//        pnlMenuCard.setAlignmentY(TOP_ALIGNMENT);
//        pnlMenuCard.setAlignmentX(TOP_ALIGNMENT);
//        pnlMenuCard.setAlignmentX(Component.TOP_ALIGNMENT);
//        pnlMenuCard.setAlignmentY(Component.TOP_ALIGNMENT);
        
            
        /*Einstellungen für die Platzierung der Buttons*/
        /*Einstellung für Platzierung in Mitte-Mitte*/
        gbc.anchor = GridBagConstraints.CENTER;
        
        
        
        /*Einstellung gleiche Größe der Elemente in einem Container*/
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        /*Einstellung für Padding -> Abstand zu Elementrändern*/
        gbc.ipadx = 20;
        gbc.ipady = 20;
        
        /*Einstellung für Abstände der Buttons*/
        gbc.insets = new Insets (5, 5, 5, 5);
        
        /*------------ Menü: Hauptmenü --------------*/
                
        JButton btnStart = new JButton ("Spiel beginnen");
        JButton btnManual = new JButton ("Spielanleitung");
        JButton btnQuit = new JButton ("Spiel beenden");
        
//        btnStart.setFont(new Font ("Fixedsys", Font.BOLD, 14));
        btnManual.setFont(new Font ("Fixedsys", Font.BOLD, 14));
        btnQuit.setFont(new Font ("Fixedsys", Font.BOLD, 16));
        
//        btnStart.setAlignmentX(Component.TOP_ALIGNMENT);
//        btnStart.setAlignmentY(Component.TOP_ALIGNMENT);
//        btnManual.setAlignmentX(Component.TOP_ALIGNMENT);
//        btnManual.setAlignmentY(Component.TOP_ALIGNMENT);
//        btnQuit.setAlignmentX(Component.TOP_ALIGNMENT);
//        btnQuit.setAlignmentY(Component.TOP_ALIGNMENT);
        /*ändert nur die Textposition innerhalb des buttons:*/
//        btnStart.setVerticalAlignment(SwingConstants.NORTH);
        
        
        btnStart.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e) 
			{
				cl.next(pnlMenuCard);
			}
		});
        
        GridBagLayout gblMenuMain = new GridBagLayout();
//        gblMenuMain.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};

        /*die Spalte/Column nimmt eine feste Breite von 300px ein*/
//        gblMenuMain.columnWidths = new int[] {300};
                
        /* die Spalte/Column nimmt alles an verfügbarem Platz ein*/
//        gblMenuMain.columnWeights = new double[] {1};
        
        JPanel pnlMenuMain = new JPanel(gblMenuMain);
        /*für Panelgrößenprüfung*/
        pnlMenuMain.setBackground(Color.red);
//        pnlMenuMain.setAlignmentY(TOP_ALIGNMENT);
//        pnlMenuMain.setAlignmentX(TOP_ALIGNMENT);
//        pnlMenuMain.setAlignmentY(Component.TOP_ALIGNMENT);
//        pnlMenuMain.setAlignmentX(Component.TOP_ALIGNMENT);
        
        
        GridBagConstraints gbcMenuMain = new GridBagConstraints();
        
        gbcMenuMain.gridwidth = GridBagConstraints.REMAINDER;
        gbcMenuMain.anchor = GridBagConstraints.PAGE_START;
        gbcMenuMain.fill = GridBagConstraints.HORIZONTAL;
//        gbcMenuMain.fill = GridBagConstraints.VERTICAL;
        /*ipadx sinnlos wegen fill*/
        gbcMenuMain.ipadx = 20;
        gbcMenuMain.ipady = 20;
        gbcMenuMain.insets = new Insets (5, 5, 5, 5);
        
        /*Auf der X-Achse (horizontal) wird aller verfügbarer Platz eingenommen*/
        gbcMenuMain.weightx = 1;
        
        /*gridheight, gridwidth = wie viele Zeilen/Spalten EINE Komponente einnimmt*/
//        gbcMenuMain.gridheight = 5;
//        gbcMenuMain.gridwidth = 1;
//        gbcMenuMain.gridx = 0;
//        gbcMenuMain.gridy = 0;
        
        /*weightx = Abstand der Elemente in Zeile zueinander, weighty = Abstand der Elemente in Spalte zueinander*/
//        gbcMenuMain.weightx = 0;
//        gbcMenuMain.weighty = 1;
        
        pnlMenuMain.add(btnStart, gbcMenuMain);
        pnlMenuMain.add(btnManual, gbcMenuMain);
        pnlMenuMain.add(btnQuit, gbcMenuMain);
        
        
        pnlMenuCard.add(pnlMenuMain);
        
        /*------------ Menü: Spieleranzahl --------------*/
        
        JLabel lblPlayerCount = new JLabel("Spieleranzahl auswählen");
        lblPlayerCount.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayerCount.setFont (new Font ("Fixedsys", Font.PLAIN, 20) );
        
        btnRadioBtn2Players = new JRadioButton("");
        btnRadioBtn3Players = new JRadioButton("");
        btnRadioBtn4Players = new JRadioButton("");
        btnRadioBtn5Players = new JRadioButton("");
        btnRadioBtn6Players = new JRadioButton("");
        
//        JRadioButton btnRadioBtn2Players = new JRadioButton("");
//        JRadioButton btnRadioBtn3Players = new JRadioButton("");
//        JRadioButton btnRadioBtn4Players = new JRadioButton("");
//        JRadioButton btnRadioBtn5Players = new JRadioButton("");
//        JRadioButton btnRadioBtn6Players = new JRadioButton("");
        
        btnRadioBtn2Players.setHorizontalAlignment(JLabel.CENTER);
        btnRadioBtn3Players.setHorizontalAlignment(JLabel.CENTER);
        btnRadioBtn4Players.setHorizontalAlignment(JLabel.CENTER);
        btnRadioBtn5Players.setHorizontalAlignment(JLabel.CENTER);
        btnRadioBtn6Players.setHorizontalAlignment(JLabel.CENTER);
        
//        btnRadioBtn2Players.setAction(actPlayerCount);
        
        ButtonGroup btnGrpPlayerCount = new ButtonGroup();
        btnGrpPlayerCount.add(btnRadioBtn2Players);
        btnGrpPlayerCount.add(btnRadioBtn3Players);
        btnGrpPlayerCount.add(btnRadioBtn4Players);
        btnGrpPlayerCount.add(btnRadioBtn5Players);
        btnGrpPlayerCount.add(btnRadioBtn6Players);
        
        JLabel lblRadioBtn2Players = new JLabel ("2");
        JLabel lblRadioBtn3Players = new JLabel ("3");
        JLabel lblRadioBtn4Players = new JLabel ("4");
        JLabel lblRadioBtn5Players = new JLabel ("5");
        JLabel lblRadioBtn6Players = new JLabel ("6");
        
        lblRadioBtn2Players.setHorizontalAlignment(JLabel.CENTER);
        lblRadioBtn3Players.setHorizontalAlignment(JLabel.CENTER);
        lblRadioBtn4Players.setHorizontalAlignment(JLabel.CENTER);
        lblRadioBtn5Players.setHorizontalAlignment(JLabel.CENTER);
        lblRadioBtn6Players.setHorizontalAlignment(JLabel.CENTER);
        
        lblRadioBtn2Players.setFont (new Font ("Fixedsys", Font.PLAIN, 20) );
        lblRadioBtn3Players.setFont (new Font ("Fixedsys", Font.PLAIN, 20) );
        lblRadioBtn4Players.setFont (new Font ("Fixedsys", Font.PLAIN, 20) );
        lblRadioBtn5Players.setFont (new Font ("Fixedsys", Font.PLAIN, 20) );
        lblRadioBtn6Players.setFont (new Font ("Fixedsys", Font.PLAIN, 20) );
        
        JPanel pnlRadioBtnPlayerCount = new JPanel(new GridLayout(2,5,0,0));
               
        pnlRadioBtnPlayerCount.setBorder(BorderFactory.createLineBorder(Color.black));
        
               
//        lblRadioBtn2Players.setAlignmentX(CENTER_ALIGNMENT);
//        pnlRadioBtnPlayerCount.setAlignmentX(Component.CENTER_ALIGNMENT);
//        pnlRadioBtnPlayerCount.setAlignmentY(Component.CENTER_ALIGNMENT);
//        pnlRadioBtnPlayerCount.setAlignmentX(lblRadioBtn2Players.CENTER_ALIGNMENT);
//        pnlRadioBtnPlayerCount.setAlignmentY(lblRadioBtn2Players.CENTER_ALIGNMENT);
//        pnlRadioBtnPlayerCount.setAlignmentX(CENTER_ALIGNMENT);
//        pnlRadioBtnPlayerCount.setAlignmentY(CENTER_ALIGNMENT);
        
        
        
        pnlRadioBtnPlayerCount.add(btnRadioBtn2Players);
        pnlRadioBtnPlayerCount.add(btnRadioBtn3Players);
        pnlRadioBtnPlayerCount.add(btnRadioBtn4Players);
        pnlRadioBtnPlayerCount.add(btnRadioBtn5Players);
        pnlRadioBtnPlayerCount.add(btnRadioBtn6Players);
        
        pnlRadioBtnPlayerCount.add(lblRadioBtn2Players);
        pnlRadioBtnPlayerCount.add(lblRadioBtn3Players);
        pnlRadioBtnPlayerCount.add(lblRadioBtn4Players);
        pnlRadioBtnPlayerCount.add(lblRadioBtn5Players);
        pnlRadioBtnPlayerCount.add(lblRadioBtn6Players);
        
//        pnlRadioBtnPlayerCount.add();
//        pnlRadioBtnPlayerCount.add(pnlRadioBtn3Players);
//        pnlRadioBtnPlayerCount.add(pnlRadioBtn4Players);
//        pnlRadioBtnPlayerCount.add(pnlRadioBtn5Players);
//        pnlRadioBtnPlayerCount.add(pnlRadioBtn6Players);
        
               
        JButton btnSetPlayerCount = new JButton ("weiter");
        btnSetPlayerCount.setAction(actPlayerCount);
        
        /*------Test für Buttonfunktion------*/
        
//        btnSetPlayerCount.addActionListener(new ActionListener() 
//        {
//			public void actionPerformed(ActionEvent e) 
//			{
//				if (btnRadioBtn2Players.isSelected())
//				{
//					iPlayerCount = 2;
//					cl.next(pnlMenuCard);
//					System.out.println("Ist 2 angeklickt? " + iPlayerCount);
//				}
//				
//				if (btnRadioBtn3Players.isSelected())
//				{
//					iPlayerCount = 3;
//					cl.next(pnlMenuCard);
//					System.out.println("Ist 2 angeklickt? " + iPlayerCount);
//				}
//				
//				if (btnRadioBtn4Players.isSelected())
//				{
//					iPlayerCount = 4;
//					cl.next(pnlMenuCard);
//					System.out.println("Ist 2 angeklickt? " + iPlayerCount);
//				}
//				
//				if (btnRadioBtn5Players.isSelected())
//				{
//					iPlayerCount = 5;
//					cl.next(pnlMenuCard);
//					System.out.println("Ist 2 angeklickt? " + iPlayerCount);
//				}
//				
//				if (btnRadioBtn6Players.isSelected())
//				{
//					iPlayerCount = 6;
//					cl.next(pnlMenuCard);
//					System.out.println("Ist 2 angeklickt? " + iPlayerCount);
//				}
//			}
//		});
        
        JButton btnReturn = new JButton ("zurück");
        
        
        
//        btnSetPlayerCount.addActionListener(new ActionListener() 
//        {
//			public void actionPerformed(ActionEvent e) 
//			{
//				cl.next(pnlMenuCard);
//			}
//		});
        btnReturn.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e) 
			{
				cl.previous(pnlMenuCard);
			}
		});

   
        JPanel pnlMenuPlayerCount = new JPanel(gblMenuMain);
        /*für Panelgrößenprüfung*/
        pnlMenuPlayerCount.setBackground(Color.blue);
         
        pnlMenuPlayerCount.add(lblPlayerCount, gbcMenuMain);
        pnlMenuPlayerCount.add(pnlRadioBtnPlayerCount, gbcMenuMain);
        pnlMenuPlayerCount.add(btnSetPlayerCount, gbcMenuMain);
        pnlMenuPlayerCount.add(btnReturn, gbcMenuMain);
        
        pnlMenuCard.add(pnlMenuPlayerCount);
        
        /*----------- Menü: Spielerangaben ----------------*/
        
        JPanel pnlMenuPlayerValues = new JPanel(new GridBagLayout());
        /*für Panelgrößenprüfung*/
        pnlMenuPlayerValues.setBackground(Color.cyan);
        
        /*iCurrentPlayerNr +1 weil von 0 an gezählt wird*/
         
        JLabel lblCurrentPlayer = new JLabel("Spieler " + (iCurrentPlayerNr +1));
        lblCurrentPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        lblCurrentPlayer.setFont (new Font ("Fixedsys", Font.PLAIN, 20) );
        
        JLabel lblChooseColor = new JLabel ("Spielerfarbe auswählen");
        lblChooseColor.setHorizontalAlignment(SwingConstants.CENTER);
        lblChooseColor.setFont (new Font ("Fixedsys", Font.PLAIN, 14) );
        
        JLabel lblChooseName = new JLabel ("Spielername eingeben");
        lblChooseName.setHorizontalAlignment(SwingConstants.CENTER);
        lblChooseName.setFont (new Font ("Fixedsys", Font.PLAIN, 14) );
        
        JRadioButton[] rdBtnArColorButtons = 
        {
        
//        JRadioButton btnRadioBtnCol1 = new JRadioButton("");
        new JRadioButton(""),
        new JRadioButton(""),
        new JRadioButton(""),
        new JRadioButton(""),
        new JRadioButton(""),
        new JRadioButton(""),
        
        };
        
        
        
        /* Prüfung ob Radiobutton bzw. Farbe schon gewählt wurde: */
        /* Wenn die Farb-Nr 0-5 bereits im Array der Spielerfarben */
        /* ist, wird der Radiobutton auf nicht wählbar gesetzt */
        for (int iColNr = 0; iColNr <= 5; iColNr++)
        {
        	for (int iPlyNr = 0; iPlyNr <= 5; iPlyNr++)
        	{
        		if (iColNr == iAPlayerColors [iPlyNr] )
        		{
        			rdBtnArColorButtons[iColNr].setEnabled(false);
        		}
        	}
        };
       
        /*Formatierung der Radiobuttons: mittige Ausrichtung*/
        for (int iColNr = 0; iColNr <= 5; iColNr ++)
        {
        	rdBtnArColorButtons[iColNr].setHorizontalAlignment(JLabel.CENTER);
        }
        
//        btnRadioBtnCol1.setHorizontalAlignment(JLabel.CENTER);
//        btnRadioBtnCol2.setHorizontalAlignment(JLabel.CENTER);
//        btnRadioBtnCol3.setHorizontalAlignment(JLabel.CENTER);
//        btnRadioBtnCol4.setHorizontalAlignment(JLabel.CENTER);
//        btnRadioBtnCol5.setHorizontalAlignment(JLabel.CENTER);
//        btnRadioBtnCol6.setHorizontalAlignment(JLabel.CENTER);
        
        /*Formatierung der Radiobuttons: Hintergrund -> Spielerfarben*/
        
        Color [] colPlayerColors = {Color.black, Color.gray, Color.white, Color.pink, Color.orange, new Color (128, 0, 128)};
        
        for (int iColNr = 0; iColNr <= 5; iColNr ++)
        {
        	rdBtnArColorButtons[iColNr].setBackground(colPlayerColors [iColNr] );
        }
        
//        btnRadioBtnCol1.setBackground(Color.black);
//        btnRadioBtnCol2.setBackground(Color.gray);
//        btnRadioBtnCol3.setBackground(Color.white);
//        btnRadioBtnCol4.setBackground(Color.pink);
//        btnRadioBtnCol5.setBackground(Color.orange);
//        btnRadioBtnCol6.setBackground(new Color (128, 0, 128));
        
        /*Radiobuttons einer ButtonGruppe hinzufügen*/
        
        ButtonGroup btnGrpPlayerColors = new ButtonGroup();
        
        for (int iColNr = 0; iColNr < rdBtnArColorButtons.length; iColNr++)
        {
        	btnGrpPlayerColors.add (rdBtnArColorButtons[iColNr]);
        }
        
//        btnGrpPlayerCol.add(btnRadioBtnCol1);
//        btnGrpPlayerCol.add(btnRadioBtnCol2);
//        btnGrpPlayerCol.add(btnRadioBtnCol3);
//        btnGrpPlayerCol.add(btnRadioBtnCol4);
//        btnGrpPlayerCol.add(btnRadioBtnCol5);
//        btnGrpPlayerCol.add(btnRadioBtnCol6);
        
//        bAColorButtons[0] = btnRadioBtnCol1;
//        bAColorButtons[1] = btnRadioBtnCol2;
//        bAColorButtons[2] = btnRadioBtnCol3;
//        bAColorButtons[3] = btnRadioBtnCol4;
//        bAColorButtons[4] = btnRadioBtnCol5;
//        bAColorButtons[5] = btnRadioBtnCol6;
        
        /*Einfügen der Radiobuttons in ein Grid-Panel*/
        
        JPanel pnlRdBtnPlayerColors = new JPanel (new GridLayout (1,6,0,0) );
        pnlRdBtnPlayerColors.setBorder ( BorderFactory.createLineBorder (Color.black) );
        
        for (int iColNr = 0; iColNr < rdBtnArColorButtons.length; iColNr++)
        {
        	pnlRdBtnPlayerColors.add ( rdBtnArColorButtons[iColNr] );
        }
        
//        pnlRadioBtnPlayerCol.add(btnRadioBtnCol1);
//        pnlRadioBtnPlayerCol.add(btnRadioBtnCol2);
//        pnlRadioBtnPlayerCol.add(btnRadioBtnCol3);
//        pnlRadioBtnPlayerCol.add(btnRadioBtnCol4);
//        pnlRadioBtnPlayerCol.add(btnRadioBtnCol5);
//        pnlRadioBtnPlayerCol.add(btnRadioBtnCol6);
        
        JTextField txtFldPlayerName = new JTextField();
        txtFldPlayerName.setColumns(10);
        
        JButton btnNextPlayer = new JButton ("nächster Spieler");
        
        btnNextPlayer.addActionListener (new ActionListener()
        {
			public void actionPerformed(ActionEvent e) 
			{
				
				/*muss auf 0 gesetzt werden, weil Ini erst in Schleife*/
				/*kann auf 0 gesetzt werden, weil Nutzung erst wenn Schleife bHasChosenColor auf true ist*/
				int iChosenColor = 0;
				
				for (int iColNr = 0; iColNr <= 5; iColNr++)
				{
					if ( rdBtnArColorButtons[iColNr].isSelected() )
					{
						setPlayerColor (iCurrentPlayerNr, iColNr);
						iChosenColor = iColNr;
						bHasChosenColor = true;
					}
				}
				if (bHasChosenColor == false)
				{
					/*Warnmeldung: Farbe auswählen*/
					
					JOptionPane opNoChosenColor = new JOptionPane ("Bitte wählen Sie eine Farbe aus!", JOptionPane.WARNING_MESSAGE);
					
					JDialog dlgNoChosenColor = opNoChosenColor.createDialog ("Keine Farbe ausgewählt");
					
					
					
					dlgNoChosenColor.setAlwaysOnTop(true);
					
					dlgNoChosenColor.setVisible(true);
					
//					opNoChosenColor.showMessageDialog (null, "Bitte wählen Sie eine Farbe aus!");
					
//					JDialog dialog = optionPane.createDialog("Warning!");
					
//					JOptionPane opNoChosenColor = new JOptionPane (JOptionPane.WARNING_MESSAGE);
					
//					JDialog dlgNoChosenColor = opNoChosenColor.createDialog("Achtung!");
//					
//					JLabel lblNoChosenColor = new JLabel ("Bitte wählen Sie eine Farbe aus!");
////					lblNoChosenColor.setAlignmentX(CENTER_ALIGNMENT);
////					lblNoChosenColor.setFont ( new Font ("Fixedsys", Font.BOLD, 20) );
//					
////					dlgNoChosenColor.getContentPane().add ( lblNoChosenColor );
//					dlgNoChosenColor.add(lblNoChosenColor);
////					dlgNoChosenColor.setAlwaysOnTop(true);
////					dlgNoChosenColor.getPreferredSize();
////					dlgNoChosenColor.setSize(dlgNoChosenColor.getPreferredSize());
//					dlgNoChosenColor.setLocationRelativeTo(null);
//					dlgNoChosenColor.setModal(true);
//					dlgNoChosenColor.setVisible(true);
					
				}
				
				if (txtFldPlayerName.getText().length() == 0)
				{
					/*Warnmeldung: Spielername eingeben*/
					
					JOptionPane opNoChosenName = new JOptionPane ("Bitte geben Sie einen Namen ein!", JOptionPane.WARNING_MESSAGE);
					
					JDialog dlgNoChosenName = opNoChosenName.createDialog ("Keinen Namen eingegeben");
					
					dlgNoChosenName.setAlwaysOnTop(true);
					
					dlgNoChosenName.setVisible(true);
				}
				
				if (bHasChosenColor == true && txtFldPlayerName.getText().length() != 0)
				{
					setPlayerName (iCurrentPlayerNr, txtFldPlayerName.getText());
					bHasChosenName = true;
				}
				
				if (bHasChosenColor == true && bHasChosenName == true)
				{
					System.out.println("Eintrag erfolgt: Name = " + sAPlayerNames[iCurrentPlayerNr] + " und Farbe mit Nummer: " + iAPlayerColors[iCurrentPlayerNr]);
					
//					pnlMenuCard.remove(pnlMenuPlayerValues);
//					pnlMenuCard.revalidate();
//					pnlMenuCard.repaint();
//					pnlMenuCard.add(pnlMenuPlayerValues, "sPnlMenuPlayerValues");
//					pnlMenuCard.revalidate();
//					pnlMenuCard.repaint();
//					cl.show(pnlMenuCard, "sPnlMenuPlayerValues");
					
//					pnlMenuPlayerValues.remove(lblCurrentPlayer);
//					
//					pnlMenuPlayerValues.revalidate();
//					pnlMenuPlayerValues.repaint();
					
					iCurrentPlayerNr ++;
					lblCurrentPlayer.setText("Spieler " + (iCurrentPlayerNr +1));
					
//					rdBtnArColorButtons[iChosenColor].setSelected(false);
//					rdBtnArColorButtons[iChosenColor].setForeground(Color.green);				
//					rdBtnArColorButtons[iChosenColor].setIcon(rdBtnArColorButtons[iChosenColor].getDisabledIcon());
					
					/*damit der Spieler nicht durch Klick auf weiter dieselbe Farbe übernehmen kann*/
					/*Zurücksetzen und Sperren des ausgewählten Radiobuttons*/
//					rdBtnArColorButtons[iChosenColor].isSelected() = false;
//					rdBtnArColorButtons[iChosenColor].setSelected(false);
//					rdBtnArColorButtons[iChosenColor].setText("");
					
					/*Zurücksetzen und Sperren des ausgewählten Radiobuttons*/
					rdBtnArColorButtons[iChosenColor].setEnabled(false);
					btnGrpPlayerColors.clearSelection();
					
					
					/*Zurücksetzen des Textfeldes für nächsten Spieler*/
					txtFldPlayerName.setText("");
					
//					pnlMenuPlayerValues.add(lblCurrentPlayer, gbcMenuMain);
					
//					pnlMenuCard.remove(pnlMenuPlayerValues);
//					pnlMenuCard.add(pnlMenuPlayerValues, "sPnlMenuPlayerValues");
//					cl.show(pnlMenuCard, "sPnlMenuPlayerValues");
					
//					pnlMenuCard.revalidate();
//					pnlMenuCard.repaint();
					
					/*Zurücksetzung der Prüfung von RadioButton und Textfeld*/
					bHasChosenColor = false;
					bHasChosenName = false;
					
					/*Umbenennung des Buttons bei letztem Spieler*/
					if ( iCurrentPlayerNr == (iPlayerCount-1) )
					{
						btnNextPlayer.setText("Spiel starten");
						
//						Board.test();
						
						
						
//						Board testspielfeld = new Board();
//						testspielfeld.berechneXYZ();
//						
//						testPanel.add(testspielfeld.test);
						
						
						
					}
					
					System.out.println("aktueller Spieler " + iCurrentPlayerNr);
					System.out.println("bHasChosenColor gesetzt auf " + bHasChosenColor);
					System.out.println("bHasChosenName gesetzt auf " + bHasChosenName);
					System.out.println("iChosenColor gesetzt auf " + iChosenColor);
					System.out.println("rdBtnArColorButtons[iChosenColor] gesetzt auf " + rdBtnArColorButtons[iChosenColor].isSelected());
					System.out.println("Spieler Anzahl gesetzt auf " + iPlayerCount);
				}
				
				/*Remove des Panels, Aufbau des Panels*/
				/*Name des Buttons abhängig von Spielerzahl*/
				/*Remove und Aufbau Panel abhängig von Spielerzahl*/
				/*nächster Spieler -> current Player +1*/
				/*bHasChosenColor wieder auf false setzen für nächsten Spieler*/
				/*bHasChosenName wieder auf false setzen für nächsten Spieler*/
			}
		});
        
        JButton btnReturn2 = new JButton ("zurück");
        
        btnReturn2.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e) 
			{
				cl.previous(pnlMenuCard);
			}
		});
        
        
        
        pnlMenuPlayerValues.add(lblCurrentPlayer, gbcMenuMain);
        pnlMenuPlayerValues.add(lblChooseColor, gbcMenuMain);
        pnlMenuPlayerValues.add(pnlRdBtnPlayerColors, gbcMenuMain);
        pnlMenuPlayerValues.add(lblChooseName, gbcMenuMain);
        pnlMenuPlayerValues.add(txtFldPlayerName, gbcMenuMain);
        pnlMenuPlayerValues.add(btnNextPlayer, gbcMenuMain);
        pnlMenuPlayerValues.add(btnReturn2, gbcMenuMain);
        
        pnlMenuCard.add(pnlMenuPlayerValues);
        
        /*Einstellung für Abstand Titel <-> Button-Panel*/
        /*gbc.weighty = 1 für Titel ganz nach oben*/
        gbc.weighty = 0;
        
        this.add(testPanel);
        this.add(pnlMenuCard, gbc);
  
        
        
	}
	
	public void setPlayerColor (int iPlayerNumber, int iChosenColor)
	{
		int iPlayerNrInArray = iPlayerNumber;
		int iChosenCol = iChosenColor;
		
		iAPlayerColors [iPlayerNrInArray] = iChosenCol;
	}
	
	public int getPlayerColor (int iPlayerNumber)
	{
		int iPlayerNrInArray = iPlayerNumber;
		return iAPlayerColors [iPlayerNrInArray];
	}
	
	public void setPlayerName (int iPlayerNumber, String sPlayerName)
	{
		int iPlayerNrInArray = iPlayerNumber;
		String sName = sPlayerName;

		sAPlayerNames[iPlayerNrInArray] = sName;
	}
	
	public String getPlayerName (int iPlayerNumber)
	{
		int iPlayerNrInArray = iPlayerNumber;
		return sAPlayerNames [iPlayerNrInArray];
	}
	
	private class ActionPlayerCount extends AbstractAction 
	{
		public ActionPlayerCount () 
		{
			putValue(NAME, "weiter");
			putValue(SHORT_DESCRIPTION, "Bitte Spielerzahl angeben");
		}
		public void actionPerformed (ActionEvent e) 
		{
			
			if (btnRadioBtn2Players.isSelected())
			{
				iPlayerCount = 2;
				cl.next(pnlMenuCard);
				System.out.println("Ist 2 angeklickt? " + iPlayerCount);
			}
			
			if (btnRadioBtn3Players.isSelected())
			{
				iPlayerCount = 3;
				cl.next(pnlMenuCard);
				System.out.println("Ist 3 angeklickt? " + iPlayerCount);
			}
			
			if (btnRadioBtn4Players.isSelected())
			{
				iPlayerCount = 4;
				cl.next(pnlMenuCard);
				System.out.println("Ist 4 angeklickt? " + iPlayerCount);
			}
			
			if (btnRadioBtn5Players.isSelected())
			{
				iPlayerCount = 5;
				cl.next(pnlMenuCard);
				System.out.println("Ist 5 angeklickt? " + iPlayerCount);
			}
			
			if (btnRadioBtn6Players.isSelected())
			{
				iPlayerCount = 6;
				cl.next(pnlMenuCard);
				System.out.println("Ist 6 angeklickt? " + iPlayerCount);
			}
			
			else
			{
//				JOptionPane opWarnPlayerCount = new JOptionPane();
//				JDialog dialog = opWarnPlayerCount.createDialog(pnlMenuCard, "Fehlende Spielerzahlangabe");
//				dialog.show();
//				
//				
//				JDialog dial = new JDialog();
//				spielAnleitung.setTitle("Spielanleitung");
//				spielAnleitung.getContentPane().add(new JLabel ("blabla Regel 1 blablabla Regel 2"));
//				spielAnleitung.setSize(200,200);
//				spielAnleitung.setModal(true);
//				spielAnleitung.setVisible(true);
			}
			
		}
	}
}
