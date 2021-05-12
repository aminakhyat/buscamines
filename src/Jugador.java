import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe amb tots els mètodes per definir i controlar el jugador.
 * 
 * @author Amina Khyat El Achlam
 */

public class Jugador {

	static Scanner reader = new Scanner(System.in);
	static ArrayList<String> nomsJugadors = new ArrayList<>();
	static ArrayList<Integer> partidesGuanyades = new ArrayList<>();

	/**
	 * Demana el nom del jugador i el guarda. Si existeix el busca, sinó el guarda i
	 * el selecciona directament per jugar la partida.
	 * 
	 * @return int la posició del jugador dintre de l'array on estan guardats tots
	 *         els jugadors.
	 */
	public static int definir() {
		System.out.println("Escriu el nom del jugador/a: ");
		String nom = reader.next();
		nom = nom.toLowerCase();
		if (existeix(nomsJugadors, nom)) {
			return nomsJugadors.indexOf(nom);
		} else {
			nomsJugadors.add(nom);
			partidesGuanyades.add(0);
			return nomsJugadors.indexOf(nom);
		}
	}

	/**
	 * Determina si l'usuari ja està guardar en l'array dels jugadors o no.
	 * 
	 * @param nomsJugadors array on es guarden tots els jugadors.
	 * @param nom          del jugador que volem comprovar.
	 * @return boolean
	 *         <ul>
	 *         <li>true: si ja està guardat</li>
	 *         <li>false: si no està guardat</li>
	 *         </ul>
	 */
	public static boolean existeix(ArrayList<String> nomsJugadors, String nom) {
		if (nomsJugadors.indexOf(nom) != -1) {
			return true;
		}

		return false;
	}

	/**
	 * Cada vegada que un jugador guanya una partida incrementa el seu número de
	 * victòries perquè puguin ser visibles en el raking.
	 * 
	 * @param posicioJugador la posició on està guardat el nom del jugador en
	 *                       l'array dels jugadors.
	 */
	public static void incrementarVictories(int posicioJugador) {
		int victories = partidesGuanyades.get(posicioJugador);
		partidesGuanyades.set(posicioJugador, victories + 1);
	}

	/**
	 * Mostra el ranking amb el nom de tots els jugadors guardats i les partides que
	 * han guanyat.
	 */
	public static void mostrarRanking() {
		if (nomsJugadors.isEmpty()) {
			System.out.println("Encara no hi ha cap jugador definit");
		} else {
			System.out.println("==========================");
			for (int i = 0; i < nomsJugadors.size(); i++) {
				System.out.println("NOM DEL JUGADOR/A: " + nomsJugadors.get(i));
				System.out.println("PARTIDES GUANYADES: " + partidesGuanyades.get(i));
				System.out.println("==========================");
			}
		}

	}
}
