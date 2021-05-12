/**
 * Mostra les instruccions del joc
 * 
 * @author Amina Khyat El Achlam
 * 
 */
public class Ajuda {

	/**
	 * Mostra les instruccions del joc
	 */
	public static void mostrarInstruccions() {
		System.out.println("****  Instruccions Buscamines ******");
		System.out.println("----------------------------------------------");
		System.out.println("Participa un únic jugador, descobrint una casella cada torn.");
		System.out.println("Mina: el programa finalitza i la partida s’ha perdut.");
		System.out.println(
				"Número entre 1 i 8: indica quantes caselles contigües contenen una mina. El jugador pot continuar jugant.");
		System.out.println("Buit (aigua): Casella que no té cap mina al seu voltant.");
		System.out.println(
				"Al destaparla provoca que es destapin automàticament totes les caselles contigües de forma ramificada fins que arriba a caselles on hi hagi un número.");
		System.out.println(
				"El joc acaba quan les úniques caselles sense descobrir siguin les mines, o bé quan el jugador descobreix una mina (BOOM!).");
		System.out.println("----------------------------------------------");
	}

}
