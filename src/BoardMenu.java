import java.awt.Color;

import javax.swing.JPanel;

public class BoardMenu extends JPanel
{
	public BoardMenu (int iPlayerCount, Color [] colArPlyColorsChosen, String [] sArPlyNamesChosen)
	{
		BoardMenuGraphics boardMenuGraphics = new BoardMenuGraphics (iPlayerCount, colArPlyColorsChosen, sArPlyNamesChosen);
		
		this.add(boardMenuGraphics);
	}
}
