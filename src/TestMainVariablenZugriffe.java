
public class TestMainVariablenZugriffe {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println("Testzahl = " + VariablenZugriffsTest.ueberallVerfuegbareZahl);
		
		VariablenZugriffsTest.testButton.setActionCommand("Testaction");
		
		String gibtErKommandoZurueck = VariablenZugriffsTest.testButton.getActionCommand();
		
		System.out.println("Testkommando = " + gibtErKommandoZurueck);
		
		VariablenZugriffsTest.testLayout.next(null);
	}

}
