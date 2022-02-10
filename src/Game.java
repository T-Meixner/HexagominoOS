import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game 
{
	public static MainFrame mainFrame;
	public static Menu menu;
	public static Board board;
	
	public static int iCurrentPlayer = 0;
	private static int iPlayerCount = 0;
	public static int iRepositoryPerPlayer = 0;
	public static int iRoundCount = 0;
	public static int iMoves = 0;
	
	public static int [][] iArCombinations;
	
	public static ArrayList <ArrayList <Hexagon> > alPlayersHexagons = new ArrayList <> ();
	public static ArrayList <ArrayList <Repository> > alPlayersRepositories = new ArrayList <> ();
	
	public static Boolean [] bArCombinationsInUse = new Boolean [420];
	
	private static Boolean bFirstTurn = true;
	private static Boolean bPlayerPlacedHexagon = false;
	private static Boolean bPlayerDrawHexagon = false;
	
	private static Field [] fields = new Field [19];
	
	private static int iStartHexagonsPerPlayer;
	
	private static String [] sArPlayerNames = new String [6];
	
	
	public Game ()
	{
		mainFrame = new MainFrame ();
		
		addMenu ();
	}
	
	public static void startGame (int iPlayerCount, Color [] colArPlyColorsChosen, String [] sArPlyNamesChosen)
	{
		removeMenu ();
		
		addBoard (iPlayerCount, colArPlyColorsChosen, sArPlyNamesChosen);
		
		createArrayOfAllHexagonsCombinations ();
		
		createArrayCombinationsInUse ();
		
		createArrayFields ();
		
		createArrayListPlayersHexagons (iPlayerCount);
		
		createArrayListPlayersRepositories (iPlayerCount);
		
		setPlayerCount (iPlayerCount);
		
		setPlayerNames (sArPlyNamesChosen);
		
		if (iPlayerCount == 2)
		{
			iStartHexagonsPerPlayer = 9;
		}
		if (iPlayerCount == 3)
		{
			iStartHexagonsPerPlayer = 6;
		}
		if (iPlayerCount == 4)
		{
			iStartHexagonsPerPlayer = 5;
		}
		if (iPlayerCount == 5)
		{
			iStartHexagonsPerPlayer = 4;
		}
		if (iPlayerCount == 6)
		{
			iStartHexagonsPerPlayer = 3;
		}
		
		for (int iPlayerIndex = 0; iPlayerIndex < iPlayerCount; iPlayerIndex++)
		{
			for (int i = 0; i < iStartHexagonsPerPlayer; i++)
			{
				drawNewHexagon ();
			}
			iCurrentPlayer += 1;
			Board.boardMenuGraphics.clBoardMenuCardLayout.next (Board.boardMenuGraphics.pnlBoardMenuCardPanel);
		}
		
		/* Generate a random number as index of the starting player */
		Random rng = new Random ();
		int iStartPlayerIndex = rng.nextInt (0, (iPlayerCount-1));
		iCurrentPlayer = iStartPlayerIndex;
		
		/* Find the corresponding player's visual representation in the board's menu */	
		Board.boardMenuGraphics.clBoardMenuCardLayout.first (Board.boardMenuGraphics.pnlBoardMenuCardPanel);
		
		for (int iSteps = 0; iSteps < iStartPlayerIndex; iSteps++)
		{
			Board.boardMenuGraphics.clBoardMenuCardLayout.next (Board.boardMenuGraphics.pnlBoardMenuCardPanel);
		}
		
		JOptionPane.showMessageDialog (null, "Das Spiel beginnt!", "Spielstart", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void addMenu ()
	{
		menu = new Menu ();
		mainFrame.add (menu);
		mainFrame.revalidate ();
		mainFrame.repaint ();
	}
	
	public static void removeMenu ()
	{
		mainFrame.remove (menu);
		mainFrame.revalidate ();
		mainFrame.repaint ();
	}
	
	public static void addBoard (int iPlayerCount, Color [] colArPlyColorsChosen, String [] sArPlyNamesChosen)
	{
		board = new Board (iPlayerCount, colArPlyColorsChosen, sArPlyNamesChosen);
		mainFrame.add (board);
		mainFrame.revalidate ();
		mainFrame.repaint ();
	}
	
	public static void removeBoard ()
	{
		mainFrame.remove (board);
		mainFrame.revalidate ();
		mainFrame.repaint ();
	}
	
	public static void setPlayerCount (int playerCount)
	{
		iPlayerCount = playerCount;
	}
	
	public static int getPlayerCount ()
	{
		return iPlayerCount;
	}
	
	public static void setPlayerNames (String [] playerNames)
	{
		sArPlayerNames = playerNames;
	}
	
	public static String [] getPlayerNames ()
	{
		return sArPlayerNames;
	}
	
	public static void setPlayerPlacedHexagon (Boolean bPlacing)
	{
		bPlayerPlacedHexagon = bPlacing;
	}	
	
	public static Boolean getPlayerPlacedHexagon ()
	{
		return bPlayerPlacedHexagon;
	}
	
	public static void setPlayerDrawHexagon (Boolean bDraw)
	{
		bPlayerDrawHexagon = bDraw;
	}
	
	public static Boolean getPlayerDrawHexagon ()
	{
		return bPlayerDrawHexagon;
	}
	
	/* x and y are the image's corner x- and y-coordinate, not those of the mouse */
	public static void checkHexagonPlacing (int x, int y, int [] colorCode, Component object)
	{
		int [] distanceToFields = new int [19];
		int shortestDistance;
		int closestFieldIndex;
		
		int iFieldNeighbourIndexN;
		int iFieldNeighbourIndexNE;
		int iFieldNeighbourIndexSE;
		int iFieldNeighbourIndexS;
		int iFieldNeighbourIndexSW;
		int iFieldNeighbourIndexNW;
		
		int iContactColoredFieldCount = 0;
		int iMatchColoredFieldCount = 0;
		
		int [] iRepositoriesInUse = new int [6];
		int iLeastRepositoriesInUse = 0;
		int iPlayerIndexLeastRepositoriesInUse = -1;
		
		int iRepositoryClear = 0;
		
		int iFieldsInUse = 0;
		
		int [][] iArWinnerList = new int [6][6];
		
		/* Abort if the player already placed a hexagon */
		if (bPlayerPlacedHexagon == true)
		{
			JOptionPane.showMessageDialog (null, "Sie haben bereits ein Sechseck platziert!", "Fehlerhafter Zug", JOptionPane.ERROR_MESSAGE);
			
			moveHexagonBackToRepository (object);
			
			return;
		}
		
		/* Finding the next field: get the distance to every single field */
		for (int i = 0; i < fields[0].pArFieldPointLocations.length; i++)
		{
			distanceToFields [i] = (int) Math.sqrt 
					(
							( (int) x - (int) fields[i].pFieldPointLocation.getX() )
							*
							( (int) x - (int) fields[i].pFieldPointLocation.getX() )
							+
							( (int) y - (int) fields[i].pFieldPointLocation.getY() )
							*
							( (int) y - (int) fields[i].pFieldPointLocation.getY() )
					);
		}
		
		/* Finding the next field: get the index of the field with lowest distance-value */
		shortestDistance = Arrays.stream(distanceToFields).min().orElseThrow();
		
		closestFieldIndex = Arrays	.stream(distanceToFields)
									.boxed()
									.collect(Collectors.toList())
									.indexOf(shortestDistance);
		
		/* Abort if the distance is too big */
		if (shortestDistance > 50)
		{
			JOptionPane.showMessageDialog (null, "Zu weit weg von einem Feld!", "Fehlerhafter Zug", JOptionPane.ERROR_MESSAGE);
			
			moveHexagonBackToRepository (object);
			
			return;
		}
		
		/* Abort if the field is in use */
		if (bFirstTurn == false && fields[closestFieldIndex].getFieldInUse() == true)
		{
			JOptionPane.showMessageDialog (null, "Das nahe Feld ist besetzt!", "Fehlerhafter Zug", JOptionPane.ERROR_MESSAGE);
			
			moveHexagonBackToRepository (object);
			
			return;
		}
			
		/* Set the hexagon's location to the closest field's location */
		if (object instanceof Hexagon)
		{
			Hexagon hexagon = (Hexagon) object;
						
			hexagon.setLocation ( fields[closestFieldIndex].getFieldPointLocation() );
			
			hexagon.repaint();
		}
		
		/* Check color matching: get the index of neighbour fields*/
		iFieldNeighbourIndexN = fields[closestFieldIndex].getFieldNeighbourIndexN();
		iFieldNeighbourIndexNE = fields[closestFieldIndex].getFieldNeighbourIndexNE();
		iFieldNeighbourIndexSE = fields[closestFieldIndex].getFieldNeighbourIndexSE();
		iFieldNeighbourIndexS = fields[closestFieldIndex].getFieldNeighbourIndexS();
		iFieldNeighbourIndexSW = fields[closestFieldIndex].getFieldNeighbourIndexSW();
		iFieldNeighbourIndexNW = fields[closestFieldIndex].getFieldNeighbourIndexNW();
		
		int [] neighbourIndices = 	{iFieldNeighbourIndexN, iFieldNeighbourIndexNE, iFieldNeighbourIndexSE,
									iFieldNeighbourIndexS, iFieldNeighbourIndexSW, iFieldNeighbourIndexNW};
		
		/* Check color matching: let each neighbour check its concerned side's color to the colorcode of the hexagon */
		for (int index = 0; index < neighbourIndices.length; index++)
		{
			if (neighbourIndices [index] != -1 && fields[neighbourIndices[index]].getFieldInUse() == true)
			{
				/* Count every contacted field that has already a colorcode */
				iContactColoredFieldCount += 1;
				
				if (fields[neighbourIndices[index]].checkColorMatch (closestFieldIndex, colorCode) == true)
				{
					/* Count every contacted field that has already a colorcode and responds matching */
					iMatchColoredFieldCount += 1;
				}
			}
		}
		
		/* Abort and move the hexagon back to the player's repository if no colored field is contacted */
		if (bFirstTurn == false && iContactColoredFieldCount == 0)
		{
			JOptionPane.showMessageDialog (null, "Kein Sechseck berührt!", "Fehlerhafter Zug", JOptionPane.ERROR_MESSAGE);
			
			moveHexagonBackToRepository (object);
			
			return;
		}
		
		/* Abort and move the hexagon back to the player's repository if there are color mismatches */
		if (bFirstTurn == false && iContactColoredFieldCount != iMatchColoredFieldCount)
		{
			JOptionPane.showMessageDialog (null, "Farbe stimmt nicht überein!", "Fehlerhafter Zug", JOptionPane.ERROR_MESSAGE);
			
			moveHexagonBackToRepository (object);
			
			return;
		}
		
		int iPlaceHexagon = JOptionPane.showConfirmDialog (null, "Möchten Sie das Hexagon hier platzieren?", 
				"erfolgreiche Platzierung", JOptionPane.YES_NO_OPTION);
		
		if (iPlaceHexagon == 0)
		{
			/* Set the colorcode of the field */
			fields[closestFieldIndex].setFieldColorCode (colorCode);
			
			/* Set the field to status used */
			fields[closestFieldIndex].setFieldInUse (true);
			
			/* Remove the ownership of the hexagon */
			if (object instanceof Hexagon)
			{
				Hexagon hexagon = (Hexagon) object;
							
				hexagon.setHexagonsOwningPlayerIndex (-1);
				
				/* Drop the hexagon to lower layer */
				board.pnlBoardGameField.remove (hexagon);
				board.pnlBoardGameField.add (hexagon, JLayeredPane.PALETTE_LAYER);
				board.pnlBoardGameField.repaint();
				board.pnlBoardGameField.revalidate();
			}
			
			/* Set the repository to status unused */
			int iRepositoryIndex = -1;
			
			if (object instanceof Hexagon)
			{
				Hexagon hexagon = (Hexagon) object;
				
				iRepositoryIndex = hexagon.getHexagonsRepositoryIndex();
				
				alPlayersRepositories.get(iCurrentPlayer).get(iRepositoryIndex).setRepositoryInUse (false);
			}
			
			/* Check if the player won by placing his/her last hexagon */
			for (int index = 0; index < alPlayersRepositories.get(iCurrentPlayer).size(); index++)
			{
				if (alPlayersRepositories.get(iCurrentPlayer).get(index).getRepositoryInUse() == false)
				{
					iRepositoryClear += 1;
				}
			}
			
			if (iRepositoryClear == alPlayersRepositories.get(iCurrentPlayer).size())
			{
				lastHexagon ();
			}
			
			/* Check if any player won because all fields are filled with hexagons */
			for (int index = 0; index < fields.length; index++)
			{
				if (fields[index].getFieldInUse() == true)
				{
					iFieldsInUse += 1;
				}
			}
			
			if (iFieldsInUse == fields.length)
			{
				boardComplete ();
			}
			
			/* Raise the counter of moves in this round */
			iMoves += 1;
			
			/* End the right to place a hexagon */
			if (bPlayerPlacedHexagon == false)
			{
				bPlayerPlacedHexagon = true;
			}
			
			/* End the rights of the first turn */
			if (bFirstTurn == true)
			{
				bFirstTurn = false;
			}
		}
		else
		{
			/* Move the hexagon back to player's repository */
			moveHexagonBackToRepository (object);
		}
	}
	
	public static void lastHexagon ()
	{
		JLabel lblWinMessage;
		
		lblWinMessage = getWinMessage ();
		
		String sVictoryMessage = "Herzlichen Glückwunsch, " + sArPlayerNames [iCurrentPlayer] + "! Sie haben gewonnen!";
		
		JOptionPane.showMessageDialog (null, sVictoryMessage, "Gewonnen!", JOptionPane.INFORMATION_MESSAGE);
		
		JOptionPane.showOptionDialog (null, lblWinMessage, "Sieger", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	
	public static void boardComplete ()
	{
		JLabel lblWinMessage;
		
		lblWinMessage = getWinMessage ();
				
		JOptionPane.showMessageDialog (null, "Das letzte Feld wurde besetzt. Die Sieger stehen fest:", "Spielende", JOptionPane.INFORMATION_MESSAGE);
		
		JOptionPane.showOptionDialog (null, lblWinMessage, "Sieger", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	
	public static void missingMoves ()
	{
		JLabel lblWinMessage;
		
		lblWinMessage = getWinMessage ();
		
		JOptionPane.showMessageDialog (null, "Eine volle Runde erfolgte kein Zug. Die Sieger stehen fest:", "Spielende", JOptionPane.INFORMATION_MESSAGE);
		
		JOptionPane.showOptionDialog (null, lblWinMessage, "Sieger", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	
	public static JLabel getWinMessage ()
	{
		int [][] iArWinnerList = new int [iPlayerCount][iPlayerCount];
		
		String [] sArRostrum = new String [iPlayerCount];
		
		String sWinMessage = "";
		
		JLabel lblWinMessage;
		
		iArWinnerList = getWinnerList ();
		
		for (int iRostrumLevel = 0; iRostrumLevel < sArRostrum.length; iRostrumLevel++)
		{
			sArRostrum [iRostrumLevel] = "Platz " + (iRostrumLevel +1) + ": ";
			
			/* iAr Winner List Length ist völlig falsch, der Array enthält vermutlich nur 2 Einträge, 1 bei 0, 1 bei 1, aber ist 6 lang */
			
			for (int iPlayerOnRostrumLevel = 0; iPlayerOnRostrumLevel < iArWinnerList[iRostrumLevel].length; iPlayerOnRostrumLevel ++)
			{
				/* Filter all unused entries from winner list*/
				if (iArWinnerList [iRostrumLevel] [iPlayerOnRostrumLevel] != -1)
				{
					sArRostrum [iRostrumLevel] += sArPlayerNames [ iArWinnerList [iRostrumLevel] [iPlayerOnRostrumLevel] ];
					
					/* Check if there will be another array entry */
					if ( iArWinnerList [iRostrumLevel].length > (iPlayerOnRostrumLevel +1) )
					{
						/* Check if the next entry is a valid player index */
						if (iArWinnerList [iRostrumLevel] [iPlayerOnRostrumLevel +1] != -1)
						{
							sArRostrum [iRostrumLevel] += ", ";
						}
					}
				}
			}
		}
		
		/* Convert the string array to a dialogue text */
		sWinMessage = "<html> <p style=\"width:300px;\">";
		
		for (int i = 0; i < sArRostrum.length; i++)
		{
			if (sArRostrum [i].length() > 9)
			{
				sWinMessage += sArRostrum [i];
			}
			
			if (sArRostrum.length > i+1)
			{
				if (sArRostrum [i+1].length() > 9)
				{
					sWinMessage += "<br><br>";
				}
			}
		}
		
		lblWinMessage = new JLabel (sWinMessage);
		lblWinMessage.setFont (new Font ("Fixedsys", Font.PLAIN, 14));
		
		return lblWinMessage;
	}

	public static int [][] getWinnerList ()
	{
		int iRostrumLevel = 0;
		int iPlayerPerRostrumLevel = 0;
		int iBigNumberForDeactivation = 100;
		
		int [] iRepositoriesInUse = new int [iPlayerCount];
		int [] iCrtLstRepInUse = new int [iPlayerCount];
		int [] iRepInUsePlyIndex = new int [iPlayerCount];
		
		int [][] iArWinnerList = new int [iPlayerCount][iPlayerCount];
		
		/* Create an array that shows amount of used repository at player's index */ 
		for (int iPlayerIndex = 0; iPlayerIndex < iPlayerCount; iPlayerIndex ++)
		{
			for (int iRepoIndex = 0; iRepoIndex < alPlayersRepositories.get(iPlayerIndex).size(); iRepoIndex++)
			{
				if (alPlayersRepositories.get(iPlayerIndex).get(iRepoIndex).getRepositoryInUse() == true)
				{
					iRepositoriesInUse [iPlayerIndex] += 1;
				}
			}
		}
		
		/* Set the base index of the winner list to -1 (= not in use) */
		for (int iLevel = 0; iLevel < iPlayerCount; iLevel ++)
		{
			for (int iPlayer = 0; iPlayer < iPlayerCount; iPlayer ++)
			{
				iArWinnerList [iLevel] [iPlayer] = -1;
			}
		}
		
		for (int i = 0; i < iRepositoriesInUse.length; i++)
		{
			/* Save the lowest value */
			iCrtLstRepInUse [i] = Arrays.stream(iRepositoriesInUse).min().orElseThrow();
			
			System.out.println("aktuell niedrigster Wert von RepInUse an Stelle " + i + " hat laut ArraysStream.min " + iCrtLstRepInUse [i]);
			
			/* Get the index of the lowest value in iRepositoriesInUse (= player's index) */
			iRepInUsePlyIndex [i] = Arrays	.stream (iRepositoriesInUse)
											.boxed ()
											.collect (Collectors.toList())
											.indexOf (iCrtLstRepInUse [i]);
			
			/* Place the (first) player with lowest amount of full repositories at first place of the rostrum */
			if (i == 0)
			{
				iArWinnerList [iRostrumLevel][iPlayerPerRostrumLevel] = iRepInUsePlyIndex [i];
				iRepositoriesInUse [ iRepInUsePlyIndex [i] ] += iBigNumberForDeactivation;
			}
			
			/* If the same number of full repositories as the predecessor, then on the same level of the rostrum */
			if (i > 0 && iCrtLstRepInUse [i] == iCrtLstRepInUse [i -1])
			{
				iPlayerPerRostrumLevel += 1;
				iArWinnerList [iRostrumLevel][iPlayerPerRostrumLevel] = iRepInUsePlyIndex [i];
				iRepositoriesInUse [ iRepInUsePlyIndex [i] ] += iBigNumberForDeactivation;
			}
			/* If higher number of full repositories as the predecessor, then on the next level of the rostrum */
			if (i > 0 && iCrtLstRepInUse [i] > iCrtLstRepInUse [i -1])
			{
				iRostrumLevel += 1;
				iPlayerPerRostrumLevel = 0;
				iArWinnerList [iRostrumLevel][iPlayerPerRostrumLevel] = iRepInUsePlyIndex [i];
				iRepositoriesInUse [ iRepInUsePlyIndex [i] ] += iBigNumberForDeactivation;
			}
		}
		return iArWinnerList;
	}
	
	public static void moveHexagonBackToRepository (Component object)
	{
		int iRepositoryIndex = -1;
		
		if (object instanceof Hexagon)
		{
			Hexagon hexagon = (Hexagon) object;
			
			iRepositoryIndex = hexagon.getHexagonsRepositoryIndex();
			
			hexagon.setLocation ( alPlayersRepositories.get(iCurrentPlayer).get(iRepositoryIndex).getRepositoryLocation() );
						
			board.pnlBoardGameField.remove (hexagon);
			board.pnlBoardGameField.add (hexagon, JLayeredPane.PALETTE_LAYER);
			
			board.pnlBoardGameField.repaint();
			board.pnlBoardGameField.revalidate();
			hexagon.repaint();
		}
	}
	
	public static void setHexagonToBackground (Component component)
	{
		if (component instanceof Hexagon)
		{
			Hexagon hexagon = (Hexagon) component;
			
			board.pnlBoardGameField.moveToBack (hexagon);
		}
	}
	
	public static void setHexagonToForeground (Component component)
	{
		if (component instanceof Hexagon)
		{
			Hexagon hexagon = (Hexagon) component;
			
			board.pnlBoardGameField.moveToFront(hexagon);
		}
	}
	
	public static void drawNewHexagon ()
	{
		Boolean bValidIndexFound = false;
		Random rng = new Random ();
		int iCombinationIndex;
		int iNewHexagonIndex;
		int iRepositoryIndex = -1;
		
		/* Check if the player already draw a new hexagon in this round */
		if (bPlayerDrawHexagon == true)
		{
			JOptionPane.showMessageDialog (null, "Sie haben bereits ein Sechseck gezogen!", "Kein Ziehen möglich", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/* Check if the player has at least one free repository and save the index*/
		for (int index = 0; index < alPlayersRepositories.get(iCurrentPlayer).size(); index++)
		{
			if (alPlayersRepositories.get(iCurrentPlayer).get(index).getRepositoryInUse() == false)
			{
				iRepositoryIndex = index;
				break;
			}
		}
		
		if (iRepositoryIndex == -1)
		{
			JOptionPane.showMessageDialog (null, "Alle Ihre Sechseckfelder sind belegt!", "Kein Ziehen möglich", JOptionPane.ERROR_MESSAGE);
			return;
		}
				
		do 
		{
			/* Create a random number between 0 and 419 as the index of a combination */
			iCombinationIndex = rng.nextInt (0, 419);
			
			/* Check if the random number is set to used (true) in the boolean-array */
			if (bArCombinationsInUse [iCombinationIndex] == false)
			{
				bValidIndexFound = true;
			}
		}
		while (bValidIndexFound = false);
		
		/* Get the entry (6 integers) in the array of all combinations at index = random number */
		int [] iArCombination = iArCombinations [iCombinationIndex];
		
		/* Add a new hexagon (JPanel) with the combination to the current player's array list */
		alPlayersHexagons.get(iCurrentPlayer).add (new Hexagon (iArCombination));
		
		/* Add the new hexagon to the board -> pnlBoardGameField (JLayeredPane) */
		iNewHexagonIndex = alPlayersHexagons.get(iCurrentPlayer).size() -1;
		
		Board.pnlBoardGameField.add ( (alPlayersHexagons.get(iCurrentPlayer).get(iNewHexagonIndex)), JLayeredPane.PALETTE_LAYER );
		mainFrame.revalidate ();
		mainFrame.repaint ();
		
		/* Save the index of the new hexagon in the player's hexagons list within the hexagon */
		alPlayersHexagons.get(iCurrentPlayer).get(iNewHexagonIndex).setOwningPlayerHexagonListIndex (iNewHexagonIndex);
		
		/* Save the index of the current player as hexagon's owner within the hexagon */
		alPlayersHexagons.get(iCurrentPlayer).get(iNewHexagonIndex).setHexagonsOwningPlayerIndex (iCurrentPlayer);
		
		/* Move the new hexagon to the first free repository's location of the current player */
		alPlayersHexagons.get(iCurrentPlayer).get(iNewHexagonIndex).setLocation 
			(alPlayersRepositories.get(iCurrentPlayer).get(iRepositoryIndex).getRepositoryLocation());
		
		/* Save the index of the used repository within the new hexagon */
		alPlayersHexagons.get(iCurrentPlayer).get(iNewHexagonIndex).setHexagonsRepositoryIndex (iRepositoryIndex);
		
		/* Set the array of used combinations to true at index = random number */
		bArCombinationsInUse [iCombinationIndex] = true;
		
		/* Set the used free repository's location to status locked */
		alPlayersRepositories.get(iCurrentPlayer).get(iRepositoryIndex).setRepositoryInUse (true);
	}
	
	private static void createArrayCombinationsInUse ()
	{
		/* Boolean array with 420 entries, index in bArCombinationsInUse = index in iArCombinations */
		Arrays.fill (bArCombinationsInUse, false);
	}
	
	private static void createArrayListPlayersHexagons (int iPlayerCount)
	{
		for (int iPlayerIndex = 0; iPlayerIndex < iPlayerCount; iPlayerIndex++)
		{
			alPlayersHexagons.add (new ArrayList <Hexagon> ());
		}
	}
	
	private static void createArrayListPlayersRepositories (int iPlayerCount)
	{
		if (iPlayerCount == 2) 
		{
			iRepositoryPerPlayer = 15;
		}
		
		if (iPlayerCount == 3) 
		{
			iRepositoryPerPlayer = 10;
		}
		
		if (iPlayerCount == 4) 
		{
			iRepositoryPerPlayer = 6;
		}
		
		if (iPlayerCount == 5) 
		{
			iRepositoryPerPlayer = 5;
		}
		
		if (iPlayerCount == 6) 
		{
			iRepositoryPerPlayer = 5;
		}
						
		if (iPlayerCount == 2 || iPlayerCount == 3 || iPlayerCount == 6) 
		{
			for (int iPlayerIndex = 0, iRepositoryIndexStart = 0; iPlayerIndex < iPlayerCount; iPlayerIndex += 1, iRepositoryIndexStart += iRepositoryPerPlayer) 
			{
				alPlayersRepositories.add (new ArrayList <Repository> ());

				for (int iRepositoryIndex = iRepositoryIndexStart; iRepositoryIndex < iRepositoryPerPlayer * (iPlayerIndex + 1); iRepositoryIndex++)
				{
					/* Keep in mind: the 1st repository of player index 1 is created with index 15 but is entry 1.0 in ArrayList */
					alPlayersRepositories.get(iPlayerIndex).add (new Repository (iRepositoryIndex) );
				}
			} 
		}
		
		if (iPlayerCount == 4)
		{
			for (int iPlayerIndex = 0; iPlayerIndex < iPlayerCount; iPlayerIndex ++) 
			{
				alPlayersRepositories.add (new ArrayList <Repository> ());
			} 
			
			int playerIndex = 0;
			for (int iRepositoryIndex = 0; iRepositoryIndex < 4; iRepositoryIndex++)
			{
				alPlayersRepositories.get(playerIndex).add ( new Repository (iRepositoryIndex) );
			}
			alPlayersRepositories.get(playerIndex).add ( new Repository (30) );
			alPlayersRepositories.get(playerIndex).add ( new Repository (31) );
			
			playerIndex = 1;
			for (int iRepositoryIndex = 11; iRepositoryIndex < 15; iRepositoryIndex++)
			{
				alPlayersRepositories.get(playerIndex).add ( new Repository (iRepositoryIndex) );
			}
			alPlayersRepositories.get(playerIndex).add ( new Repository (32) );
			alPlayersRepositories.get(playerIndex).add ( new Repository (33) );
			
			playerIndex = 2;
			for (int iRepositoryIndex = 15; iRepositoryIndex < 19; iRepositoryIndex++)
			{
				alPlayersRepositories.get(playerIndex).add ( new Repository (iRepositoryIndex) );
			}
			alPlayersRepositories.get(playerIndex).add ( new Repository (34) );
			alPlayersRepositories.get(playerIndex).add ( new Repository (35) );
			
			playerIndex = 3;
			for (int iRepositoryIndex = 26; iRepositoryIndex < 30; iRepositoryIndex++)
			{
				alPlayersRepositories.get(playerIndex).add ( new Repository (iRepositoryIndex) );
			}
			alPlayersRepositories.get(playerIndex).add ( new Repository (36) );
			alPlayersRepositories.get(playerIndex).add ( new Repository (37) );
		}
		
		if (iPlayerCount == 5)
		{
			for (int iPlayerIndex = 0, iRepositoryIndexStart = 0; iPlayerIndex < 4; iPlayerIndex += 1, iRepositoryIndexStart += iRepositoryPerPlayer) 
			{
				alPlayersRepositories.add (new ArrayList <Repository> ());

				for (int iRepositoryIndex = iRepositoryIndexStart; iRepositoryIndex < iRepositoryPerPlayer * (iPlayerIndex + 1); iRepositoryIndex++)
				{
					alPlayersRepositories.get(iPlayerIndex).add (new Repository (iRepositoryIndex) );
				}
			}
			/* Adding the repositories of the 5th player */
			alPlayersRepositories.add (new ArrayList <Repository> ());
			alPlayersRepositories.get(4).add (new Repository (25) );
			alPlayersRepositories.get(4).add (new Repository (26) );
			alPlayersRepositories.get(4).add (new Repository (27) );
			alPlayersRepositories.get(4).add (new Repository (28) );
			alPlayersRepositories.get(4).add (new Repository (29) );
		}
	}
	
		
	private static void createArrayOfAllHexagonsCombinations ()
	{
		HexagonCombinations combinations = new HexagonCombinations ();
		iArCombinations = combinations.getCombinations();
	}
	
	private static void createArrayFields ()
	{
		for (int index = 0; index < 19; index++)
		{
			fields [index] = new Field (index);
		}
	}
}
