import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Sopa extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final boolean verbose = true;
	
	private int lineas, columnas, palabrasTotales;
	private char[][] tablero;
	private List<Palabra> palabras;
	
	private JTextField[][] celdasPantalla;
	private Font fuente;
	
	Sopa() {
		this(10, 10, 5);
	}
	Sopa(int lineas, int columnas) {
		this(lineas, columnas, 5);
	}
	Sopa(int lineas, int columnas, int palabrasTotales) {
		this.lineas = lineas;
		this.columnas = columnas;
		this.palabrasTotales = palabrasTotales;
		tablero = new char[lineas][columnas];
		palabras = new ArrayList<Palabra>();
		
		celdasPantalla = new JTextField[lineas][columnas];
		fuente = new Font("Monospaced", Font.BOLD, 20);
	}
	
	public void generarTablero() {		
		int palabrasGeneradas = 0;
		while(palabrasGeneradas < palabrasTotales) {
			int rndLinea = (int) (Math.random()*lineas);
			int rndCol = (int) (Math.random()*columnas);
			int rndOrientacion = (int) (Math.random()*Orientacion.ORIENTACION.length);
			String palabra = Diccionario.getRandom();
			
			if(palabraValida(rndLinea, rndCol, rndOrientacion, palabra)) {
				if(verbose) {
					System.out.println("Palabra "+palabra+" en "+rndLinea+", "+rndCol+" con orientacion "+Orientacion.ORIENTACION[rndOrientacion]);
				}				
				Palabra pl = new Palabra(rndLinea, rndCol, rndOrientacion, palabra, this);
				palabras.add(pl);
				pl.meterEnTablero();
				
				palabrasGeneradas++;
			}
		}		
		
		for(int linea=0; linea<lineas; linea++) {
			for(int col=0; col<columnas; col++) {
				char rndLetra = (char) ((int) (Math.random()*26)+65);
				if(letraValida(linea, col, rndLetra)) {
					tablero[linea][col] = rndLetra;
				}
			}
		}
	}
	
	public void pintarTablero() {
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(lineas, columnas));
		
		for(int linea=0; linea<lineas; linea++) {
			for(int col=0; col<columnas; col++) {
				celdasPantalla[linea][col] = new JTextField();
				cp.add(celdasPantalla[linea][col]);
				
				celdasPantalla[linea][col].setText(tablero[linea][col]+"");
				celdasPantalla[linea][col].setEditable(false);
				celdasPantalla[linea][col].setHorizontalAlignment(JTextField.CENTER);
				celdasPantalla[linea][col].setFont(fuente);
			}
		}
		
		cp.setPreferredSize(new Dimension(lineas, columnas));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sopa de Letras");
		setSize(600, 600);
		setVisible(true);
	}
	
	private boolean palabraValida(int linea, int col, int orientacion, String palabra) {
		if(palabraUsada(palabra)) {
			return false;
		}
		for(int i=0; i<palabra.length(); i++) {
			char letra = palabra.charAt(i);
			switch (orientacion) {
			case Orientacion.HORIZONTAL:				
				if(!letraValida(linea, col+i, letra)) {
					return false;
				}
				break;
			case Orientacion.VERTICAL:
				if(!letraValida(linea+i, col, letra)) {
					return false;
				}
				break;
			case Orientacion.DIAGONAL_DRC:
				if(!letraValida(linea+i, col+i, letra)) {
					return false;
				}
				break;
			case Orientacion.DIAGONAL_IZQ:
				if(!letraValida(linea+i, col-i, letra)) {
					return false;
				}
				break;
			case Orientacion.INV_HORIZONTAL:				
				if(!letraValida(linea, col-i, letra)) {
					return false;
				}
				break;
			case Orientacion.INV_VERTICAL:
				if(!letraValida(linea-i, col, letra)) {
					return false;
				}
				break;
			case Orientacion.INV_DIAGONAL_DRC:
				if(!letraValida(linea-i, col+i, letra)) {
					return false;
				}
				break;
			case Orientacion.INV_DIAGONAL_IZQ:
				if(!letraValida(linea-i, col-i, letra)) {
					return false;
				}
				break;
			default:
				return false;
			}			
		}
		
		return true;
	}
	
	private boolean letraValida(int linea, int col, char letra) {
		if(linea >= lineas || col >= columnas || linea < 0 || col < 0) {
			return false;
		}
		if(tablero[linea][col] != '\u0000' && tablero[linea][col] != letra) {
			return false;
		}
		return true;
	}
	
	private boolean palabraUsada(String palabra) {
		for(Palabra pl : palabras) {
			if(pl.getPalabra().equals(palabra)) {
				return true;
			}
		}
		return false;
	}
	
	public char[][] getTablero() { return tablero; }
}
