import java.util.Random;
import java.util.Scanner;

/**
 * Classe amb tots els mètodes que permeten jugar una partida i controlar si
 * s'ha guanyat o no.
 * 
 * @author Amina Khyat El Achlam
 * 
 *
 */
public class Joc {
	static Scanner reader = new Scanner(System.in);
	static int[][] mines; // el tauler que controla les mines
	static int[][] camp; // el tauler que es mostra a l'usuari

	/**
	 * Inicialitza el tauler que no veu el jugador amb la quantitat de mines que se
	 * li passa. També les posa en posicions escollides a l'atzar.
	 * 
	 * @param files    la quantitat de files que té el tauler
	 * @param columnes la quantitat de columnes que té el tauler
	 * @param numMines la quantitat de mines que s'han de col·locar dins del taulerº
	 */
	public static void inicialitzarMines(int files, int columnes, int numMines) {
		mines = new int[files][columnes];
		Random ran = new Random();
		int contMines = 0;
		while (contMines < numMines) {
			int x = ran.nextInt(files);
			int y = ran.nextInt(columnes);

			if (mines[x][y] != 1) {
				mines[x][y] = 1;
				contMines++;
			}

		}

	}

	/**
	 * Inicialitza totes les posicions del camp amb un 9.
	 * 
	 * @param files    quantitat de files que té el tauler
	 * @param columnes quantitat de columnes que té el tauler
	 */
	public static void inicialitzarCamp(int files, int columnes) {
		camp = new int[files][columnes];
		for (int i = 0; i < camp.length; i++) {
			for (int j = 0; j < camp[i].length; j++) {
				camp[i][j] = 9;
			}
		}
	}

	/**
	 * Mostra el tauler al jugador. Si la casella encara no està destapada mostra un
	 * '-' però si ho està i és una mina mostra un '@'.
	 */
	public static void visualitzarCamp() {
		for (int i = 0; i < camp.length; i++) {
			for (int j = 0; j < camp[i].length; j++) {
				if (camp[i][j] == 9) {
					System.out.print(" - ");
				} else if (camp[i][j] == -1) {
					System.out.print(" @ ");
				} else {
					System.out.print(" " + camp[i][j] + " ");
				}
				if (j != camp[i].length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i != camp[i].length - 1) {
				for (int k = 0; k < camp[i].length * 2; k++) {
					System.out.print("--");
				}
				System.out.println();
			}
		}
		System.out.println();
	}

	/**
	 * Mostra el tauler amb les posicions de les mines. Aquesta tauler només es
	 * mostra per fer les proves.
	 */
	public static void visualitzarMines() {
		for (int i = 0; i < mines.length; i++) {
			for (int j = 0; j < mines[i].length; j++) {
				System.out.print(" " + mines[i][j] + " ");
				if (j != mines[i].length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i != mines[i].length - 1) {
				for (int k = 0; k < camp[i].length * 2; k++) {
					System.out.print("--");
				}
				System.out.println();
			}
		}
		System.out.println();
	}

	/**
	 * Demana al jugador les coordenades de la casella que vol destapar. Les retona
	 * restan un 1 perquè les posicions del tauler comencen per 0.
	 * 
	 * @return un array de dues posicions, la posició 0 fa referència a la fila, i
	 *         la posició 1 a la columna
	 */
	public static int[] demanarCoordenades() {
		System.out.println("Escriu la fila");
		int x = comprovarCoordenadaFila(camp);
		System.out.println("Escriu la columna");
		int y = comprovarCoordenadaCol(camp);

		return new int[] { x - 1, y - 1 };
	}

	/**
	 * Llegeix i comprova que la fila a la que es vol accedir és vàlida.
	 * 
	 * @param camp el tauler que es mostra al jugador
	 * @return int el número de fila
	 */
	public static int comprovarCoordenadaFila(int[][] camp) {

		boolean correcte = false;
		while (!correcte) {
			try {
				int n = reader.nextInt();
				if (n <= 0 || n > camp.length) {
					System.out.println("Valor no vàlid. Ha de ser un número més gran que 0 i més petit o igual que "
							+ camp.length);
				} else {
					return n;
				}
			} catch (Exception e) {
				System.out.println("Atenció! Únicament es permet insertar números enters. ");
				reader.nextLine();
			}
		}
		return -1;
	}

	/**
	 * Llegeix i comprova que la columna a la que es vol accedir és vàlida.
	 * 
	 * @param camp el tauler que es mostra al jugador
	 * @return int el número de columna
	 */
	public static int comprovarCoordenadaCol(int[][] camp) {
		boolean correcte = false;
		while (!correcte) {
			try {
				int n = reader.nextInt();
				if (n <= 0 || n > camp[0].length) {
					System.out.println("Valor no vàlid. Ha de ser un número més gran que 0 i més petit o igual que "
							+ camp[0].length);
				} else {
					return n;
				}
			} catch (Exception e) {
				System.out.println("Atenció! Únicament es permet insertar números enters. ");
				reader.nextLine();
			}
		}
		return -1;
	}

	/**
	 * Comprova si a les coordenades escollides hi ha una mina o no. En el cas que
	 * no hi hagi, comprova el número de mines que té al voltant la casella en
	 * qüestió i la mostra.
	 * 
	 * @param mines       el tauler amb les mines que no veu el jugador.
	 * @param coordenades array amb les coordenades escollides pel jugador
	 * @param camp        el tauler que veu el jugador
	 * @return retorna la quantitat de mines que hi ha al voltant de les coordenades
	 *         escollides.
	 */
	public static int descobrir(int[][] mines, int[] coordenades, int[][] camp) {
		int x = coordenades[0];
		int y = coordenades[1];
		int quantitatMines = 0;

		if (mines[x][y] == 1) {
			System.out.println("BOOOM!");
			camp[x][y] = -1;
			return -1;
		} else {
			if ((x - 1 >= 0) && mines[x - 1][y] == 1) {
				quantitatMines++;
			}
			if ((y - 1 >= 0) && mines[x][y - 1] == 1) {
				quantitatMines++;
			}
			if ((y + 1 < mines[0].length) && mines[x][y + 1] == 1) {
				quantitatMines++;
			}
			if ((x + 1 < mines.length && y + 1 < mines[0].length) && mines[x + 1][y + 1] == 1) {
				quantitatMines++;
			}
			if ((x + 1 < mines.length && y - 1 >= 0) && mines[x + 1][y - 1] == 1) {
				quantitatMines++;
			}
			if ((x + 1 < mines.length) && mines[x + 1][y] == 1) {
				quantitatMines++;
			}
			if ((x - 1 >= 0 && y - 1 >= 0) && mines[x - 1][y - 1] == 1) {
				quantitatMines++;
			}
			if ((x - 1 >= 0 && y + 1 < mines[0].length) && mines[x - 1][y + 1] == 1) {
				quantitatMines++;
			}
			camp[x][y] = quantitatMines;
			return quantitatMines;
		}

	}

	/**
	 * Crida a tots els mètodes necessaris per jugar una partida i determina si s'ha
	 * guanyat o s'ha perdut.
	 * 
	 * @param midaTauleriMines array de 2 posicions amb la mida del tauler
	 * @return
	 *         <ul>
	 *         <li>true: si ha guanyat la partida</li>
	 *         <li>false: si ha perdut la partida</li>
	 *         </ul>
	 */
	public static boolean jugar(int[] midaTauleriMines) {
		int files = midaTauleriMines[0];
		int columnes = midaTauleriMines[1];
		int numeroMines = midaTauleriMines[2];

		inicialitzarMines(files, columnes, numeroMines);
		inicialitzarCamp(files, columnes);
		// visualitzarMines(); // visualitzem camp de mines per les proves
		visualitzarCamp();
		boolean partidaEnCurs = true;

		while (partidaEnCurs) {
			int[] coordenades = demanarCoordenades();
			int destapatMina = descobrir(mines, coordenades, camp);
			visualitzarCamp();
			if (destapatMina == -1) {
				partidaEnCurs = false;
				System.out.println("Has perdut la partida");
				return false;
			} else {
				boolean haGuanyat = comprovarSiHaGuanyat(numeroMines, camp);
				if (haGuanyat) {
					System.out.println("FELICITATS, HAS GUANYAT LA PARTIDA!");
					partidaEnCurs = false;
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Comprova si el jugador ha guanyat la partida, tenint en compte que la
	 * quantitat de caselles sense descobrir és la mateixa que la quantitat de mines
	 * que hi ha en el tauler
	 * 
	 * @param mines la quantitat de mines que hi ha en el tauler
	 * @param camp  el tauler que veu el jugador.
	 * @return boolean
	 *         <ul>
	 *         <li>true: si la quantitat de caselles sense descrobrir és la mateixa
	 *         que la quantiat de mines, per tant ha guanyat</li>
	 *         <li>false: si la quantitat de caselles sense descrobrir és diferent
	 *         que la quantiat de mines, per tant no ha guanyat</li>
	 *         </ul>
	 * 
	 */
	public static boolean comprovarSiHaGuanyat(int mines, int[][] camp) {

		int casellesSenseDescobrir = 0;

		for (int i = 0; i < camp.length; i++) {
			for (int j = 0; j < camp[i].length; j++) {
				if (camp[i][j] == 9) {
					casellesSenseDescobrir++;
				}
			}
		}

		return casellesSenseDescobrir == mines;
	}

}
