 /**
 * 
 * @author Amina Khyat El Achlam
 * 
 * @version Feb/2021
 * 
 * Implementació del joc Buscamines.
 *
 */
public class Programa {

	public static void main(String[] args) {
		int opcio;
		int posicioJugador=0;
		boolean nivellJugadorDefinit= false;
		int[] midaTauleriMines = null;
		
		do {
			Menu.mostrar();
			opcio = Menu.llegirOpcioMenu();
			switch (opcio) {
			case 1:
				Ajuda.mostrarInstruccions();
				break;
				
			case 2:
				posicioJugador = Jugador.definir();
				Opcions.mostrarOpcionsNivell();
				int nivell = Opcions.llegirOpcioNivell();
				midaTauleriMines = Opcions.opcio(nivell);
				
				nivellJugadorDefinit = true;
				break;
				
			case 3:
				if (nivellJugadorDefinit) {
					boolean haGuanyat = Joc.jugar(midaTauleriMines);
					if (haGuanyat) {
						Jugador.incrementarVictories(posicioJugador);
					}
				} else {
					Opcions.mostraErrorNivellNoDefinit();
				}
				
				break;
				
			case 4:
				Jugador.mostrarRanking();
				break;
				
			case 0:
				System.out.println("Sortim del programa");
				break;

			}

		} while (opcio != 0);

	}

}