package packagevista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Tamagotchi;
import packagemodelo.Tablero;
import packagemodelo.TamaMini;
import vista.Juego;
import vista.Menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;

public class minijuego extends JFrame  implements Observer{

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblNewLabel_3;
	private JPanel espCasillas;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnExit;
	private JButton[][] matrizCasillas = new JButton[8][12];
	private JLabel label;
	private Controler controler = null;
	private int[] coordenadasTama = new int[1];
	private int puntuacion;

	/**
	 * Launch the application.
	 */
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					minijuego frame = new minijuego();
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
	public minijuego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel_2(), BorderLayout.SOUTH);
		contentPane.add(getespCasillas(), BorderLayout.CENTER);
		
		Tablero tablero = Tablero.getTablero();
		tablero.addObserver(this);
		tablero.crearCasillas();
		tablero.crearBollo();
		tablero.iniciarCont();
		
		TamaMini tamaMini = TamaMini.getTamaMini();
		tamaMini.addObserver(this);
		
		Tamagotchi tamagotchi = Tamagotchi.getTamagotchi();
		tamagotchi.addObserver(this);
	}
	
	 
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(new Color(0, 0, 0));
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			panel.add(evolucion());
			panel.add(score());
			panel.add(getBtnExit());
		}
		return panel;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(new Color(0, 0, 0));
			panel_2.setForeground(new Color(255, 255, 255));
			panel_2.add(evolucion_3());
		}
		return panel_2;
	}
	private JLabel evolucion_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Minijuegooooooooooooo");
			lblNewLabel_3.setForeground(new Color(255, 255, 255));
		}
		return lblNewLabel_3;
	}
	private JPanel getespCasillas() {
		if (espCasillas == null) {
			espCasillas = new JPanel();
			espCasillas.setBackground(new Color(0, 0, 0));
			espCasillas.setLayout(new GridLayout(8, 12, 0, 0));
			
		}
		return espCasillas;
	}
	

	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
		
		//-----------------------------Controler------------------------------------------------
	/*private class Controler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			JButton casilla = (JButton) e.getSource();
	        String nombreCasilla = casilla.getText();

	        // Pasar el nombre al tablero
	        Tablero tablero = Tablero.getTablero();
	        tablero.gestionarCasillas(nombreCasilla);
		}
	}*/
	
	public class Controler implements MouseListener, ActionListener, KeyListener
	{

		public Controler() {
			addKeyListener(this);
	        setFocusable(true);
	        setFocusTraversalKeysEnabled(false);
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			JButton casilla = (JButton) e.getSource();
			String nombreCasilla = casilla.getText();
			
			// Obtener las coordenadas de la casilla desde el nombre
		    String[] coor = nombreCasilla.split(":");
		    int fila = Integer.parseInt(coor[0]);
		    int col = Integer.parseInt(coor[1]);

		    // Obtener la dureza de la casilla desde la matriz de casillas
		    int dureza = Tablero.getTablero().getDurezaCasilla(fila, col);

		    // Sumar la dureza a la puntuaci�n
		    Tamagotchi.getTamagotchi().sumarPuntuacion(dureza);

		    // Actualizar la etiqueta de puntuaci�n en la interfaz gr�fica
		    //actualizarPuntuacion();
			
	        // Pasar el nombre al tablero
	        Tablero tablero = Tablero.getTablero();
	        tablero.gestionarCasillas(nombreCasilla);
		}

		/*private void actualizarPuntuacion() {
			Tamagotchi tama = Tamagotchi.getTamagotchi();
			int punt = tama.getScore();
			lblNewLabel_1.setText("Score: "+punt);
		}*/

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			JButton casilla = (JButton) e.getSource();
	        String nombreCasilla = casilla.getText();

	        // Pasar el nombre al tablero
	        Tablero tablero = Tablero.getTablero();
	        tablero.gestionarBotones(nombreCasilla);
		}

		@Override
	    public void keyPressed(KeyEvent e) {
	        // Acciones cuando se presiona una tecla
	        int keyCode = e.getKeyCode();
	        System.out.println("Tecla presionada (c�digo): " + keyCode);
	        
	        TamaMini tamaMini = TamaMini.getTamaMini();
	        tamaMini.gestionarTeclas(keyCode);
	        
	        Tablero tablero = Tablero.getTablero();
	        tablero.gestionarCasillas("ActCoordenadasTama");

	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	        // Acciones cuando se suelta una tecla
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {
	        // Acciones cuando se presiona y luego se libera una tecla (no se usa com�nmente)
	    }

	}
	
	
		//-----------------------------Controler------------------------------------------------
		
		@Override
		public void update(Observable o, Object arg) {
			if (arg instanceof Object[]) 
			{
				Object[] objectArray = (Object[]) arg;
				
				if (objectArray[0].equals("ActTama")) {
					int filaAnt = (int) objectArray[1];
					int colAnt = (int) objectArray[2];
					int filaNueva = (int) objectArray[3];
					int colNueva = (int) objectArray[4];
					//matrizCasillas[filaAnt][colAnt].setBackground(new Color(0,0,0));
					//matrizCasillas[filaNueva][colNueva].setBackground(Color.blue);
					matrizCasillas[filaAnt][colAnt].setIcon(null);
					matrizCasillas[filaNueva][colNueva].setIcon(new ImageIcon(Menu.class.getResource("/vista/fotos/MimitchiMini2.png")));
				}
				else if (objectArray[0].equals("Actualizar puntuacion")) {
					puntuacion = (int) objectArray[1];
					lblNewLabel_1.setText("Score: "+ puntuacion);
				}
				else if (objectArray[0].equals("NoPintarNuevo")) {
					int filaAnt = (int) objectArray[1];
					int colAnt = (int) objectArray[2];
					matrizCasillas[filaAnt][colAnt].setBackground(new Color(0,0,0));
				}
				else if (objectArray[0].equals("A�adirCasilla")) {
				      int fila = (int) objectArray[1];
				      int col = (int) objectArray[2];
				      String filaCol = Integer.toString(fila) +":" +Integer.toString(col);
				      //JButton casilla = new JButton(objectArray[1].toString());
				      JButton casilla = new JButton(filaCol);
				      casilla.setOpaque(true);
				      casilla.setBorder(null);
				      int dureza = (int) objectArray[3];
				      if (dureza == 1) {
				        casilla.setBackground(new Color(90,0,0));
				      } else if (dureza == 2) {
				        casilla.setBackground(new Color(130,0,0));
				      } else if (dureza == 3) {
				        casilla.setBackground(Color.red);
				      } 
					      
				      
				     // casilla.addActionListener(getControler());
				      casilla.addMouseListener(getControler());
				      
					matrizCasillas[fila][col] = casilla;
					espCasillas.add(casilla, BorderLayout.CENTER,-1);
				}
				else if (objectArray[0].equals("Cambiar")) {
					int fila = (int) objectArray[1];
				    int col = (int) objectArray[2];
					if (objectArray[3].equals(2)) {
						matrizCasillas[fila][col].setBackground(new Color(130,0,0));
					}
					else if (objectArray[3].equals(1)) {
						matrizCasillas[fila][col].setBackground(new Color(90,0,0));
					}
					else if (objectArray[3].equals(0)) {
						matrizCasillas[fila][col].setBackground(new Color(0,0,0));
					}
					else if (objectArray[3].equals(-1)) {
						matrizCasillas[fila][col].setBackground(new Color(0,0,0));
						matrizCasillas[fila][col].setIcon(new ImageIcon(Menu.class.getResource("/vista/fotos/MimitchiMini2.png")));
				      }
				}
				else if (objectArray[0].equals("A�adirBollo")) {
					int filaB = (int) objectArray[1];
					int colB = (int) objectArray[2];
					if (objectArray[3].equals(-1)) {
						matrizCasillas[filaB][colB].setBackground(new Color(0,0,0));
						matrizCasillas[filaB][colB].setIcon(new ImageIcon(Menu.class.getResource("/vista/fotos/dorayakiSmall.png")));
					}
					
				}
			}
			else if (arg instanceof String)
			{
				String mensaje = (String) arg;
				
				if (mensaje.equals("Exit")) 
				{
					setVisible(false);
					Juego.getJuego().setVisible(true);
				}
				else if (mensaje.equals("HAS GANADO")) 
				{
					setVisible(false);
					Juego.getJuego().setVisible(true);
				}
				else if (mensaje.equals("Tiempo")) 
				{
					setVisible(false);
					Juego.getJuego().setVisible(true);
				}
			}
			/*else if(arg instanceof int[]) {
				coordenadasTama= (int[]) arg;
				matrizCasillas[coordenadasTama[0]][coordenadasTama[1]].setBackground(Color.blue);
			}*/
			
		}
	
	private JLabel evolucion() {
		if (lblNewLabel == null) {
			Tamagotchi tama = Tamagotchi.getTamagotchi();
			String evol=tama.getEvol();
			lblNewLabel = new JLabel(evol);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}
	private JLabel score() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Score: ");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_1;
	}
	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Exit");
			btnExit.addActionListener(getControler());
		}
		return btnExit;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Minijuegoooooooooooooooooooooooooooooooooooooooo");
		}
		return label;
	}
	
	
	
}