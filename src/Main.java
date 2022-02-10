import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main 
{
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater (new Runnable () 
		{
			public void run() 
			{
				try 
				{
//					UIManager.setLookAndFeel (UIManager.getCrossPlatformLookAndFeelClassName());
					
					Game game = new Game ();
					
				}
//				catch (UnsupportedLookAndFeelException e)
//				{
//				       // handle exception
//				}
//				catch (ClassNotFoundException e) 
//				{
//				       // handle exception
//				}
//				catch (InstantiationException e)
//				{
//				       // handle exception
//				}
//				catch (IllegalAccessException e) 
//				{
//				       // handle exception
//				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

}
