import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Board extends JPanel
{
	public static BoardMenuGraphics boardMenuGraphics;
	
	public static JLayeredPane pnlBoardGameField;
	
	private JPanel pnlBoardImage;
	
	private ImageIcon iiBoardImage;
	
	private JLabel lblBoardImage;
		
	private GridBagLayout gblBoard;
	
	private GridBagConstraints gbcBoard;
	
	private int iBoardImageWidth;
	private int iBoardImageHeight;
	
	public Board (int iPlayerCount, Color [] colArPlyColorsChosen, String [] sArPlyNamesChosen)
	{			
//		this.iPlayerCount = iPlayerCount;
//		this.colArPlyColors = colArPlyColorsChosen;
//		this.sArPlyNames = sArPlyNamesChosen;
		
		gblBoard = new GridBagLayout ();
		gbcBoard = new GridBagConstraints ();
		
		this.setLayout (gblBoard);
		this.setBackground ( Color.decode ("#6D4C41") );
		/* Define GridbagLayout for the playing field's colored background */		
		gbcBoard.anchor = GridBagConstraints.CENTER;
		gbcBoard.fill = GridBagConstraints.BOTH;
		gbcBoard.weighty = 1;
		gbcBoard.weightx = 1;
		
		/* Test für Menü größer */
//		gblBoard.columnWidths = new int [] {624};
//		gblBoard.columnWeights = new double [] {0, 1};
		
		/* Set up the LayeredPane for the board */
		pnlBoardGameField = new JLayeredPane ();
		
	    /* Set up the board's playing field  */
		iiBoardImage = createBoardImage (iPlayerCount, colArPlyColorsChosen);
		
		lblBoardImage = new JLabel ();
		lblBoardImage.setIcon (iiBoardImage);
		lblBoardImage.setBounds (0, 0, iBoardImageWidth, iBoardImageHeight);
		
		pnlBoardImage = new JPanel (null);
		pnlBoardImage.add (lblBoardImage);
		
		pnlBoardImage.setBounds (0, 0, iBoardImageWidth, iBoardImageHeight);
		pnlBoardImage.setOpaque (true);
		pnlBoardImage.setBackground ( Color.decode ("#6D4C41") );
		
		pnlBoardGameField.add (pnlBoardImage, JLayeredPane.DEFAULT_LAYER);
		pnlBoardGameField.moveToBack(pnlBoardImage);
		
		this.add(pnlBoardGameField, gbcBoard);
	    
	    /* Define GridbagLayout for the board's menu */
	    gbcBoard.anchor = GridBagConstraints.EAST;
	    gbcBoard.weighty = 1;
		gbcBoard.weightx = 0;
		
//		gbcBoard.insets = new Insets (5, 5, 5, 5);
		gbcBoard.ipadx = 10;
						
		boardMenuGraphics = new BoardMenuGraphics (iPlayerCount, colArPlyColorsChosen, sArPlyNamesChosen);
		
		BoardMenuActions boardMenuActions = new BoardMenuActions ();
		
		this.add (boardMenuGraphics, gbcBoard);
	}
	

	private ImageIcon createBoardImage (int iPlayerCount, Color [] colArPlyColors)
	{
		BufferedImage biBoardImage;
		ImageIcon iiBoardIcon;
		JLabel lblBoardIcon;
		JPanel pnlBoardIcon;
		String sBoardImage;
		int iBoardReSizeX;
		int iBoardReSizeY;
		
		try 
		{
			sBoardImage = "boardImagePlayerCount" + iPlayerCount + ".png";
			
			biBoardImage = tintBoardImage (ImageIO.read (new File (sBoardImage)), colArPlyColors);
			
			iBoardReSizeX = (int) ( biBoardImage.getWidth () * (2f / 3f) );
			iBoardReSizeY = (int) ( biBoardImage.getHeight () * (2f / 3f) );
			
			iBoardImageWidth = iBoardReSizeX;
			iBoardImageHeight = iBoardReSizeY;
			
			Image imgBoardImageResized = biBoardImage.getScaledInstance (iBoardReSizeX, iBoardReSizeY, Image.SCALE_SMOOTH);
			
			iiBoardIcon = new ImageIcon (imgBoardImageResized);
			
//			iiBoardIcon = new ImageIcon (biBoardImage);
			
			return iiBoardIcon;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			
			return null;
		}
	}
	
	private static BufferedImage tintBoardImage (BufferedImage biBoardImage, Color [] colArPlyColors)
	{
		int biBoardImageWidth = biBoardImage.getWidth ();
	    int biBoardImageHeight = biBoardImage.getHeight ();
	    WritableRaster wrbiBoardImageRaster = biBoardImage.getRaster ();
	    
	    for (int x = 0; x < biBoardImageWidth; x++) 
	    {
	      for (int y = 0; y < biBoardImageHeight; y++) 
	      {
	        /* Create an array with RGB Values of the currently checked pixel */
	    	int [] iArPixelValuesRGB = wrbiBoardImageRaster.getPixel (x, y, (int[]) null);
	        
	    	/* Set all white colored pixels to transparent */
	    	if (iArPixelValuesRGB [0] == 255 && iArPixelValuesRGB [1] == 255 && iArPixelValuesRGB [2] == 255)
	        {
	    		iArPixelValuesRGB [3] = 0;
	        	wrbiBoardImageRaster.setPixel (x, y, iArPixelValuesRGB);
	        }
	    	
	    	/* Set all red colored pixels to the chosen color of 1st player (index 0) */
	    	if (iArPixelValuesRGB [0] == 255 && iArPixelValuesRGB [1] == 0 && iArPixelValuesRGB [2] == 0)
	    	{
	    		iArPixelValuesRGB [0] = colArPlyColors [0].getRed();
	        	iArPixelValuesRGB [1] = colArPlyColors [0].getGreen();
	        	iArPixelValuesRGB [2] = colArPlyColors [0].getBlue();
	        	wrbiBoardImageRaster.setPixel (x, y, iArPixelValuesRGB);
	    	}
	    	
	    	/* Set all green colored pixels to the chosen color of 2nd player (index 1) */
	    	if (iArPixelValuesRGB [0] == 0 && iArPixelValuesRGB [1] == 255 && iArPixelValuesRGB [2] == 0)
	    	{
	    		iArPixelValuesRGB [0] = colArPlyColors [1].getRed();
	        	iArPixelValuesRGB [1] = colArPlyColors [1].getGreen();
	        	iArPixelValuesRGB [2] = colArPlyColors [1].getBlue();
	        	wrbiBoardImageRaster.setPixel (x, y, iArPixelValuesRGB);
	    	}
	    	
	    	/* Set all blue colored pixels to the chosen color of 3rd player (index 2) */
	    	if (iArPixelValuesRGB [0] == 0 && iArPixelValuesRGB [1] == 0 && iArPixelValuesRGB [2] == 255)
	    	{
	    		iArPixelValuesRGB [0] = colArPlyColors [2].getRed();
	        	iArPixelValuesRGB [1] = colArPlyColors [2].getGreen();
	        	iArPixelValuesRGB [2] = colArPlyColors [2].getBlue();
	        	wrbiBoardImageRaster.setPixel (x, y, iArPixelValuesRGB);
	    	}
	    	
	    	/* Set all magenta colored pixels to the chosen color of 4th player (index 3) */
	    	if (iArPixelValuesRGB [0] == 255 && iArPixelValuesRGB [1] == 0 && iArPixelValuesRGB [2] == 255)
	    	{
	    		iArPixelValuesRGB [0] = colArPlyColors [3].getRed();
	        	iArPixelValuesRGB [1] = colArPlyColors [3].getGreen();
	        	iArPixelValuesRGB [2] = colArPlyColors [3].getBlue();
	        	wrbiBoardImageRaster.setPixel (x, y, iArPixelValuesRGB);
	    	}
	    	
	    	/* Set all cyan colored pixels to the chosen color of 5th player (index 4) */
	    	if (iArPixelValuesRGB [0] == 0 && iArPixelValuesRGB [1] == 255 && iArPixelValuesRGB [2] == 255)
	    	{
	    		iArPixelValuesRGB [0] = colArPlyColors [4].getRed();
	        	iArPixelValuesRGB [1] = colArPlyColors [4].getGreen();
	        	iArPixelValuesRGB [2] = colArPlyColors [4].getBlue();
	        	wrbiBoardImageRaster.setPixel (x, y, iArPixelValuesRGB);
	    	}
	    	
	    	/* Set all yellow colored pixels to the chosen color of 6th player (index 5) */
	    	if (iArPixelValuesRGB [0] == 255 && iArPixelValuesRGB [1] == 255 && iArPixelValuesRGB [2] == 0)
	    	{
	    		iArPixelValuesRGB [0] = colArPlyColors [5].getRed();
	        	iArPixelValuesRGB [1] = colArPlyColors [5].getGreen();
	        	iArPixelValuesRGB [2] = colArPlyColors [5].getBlue();
	        	wrbiBoardImageRaster.setPixel (x, y, iArPixelValuesRGB);
	    	}
	      }
	    }
	    return biBoardImage;
	}
	
}
