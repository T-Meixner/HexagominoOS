import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;



public class Hexagon extends JPanel
{
	private ImageIcon hexagon;
	private JLabel lblHexagon;

	private int iOwningPlayerIndex;
	private int iOwningPlayerHexagonListIndex;
	private int iRepositoryIndex;
	
	private int iHexagonPanelWidth;
	private int iHexagonPanelHeight;
		
	private int [] iArColorCode = new int [6];
	
	private boolean clickInsideImage = false;
		
	private static Color [] cArColors = new Color []
			{
//					new Color (255, 0, 0), 		/*red*/
//					new Color (0, 255, 0),		/*green*/
//					new Color (0, 0, 255),		/*blue*/
//					new Color (255, 255, 0),	/*yellow*/
//					new Color (255, 0, 255),	/*magenta*/
//					new Color (0, 255, 255)		/*cyan*/
					
					(Color.decode ("#E57373")), 	/* red 		300 */
					(Color.decode ("#81C784")),		/* green 	300 */
					(Color.decode ("#64B5F6")),		/* blue 	300 */
					(Color.decode ("#FFF176")),		/* yellow 	300 */
					(Color.decode ("#E0E0E0")),		/* gray 	300 */
					(Color.decode ("#A1887F")),		/* brown 	300 */
			};
	
	public Hexagon (int [] iArHexCombination)
	{
		this.iArColorCode = iArHexCombination;
		
		DragListener drag = new DragListener();
		this.addMouseListener ( drag );
		this.addMouseMotionListener ( drag );
				
		/* Create the hexagon-image dependent on the colorcode */
		hexagon = drawHexagon ();
		
		lblHexagon = new JLabel ();
		lblHexagon.setIcon (hexagon);
		lblHexagon.setBounds (0, 0, iHexagonPanelWidth, iHexagonPanelHeight);
		
		/* Hexagon start position and size for LayeredPane*/
		this.setBounds (0, 0, iHexagonPanelWidth, iHexagonPanelHeight);
		
		/* Set layout manager to null for positioning of the imageicon */
		this.setLayout(null);
		
		/* Set background color to transparent */
		this.setBackground (new Color (0, 0, 0, 0));
		
		/* Set opaque for the layered pane */
		this.setOpaque(true);
				
		this.add(lblHexagon);
	}
	
	public int getHexagonsRepositoryIndex ()
	{
		return iRepositoryIndex;
	}
	
	public void setHexagonsRepositoryIndex (int index)
	{
		this.iRepositoryIndex = index;
	}
	
	public int getHexagonsOwningPlayerIndex ()
	{
		return iOwningPlayerIndex;
	}
	
	public void setHexagonsOwningPlayerIndex (int index)
	{
		this.iOwningPlayerIndex = index;
	}
	
	public int getOwningPlayerHexagonListIndex ()
	{
		return iOwningPlayerHexagonListIndex ;
	}
	
	public void setOwningPlayerHexagonListIndex (int index)
	{
		this.iOwningPlayerHexagonListIndex = index;
	}
	
	public void turnHexagon ()
	{
		int [] iArColorCodeCopy = new int [6];
		
		for (int i = 0; i < iArColorCode.length; i++)
		{
			iArColorCodeCopy [i] = iArColorCode [i];
		}
		
		iArColorCode [0] = iArColorCodeCopy [5];
		iArColorCode [1] = iArColorCodeCopy [0];
		iArColorCode [2] = iArColorCodeCopy [1];
		iArColorCode [3] = iArColorCodeCopy [2];
		iArColorCode [4] = iArColorCodeCopy [3];
		iArColorCode [5] = iArColorCodeCopy [4];
		
		hexagon = drawHexagon ();
		
		lblHexagon.setIcon (null);
		lblHexagon.setIcon (hexagon);
		lblHexagon.repaint ();
		lblHexagon.revalidate ();
	}
	
	public ImageIcon drawHexagon ()
	{
		BufferedImage [] biArHexagonSide = new BufferedImage [6];
		String sImageName;
		ImageIcon iiHexagon;
		Image imgHexagonResized;
		int iHexagonReSizeX;
		int iHexagonReSizeY;
		
		for (int iHexSide = 0; iHexSide < biArHexagonSide.length; iHexSide++)
		{
			try 
			{
				sImageName = "hxgImageSide" + iHexSide + ".png";
				
				biArHexagonSide [iHexSide] = tintHexagonSide (ImageIO.read (new File (sImageName)), iArColorCode [iHexSide]);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		BufferedImage biHexagonSidesCombined = new BufferedImage 
				(
				biArHexagonSide [0].getWidth(), 
				biArHexagonSide [0].getHeight(), 
				BufferedImage.TYPE_INT_ARGB
				);
		Graphics2D graphics = biHexagonSidesCombined.createGraphics();
		
		for (int iHexSide = 0; iHexSide < biArHexagonSide.length; iHexSide++)
		{
			graphics.drawImage (biArHexagonSide [iHexSide], 0, 0, null);
		}
		
		graphics.dispose();
		
		iHexagonReSizeX = (int) ( biHexagonSidesCombined.getWidth () * (2f / 3f) );
		iHexagonReSizeY = (int) ( biHexagonSidesCombined.getHeight () * (2f / 3f) );
		
		iHexagonPanelWidth = iHexagonReSizeX;
		iHexagonPanelHeight = iHexagonReSizeY;
		
		imgHexagonResized =  biHexagonSidesCombined.getScaledInstance (iHexagonReSizeX, iHexagonReSizeY, Image.SCALE_FAST);
				
		iiHexagon = new ImageIcon (imgHexagonResized);
		
		return iiHexagon;
	}
	
	private static BufferedImage tintHexagonSide (BufferedImage biHexagonSide, int iColorArrayIndex)
	{
		int biHexagonSideWidth = biHexagonSide.getWidth ();
	    int biHexagonSideHeight = biHexagonSide.getHeight ();
	    WritableRaster wrbiHexagonSideRaster = biHexagonSide.getRaster ();
	    
	    for (int x = 0; x < biHexagonSideWidth; x++) 
	    {
	      for (int y = 0; y < biHexagonSideHeight; y++) 
	      {
	        /* Create an array with RGB Values of the currently checked pixel */
	    	int [] iArPixelValuesRGB = wrbiHexagonSideRaster.getPixel (x, y, (int[]) null);
	        
	        /* Change black colored pixels to color at index of colors */
	        if (iArPixelValuesRGB [0] == 0 && iArPixelValuesRGB [1] == 0 && iArPixelValuesRGB [2] == 0)
	        {
	        	iArPixelValuesRGB [0] = cArColors [iColorArrayIndex].getRed();
	        	iArPixelValuesRGB [1] = cArColors [iColorArrayIndex].getGreen();
	        	iArPixelValuesRGB [2] = cArColors [iColorArrayIndex].getBlue();
	        	wrbiHexagonSideRaster.setPixel (x, y, iArPixelValuesRGB);
	        }
	        /* Change white colored pixels to transparent */
	        if (iArPixelValuesRGB [0] == 255 && iArPixelValuesRGB [1] == 255 && iArPixelValuesRGB [2] == 255)
	        {
	        	iArPixelValuesRGB [3] = 0;
	        	wrbiHexagonSideRaster.setPixel (x, y, iArPixelValuesRGB);
	        }
	      }
	    }
	    return biHexagonSide;
	}
	
	private class DragListener extends MouseInputAdapter
	{
	    Point location;
	    MouseEvent pressed;
	    Boolean bIsLeftClicked = false;
	    
	    long lSystemMilSec = 0;
	 
	    public void mousePressed (MouseEvent me)
	    {
	    	if (Game.iCurrentPlayer != iOwningPlayerIndex)
			{
				return;
			}
	    	
	    	Game.mainFrame.repaint();
        	Game.mainFrame.revalidate();
	    	
	        pressed = me;
	        bIsLeftClicked = true;
	        
	        Game.setHexagonToForeground (me.getComponent());
	        
	        if (SwingUtilities.isRightMouseButton (me) == true && bIsLeftClicked == true)
			{
	        	turnHexagon ();
	        	Game.mainFrame.repaint();
	        	Game.mainFrame.revalidate();
			}
	    }
	 
	    public void mouseDragged (MouseEvent me)
	    {
	    	if (Game.iCurrentPlayer != iOwningPlayerIndex)
			{
				return;
			}

	    	
	    	if ( System.currentTimeMillis () >= (lSystemMilSec + (1000/30)) ) /* 1000 = 1 second, 30 fps */
		    {
	    		lSystemMilSec = System.currentTimeMillis ();
	    		
	    		Component component = me.getComponent ();
	    		location = component.getLocation (location);
	    		int x = location.x - pressed.getX() + me.getX();
	    		int y = location.y - pressed.getY() + me.getY();
	    		component.setLocation (x, y);
	    		component.repaint();
	    		component.revalidate();
	    		
	    		Game.mainFrame.repaint();
	        	Game.mainFrame.revalidate();
		    }
	     }
	    
	    public void mouseReleased (MouseEvent me)
	    {
	    	if (Game.iCurrentPlayer != iOwningPlayerIndex)
			{
				return;
			}
	    	
	    	if (SwingUtilities.isLeftMouseButton(me))
			{
	    		bIsLeftClicked = false;
	    		Component component = me.getComponent();
	    		Point pHexagonCorner = component.getLocation();
	    		int iHexagonCornerX = (int) pHexagonCorner.getX();
	    		int iHexagonCornerY = (int) pHexagonCorner.getY();
	    		
	    		Game.checkHexagonPlacing (iHexagonCornerX, iHexagonCornerY, iArColorCode, component);
	    		
	    		Game.setHexagonToBackground (me.getComponent());
	    		
	    		System.out.println("Führe Funktion der Farbprüfung aus!");
			}
	    }
	}
}
