/*
 * Programación Interactiva. 
 * Autor: Carolain Jimenez Bedoya - 2071368 
 * Mini-proyecto 2: Arca de Noé. 
 */

package ArcaDeNoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import misComponentes.Titulos;



// TODO: Auto-generated Javadoc
/**
 * The Class VistaGUI. Es la clase que genera la interfaz grafica 
 */
public class VistaGUI extends JFrame {


	/** The Constant columna. */
	public static final int fila= 3, columna= 4;

	/** The juego. */
	private JLabel puntos,ronda,juego;

	/** The panel 2. */
	private JPanel centralPanel, panelBotones, panelRecuento,panelInicial,panel2;

	/** The inicio. */
	private JButton salir, inicio;

	/** The escucha. */
	private Escuchas escucha;

	/** The valor ronda. */
	private JTextField valorPuntos,valorRonda;

	/** The objeto mando. */
	private LogicaArcaNoe objetoMando;

	/** The botones. */
	private String [] botones = { " Sí deseo volver a jugar", "No lo deseo"};

	/** The boton 1. */
	private ArrayList<Cartas> boton1= new ArrayList<Cartas>();
	

	/**
	 * Instantiates a new vista GUI. pongo la ventana Default
	 */
	public VistaGUI() {

		initGUI();  

		this.setUndecorated(true);
		this.pack();
		this.setBackground(new Color (250, 215, 160 ));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Inits the GUI.Donde se crean los componentes gráficos
	 */
	private void initGUI() {
		//Define el contenedor y layaout

		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contenedor = new GridBagConstraints();

		//crear el escucha
		escucha = new Escuchas();
		objetoMando = new LogicaArcaNoe();

		//Titulos
		Titulos titulo =new Titulos("Arca de Noé",30,Color.BLACK);
		contenedor.gridx=0;
		contenedor.gridy=0;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.HORIZONTAL;
		add(titulo,contenedor); 

		//Panel inicial 

		panelInicial = new JPanel();
		panelInicial();

		contenedor.gridx=0;
		contenedor.gridy=2 ;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(panelInicial,contenedor);

		//panelRecuento
		panelRecuento= new JPanel();
		panelRecuento.setLayout(new GridLayout(2,2));
		puntos = new JLabel("Puntos");

		valorPuntos= new JTextField(2);
		valorPuntos.setEditable(false); 

		ronda= new JLabel("Ronda");
		valorRonda= new JTextField(2);
		valorRonda.setEditable(false);

		panelRecuento.add(puntos);
		panelRecuento.add(ronda);
		panelRecuento.add(valorPuntos);
		panelRecuento.add(valorRonda);
		panelRecuento.setBackground(new Color (16, 161, 161));

		contenedor.gridx=0;
		contenedor.gridy=1 ;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;

		panelRecuento.setVisible(false);
		add(panelRecuento,contenedor); 


		//Panel central
		centralPanel = new JPanel();
		centralPanel.setLayout(new GridLayout(fila,columna));

		objetoMando.darImagenes();

		for(int i=0; i< 12;i++) { //inicializo los cuadros totales del juego y sus condiciones
			Cartas carta=new Cartas();


			carta.setEnabled(false);
			carta.setVisible(false);


			carta.addMouseListener(escucha);
			boton1.add(carta);

			centralPanel.add(carta);
		}

		for(int i=0; i<objetoMando.cartasEnElArray() ;i++) {//Hace visibles los cuadros que se necesitan en cada instancia del juego 



			boton1.get(i).setNumero(objetoMando.getCartas().get(i).getBotonAPulsar()); //envia la imagen
			boton1.get(i).reiniciarContador();
			boton1.get(i).mostrarOcultar(0);
			boton1.get(i).setVisible(true);
			boton1.get(i).setEnabled(true);


		}

		contenedor.gridx=0;
		contenedor.gridy=2 ;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;

		centralPanel.setVisible(false);

		add(centralPanel, contenedor);




		//panelBotones


		panelBotones= new JPanel();



		salir = new JButton ("Salir");
		salir.setPreferredSize(new Dimension(700,30));
		salir.setBackground(new Color(34, 153, 84));
		salir.addActionListener(escucha);
		panelBotones.add(salir);

		inicio = new JButton ("Inicio");
		inicio.setPreferredSize(new Dimension(100,30));
		inicio.addActionListener(escucha);
		inicio.setBackground(new Color(245, 176, 65));
		panelBotones.add(inicio);


		contenedor.gridx=0;
		contenedor.gridy=3;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(panelBotones,contenedor); 




	}




	/**
	 * The Class Escuchas.Es la clase que contiene los escuchas del mouse y del ActionListener
	 */
	private class Escuchas extends MouseAdapter implements ActionListener{

		/**
		 * Action performed.Cuandoun elemento es clickeado
		 *
		 * @param eventAction the event action
		 */
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub

			if(eventAction.getSource()== salir) {
				String mensaje = "Puntuación: "+ objetoMando.getPunto()+ "\n¿Deseas volver a jugar?,Es un juego muy divertido ";

				int result= JOptionPane.showOptionDialog (null, mensaje, "Resultado", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);

				if(result == JOptionPane.YES_OPTION) {

					PrincipalArcaNoe.main(null); //reinicia el juego automaticamente 
				}


				if(result == JOptionPane.NO_OPTION) {
					System.exit(0); 
				}


			}


			if (eventAction.getSource()== inicio) {
				panelRecuento.setVisible(true);
				panelInicial.setVisible(false);
				centralPanel.setVisible(true);
				inicio.setVisible(false);

				ventanaEmergente();


				valorPuntos.setText(String.valueOf(objetoMando.getPunto()));
				valorRonda.setText(String.valueOf(objetoMando.getRonda()));


			}

		}


		/**
		 * Mouse clicked.un elemento es clickeado y el mouse lo reconoce
		 *
		 * @param eventMouse the event mouse
		 */
		@Override
		public void mouseClicked(MouseEvent eventMouse) {


			Cartas boton = (Cartas)eventMouse.getSource(); 
			boton.mostrarOcultar(1);
			boton.obtenerContador();
			objetoMando.verificar(boton, boton1);
			determinar();
			valorPuntos.setText(String.valueOf(objetoMando.getPunto()));
			valorRonda.setText(String.valueOf(objetoMando.getRonda()));
			ventanaEmergente(); 

		}


	}



	/**
	 * Ventana emergente. Cuando la ronda es 12 le digo al usuario que ganó y si desea volver a jugar
	 */
	public void ventanaEmergente() {

		if ( objetoMando.getRonda()==12 ) {

			String mensaje1 = "Felicidades, has ganado\n"+" su putuación es: "+ objetoMando.getPunto()+ "\n¿Deseas volver a jugar?,Es un juego muy divertido ";

			int result= JOptionPane.showOptionDialog (null, mensaje1, "Resultado", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);

			if(result == JOptionPane.YES_OPTION) {

				PrincipalArcaNoe.main(null);
			}
			if(result == JOptionPane.NO_OPTION) {
				System.exit(0); 
			}


		}else if(objetoMando.getRonda() >2 && objetoMando.getPunto()==0) {
			String mensaje2 = "Perdiste :(( \n"+" su putuación es: "+ objetoMando.getPunto()+ "\n¿Deseas volver a jugar?, Es un juego muy divertido ";
			int result= JOptionPane.showOptionDialog (null, mensaje2, "Resultado", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);

			if(result == JOptionPane.YES_OPTION) {

				PrincipalArcaNoe.main(null);
			}
			if(result == JOptionPane.NO_OPTION) {
				System.exit(0); 
			}
		}

	}





	/**
	 * Determinar. funcion que sigue la logica del juego luego de que todas los botones han sido destapados
	 */
	public void determinar() {

		if(objetoMando.cartasDestapadas()==true) {

			objetoMando.aumentarRonda();
			objetoMando.ceroContador();

			if(objetoMando.getRonda()<6) {
				objetoMando.añadirBotones(); 
			}


			objetoMando.darImagenes();

			for(int i=0; i<objetoMando.cartasEnElArray() ;i++) {


				boton1.get(i).setNumero(objetoMando.getCartas().get(i).getBotonAPulsar());
				boton1.get(i).reiniciarContador();
				boton1.get(i).mostrarOcultar(0);
				boton1.get(i).setVisible(true);
				boton1.get(i).setEnabled(true);


			}
		}



	}


	/**
	 * Panel inicial. Establezco las imagenes iniciales 
	 */
	public void panelInicial() {

		panelInicial.setPreferredSize(new Dimension(800,600));
		juego= new JLabel();
		ImageIcon icon = new ImageIcon("src/ImagenesArca/gif.gif"); 
		
		juego.setIcon(icon);
		panelInicial.add(juego);

	}




}


