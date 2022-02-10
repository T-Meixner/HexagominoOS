import java.awt.Point;

public class Repository 
{
	public Point [] pArRepositoryPointLocations = new Point [38];
	
	private Point pRepositoryPointLocation;
	
	private int iIndex = -1;
	
	private Boolean bInUse = false;
	
	public Repository (int index)
	{
		this.iIndex = index;
		
		prepareRepositoryPointLocations ();
		recalibrateRepositoryLocationList ();
		setRepositoryLocation (pArRepositoryPointLocations [iIndex]);
	}
	
	public Boolean getRepositoryInUse ()
	{
		return bInUse;
	}
	
	public void setRepositoryInUse (Boolean status)
	{
		bInUse = status;
	}
	
	public Point getRepositoryLocation ()
	{
		return pRepositoryPointLocation;
	}
	
	public void setRepositoryLocation (Point location)
	{
		pRepositoryPointLocation = location;
	}
	
	public void recalibrateRepositoryLocationList ()
	{
		for (int i = 0; i < pArRepositoryPointLocations.length; i++)
		{
			int x = (int) ( pArRepositoryPointLocations [i].getX() * (2f / 3f) );
			int y = (int) ( pArRepositoryPointLocations [i].getY() * (2f / 3f) );
			pArRepositoryPointLocations [i].setLocation (x, y);
		}
	}
	
	public void prepareRepositoryPointLocations ()
	{
		/* 2 players: 	player 0 -> index 0-14, player 1 -> index 15-29 							*/
		/*				indices not in use: 30-36 													*/
		/* 3 players: 	player 0 -> index 0-9, player 1 -> index 10-19, player 2 -> index 20-29 	*/
		/*				indices not in use: 30-36 													*/
		/* 4 players: 	player 0 -> index 0-3, 30, 31, player 1 -> index 11-14, 32, 33,  			*/
		/* 				player 2 -> index 15-18, 34, 35, player 3 -> index 26-29, 35, 37 			*/
		/*				indices not in use: 4-10, 19-24 											*/
		/* 5 players: 	player 0 -> index 0-4, player 1 -> index 5-9, player 2 -> index 10-14, 		*/
		/*				player 3 -> index 15-19, player 4 -> index 25-29 (index 20-24 not used) 	*/
		/*				indices not in use: 30-36 													*/
		/* 6 players:	player 0 -> index 0-4, player 1 -> index 5-9, player 2 -> index 10-14 		*/
		/*				player 3 -> index 15-19, player 4 -> index 20-24, player 5 -> index 25-29 	*/
		/*				indices not in use: 30-36 													*/
						
		pArRepositoryPointLocations [0] = new Point (52, 330);
		pArRepositoryPointLocations [1] = new Point (0, 240);
		pArRepositoryPointLocations [2] = new Point (104, 240);
		pArRepositoryPointLocations [3] = new Point (52, 150);
		pArRepositoryPointLocations [4] = new Point (156, 150);
		
		pArRepositoryPointLocations [5] = new Point (259, 90);
		pArRepositoryPointLocations [6] = new Point (311, 0);
		pArRepositoryPointLocations [7] = new Point (363, 90);
		pArRepositoryPointLocations [8] = new Point (415, 0);
		pArRepositoryPointLocations [9] = new Point (467, 90);
		
		pArRepositoryPointLocations [10] = new Point (570, 150);
		pArRepositoryPointLocations [11] = new Point (674, 150);
		pArRepositoryPointLocations [12] = new Point (622, 240);
		pArRepositoryPointLocations [13] = new Point (726, 240);
		pArRepositoryPointLocations [14] = new Point (674, 330);
		
		pArRepositoryPointLocations [15] = new Point (674, 450);
		pArRepositoryPointLocations [16] = new Point (726, 540);
		pArRepositoryPointLocations [17] = new Point (622, 540);
		pArRepositoryPointLocations [18] = new Point (674, 630);
		pArRepositoryPointLocations [19] = new Point (570, 630);
		
		pArRepositoryPointLocations [20] = new Point (467, 690);
		pArRepositoryPointLocations [21] = new Point (415, 780);
		pArRepositoryPointLocations [22] = new Point (363, 690);
		pArRepositoryPointLocations [23] = new Point (311, 780);
		pArRepositoryPointLocations [24] = new Point (259, 690);
		
		pArRepositoryPointLocations [25] = new Point (156, 630);
		pArRepositoryPointLocations [26] = new Point (52, 630);
		pArRepositoryPointLocations [27] = new Point (104, 540);
		pArRepositoryPointLocations [28] = new Point (0, 540);
		pArRepositoryPointLocations [29] = new Point (52, 450);
		
		pArRepositoryPointLocations [30] = new Point (0, 60);
		pArRepositoryPointLocations [31] = new Point (104, 60);
		
		pArRepositoryPointLocations [32] = new Point (726, 60);
		pArRepositoryPointLocations [33] = new Point (622, 60);
		
		pArRepositoryPointLocations [34] = new Point (726, 720);
		pArRepositoryPointLocations [35] = new Point (622, 720);
		
		pArRepositoryPointLocations [36] = new Point (0, 720);
		pArRepositoryPointLocations [37] = new Point (104, 720);
	}
}
