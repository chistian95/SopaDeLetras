
public class Palabra {
	private int linea, col, orientacion;
	private String palabra;
	private Sopa sp;
	
	Palabra(int linea, int col, int orientacion, String palabra, Sopa sp) {
		this.linea = linea;
		this.col = col;
		this.orientacion = orientacion;
		this.palabra = palabra;
		this.sp = sp;
	}
	
	public void meterEnTablero() {
		char[][] tablero = sp.getTablero();
		for(int i=0; i<palabra.length(); i++) {
			char letra = palabra.charAt(i);
			switch (orientacion) {
			case Orientacion.HORIZONTAL:				
				tablero[linea][col+i] = letra;
				break;
			case Orientacion.VERTICAL:
				tablero[linea+i][col] = letra;
				break;
			case Orientacion.DIAGONAL_DRC:
				tablero[linea+i][col+i] = letra;
				break;
			case Orientacion.DIAGONAL_IZQ:
				tablero[linea+i][col-i] = letra;
				break;
			case Orientacion.INV_HORIZONTAL:				
				tablero[linea][col-i] = letra;
				break;
			case Orientacion.INV_VERTICAL:
				tablero[linea-i][col] = letra;
				break;
			case Orientacion.INV_DIAGONAL_DRC:
				tablero[linea-i][col+i] = letra;
				break;
			case Orientacion.INV_DIAGONAL_IZQ:
				tablero[linea-i][col-i] = letra;
				break;
			}
		}
	}
	public int longitud() { return palabra.length(); }
	
	public int getLinea() { return linea; }
	public int getCol() { return col; }
	public int getOrientacion() { return orientacion; }
	public String getPalabra() { return palabra; }
}
