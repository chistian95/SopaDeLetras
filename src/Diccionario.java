
public class Diccionario {
	private static final String[] PALABRAS = 
		{
				"PATO",
				"PERRO",
				"CASA",
				"CONEJO",
				"VACA",
				"ALCOHOL",
				"TECLADO",
				"PINTURA",
				"PEGATINA",
				"PAPEL",
				"DICCIONARIO"
		};
	
	public static String getRandom() {
		return PALABRAS[(int) (Math.random()*PALABRAS.length)];
	}
}
