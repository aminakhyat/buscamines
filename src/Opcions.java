import java.util.Scanner;

/**
 * 
 * @author Amina Khyat El Achlam
 * 
 *         Clase que controla totes les opcions de dificultat que té el joc.
 *
 */
public class Opcions {
	static Scanner reader = new Scanner(System.in);

	/**
	 * Mostra tots els nivells de dificultat que té el joc per tal que el jugador
	 * pugui triar.
	 */
	public static void mostrarOpcionsNivell() {
		System.out.println();
		System.out.println("Quin nivell de dificultat vols? Escull una opció: ");
		System.out.println("1. Nivell principiant");
		System.out.println("2. Nivell intermig");
		System.out.println("3. Nivell expert");
		System.out.println("4. Nivell personalitzat");

	}

	/**
	 * Mètode que llegeix una opció que fa referència a un dels nivells de
	 * dificultat.
	 * 
	 * @return int retorna el nivell de dificultat que ha triat el jugador.
	 */
	public static int llegirOpcioNivell() {
		int op = 0;
		while (op > 4 || op < 1) {
			try {
				op = reader.nextInt();
				if (op > 4 || op < 1) {
					System.out.println("Opció no vàlida. Introdueix una altra.");
				}
			} catch (Exception e) {
				System.out.println("Atenció! Únicament es permet insertar números enters. ");
				reader.nextLine();
			}
		}
		return op;

	}

	/**
	 * Mètode que reb el nivell que el jugador ha triat i retorna el número de
	 * files, columnes i la quantitat de mines que tindrà el tauler.
	 * 
	 * @param int nivell de dificultat que ha escollit el jugador.
	 * @return un array de 3 posicions, on la posició 0 fa referència al número de
	 *         files, la posició 1 al número de columnes i la posició 3 al número de
	 *         mines.
	 */
	public static int[] opcio(int nivell) {
		switch (nivell) {
		case 1:
			return new int[] { 8, 8, 10 };
		case 2:
			return new int[] { 16, 16, 40 };
		case 3:
			return new int[] { 16, 30, 99 };
		case 4:
			System.out.println("De quantes files vols que sigui el tauler?");
			int files = llegirFilaColumna();
			System.out.println("De quantes columnes vols que sigui el tauler?");
			int columnes = llegirFilaColumna();
			System.out.println("Quantes mines vols que hi hagi?");
			int mines = llegirMines(files, columnes);
			return new int[] { files, columnes, mines };
		default:
			return new int[] { 8, 8, 10 };

		}

	}

	/**
	 * Llegeix el número de fila o columna en cas de que el jugador trii la opció de
	 * nivell personalitzat. També comprova que sigui un número més gran que 0 i
	 * vàlid. En aquest cas el jugador tria les mides del tauler i la quantitat de
	 * mines.
	 *
	 * @return int la mida de files o columnes que tindrà el tauler.
	 */
	public static int llegirFilaColumna() {
		boolean correcte = false;
		while (!correcte) {
			try {
				int filaColumna = reader.nextInt();
				if (filaColumna > 0) {
					return filaColumna;
				} else {
					System.out.println("Ha de ser un número més gran que 0");
				}

			} catch (Exception e) {
				System.out.println("Atenció! Únicament es permet insertar números enters. ");
				reader.nextLine();
			}
		}
		return -1;
	}

	/**
	 * En cas que el jugador esculli opció personalitzada, ha de triar la quantitat
	 * de mines que vol que tingui el tauler. Es té en compte que la quantitat de
	 * mines no sigui més gran que la quantitat de posicions que té el tauler.
	 * 
	 * @param files    la quantitat de files que té el tauler
	 * @param columnes la quantitat de columnes que té el tauler
	 * @return int quantitat de mines escollia.
	 */
	public static int llegirMines(int files, int columnes) {
		boolean correcte = false;
		while (!correcte) {
			try {
				int mines = reader.nextInt();
				if (mines < files * columnes && mines > 0) {
					return mines;
				} else {
					System.out
							.println("No pot haver més mines que caselles en el tauler i ha d'haver al menys una mina");
				}
			} catch (Exception e) {
				System.out.println("Atenció! Únicament es permet insertar números enters. ");
				reader.nextLine();
			}
		}
		return -1;
	}

	/**
	 * Mostra un missatge d'error en cas que el jugador no hagi dit el seu nom ni
	 * hagi escollit el nivell de dificultat que vol.
	 */
	public static void mostraErrorNivellNoDefinit() {
		System.out.println(
				"No es pot realitzar aquesta opció sense haver definit el jugador i el nivell prèviament. Vés a Opcions.");
	}
}
