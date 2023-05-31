package controlador;

import java.util.Scanner;

public class Lector {

	public static String leerString(String frase) {
	
		String dato;
		Scanner lector = new Scanner(System.in);
	
		System.out.println(frase);
		dato = lector.nextLine();
	
		return dato;
	}

	public static char leerChar(String frase) {
	
		char dato;
		Scanner lector = new Scanner(System.in);
	
		System.out.println(frase);
		dato = lector.next().toUpperCase().charAt(0);
		lector.nextLine();
	
		return dato;
	}

	public static int leerInt(String frase) {
	
		int dato;
		Scanner lector = new Scanner(System.in);
	
		System.out.println(frase);
		dato = lector.nextInt();
		lector.nextLine();
	
		return dato;
	}

}
