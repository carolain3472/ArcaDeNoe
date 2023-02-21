/*
 * Programación Interactiva. 
 * Autor: Carolain Jimenez Bedoya - 2071368 
 * Mini-proyecto 2: Arca de Noé. 
 */

package ArcaDeNoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


// TODO: Auto-generated Javadoc
/**
 * The Class LogicaArcaNoe.Es la clase donde se establecen las reglas del juego, es decir donde se utilizará el juego por medio de la interfaz
 */
public class LogicaArcaNoe {

	/** The cartas. */
	private ArrayList<Cartas> cartas = new ArrayList<Cartas>(); //cuadros visibles 

	/** The diseños animales. total imagenes*/
	private ArrayList<Integer> diseñosAnimales = new ArrayList<Integer>();

	/** The cantidad. */
	private int identificador1, identificador2,contador,punto, ronda,fichaAl,comparacion,botonPulsado1,botonPulsado2,cantidad,click1,click2;

	/** The iguales. */
	private boolean gano,iguales;


	/**
	 * Instantiates a new logica arca noe. Es el constructor de la clase
	 */
	public LogicaArcaNoe() {  

		punto=0;
		ronda=1;
		identificador1=0;
		identificador2=0;
		contador=0;


		for(int i=0;i<4; i++ ) { //inician siendo 4 botones dentro del juego
			Cartas nuevaCarta= new Cartas();
			cartas.add(nuevaCarta);

		}


		for(int i=1;i<=15; i++ ) { //son 15 imagenes totales
			diseñosAnimales.add(i);

		}


	}

	/**
	 * Obtener ficha aleatoria.obtiene un numero aleatorio del 1  al 15
	 *
	 * @return the int, retorna un numero
	 */
	public int obtenerFichaAleatoria() {
		Random aleatorio = new Random();
		fichaAl= aleatorio.nextInt(15)+1;
		return fichaAl;
	}



	/**
	 * Añadir botones.
	 *
	 * @return the array list, añade 2 botones a la lista de botones visibles 
	 */
	public ArrayList<Cartas> añadirBotones() { 
		//cartas.clear();
		cartas.add(new Cartas());
		cartas.add(new Cartas()); 



		return cartas;

	}


	/**
	 * Dar imagenes. escoge un numero aleatorio, lo repite, lo borra del array disponible para que no sea usado de nuevo, y asi sucesivamente hasta completar el total de cartas
	 */
	public void darImagenes() {


		for(int i = 0; i<cartas.size(); i+=2) {

			int cualDiseño = obtenerFichaAleatoria();


			if(diseñosAnimales.contains(cualDiseño)) {
				diseñosAnimales.remove(diseñosAnimales.indexOf(cualDiseño)); 

			}else {
				while(!diseñosAnimales.contains(cualDiseño)) { 
					cualDiseño = obtenerFichaAleatoria();

				}
				diseñosAnimales.remove(diseñosAnimales.indexOf(cualDiseño)); 


			}

			cartas.get(i).setNumero(cualDiseño);
			cartas.get(i+1).setNumero(cualDiseño);	

		}

		revolverArray();

		diseñosAnimales.clear(); 

		for (int i=1;i<=15; i++) {

			diseñosAnimales.add(i); //Guarda dentro de un array los numeros disponibles 
		}



	}


	/**
	 * Cartas en el array. Cantidad de cuadros visibles que hay en el juego
	 *
	 * @return the int la cantidad de cuadros visibles 
	 */
	public int cartasEnElArray() {
		cantidad= cartas.size();
		// System.out.println(cantidad); 
		return cantidad;

	}


	/**
	 * Revolver array. Revuelve una lista, para poner sus posiciones aleatorias 
	 */
	public void revolverArray(){
		Collections.shuffle(cartas);

	}



	/**
	 * Verificar.Evalua si dos fichas clickeadas son iguales, o distintas, de acuerdo a la imagen y a su posicion
	 *
	 * @param carta the carta Son las cartas que son clickeadas por el usuario
	 * @param array the array Establece el array de cartas disponibles
	 * @return true, if successful
	 */
	public boolean verificar(Cartas carta, ArrayList<Cartas> array){

		comparacion+=1; 
		carta.mostrarOcultarImg(1);

		if(comparacion%2!=0){

			botonPulsado1 = carta.getBotonAPulsar();
			identificador1 =array.indexOf(carta); 
			click1= carta.obtenerContador();
		}else{

			botonPulsado2 = carta.getBotonAPulsar();
			identificador2 = array.indexOf(carta); 
			click2= carta.obtenerContador();

			if(botonPulsado1 == botonPulsado2 && identificador1 != identificador2 ){ 

				iguales=true;
				punto+=1;
				contador+=1;
				carta.setEnabled(false);
				array.get(identificador1).setEnabled(false);
			}else{

				iguales=false;
				perderPunto();
				carta.mostrarOcultarImg(0);
				array.get(identificador1).mostrarOcultarImg(0);
			}
		}
		return iguales;
	}




	/**
	 * Perder punto. pierde un punto si las cartas han sido clickeadas más de una vez y no son iguales
	 *
	 * 
	 */
	public void perderPunto( ) {
		if (click1>1 || click2>1 && iguales==false) {
			punto--;
		}
	}



	/**
	 * Gets the punto.Retorna el punto
	 *
	 * @return the punto
	 */
	public int getPunto() {
		return punto;

	}





	/**
	 * Cartas destapadas. determina si todos los botones han sido destapados
	 *
	 * @return true, if successful
	 */
	public boolean cartasDestapadas(){
		boolean gano=false;
		if(contador == (cartas.size()/2)){ 
			gano = true;
		}

		return gano;
	}




	/**
	 * Cero contador. vuelve a poner el contador en cero
	 */
	public void ceroContador() {
		contador=0;

	}


	/**
	 * Aumentar ronda. aumenta la ronda en uno
	 */
	public void aumentarRonda() {
		ronda+=1;   

	}


	/**
	 * Gets the ronda. Adquiere en que ronda del juego se está
	 *
	 * @return the ronda
	 */
	public int getRonda() {
		return ronda;

	}



	/**
	 * Gets the cartas.Obtiene un array de botones
	 *
	 * @return the cartas
	 */
	public ArrayList<Cartas> getCartas() { 


		return cartas;


	}


}


