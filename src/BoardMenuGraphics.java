
import java.awt.image.WritableRaster;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BoardMenuGraphics extends JPanel

{
	public Button btnBoardMenuNext;
	public Button btnBoardMenuNewHxg;
	public Button btnBoardMenuManual;
	public Button btnBoardMenuQuit;
	
	public JPanel pnlBoardMenuCardPanel;
	
	public CardLayout clBoardMenuCardLayout;
	
	private int iPlayerCount;
	
	private JLabel lblImageHexagon;
	
	private JLabel [] lblBoardMenuPlayerNr = new JLabel [6];
	private JLabel [] lblBoardMenuPlayerImg = new JLabel [6];
	private JLabel [] lblBoardMenuPlayerName = new JLabel [6];
	
	private JPanel pnlImageHexagon;
	private JPanel pnlControls;
	
	private JPanel [] pnlArBoardMenuPlayerPages = new JPanel [6];

//	private static ImageIcon [] iiArPlayerIcons;
	
	private ImageIcon imgHexagonSmall;
	
	private String [] sArPlayerNames = {"", "", "", "", "", ""};
	
	private static Color [] colArPlayerColors = new Color [6];
	
	private GridBagLayout gblBoardMenu;
	private GridBagLayout gblBoardMenuPlayerPages;
	
	private GridBagConstraints gbcBoardMenu;
	private GridBagConstraints gbcBoardMenuPlayerPages;
	
	public BoardMenuGraphics (int iPlayerCount, Color [] colArPlayerColors, String [] sArPlayerNames)
	{
		this.iPlayerCount = iPlayerCount;
		this.colArPlayerColors = colArPlayerColors;
		this.sArPlayerNames = sArPlayerNames;
		
		gblBoardMenu = new GridBagLayout ();
		gbcBoardMenu = new GridBagConstraints ();
		
		this.setBackground (Color.decode ("#8D6E63") );
		
		this.setLayout (gblBoardMenu);
		
		gbcBoardMenu.gridwidth = GridBagConstraints.REMAINDER;
		gbcBoardMenu.anchor = GridBagConstraints.PAGE_START;
		gbcBoardMenu.fill = GridBagConstraints.HORIZONTAL;
		gbcBoardMenu.insets = new Insets (5, 5, 5, 5);
		gbcBoardMenu.ipadx = 20;
		gbcBoardMenu.ipady = 20;
		gbcBoardMenu.weightx = 1;
		gbcBoardMenu.weighty = 0;
		
		createPlayerPanels ();
		this.add (pnlBoardMenuCardPanel, gbcBoardMenu);
				
		prepareBtnNextPly ();
//		preparePnlImgHxg ();
		prepareBtnDrawHxg ();
		
		pnlControls = new JPanel (gblBoardMenu);
		pnlControls.setBackground (Color.decode ("#8D6E63") );
		
		gbcBoardMenu.anchor = GridBagConstraints.CENTER;
		gbcBoardMenu.insets = new Insets (5, 0, 5, 0);
		gbcBoardMenu.weighty = 0;
		
		pnlControls.add (btnBoardMenuNext, gbcBoardMenu);
//		gbcBoardMenu.ipadx = 0;
//		gbcBoardMenu.ipady = 0;
//		pnlControls.add (pnlImageHexagon, gbcBoardMenu);
		gbcBoardMenu.ipadx = 20;
		gbcBoardMenu.ipady = 20;
		pnlControls.add (btnBoardMenuNewHxg, gbcBoardMenu);
		
		gbcBoardMenu.insets = new Insets (5, 5, 5, 5);
		gbcBoardMenu.weighty = 1;
		
		this.add (pnlControls, gbcBoardMenu);
		
		gbcBoardMenu.anchor = GridBagConstraints.PAGE_END;
		gbcBoardMenu.weighty = 0;
		
		prepareBtnManual ();
		this.add (btnBoardMenuManual, gbcBoardMenu);
		
		prepareBtnQuit ();
		this.add (btnBoardMenuQuit, gbcBoardMenu);
		
	}
	
	private void createPlayerPanels ()
	{
		/* Create cardpanel for player panels */
		
		pnlBoardMenuCardPanel = new JPanel ();
		clBoardMenuCardLayout = new CardLayout ();
		
		pnlBoardMenuCardPanel.setLayout (clBoardMenuCardLayout);
		
		/* Create layout for player panels */
		
		gblBoardMenuPlayerPages = new GridBagLayout ();
		gbcBoardMenuPlayerPages = new GridBagConstraints ();
		
		gbcBoardMenuPlayerPages.gridwidth = GridBagConstraints.REMAINDER;
		gbcBoardMenuPlayerPages.anchor = GridBagConstraints.CENTER;
		gbcBoardMenuPlayerPages.fill = GridBagConstraints.HORIZONTAL;
		gbcBoardMenuPlayerPages.insets = new Insets (5, 5, 5, 5);
		
		/* Create a player panel for each player*/
		
		for (int iPlayerNumber = 0; iPlayerNumber < iPlayerCount; iPlayerNumber++)
		{
			pnlArBoardMenuPlayerPages [iPlayerNumber] = new JPanel (gblBoardMenuPlayerPages);
			
			pnlArBoardMenuPlayerPages [iPlayerNumber].setBackground (Color.decode ("#D7CCC8") );
			
			lblBoardMenuPlayerNr [iPlayerNumber] = new JLabel ("SPIELER " + (iPlayerNumber + 1));
			lblBoardMenuPlayerImg [iPlayerNumber] = createPlayerImage (iPlayerNumber);
			lblBoardMenuPlayerName [iPlayerNumber] = new JLabel (sArPlayerNames [iPlayerNumber]);
			
			/* Format the player page's labels */
			lblBoardMenuPlayerNr [iPlayerNumber].setHorizontalAlignment (SwingConstants.CENTER);
			lblBoardMenuPlayerImg [iPlayerNumber].setHorizontalAlignment (SwingConstants.CENTER);
			lblBoardMenuPlayerName [iPlayerNumber].setHorizontalAlignment (SwingConstants.CENTER);
			
			lblBoardMenuPlayerNr [iPlayerNumber].setFont (new Font ("Fixedsys", Font.BOLD, 16));
			lblBoardMenuPlayerName [iPlayerNumber].setFont (new Font ("Fixedsys", Font.BOLD, 16));
			
			/* Add the player page's components to the player's page resp. panel */
			pnlArBoardMenuPlayerPages [iPlayerNumber].add (lblBoardMenuPlayerNr [iPlayerNumber], gbcBoardMenuPlayerPages);
			pnlArBoardMenuPlayerPages [iPlayerNumber].add (lblBoardMenuPlayerImg [iPlayerNumber], gbcBoardMenuPlayerPages);
			pnlArBoardMenuPlayerPages [iPlayerNumber].add (lblBoardMenuPlayerName [iPlayerNumber], gbcBoardMenuPlayerPages);
			
			/* Add the player's page resp. panel to the cardpanel */
			pnlBoardMenuCardPanel.add (pnlArBoardMenuPlayerPages [iPlayerNumber]);
		}
	}
	
	private static JLabel createPlayerImage (int iPlyNo)
	{
		BufferedImage biPlayerImage;
		ImageIcon iiPlayerIcon;
		JLabel lblPlayerIcon;
				
		try 
		{
			biPlayerImage = tintPlayerImage (ImageIO.read (new File ("playerImage.png")), iPlyNo);
			
			iiPlayerIcon = new ImageIcon (biPlayerImage);
		    
		    lblPlayerIcon = new JLabel ();
			
			lblPlayerIcon.setIcon (iiPlayerIcon);
			
			return lblPlayerIcon;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
			return null;
		}
	}
	
	private static BufferedImage tintPlayerImage (BufferedImage biPlayerImage, int iPlyNo)
	{
		int biPlayerImageWidth = biPlayerImage.getWidth ();
	    int biPlayerImageHeight = biPlayerImage.getHeight ();
	    WritableRaster wrbiPlayerImageRaster = biPlayerImage.getRaster ();
	    
	    for (int x = 0; x < biPlayerImageWidth; x++) 
	    {
	      for (int y = 0; y < biPlayerImageHeight; y++) 
	      {
	        /* Create an array with RGB Values of the currently checked pixel */
	    	int [] iArPixelValuesRGB = wrbiPlayerImageRaster.getPixel (x, y, (int[]) null);
	        
	        /* Change pixel color black to pixel color from color-array at player's position */
	        if (iArPixelValuesRGB [0] == 0 && iArPixelValuesRGB [1] == 0 && iArPixelValuesRGB [2] == 0)
	        {
	        	iArPixelValuesRGB [0] = colArPlayerColors [iPlyNo].getRed();
	        	iArPixelValuesRGB [1] = colArPlayerColors [iPlyNo].getGreen();
	        	iArPixelValuesRGB [2] = colArPlayerColors [iPlyNo].getBlue();
	        	wrbiPlayerImageRaster.setPixel (x, y, iArPixelValuesRGB);
	        }
	        /* Set all non-black colored pixels to transparent */
	        else
	        {
	        	iArPixelValuesRGB [3] = 0;
	        	wrbiPlayerImageRaster.setPixel (x, y, iArPixelValuesRGB);
	        }
	      }
	    }
	    return biPlayerImage;
	}
	
	
	private void prepareBtnNextPly ()
	{
		btnBoardMenuNext = new Button ("weiter");
	}
	
	private void preparePnlImgHxg ()
	{
		pnlImageHexagon = new JPanel ();
		lblImageHexagon = new JLabel ();
		imgHexagonSmall = new ImageIcon ("hxgImage.png");
		
		lblImageHexagon.setIcon (imgHexagonSmall);
		pnlImageHexagon.add (lblImageHexagon);
		pnlImageHexagon.setBackground (Color.decode ("#D7CCC8") );
	}
	
	private void prepareBtnDrawHxg ()
	{
		btnBoardMenuNewHxg = new Button ("ziehen");
	}
	
	private void prepareBtnManual ()
	{
		btnBoardMenuManual = new Button ("Anleitung");
	}
	
	private void prepareBtnQuit  ()
	{
		btnBoardMenuQuit = new Button ("Beenden");
	}
}
