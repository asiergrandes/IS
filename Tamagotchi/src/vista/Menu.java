package vista;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.awt.event.KeyAdapter;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import modelo.Tamagotchi;

@SuppressWarnings("deprecation")
public class Menu extends JFrame implements Observer {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel labelTitulo;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel labelNombrej;
	private JTextField nombrej;
	private JPanel panel_3;
	private JButton jugar;
	private JPanel panel_4;
	private JButton salir;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel labelLeaderboard;
	private JPanel panel_8;
	private JPanel panel_9;
	private JLabel ranking;
	private JLabel nombres;
	private JLabel primero;
	private JLabel segundo;
	private JLabel tercero;
	private JLabel cuarto;
	private JLabel quinto;
	private JLabel prim;
	private JLabel seg;
	private JLabel ter;
	private JLabel cua;
	private JLabel qui;
	private JPanel panel_7;
	private JLabel foto;
	private Controler controler = null;
	private ArrayList<Jugador> leaderboard;
	private int score;
	private String nombre;
	private static Menu miMenu = new Menu();
	private JLabel lblNewLabel;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	Menu frame = miMenu;
					Menu frame = new Menu();
					frame.setVisible(true);
					frame.ordenarLeaderboard();
					frame.actualizarLeaderboard();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void ordenarLeaderboard() {
		List<Jugador> leaderboard = new ArrayList<>();

        // Leer el archivo LeaderBoard.txt y almacenar los jugadores en la lista
        try (BufferedReader br = new BufferedReader(new FileReader("LeaderBoard.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    String nombre = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    leaderboard.add(new Jugador(score, nombre));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ordenar la lista de jugadores seg�n la puntuaci�n en orden descendente
        Collections.sort(leaderboard, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador jugador1, Jugador jugador2) {
                return Integer.compare(jugador2.getScore(), jugador1.getScore());
            }
        });

        // Escribir los jugadores ordenados de nuevo en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("LeaderBoard.txt"))) {
            for (Jugador jugador : leaderboard) {
                bw.write(jugador.getNombre() + " " + jugador.getScore());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	

	/**
	 * Create the frame.
	 */
	private Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel_5_1(), BorderLayout.CENTER);
		Tamagotchi tama = Tamagotchi.getTamagotchi();
		tama.addObserver(this);
		Jugador j = new Jugador(score,nombre);
		j.addObserver(this);
	}
	
	public static Menu getMenu() {
		return miMenu;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(new Color(0, 0, 0));
			panel.setLayout(new GridLayout(2, 1, 0, 0));
			panel.add(getLabelTitulo());
			panel.add(getPanel_1_1());
		}
		return panel;
	}
	private JLabel getLabelTitulo() {
		if (labelTitulo == null) {
			labelTitulo = new JLabel("");
			labelTitulo.setIcon(new ImageIcon(Menu.class.getResource("/vista/fotos/MainTitle.png")));
			labelTitulo.setFont(new Font("Curlz MT", Font.PLAIN, 11));
			labelTitulo.setForeground(new Color(255, 0, 0));
			labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		}
		return labelTitulo;
	}
	private JPanel getPanel_1_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(new Color(0, 0, 0));
			panel_1.add(getPanel_2_1());
			panel_1.add(getLabelNombreJ());
			panel_1.add(getPanel_3_1());
			panel_1.add(getPanel_4_1());

		}
		return panel_1;
	}
	private JPanel getPanel_2_1() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(new Color(0, 0, 0));
			panel_2.add(getNombrej());
		}
		return panel_2;
	}
	private JLabel getLabelNombreJ() {
		if (labelNombrej == null) {
			labelNombrej = new JLabel("Your name:");
			labelNombrej.setForeground(new Color(255, 0, 0));
		}
		return labelNombrej;
	}
	private JTextField getNombrej() {
		if (nombrej == null) {
			nombrej = new JTextField();
			nombrej.setHorizontalAlignment(SwingConstants.CENTER);
			nombrej.setForeground(new Color(0, 0, 0));
			nombrej.setBackground(new Color(192, 192, 192));
			nombrej.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(nombrej.getText().length()>=3) {
						e.consume();
						}
				}
			});
			nombrej.setColumns(10);
		}
		return nombrej;
	}
	private JPanel getPanel_3_1() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(new Color(0, 0, 0));
			panel_3.add(getJugar());
		}
		return panel_3;
	}
	private JButton getJugar() {
		if (jugar == null) {
			jugar = new JButton("Play");
			jugar.setForeground(new Color(0, 0, 0));
			jugar.setBackground(new Color(255, 0, 0));
			jugar.addActionListener(getControler());
		}
		return jugar;
	}
	private JPanel getPanel_4_1() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(new Color(0, 0, 0));
			panel_4.add(getSalir());
		}
		return panel_4;
	}
	private JButton getSalir() {
		if (salir == null) {
			salir = new JButton("Exit");
			salir.setBackground(new Color(255, 0, 0));
			salir.addActionListener(getControler());
		}
		return salir;
	}
	private JPanel getPanel_5_1() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setLayout(new GridLayout(1, 0, 0, 0));
			panel_5.add(getPanel_6_1());
			panel_5.add(getPanel_7_1());
		}
		return panel_5;
	}
	private JPanel getPanel_6_1() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(new Color(0, 0, 0));
			panel_6.setLayout(new GridLayout(0, 1, 0, 0));
			panel_6.add(getLabelLeaderboard());
			panel_6.add(getPanel_8_1());
		}
		return panel_6;
	}
	private JLabel getLabelLeaderboard() {
		if (labelLeaderboard == null) {
			labelLeaderboard = new JLabel("Leaderboard");
			labelLeaderboard.setForeground(new Color(255, 0, 0));
			labelLeaderboard.setBackground(new Color(0, 0, 0));
			labelLeaderboard.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return labelLeaderboard;
	}
	private JPanel getPanel_8_1() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setLayout(new GridLayout(1, 0, 0, 0));
			panel_8.add(getPanel_9_1());
		}
		return panel_8;
	}
	private JPanel getPanel_9_1() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setBackground(new Color(0, 0, 0));
			panel_9.setLayout(new GridLayout(6, 2, 0, 0));
			panel_9.add(getRanking());
			panel_9.add(getNombres());
			panel_9.add(getPrimero());
			panel_9.add(getLblPrimero());
			panel_9.add(getSegundo());
			panel_9.add(getLblSeg());
			panel_9.add(getTercero());
			panel_9.add(getLblTer());
			panel_9.add(getCuarto());
			panel_9.add(getLblCua());
			panel_9.add(getQuinto());
			panel_9.add(getLblQuinto());
		}
		return panel_9;
	}
	private JLabel getRanking() {
		if (ranking == null) {
			ranking = new JLabel("Rank");
			ranking.setBackground(new Color(0, 0, 0));
			ranking.setForeground(new Color(255, 0, 0));
		}
		return ranking;
	}
	private JLabel getNombres() {
		if (nombres == null) {
			nombres = new JLabel("Name    Score");
			nombres.setBackground(new Color(0, 0, 0));
			nombres.setForeground(new Color(255, 0, 0));

		}
		return nombres;
	}
	private JLabel getPrimero() {
		if (primero == null) {
			primero = new JLabel("1st");
			primero.setBackground(new Color(0, 0, 0));
			primero.setForeground(new Color(255, 0, 0));
		}
		return primero;
	}
	private JLabel getSegundo() {
		if (segundo == null) {
			segundo = new JLabel("2nd");
			segundo.setBackground(new Color(0, 0, 0));
			segundo.setForeground(new Color(255, 0, 0));
		}
		return segundo;
	}
	private JLabel getTercero() {
		if (tercero == null) {
			tercero = new JLabel("3rd");
			tercero.setBackground(new Color(0, 0, 0));
			tercero.setForeground(new Color(255, 0, 0));
		}
		return tercero;
	}
	private JLabel getCuarto() {
		if (cuarto == null) {
			cuarto = new JLabel("4th");
			cuarto.setBackground(new Color(0, 0, 0));
			cuarto.setForeground(new Color(255, 0, 0));
		}
		return cuarto;
	}
	private JLabel getQuinto() {
		if (quinto == null) {
			quinto = new JLabel("5th");
			quinto.setBackground(new Color(0, 0, 0));
			quinto.setForeground(new Color(255, 0, 0));
		}
		return quinto;
	}
	private JPanel getPanel_7_1() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setBackground(new Color(0, 0, 0));
			panel_7.add(getFoto());
		}
		return panel_7;
	}
	private JLabel getFoto() {
		if (foto == null) {
			foto = new JLabel();
			foto.setHorizontalAlignment(SwingConstants.RIGHT);
			foto.setVerticalAlignment(SwingConstants.BOTTOM);
			foto.setIcon(new ImageIcon(Menu.class.getResource("/vista/fotos/Marutchi1.png")));
		}
		return foto;
	}

	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}
	
	//-----------------------------Controler------------------------------------------------
	private class Controler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			String nombre = getNombrej().getText();
			Tamagotchi tama = Tamagotchi.getTamagotchi();
			tama.actualizarNombre(nombre);
			tama.gestionarBotones(e.getActionCommand());
		}

	}
	//-----------------------------Controler------------------------------------------------


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof String) {
			String mensaje = (String) arg;
			if (mensaje.equals("Juego iniciado") && getNombrej().getText().length()==3) {
				setVisible(false);
				Juego juego = Juego.getJuego();
				juego.setVisible(true);
			}
			else if (mensaje.equals("Cerrar todo")) {
				//Poner que cierre todo
				//setVisible(false);
				System.exit(0);
			}
			else if (mensaje.equals("Cerrar juego")) {
				ordenarLeaderboard();
				actualizarLeaderboard();
			}
		}
	}
	
	private void actualizarLeaderboard() 
	{
		Scanner scanner;
		try 
		{
			scanner = new Scanner(new File("LeaderBoard.txt"));
			for (int i =0; i<5; i++) 
			{
			    if (scanner.hasNextLine())
			    {
			    	String linea = scanner.nextLine();
			    	if (i==0)
			    	{
			    		getLblPrimero().setText(linea);
			    	}
			    	else if (i==1)
			    	{
			    		getLblSeg().setText(linea);
			    	}
			    	else if (i==2)
			    	{
			    		getLblTer().setText(linea);
			    	}
			    	else if (i==3)
			    	{
			    		getLblCua().setText(linea);
			    	}
			    	else if (i==4)
			    	{
			    		getLblQuinto().setText(linea);
			    	}
			    }
			}
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private JLabel getLblPrimero() {
		if (prim == null) {
			prim = new JLabel("");
			prim.setForeground(Color.RED);
		}
		return prim;
	}
	
	private JLabel getLblSeg() {
		if (seg == null) {
			seg = new JLabel("");
			seg.setForeground(Color.RED);
		}
		return seg;
	}
	
	private JLabel getLblTer() {
		if (ter == null) {
			ter = new JLabel("");
			ter.setForeground(Color.RED);
		}
		return ter;
	}
	
	private JLabel getLblCua() {
		if (cua == null) {
			cua = new JLabel("");
			cua.setForeground(Color.RED);
		}
		return cua;
	}
	
	private JLabel getLblQuinto() {
		if (qui == null) {
			qui = new JLabel("");
			qui.setForeground(Color.RED);
		}
		return qui;
	}
}