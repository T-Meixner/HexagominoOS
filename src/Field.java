import java.awt.Point;
import java.util.ArrayList;

public class Field 
{
	public int iFieldIndex;
	public int iFieldNeighbourIndexN 	= -1;
	public int iFieldNeighbourIndexNE 	= -1;
	public int iFieldNeighbourIndexSE 	= -1;
	public int iFieldNeighbourIndexS 	= -1;
	public int iFieldNeighbourIndexSW 	= -1;
	public int iFieldNeighbourIndexNW 	= -1;
	
	public Point pFieldPointLocation;
	
	public int [] iArFieldGridLocation;
	
//	public ArrayList <Point> alPointLocations = new ArrayList <Point> ();
//	
//	public ArrayList <ArrayList <Integer> > alGridLocations = new ArrayList <> ();
	
	public Point [] pArFieldPointLocations = new Point [19];
		
	public int [][] iArGridLocations = new int [19][2];
	
	private int [] iArFieldColorCode = new int [6];
	
	private Boolean bInUse = false;
	
	public Field (int index)
	{
		this.iFieldIndex = index;
		
		preparePointLocationList ();
		recalibrateFieldLocationList ();
		setFieldPointLocation ();
		
		prepareGridLocationList ();
		setFieldGridLocation ();
		
		setNeighbourIndices ();
	}
	
	public Boolean checkColorMatch (int indexNeighbour, int [] colorCode)
	{
		System.out.println("Ich bin Feld mit Index " + iFieldIndex + " und habe den Nachbarfeldindex " + indexNeighbour + " bekommen");
		System.out.println("Ich bin Feld mit Index " + iFieldIndex + " und mein Nachbar im Norden hat den Index " + iFieldNeighbourIndexN);
		System.out.println("Ich bin Feld mit Index " + iFieldIndex + " und mein Nachbar im NordOsten hat den Index " + iFieldNeighbourIndexNE);
		System.out.println("Ich bin Feld mit Index " + iFieldIndex + " und mein Nachbar im SüdOsten hat den Index " + iFieldNeighbourIndexSE);
		System.out.println("Ich bin Feld mit Index " + iFieldIndex + " und mein Nachbar im Süden hat den Index " + iFieldNeighbourIndexS);
		System.out.println("Ich bin Feld mit Index " + iFieldIndex + " und mein Nachbar im SüdWesten hat den Index " + iFieldNeighbourIndexSW);
		System.out.println("Ich bin Feld mit Index " + iFieldIndex + " und mein Nachbar im NordWesten hat den Index " + iFieldNeighbourIndexNW);
		
		Boolean match = false;
		
		if (bInUse == true)
		{
			if (indexNeighbour == iFieldNeighbourIndexN)
			{
				if (colorCode [2] == iArFieldColorCode [5])
				{
					match = true;
				}
			}
			
			if (indexNeighbour == iFieldNeighbourIndexNE)
			{
				if (colorCode [3] == iArFieldColorCode [0])
				{
					match = true;
				}
			}
			
			if (indexNeighbour == iFieldNeighbourIndexSE)
			{
				if (colorCode [4] == iArFieldColorCode [1])
				{
					match = true;
				}
			}
			
			if (indexNeighbour == iFieldNeighbourIndexS)
			{
				if (colorCode [5] == iArFieldColorCode [2])
				{
					match = true;
				}
			}
			
			if (indexNeighbour == iFieldNeighbourIndexSW)
			{
				if (colorCode [0] == iArFieldColorCode [3])
				{
					match = true;
				}
			}
			
			if (indexNeighbour == iFieldNeighbourIndexNW)
			{
				if (colorCode [1] == iArFieldColorCode [4])
				{
					match = true;
				}
			}
				 
		}
		
		return match;
	}
	
	public Boolean getFieldInUse ()
	{
		return bInUse;
	}
	
	public void setFieldInUse (Boolean status)
	{
		bInUse = status;
	}
	
	public int [] getFieldColorCode ()
	{
		return iArFieldColorCode;
	}
	
	public void setFieldColorCode (int [] colorCode)
	{
		iArFieldColorCode = colorCode;
	}
	
	public int getFieldNeighbourIndexN ()
	{
		return iFieldNeighbourIndexN;
	}
	
	public int getFieldNeighbourIndexNE ()
	{
		return iFieldNeighbourIndexNE;
	}
	
	public int getFieldNeighbourIndexSE ()
	{
		return iFieldNeighbourIndexSE;
	}
	
	public int getFieldNeighbourIndexS ()
	{
		return iFieldNeighbourIndexS;
	}
	
	public int getFieldNeighbourIndexSW ()
	{
		return iFieldNeighbourIndexSW;
	}
	
	public int getFieldNeighbourIndexNW ()
	{
		return iFieldNeighbourIndexNW;
	}
	
	public Point getFieldPointLocation ()
	{
		return pFieldPointLocation;
	}
	
	private void setFieldPointLocation ()
	{
		pFieldPointLocation = pArFieldPointLocations [iFieldIndex];
	}
	
	private void setFieldGridLocation ()
	{
		iArFieldGridLocation = iArGridLocations [iFieldIndex];
	}
	
	private void setNeighbourIndices ()
	{

		int iFieldX = iArGridLocations [iFieldIndex] [0];
		int iFieldY = iArGridLocations [iFieldIndex] [1];
		
		int iFieldNeighbourNX = iFieldX + 0;
		int iFieldNeighbourNY = iFieldY + 1;
		
		int iFieldNeighbourNEX = iFieldX + 1;
		int iFieldNeighbourNEY = iFieldY + 1;
		
		int iFieldNeighbourSEX = iFieldX + 1;
		int iFieldNeighbourSEY = iFieldY + 0;
		
		int iFieldNeighbourSX = iFieldX + 0;
		int iFieldNeighbourSY = iFieldY - 1;
		
		int iFieldNeighbourSWX = iFieldX - 1;
		int iFieldNeighbourSWY = iFieldY - 1;
		
		int iFieldNeighbourNWX = iFieldX - 1;
		int iFieldNeighbourNWY = iFieldY + 0;
		
		for (int index = 0; index < iArGridLocations.length; index++)
		{
			if (iArGridLocations [index][0] == iFieldNeighbourNX && iArGridLocations [index][1] == iFieldNeighbourNY)
			{
				iFieldNeighbourIndexN = index;
			}
			
			if (iArGridLocations [index][0] == iFieldNeighbourNEX && iArGridLocations [index][1] == iFieldNeighbourNEY)
			{
				iFieldNeighbourIndexNE = index;
			}
			
			if (iArGridLocations [index][0] == iFieldNeighbourSEX && iArGridLocations [index][1] == iFieldNeighbourSEY)
			{
				iFieldNeighbourIndexSE = index;
			}
			
			if (iArGridLocations [index][0] == iFieldNeighbourSX && iArGridLocations [index][1] == iFieldNeighbourSY)
			{
				iFieldNeighbourIndexS = index;
			}
			
			if (iArGridLocations [index][0] == iFieldNeighbourSWX && iArGridLocations [index][1] == iFieldNeighbourSWY)
			{
				iFieldNeighbourIndexSW = index;
			}
			
			if (iArGridLocations [index][0] == iFieldNeighbourNWX && iArGridLocations [index][1] == iFieldNeighbourNWY)
			{
				iFieldNeighbourIndexNW = index;
			}
		}

	}
	
	private void recalibrateFieldLocationList ()
	{
		for (int i = 0; i < pArFieldPointLocations.length; i++)
		{
			int x = (int) ( pArFieldPointLocations [i].getX() * (2f / 3f) );
			int y = (int) ( pArFieldPointLocations [i].getY() * (2f / 3f) );
			pArFieldPointLocations [i].setLocation (x, y);
		}
	}
	
	private void preparePointLocationList ()
	{
		pArFieldPointLocations [0] = new Point (363, 390);
		pArFieldPointLocations [1] = new Point (415, 300);
		pArFieldPointLocations [2] = new Point (467, 390);
		pArFieldPointLocations [3] = new Point (415, 480);
		pArFieldPointLocations [4] = new Point (311, 480);
		pArFieldPointLocations [5] = new Point (259, 390);
		pArFieldPointLocations [6] = new Point (311, 300);
		pArFieldPointLocations [7] = new Point (363, 210);
		pArFieldPointLocations [8] = new Point (467, 210);
		pArFieldPointLocations [9] = new Point (519, 300);
		pArFieldPointLocations [10] = new Point (571, 390);
		pArFieldPointLocations [11] = new Point (519, 480);
		pArFieldPointLocations [12] = new Point (467, 570);
		pArFieldPointLocations [13] = new Point (363, 570);
		pArFieldPointLocations [14] = new Point (259, 570);
		pArFieldPointLocations [15] = new Point (207, 480);
		pArFieldPointLocations [16] = new Point (155, 390);
		pArFieldPointLocations [17] = new Point (207, 300);
		pArFieldPointLocations [18] = new Point (259, 210);
	}
	
	private void prepareGridLocationList ()
	{
		iArGridLocations [0] = new int [] 	{0, 0};
		iArGridLocations [1] = new int [] 	{1, 1};
		iArGridLocations [2] = new int [] 	{1, 0};
		iArGridLocations [3] = new int [] 	{0, -1};
		iArGridLocations [4] = new int [] 	{-1, -1};
		iArGridLocations [5] = new int [] 	{-1, 0};
		iArGridLocations [6] = new int [] 	{0, 1};
		iArGridLocations [7] = new int [] 	{1, 2};
		iArGridLocations [8] = new int [] 	{2, 2};
		iArGridLocations [9] = new int [] 	{2, 1};
		iArGridLocations [10] = new int [] 	{2, 0};
		iArGridLocations [11] = new int [] 	{1, -1};
		iArGridLocations [12] = new int [] 	{0, -2};
		iArGridLocations [13] = new int [] 	{-1, -2};
		iArGridLocations [14] = new int [] 	{-2, -2};
		iArGridLocations [15] = new int [] 	{-2, -1};
		iArGridLocations [16] = new int [] 	{-2, 0};
		iArGridLocations [17] = new int [] 	{-1, 1};
		iArGridLocations [18] = new int [] 	{0, 2};
	}
}
