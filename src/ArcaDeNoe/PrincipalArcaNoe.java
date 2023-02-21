
/*
 * Programación Interactiva. 
 * Autor: Carolain Jimenez Bedoya - 2071368 
 * Mini-proyecto 2: Arca de Noé. 
 */

package ArcaDeNoe;

import java.awt.EventQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class PrincipalArcaNoe.Es la clase que contiene el metodo main()
 */
public class PrincipalArcaNoe {

	/**
	 * The main method.Ejecuta el programa
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run () {

				VistaGUI myVista= new VistaGUI();
			}
		});	


	}

}
