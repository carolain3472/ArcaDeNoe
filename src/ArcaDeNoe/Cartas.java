/*
 * Programación Interactiva. 
 * Autor: Carolain Jimenez Bedoya - 2071368 
 * Mini-proyecto 2: Arca de Noé. 
 */


package ArcaDeNoe;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


// TODO: Auto-generated Javadoc
/**
 * The Class Cartas. clase que me brinda los atributos de cada boton
 */
public class Cartas extends JButton {



	/** The Constant tamaño. */
	private static final int tamaño=180;

	/** The contador. */ //numero de la imagen, contador
	private int  botonAPulsar,contador; 

	/** The imagen. */ //Respectivas imagenes del reverso y del boton
	private ImageIcon  image, imagen; 

	



	/**
	 * Instantiates a new cartas. Constructor de la clase
	 */
	public Cartas(){
		setPreferredSize(new Dimension(tamaño,tamaño));	
		contador=0;

	} 



	/**
	 * Sets the numero. Recibe el numero que indica la imagen que tendra el boton y como se llamara el boton 
	 *
	 * @param numero the new numero  devuelve un numero
	 */
	public void setNumero(int numero){

		botonAPulsar= numero; 

	}


	/**
	 * Sets the imagen. Asigna la imagen correspondiente al numero indicado anteriormente
	 */

	public void  setImagen(){

		image = new ImageIcon("src/ImagenesArca/"+ botonAPulsar +".jpg");
		setIcon(image);


	}


	/**
	 * Gets the boton A pulsar. contiene el numero para la imagen del boton
	 *
	 * @return the boton A pulsar, devuelve un numero
	 */
	public int getBotonAPulsar () {
		return botonAPulsar;
	}





	/**
	 * Sets the imagen reverso. asigna la imagen del reverso 
	 */
	public void setImagenReverso(){

		imagen = new ImageIcon("src/ImagenesArca/Reverso1.jpg");
		setIcon(imagen);

	}




	/**
	 * Mostrar ocultar.Establece la imagen inicial del juego, cuenta cuantas veces ha sido clickeado un boton
	 *
	 * @param mostrarOcultar 0: imagen inicial, 1: veces que se abre la carta
	 */
	public void mostrarOcultar(int mostrarOcultar) {
		//0: imagen inicial, 1: veces que se abre la carta

		switch (mostrarOcultar) {
		case 0:  setImagenReverso();
		break;
		case 1: 
			contador+=1;
			break;
		}

	}




	/**
	 * Mostrar ocultar img. es un hilo que me da un tiempo de espera para voltear las cartas una vez que estas no sean iguales, y cuando se de click en una, se muestre la imagen almacenada
	 *
	 * @param mostrarOcultar the mostrar ocultar, si muestra u oculta la carta
	 */
	public void mostrarOcultarImg(int mostrarOcultar){
		// mostrar=1 y ocultar=0

		Thread hilo = new Thread(){
			public synchronized void run(){
				try {
					switch (mostrarOcultar) {
					// Si las imagenes no son iguales, espera para luego voltearlas
					case 0: sleep(500); break;
					case 1: setImagen();
					sleep(5);


					break;

					}
					if(mostrarOcultar==0){
						setImagenReverso();

					}
				}catch (InterruptedException excepcion){
					excepcion.printStackTrace();
				}
			}
		};hilo.start();
	}


	/**
	 * Obtener contador. Obtiene cuantas veces se ha abierto una carta
	 *
	 * @return the int retorna contador
	 */
	public int obtenerContador() {
		return contador;

	}

	/**
	 * Reiniciar contador.Pone el contador en 0, para que cuando se pase de ronda, los botones no almacenen las veces clickeados anteriormente
	 *
	 * @return the int retorna contador
	 */
	public int reiniciarContador() {  
		contador=0;
		return contador;

	}

}	

