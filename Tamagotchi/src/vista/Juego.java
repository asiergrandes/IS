package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import modelo.Tamagotchi;
import packagevista.minijuego;

import javax.swing.JSeparator;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;



@SuppressWarnings("deprecation")
public class Juego extends JFrame implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel evo;
	private JPanel tamagotchi;
	private JPanel comida;
	private JPanel vida;
	private JLabel evolucion;
	private JLabel puntuacion;
	private JButton exit;
	private JPanel candysoup;
	private JLabel cora4;
	private JLabel cora2;
	private JLabel cora1;
	private JLabel cora3;
	private JLabel comida4;
	private JLabel comida1;
	private JLabel comida2;
	private JLabel comida3;
	private JLabel icono;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JSeparator separator_1;
	private Controler controler = null;
	private int vidarestante = 0;
	private int hambrerestante = 0;
	private int score = 0;
	private JButton objCandy;
	private JButton objSoup;
	private JButton caca;
	private JButton enfermucho;
	private static Juego miJuego = new Juego();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego frame = new Juego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private Juego() {
		getContentPane().setBackground(Color.BLACK);
		setBounds(100, 100, 611, 434);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getEvo(), BorderLayout.NORTH);
		getContentPane().add(getTamagotchi(), BorderLayout.CENTER);
		getContentPane().add(getComida(), BorderLayout.EAST);
		getContentPane().add(getVida(), BorderLayout.WEST);
		getContentPane().add(getCandysoup(), BorderLayout.SOUTH);
		Tamagotchi tama = Tamagotchi.getTamagotchi();
		tama.addObserver(this);
	}
	
	public static Juego getJuego() {
		return miJuego;
	}
	
	private JPanel getEvo() {
		if (evo == null) {
			evo = new JPanel();
			evo.setBackground(Color.BLACK);
			evo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			evo.add(getEvolucion());
			evo.add(getPuntuacion());
			evo.add(getExit());
		}
		return evo;
	}
	private JPanel getTamagotchi() {
		if (tamagotchi == null) {
			tamagotchi = new JPanel();
			tamagotchi.setBackground(Color.BLACK);
			tamagotchi.setLayout(new BorderLayout(0, 0));
			tamagotchi.add(getIcono(), BorderLayout.CENTER);
			tamagotchi.add(getCaca(), BorderLayout.WEST);
			tamagotchi.add(getEnfermucho(), BorderLayout.EAST);
		}
		return tamagotchi;
	}
	private JPanel getComida() {
		if (comida == null) {
			comida = new JPanel();
			comida.setBackground(Color.BLACK);
			comida.setLayout(new GridLayout(4, 4, 0, 0));
			comida.add(getComida4());
			comida.add(getComida3());
			comida.add(getComida2());
			comida.add(getComida1());
		}
		return comida;
	}
	private JPanel getVida() {
		if (vida == null) {
			vida = new JPanel();
			vida.setBackground(Color.BLACK);
			vida.setLayout(new GridLayout(4, 4, 0, 0));
			vida.add(getCora4());
			vida.add(getCora3());
			vida.add(getCora2());
			vida.add(getCora1());
		}
		return vida;
	}
	private JLabel getEvolucion() {
		if (evolucion == null) {
			evolucion = new JLabel("Egg");
			evolucion.setForeground(Color.WHITE);
		}
		return evolucion;
	}
	private JLabel getPuntuacion() {
		if (puntuacion == null) {
			puntuacion = new JLabel("Score: " + this.score);
			puntuacion.setForeground(Color.WHITE);
		}
		return puntuacion;
	}
	private JButton getExit() {
		if (exit == null) {
			exit = new JButton("exit");
			exit.setBackground(Color.BLACK);
			exit.setForeground(Color.DARK_GRAY);
			exit.addActionListener(getControler());
			
		}
		return exit;
	}
	private JPanel getCandysoup() {
		if (candysoup == null) {
			candysoup = new JPanel();
			candysoup.setBackground(Color.BLACK);
			candysoup.setLayout(new GridLayout(0, 3, 0, 0));
			candysoup.add(getLblNewLabel());
			candysoup.add(getSeparator());
			candysoup.add(getLblNewLabel_1());
			candysoup.add(getObjCandy());
			candysoup.add(getSeparator_1());
			candysoup.add(getObjSoup());
		}
		return candysoup;
	}
	
	
	private JButton getObjCandy() 
	{
		if (objCandy == null) {
    	objCandy = new JButton("candy");
    	 // Configurar el layout del botón para que pueda contener varias imágenes
        objCandy.setLayout(new BorderLayout());   
        
        // ImageIcon icon = new ImageIcon(getClass().getResource("/vista/fotos/candy.png"));
        //objCandy.setIcon(icon);
        objCandy.setBackground(Color.BLACK);
        objCandy.addActionListener(getControler());
        //
        objCandy.setBackground(Color.BLACK);
        objCandy.setBorderPainted(false);
        objCandy.setForeground(objCandy.getBackground());
        objCandy.setFocusPainted(false);
        objCandy.setBorder(BorderFactory.createEmptyBorder());
		}
		return objCandy;
    }

	
	private JButton getObjSoup() {
		if (objSoup == null) {
			objSoup = new JButton("soup");
	    	 // Configurar el layout del botón para que pueda contener varias imágenes
			objSoup.setLayout(new BorderLayout());   
	        
	        // ImageIcon icon = new ImageIcon(getClass().getResource("/vista/fotos/candy.png"));
	        //objCandy.setIcon(icon);
			objSoup.setBackground(Color.BLACK);
			objSoup.addActionListener(getControler());
	        //
			objSoup.setBackground(Color.BLACK);
			objSoup.setBorderPainted(false);
			objSoup.setForeground(objSoup.getBackground());
			objSoup.setFocusPainted(false);
			objSoup.setBorder(BorderFactory.createEmptyBorder());
		}
		return objSoup;
	}
	
	private JLabel getCora4() {
		if (cora4 == null) {
			cora4 = new JLabel("");
			cora4.setHorizontalAlignment(SwingConstants.CENTER);
			cora4.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/heart.png")));
			cora4.setBackground(Color.BLACK);
		}
		return cora4;
	}
	private JLabel getCora2() {
		if (cora2 == null) {
			cora2 = new JLabel("");
			cora2.setHorizontalAlignment(SwingConstants.CENTER);
			cora2.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/heart.png")));
		}
		return cora2;
	}
	private JLabel getCora1() {
		if (cora1 == null) {
			cora1 = new JLabel("");
			cora1.setHorizontalAlignment(SwingConstants.CENTER);
			cora1.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/heart.png")));
		}
		return cora1;
	}
	private JLabel getCora3() {
		if (cora3 == null) {
			cora3 = new JLabel("");
			cora3.setHorizontalAlignment(SwingConstants.CENTER);
			cora3.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/heart.png")));
		}
		return cora3;
	}
	private JLabel getComida4() {
		if (comida4 == null) {
			comida4 = new JLabel("");
			comida4.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/hungry.png")));
		}
		return comida4;
	}
	private JLabel getComida1() {
		if (comida1 == null) {
			comida1 = new JLabel("");
			comida1.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/hungry.png")));
		}
		return comida1;
	}
	private JLabel getComida2() {
		if (comida2 == null) {
			comida2 = new JLabel("");
			comida2.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/hungry.png")));
		}
		return comida2;
	}
	private JLabel getComida3() {
		if (comida3 == null) {
			comida3 = new JLabel("");
			comida3.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/hungry.png")));
		}
		return comida3;
	}
	private JLabel getIcono() {
		if (icono == null) {
			icono = new JLabel("");
			icono.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/Egg1.png")));
			icono.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return icono;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Candy");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Soup");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_1;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setBackground(Color.BLACK);
			separator.setForeground(Color.BLACK);
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
			separator_1.setBackground(Color.BLACK);
			separator_1.setForeground(Color.BLACK);
		}
		return separator_1;
	}
	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	//--------------------------------------Controler------------------------------------
	private class Controler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			Tamagotchi tama = Tamagotchi.getTamagotchi();
			tama.gestionarBotones(e.getActionCommand());
		}
	}
	//--------------------------------------Controler------------------------------------

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof String) 
		{
			String mensaje = (String) arg;
			
			if (mensaje.equals("Cerrar juego")) 
			{
				setVisible(false);
				Menu menu = Menu.getMenu();
				menu.setVisible(true);
			}
			if (mensaje.equals("Juego iniciado")) {
				gestionarComida();
				gestionarCorazones();
				icono.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/Egg1.png")));
			}
			else if (mensaje.equals("Muerto")) 
			{		
				 //quitarle los corazones y sopas
				
				ImageIcon iconoMuerto = new ImageIcon(Juego.class.getResource("/vista/fotos/death.gif"));
			     icono.setIcon(iconoMuerto);
			}
			else if (mensaje.equals("Limpiado")) {		
				caca.setVisible(false);
			}
			else if (mensaje.equals("Curado"))
			{		
				enfermucho.setVisible(false);
			}
			else if (mensaje.equals("minijuego"))
			{		
				setVisible(false);
				minijuego mini = new minijuego();
				mini.setVisible(true);
			}
		
		}
		else if (arg instanceof Object[]) 
		{
			Object[] objectArray = (Object[]) arg;
			if (objectArray[0].equals("Actualizar puntuacion")) {
				int punt = (int) objectArray[1];
				puntuacion.setText("Score: " + punt);
			}
			else if (objectArray[0].equals("Tamagotchi evolucionado")) {
				evolucion.setText((String) objectArray[1]);
				if(((String) objectArray[1]).equals("Kuchipatchi")) {
					icono.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/Kuchipatchi1.png")));
				}
				else if(((String) objectArray[1]).equals("Mimitchi")) {
					icono.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/Mimitchi1.png")));
				}
				else if(((String) objectArray[1]).equals("Maskutchi")) {
					icono.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/Maskutchi1.png")));
				}
				else if(((String) objectArray[1]).equals("Mametchi")) {
					icono.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/Marutchi1.png")));
				}
			}
			else if (objectArray[0].equals("Actualizar corazones")) {
				vidarestante = (int) objectArray[1];
				gestionarCorazones();
			}
			else if (objectArray[0].equals("Actualizar comida")) {
				hambrerestante = (int) objectArray[1];
				gestionarComida();
			}
			else if (objectArray[0].equals("Cagado")) {
				//poner icono
				if (	((Boolean) objectArray[1]).equals(true)	)
				{
					caca.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/kk.png")));
					caca.setVisible(true);
				}
				
			}
			else if (objectArray[0].equals("Enfermado")) {
				//poner icono
				if (	((Boolean) objectArray[1]).equals(true)	)
				{
					enfermucho.setIcon(new ImageIcon(Juego.class.getResource("/vista/fotos/Virus.png")));
					enfermucho.setVisible(true);
				}
			}
			else if (objectArray[0].equals("Actualizar candys")) {


			    // Obtener el número actual de candys
			    int numC = (int) objectArray[1];
				
				
				if (numC==0)
				{
					//no hay ningun candy
					objCandy.removeAll();		        
			        objCandy.revalidate();
				    objCandy.repaint();
				}
				else if (numC==1)
				{
					objCandy.removeAll();	
					// Crear un panel para cada imagen y agregarlas al botón en diferentes posiciones
			        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			        panel1.setOpaque(false); // Hacer el panel transparente
			        JLabel imageLabel1 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/candy.png")));
			        panel1.add(imageLabel1);
			        objCandy.add(panel1, BorderLayout.WEST); // Agregar la primera imagen a la izquierda del botón
			        
			        objCandy.revalidate();
				    objCandy.repaint();
				}
				else if (numC==2)
				{
					objCandy.removeAll();	
					// Crear un panel para cada imagen y agregarlas al botón en diferentes posiciones
			        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			        panel1.setOpaque(false); // Hacer el panel transparente
			        JLabel imageLabel1 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/candy.png")));
			        panel1.add(imageLabel1);
			        objCandy.add(panel1, BorderLayout.WEST); // Agregar la primera imagen a la izquierda del botón
			        
			        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			        panel2.setOpaque(false); // Hacer el panel transparente
			        JLabel imageLabel2 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/candy.png")));
			        panel2.add(imageLabel2);
			        objCandy.add(panel2, BorderLayout.CENTER); // Agregar la segunda imagen al centro del botón
			        
			        
			        objCandy.revalidate();
				    objCandy.repaint();
				}
				else if (numC==3)
				{
				objCandy.removeAll();	
				// Crear un panel para cada imagen y agregarlas al botón en diferentes posiciones
		        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        panel1.setOpaque(false); // Hacer el panel transparente
		        JLabel imageLabel1 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/candy.png")));		 
		        panel1.add(imageLabel1);
		        objCandy.add(panel1, BorderLayout.WEST); // Agregar la primera imagen a la izquierda del botón
		        
		        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        panel2.setOpaque(false); // Hacer el panel transparente
		        JLabel imageLabel2 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/candy.png")));
		        panel2.add(imageLabel2);
		        objCandy.add(panel2, BorderLayout.CENTER); // Agregar la segunda imagen al centro del botón
		        
		        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        panel3.setOpaque(false); // Hacer el panel transparente
		        JLabel imageLabel3 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/candy.png")));
		        panel3.add(imageLabel3);
		        objCandy.add(panel3, BorderLayout.EAST); // Agregar la tercera imagen a la derecha del botón
		        
		        objCandy.revalidate();
			    objCandy.repaint();
				}
			}
			else if (objectArray[0].equals("Actualizar sopas")) {


			    // Obtener el número actual de sopas
			    int numS = (int) objectArray[1];
				
				
				if (numS==0)
				{
					//no hay ningun candy
					objSoup.removeAll();		        
					objSoup.revalidate();
					objSoup.repaint();
				}
				else if (numS==1)
				{
					objSoup.removeAll();	
					// Crear un panel para cada imagen y agregarlas al botón en diferentes posiciones
			        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			        panel1.setOpaque(false); // Hacer el panel transparente
			        JLabel imageLabel1 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/spoon.png")));
			        panel1.add(imageLabel1);
			        objSoup.add(panel1, BorderLayout.WEST); // Agregar la primera imagen a la izquierda del botón
			        
			        objSoup.revalidate();
			        objSoup.repaint();
				}
				else if (numS==2)
				{
					objSoup.removeAll();	
					// Crear un panel para cada imagen y agregarlas al botón en diferentes posiciones
			        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			        panel1.setOpaque(false); // Hacer el panel transparente
			        JLabel imageLabel1 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/spoon.png")));
			        panel1.add(imageLabel1);
			        objSoup.add(panel1, BorderLayout.WEST); // Agregar la primera imagen a la izquierda del botón
			        
			        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
			        panel2.setOpaque(false); // Hacer el panel transparente
			        JLabel imageLabel2 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/spoon.png")));
			        panel2.add(imageLabel2);
			        objSoup.add(panel2, BorderLayout.CENTER); // Agregar la segunda imagen al centro del botón
			        
			        
			        objSoup.revalidate();
			        objSoup.repaint();
				}
				else if (numS==3)
				{
					objSoup.removeAll();	
				// Crear un panel para cada imagen y agregarlas al botón en diferentes posiciones
		        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        panel1.setOpaque(false); // Hacer el panel transparente
		        JLabel imageLabel1 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/spoon.png")));		 
		        panel1.add(imageLabel1);
		        objSoup.add(panel1, BorderLayout.WEST); // Agregar la primera imagen a la izquierda del botón
		        
		        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        panel2.setOpaque(false); // Hacer el panel transparente
		        JLabel imageLabel2 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/spoon.png")));
		        panel2.add(imageLabel2);
		        objSoup.add(panel2, BorderLayout.CENTER); // Agregar la segunda imagen al centro del botón
		        
		        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        panel3.setOpaque(false); // Hacer el panel transparente
		        JLabel imageLabel3 = new JLabel(new ImageIcon(getClass().getResource("/vista/fotos/spoon.png")));
		        panel3.add(imageLabel3);
		        objSoup.add(panel3, BorderLayout.EAST); // Agregar la tercera imagen a la derecha del botón
		        
		        objSoup.revalidate();
		        objSoup.repaint();
				}
			}
		}
	}
	
	private void gestionarCorazones() {
		if (vidarestante==4) {
			cora4.setEnabled(true);
			cora4.setEnabled(true);
			cora4.setEnabled(true);
			cora4.setEnabled(true);
		}
		else if (vidarestante==3) {
			cora4.setEnabled(false);
			cora3.setEnabled(true);
			cora2.setEnabled(true);
			cora1.setEnabled(true);
		}
		else if (vidarestante==2) {
			cora4.setEnabled(false);
			cora3.setEnabled(false);
			cora2.setEnabled(true);
			cora1.setEnabled(true);
		}
		else if (vidarestante==1) {
			cora4.setEnabled(false);
			cora3.setEnabled(false);
			cora2.setEnabled(false);
			cora1.setEnabled(true);
		}
		else if (vidarestante==0) {
			cora4.setEnabled(false);
			cora3.setEnabled(false);
			cora2.setEnabled(false);
			cora1.setEnabled(false);
			ImageIcon iconoMuerto = new ImageIcon(Juego.class.getResource("/vista/fotos/death.gif"));
		     icono.setIcon(iconoMuerto);
		     caca.setVisible(false);
		     enfermucho.setVisible(false);	     
		}
	}
	
	private void gestionarComida() {
		if (hambrerestante==4) {
			comida4.setEnabled(true);
			comida3.setEnabled(true);
			comida2.setEnabled(true);
			comida1.setEnabled(true);
		}
		else if (hambrerestante==3) {
			comida4.setEnabled(false);
			comida3.setEnabled(true);
			comida2.setEnabled(true);
			comida1.setEnabled(true);
		}
		else if (hambrerestante==2) {
			comida4.setEnabled(false);
			comida3.setEnabled(false);
			comida2.setEnabled(true);
			comida1.setEnabled(true);
		}
		else if (hambrerestante==1) {
			comida4.setEnabled(false);
			comida3.setEnabled(false);
			comida2.setEnabled(false);
			comida1.setEnabled(true);
		}
		else if (hambrerestante==0) {
			comida4.setEnabled(false);
			comida3.setEnabled(false);
			comida2.setEnabled(false);
			comida1.setEnabled(false);
			ImageIcon iconoMuerto = new ImageIcon(Juego.class.getResource("/vista/fotos/death.gif"));
		    icono.setIcon(iconoMuerto);
		    caca.setVisible(false);
		    enfermucho.setVisible(false);
		}
	}
	private JButton getCaca() {
		if (caca == null) {
			caca = new JButton("caca");
			caca.setVisible(false);
			caca.addActionListener(getControler());
			caca.setBackground(Color.BLACK);
	   	 	caca.setBorderPainted(false);
	   	 	caca.setForeground(caca.getBackground());
	        caca.setFocusPainted(false);
	        caca.setBorder(BorderFactory.createEmptyBorder());
		}
		return caca;
	}
	private JButton getEnfermucho() {
		if (enfermucho == null) {
			enfermucho = new JButton("enfermucho");
			enfermucho.setVisible(false);
			enfermucho.addActionListener(getControler());
			enfermucho.setBackground(Color.BLACK);
			enfermucho.setBorderPainted(false);
			enfermucho.setForeground(enfermucho.getBackground());
			enfermucho.setFocusPainted(false);
			enfermucho.setBorder(BorderFactory.createEmptyBorder());
		}
		return enfermucho;
	}
}