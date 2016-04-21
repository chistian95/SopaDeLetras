
public class Diccionario {
	private static final String[] PALABRAS = 
		{
				"PATO",
				"PERRO",
				"CASA",
				"CONEJO",
				"VACA",
				"ALCOHOL",
				"TECLADO"
		};
	
	public static String getRandom() {
		return PALABRAS[(int) (Math.random()*PALABRAS.length)];
	}
}
