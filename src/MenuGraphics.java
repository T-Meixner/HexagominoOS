import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class MenuGraphics extends JPanel

{
	public static Button btnMenuMainStart;
	public static Button btnMenuMainManual;
	public static Button btnMenuMainQuit;
	public static Button btnMenuPlyCntNext;
	public static Button btnMenuPlyCntPrev;
	public static Button btnMenuPlyValNext;
	public static Button btnMenuPlyValPrev;
	
	public static JRadioButton [] rdBtnArMenuPlyCntNumbers;
	public static JRadioButton [] rdBtnArMenuPlyValColors;
	
	public static ButtonGroup btnGrpMenuPlyCntNumbers;
	public static ButtonGroup btnGrpMenuPlyValColors;
	
	public static Color [] colArPlyColors;
	
	public static JTextField txtFldMenuPlyValName;
	
	public static JLabel lblMenuPlyValCurrentPlayer;
	
	public static JPanel pnlMenuCardPanel;
	
	public static CardLayout clMenuCardLayout;
	
	private JLabel lblMenuHeadline;
	private JLabel lblMenuPlyCntChooseNumber;
	private JLabel lblMenuPlyValChooseColor;
	private JLabel lblMenuPlyValChooseName;
	
	private JLabel [] lblArMenuPlyCntNumbers;
	
	private JPanel pnlMenuMain;
	private JPanel pnlMenuPlyCnt;
	private JPanel pnlMenuPlyCntRadButtons;
	private JPanel pnlMenuPlyVal;
	private JPanel pnlMenuPlyValRadButtons;
	
	private GridBagLayout gblMenu;
	private GridBagLayout gblMenuPages;
	
	private GridBagConstraints gbcMenu;
	private GridBagConstraints gbcMenuPages;
	
	private Border brdRaisedBevel;
	private Border brdLoweredBevel;
	private Border brdHeadline;
	private Border brdLabel;
			
	public MenuGraphics ()
	{
//		this.setBackground (Color.white);
		this.setBackground ( Color.decode ("#8D6E63") );
		
		gblMenu = new GridBagLayout ();
		gbcMenu = new GridBagConstraints ();
		
		this.setLayout (gblMenu);
		
		/* GridbagLayout-Formatting for the menu's headline */
		
		gbcMenu.gridwidth = GridBagConstraints.REMAINDER;
		gbcMenu.anchor = GridBagConstraints.NORTH;
		gbcMenu.ipadx = 100;
		gbcMenu.ipady = 50;
		gbcMenu.fill = GridBagConstraints.HORIZONTAL;
		gbcMenu.insets = new Insets (5, 0, 50, 0);
		
		brdRaisedBevel = BorderFactory.createRaisedBevelBorder ();
		brdLoweredBevel = BorderFactory.createLoweredBevelBorder ();
		brdHeadline = BorderFactory.createCompoundBorder (brdRaisedBevel, brdLoweredBevel);
		brdLabel = BorderFactory.createLineBorder ( Color.decode ("#5D4037"), 2 );
		
		lblMenuHeadline = new JLabel ("<html><h1><strong><i>HEXAGOMINO</i></strong></h1><hr></html>");
		lblMenuHeadline.setFont (new Font ("Fixedsys", Font.PLAIN, 28) );
        lblMenuHeadline.setForeground (Color.decode ("#3E2723"));
        lblMenuHeadline.setHorizontalAlignment (SwingConstants.CENTER);
        lblMenuHeadline.setBorder (brdHeadline);
		
		/* Adding the headline to the menu's panel */
		
		this.add (lblMenuHeadline, gbcMenu);
		
		/* preparing the menu's pages */
		
		prepareMenuPagesLayout ();
		prepareMenuMain ();
        prepareMenuPlyCnt ();
        prepareMenuPlyVal ();
        
        /* GridbagLayout-Formatting for the menu's pages */
        
        gbcMenu.anchor = GridBagConstraints.CENTER;
        gbcMenu.fill = GridBagConstraints.HORIZONTAL;
        gbcMenu.ipadx = 0;
        gbcMenu.ipady = 20;
        gbcMenu.insets = new Insets (5, 0, 5, 0);
        gbcMenu.weighty = 0;
        
        /* Adding the pages to the menu's panel */
        
        this.add (pnlMenuCardPanel, gbcMenu);
	}
	
	private void prepareMenuPagesLayout ()
	{
		pnlMenuCardPanel = new JPanel ();
		clMenuCardLayout = new CardLayout ();
		
		pnlMenuCardPanel.setLayout (clMenuCardLayout);
		
		gblMenuPages = new GridBagLayout ();
		gbcMenuPages = new GridBagConstraints ();
		
		gbcMenuPages.gridwidth = GridBagConstraints.REMAINDER;
		gbcMenuPages.anchor = GridBagConstraints.PAGE_START;
		/* all cardpage-elements adapt to the broadest element */
		gbcMenuPages.fill = GridBagConstraints.HORIZONTAL;
		gbcMenuPages.ipadx = 20;
		gbcMenuPages.ipady = 20;
		gbcMenuPages.insets = new Insets (5, 0, 5, 0);
		/* all cardpages adapt to the broadest cardpage */ 
		gbcMenuPages.weightx = 1;
	}
	
	private void prepareMenuMain ()
	{
		pnlMenuMain = new JPanel (gblMenuPages);
		
		pnlMenuMain.setBackground ( Color.decode ("#8D6E63") );
		
		btnMenuMainStart = new Button ("Spiel beginnen");
		btnMenuMainManual = new Button ("Spielanleitung");
		btnMenuMainQuit = new Button ("Spiel beenden");
		
		pnlMenuMain.add (btnMenuMainStart, gbcMenuPages);
		pnlMenuMain.add (btnMenuMainManual, gbcMenuPages);
		pnlMenuMain.add (btnMenuMainQuit, gbcMenuPages);
		
		pnlMenuCardPanel.add (pnlMenuMain, "pnlMenuMain");
	}
	
	private void prepareMenuPlyCnt ()
	{
		/* Instancing of the page's components */
		
		pnlMenuPlyCnt = new JPanel (gblMenuPages);
		
		lblMenuPlyCntChooseNumber = new JLabel ("Spieleranzahl auswählen");
		
		pnlMenuPlyCntRadButtons = new JPanel (new GridLayout (2,5,0,0));
		
		rdBtnArMenuPlyCntNumbers = new JRadioButton []
				{
					new JRadioButton (""),
					new JRadioButton (""),
					new JRadioButton (""),
					new JRadioButton (""),
					new JRadioButton (""),				
				};
		
		btnGrpMenuPlyCntNumbers = new ButtonGroup ();
		
		lblArMenuPlyCntNumbers = new JLabel []
				{
					new JLabel ("2"),
					new JLabel ("3"),
					new JLabel ("4"),
					new JLabel ("5"),
					new JLabel ("6"),
				};
		
		btnMenuPlyCntNext = new Button ("weiter");
		btnMenuPlyCntPrev = new Button ("zurück");
		
		/* Formatting of the page's components */
		
		pnlMenuPlyCnt.setBackground (Color.decode ("#8D6E63") );
		
		lblMenuPlyCntChooseNumber.setHorizontalAlignment (SwingConstants.CENTER);
		lblMenuPlyCntChooseNumber.setFont (new Font ("Fixedsys", Font.BOLD, 16));
		lblMenuPlyCntChooseNumber.setOpaque (true);
		lblMenuPlyCntChooseNumber.setBackground (Color.decode ("#D7CCC8") );
		lblMenuPlyCntChooseNumber.setBorder(brdLabel);
		
		for (int i = 0; i < rdBtnArMenuPlyCntNumbers.length; i++)
		{
			rdBtnArMenuPlyCntNumbers[i].setHorizontalAlignment (JLabel.CENTER);
			rdBtnArMenuPlyCntNumbers[i].setBackground (Color.decode ("#D7CCC8") );
		}
		
		for (int i = 0; i < lblArMenuPlyCntNumbers.length; i++)
		{
			lblArMenuPlyCntNumbers[i].setHorizontalAlignment (JLabel.CENTER);
		}
		
		for (int i = 0; i < lblArMenuPlyCntNumbers.length; i++)
		{
			lblArMenuPlyCntNumbers[i].setFont (new Font ("Fixedsys", Font.BOLD, 16));
		}
		
		/* Adding of the page's components */
		
		for (int i = 0; i < rdBtnArMenuPlyCntNumbers.length; i++)
		{
			btnGrpMenuPlyCntNumbers.add (rdBtnArMenuPlyCntNumbers [i]);
		}
		
		for (int i = 0; i < rdBtnArMenuPlyCntNumbers.length; i++)
		{
			pnlMenuPlyCntRadButtons.add (rdBtnArMenuPlyCntNumbers [i]);
		}
		
		for (int i = 0; i < lblArMenuPlyCntNumbers.length; i++)
		{
			pnlMenuPlyCntRadButtons.add (lblArMenuPlyCntNumbers [i]);
		}
		
		pnlMenuPlyCntRadButtons.setBackground (Color.decode ("#D7CCC8") );
		pnlMenuPlyCntRadButtons.setBorder (brdLabel);
		
		pnlMenuPlyCnt.add (lblMenuPlyCntChooseNumber, gbcMenuPages);
		pnlMenuPlyCnt.add (pnlMenuPlyCntRadButtons, gbcMenuPages);
		pnlMenuPlyCnt.add (btnMenuPlyCntNext, gbcMenuPages);
		pnlMenuPlyCnt.add (btnMenuPlyCntPrev, gbcMenuPages);
		
		pnlMenuCardPanel.add (pnlMenuPlyCnt, "pnlMenuPlyCnt");
	}
	
	private void prepareMenuPlyVal ()
	{
		/* Instancing of the page's components */
		
		Border border = BorderFactory.createLineBorder (Color.BLUE, 2);
		
		pnlMenuPlyVal = new JPanel (gblMenuPages);
		
		lblMenuPlyValCurrentPlayer = new JLabel ("Spieler 1");
		
		lblMenuPlyValChooseColor = new JLabel ("Spielerfarbe auswählen");
		
		pnlMenuPlyValRadButtons = new JPanel (new GridLayout (1,6,0,0));
		
		rdBtnArMenuPlyValColors = new JRadioButton []
				{
					new JRadioButton (""),
					new JRadioButton (""),
					new JRadioButton (""),
					new JRadioButton (""),
					new JRadioButton (""),
					new JRadioButton (""),
				};
		
		btnGrpMenuPlyValColors = new ButtonGroup ();
		
		colArPlyColors = new Color []
				{
					new Color (0, 128, 0),		/*olive*/
					new Color (128, 0, 255),	/*purple*/
					new Color (255, 0, 128),	/*pink*/
					new Color (255, 128, 0),	/*orange*/
					new Color (0, 128, 255),	/*Dodger Blue*/
					new Color (255, 255, 255)	/*white*/
				};
		
		lblMenuPlyValChooseName = new JLabel ("Spielername eingeben");
		
		txtFldMenuPlyValName = new JTextField ();
		
		btnMenuPlyValNext = new Button ("nächster Spieler");
		btnMenuPlyValPrev = new Button ("zurück");
		
		/* Formatting of the page's components */
		
		pnlMenuPlyVal.setBackground (Color.decode ("#8D6E63") );
		
		pnlMenuPlyValRadButtons.setBorder(brdLabel);
		
		lblMenuPlyValCurrentPlayer.setHorizontalAlignment (SwingConstants.CENTER);
		lblMenuPlyValCurrentPlayer.setFont (new Font ("Fixedsys", Font.BOLD, 16));
		lblMenuPlyValCurrentPlayer.setBorder (brdLabel);
		lblMenuPlyValCurrentPlayer.setOpaque (true);
		lblMenuPlyValCurrentPlayer.setBackground (Color.decode ("#D7CCC8") );
				
		lblMenuPlyValChooseColor.setHorizontalAlignment (SwingConstants.CENTER);
		lblMenuPlyValChooseColor.setFont (new Font ("Fixedsys", Font.PLAIN, 16));
		lblMenuPlyValChooseColor.setBorder (brdLabel);
		lblMenuPlyValChooseColor.setOpaque (true);
		lblMenuPlyValChooseColor.setBackground (Color.decode ("#D7CCC8") );
				
		for (int i = 0; i < rdBtnArMenuPlyValColors.length; i++)
		{
			rdBtnArMenuPlyValColors[i].setHorizontalAlignment (JLabel.CENTER);
		}
		
		for (int i = 0; i < rdBtnArMenuPlyValColors.length && i < colArPlyColors.length; i ++)
        {
			rdBtnArMenuPlyValColors[i].setBackground (colArPlyColors [i]);
        }
		
		lblMenuPlyValChooseName.setHorizontalAlignment (SwingConstants.CENTER);
		lblMenuPlyValChooseName.setFont (new Font ("Fixedsys", Font.PLAIN, 16));
		lblMenuPlyValChooseName.setBorder (brdLabel);
		lblMenuPlyValChooseName.setOpaque (true);
		lblMenuPlyValChooseName.setBackground (Color.decode ("#D7CCC8") );
		
		txtFldMenuPlyValName.setColumns(10);
		
		btnMenuPlyValNext.setFont (new Font ("Fixedsys", Font.BOLD, 16));
		btnMenuPlyValPrev.setFont (new Font ("Fixedsys", Font.BOLD, 16));
		
		/* Adding of the page's components */
		
		for (int i = 0; i < rdBtnArMenuPlyValColors.length; i++)
		{
			btnGrpMenuPlyValColors.add (rdBtnArMenuPlyValColors [i]);
		}
		
		for (int i = 0; i < rdBtnArMenuPlyValColors.length; i++)
		{
			pnlMenuPlyValRadButtons.add (rdBtnArMenuPlyValColors [i]);
		}
		
		pnlMenuPlyVal.add (lblMenuPlyValCurrentPlayer, gbcMenuPages);
		pnlMenuPlyVal.add (lblMenuPlyValChooseColor, gbcMenuPages);
		pnlMenuPlyVal.add (pnlMenuPlyValRadButtons, gbcMenuPages);
		pnlMenuPlyVal.add (lblMenuPlyValChooseName, gbcMenuPages);
		pnlMenuPlyVal.add (txtFldMenuPlyValName, gbcMenuPages);
		pnlMenuPlyVal.add (btnMenuPlyValNext, gbcMenuPages);
		pnlMenuPlyVal.add (btnMenuPlyValPrev, gbcMenuPages);
		
		pnlMenuCardPanel.add (pnlMenuPlyVal, "pnlMenuPlyVal");
	}
}
