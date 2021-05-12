import java.util.Scanner;

/**
 * Mostra i gestiona totes les opcions de les que disposa el joc
 * @author Amina Khyat El Achlam
 *
 */
public class Menu {

	static Scanner reader = new Scanner(System.in);
	
	/**
	 * Mostra totes les opcions disponibles del joc. 
	 */
	public static void mostrar() {
		System.out.println();
		System.out.println(" ==== MENÚ ====");
		System.out.println("Escull una opció: ");
		System.out.println("1. Mostrar ajuda");
		System.out.println("2. Opcions");
		System.out.println("3. Jugar partida");
		System.out.println("4. Veure Ranking");
		System.out.println("0. Sortir");
	}
	
	/**
	 * Classe que llegeix la opció que escull el jugador 
	 * @return int opció escollida del menú del joc 
	 * 
	 */
	public static int llegirOpcioMenu() {
		int op = -1;
		while (op > 4 || op < 0) {
			try {
				op = reader.nextInt();
				if (op > 4 || op < 0) {
					System.out.println("Opció no vàlida. Introdueix una altra.");
				}
			} catch (Exception e) {
				System.out.println("Atenció! Únicament es permet insertar números enters. ");
				reader.nextLine();
			}
		}
		return op;
	}

}
