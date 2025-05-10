package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import vista.Menu;


@SuppressWarnings("deprecation")
public class Tamagotchi extends Observable{
	
	private String nombre;
	private int vida;
	private int hambre;
	private Timer timer;
	private int cont;
	private int score;
	private String evolucion;
	private boolean cagado;
	private boolean enfermado;
	private boolean muerto;
	private boolean ultima;
	private static Tamagotchi miTamagotchi = new Tamagotchi();
	private int sopas;
	private int candys;
	private static TimerTask segundos;
	private boolean enMiniJuego;
	
	private Tamagotchi() {
		vida = 40;
		hambre = 40;
		evolucion = "egg";
		cont = 0;
		score = 0;
		cagado = false;
		enfermado = false;
		muerto = false;
		ultima=false;
		enMiniJuego = false;
		sopas=0;
		candys=0;
		}
	
	public static Tamagotchi getTamagotchi() {
		return miTamagotchi;	}
	
	public void iniciarCont() 
	{
		segundos = new TimerTask()
		{
			
			@Override
			public void run() {
				if (!muerto && !enMiniJuego) {
					actualizarTama();
				}
			}
		};
				
		timer = new Timer();
		timer.scheduleAtFixedRate(segundos, 0, 1000);
		enMiniJuego = false;
	}
	
	private void pararCont() {
		if(segundos!=null) {
			segundos.cancel();
		}
	}
	
	
	private void actualizarTama() {//se llama a este metodo cada 1 segundo
		cont++;
		if(cont%4==0) //cada 4 segundos
		{
			Random random = new Random();
			double minijuego = random.nextDouble(); //numero al azar para determinar si salta el minijuego
			
			if (minijuego<=0.12)
			{
				String mensaje = "minijuego";
				setChanged();
				notifyObservers(mensaje);
				System.out.println("Minijuego");
				pararCont();
				enMiniJuego = true;
			}
			
			
			if (evolucion.equals("Egg"))
			{
				score++;
			}
			else
			{
				if (!cagado) //determinar si se ha cagado o se ha enfermado, solo se hace en caso de que no este enfermo ni cagado
				{
					//numero al azar para determinar si se ha cagado o se ha enfermado				
					double caca = random.nextDouble();	
					
					if (caca <= 0.2) 
					{
						cagado = true;
						Object[] objectArray = new Object[] {"Cagado",cagado}; 
						setChanged();
						notifyObservers(objectArray);
					}
				}
				
				if (!enfermado) //determinar si se ha cagado o se ha enfermado, solo se hace en caso de que no este enfermo ni cagado
				{
					//numero al azar para determinar si se ha cagado o se ha enfermado				
					double enfermo = random.nextDouble();			
					if (enfermo <= 0.3) 
					{
							enfermado = true;
							Object[] objectArray = new Object[] {"Enfermado",enfermado}; 
							setChanged();
							notifyObservers(objectArray);
					}				
				}
				
				
				//si se ha cagado o enfermado (o no se ha cagado ni enfermado) reducimos/aumentamos el score, hambre y vida 
				if (cagado)
				{
					score=score-5;
					vida=vida-5;
					if (hambre >=30)
					{
						hambre=40;
					}
					else
					{
						hambre=hambre+10;
					}
				}
				else if (enfermado)
				{
					score=score-5;
					vida=vida-7;
					if (hambre >=35)
					{
						hambre=40;
					}
					else
					{
						hambre=hambre+5;
					}
	
				}
				else
				{
					score++;
				}
			}
		    
			//dependiendo de la evoluci�n cambiamos el hambre y la vida
			actualizarHambreVida();
			aumentarCandys(); //cada 4 segundos se le aumenta un candy
    		aumentarSopas();//cada 4 segundos se le aumenta una sopa
			
			//para visionar en consola lo que va pasando
			System.out.println("cont: "+cont+"| score: "+score+"| evoluci�n: "+evolucion+"| hambre: "+hambre+"| vida: "+vida+" CANDYS: "+candys+" SOPAS: "+sopas);
			if (cagado)
			{
				System.out.println("cagado");
			}
			
			if (enfermado)
			{		
				System.out.println("enfermo");
			}
			
			System.out.println(" ");
		}
		
        if ((vida<=0 || hambre<=0))
        {
        	muerto=true;
        	String mensaje = "Muerto"; 
        	System.out.println("MUERTO");
        	pararCont();
     		setChanged();
     		notifyObservers(mensaje);
     		
        }
		
        
        gestionarCandys();
        gestionarSopas();
        evolucionar(); //dependiendo del tiempo que llevemos cambia la evoluci�n
        gestionarCorazones(); //dependiendo de la vida cambia el numero de corazones de la pantalla
        gestionarComida(); //dependiendo del hambre cambia el numero de cuencos de la pantalla
        Object[] objectArray = new Object[] {"Actualizar puntuacion",score}; 
		setChanged();
		notifyObservers(objectArray);
	}
	
	private void gestionarSopas() {
		Object[] objectArray = new Object[] {"Actualizar sopas",sopas}; 
		setChanged();
		notifyObservers(objectArray);
	}

	private void gestionarCandys() {
		Object[] objectArray = new Object[] {"Actualizar candys",candys}; 
		setChanged();
		notifyObservers(objectArray);
	}

	private void aumentarSopas() {
		if (sopas<3)
		{
			sopas++;
		}
		Object[] objectArray = new Object[] {"Actualizar sopas",sopas}; 
		setChanged();
		notifyObservers(objectArray);
	}

	private void aumentarCandys() {
		if (candys<3)
		{
			candys++;
		}
		Object[] objectArray = new Object[] {"Actualizar candys",candys}; 
		setChanged();
		notifyObservers(objectArray);
	}

	public void gestionarBotones(String pBoton) {
		
		String mensaje = null;
		if (pBoton.equals("Play") && nombre.length()==3) {
			reiniciar();
			this.iniciarCont();
			mensaje = "Juego iniciado";
			}
		else if (pBoton.equals("exit")) {
			System.out.println("exit");
			this.pararCont();
			int punt = score;
			String nombre = this.nombre;
			mensaje = "Cerrar juego";
			actualizarScores(nombre,punt);	
		}
		else if (pBoton.equals("Exit")) {
			//this.pararCont();
			mensaje = "Cerrar todo";
		}
		else if (pBoton.equals("candy")) {
			if (candys>0)
			{
				candys--;
				if (!muerto)
				{
					if (vida==40)
					{
					score = score-5;
					System.out.println("CANDY!--> LLENO --- -5 de score");
					}
					else if (vida>=30)
					{
					score=score+3;
					vida=40;
					System.out.println("CANDY!--> +3 score --- +10 vida");
					}
					else
					{
					vida = vida+10;
					score = score+3;
					System.out.println("CANDY!--> +3 score --- +10 vida");
					}
				}
				else
				{
					System.out.println("Est� muerto capullo no le des candy");
				}
			}
			else 
			{
				System.out.println("NO quedan candys");
			}
			
		}
		else if (pBoton.equals("soup")) {
			if (sopas>0)
			{
				sopas--;
				if (!muerto)
				{
					if (hambre==40)
					{
						score = score-5;
						System.out.println("SOUP!--> LLENO --- -5 de score");
					}
					else if (hambre>=30)
					{
						score=score+3;
						hambre=40;
						System.out.println("SOUP!--> +3 score --- +10 hambre");
					}
					else
					{
						hambre = hambre+10;
						score = score+3;
						System.out.println("SOUP!--> +3 score --- +10 hambre");
					}
				}
				else
				{
					System.out.println("Est� muerto capullo no le des sopa");
				}
			}
			else
			{
				System.out.println("No quedan soups");
			}
		}
		else if (pBoton.equals("caca")) {
			cagado=false;
			mensaje = "Limpiado";
			System.out.println("se ha limpiadoooooooo");
		}
		else if (pBoton.equals("enfermucho")) {
			enfermado=false;
			mensaje = "Curado";
			System.out.println("se ha curadoooo siiii");
		}
		setChanged();
		notifyObservers(mensaje);

	}
	
	private void actualizarHambreVida() {
		if (evolucion.equals("Kuchipatchi")) {
			vida = vida -2;
			hambre = hambre - 5;
		}
		else if (evolucion.equals("Mimitchi")) {
			vida = vida -7;
			hambre = hambre - 7;
		}
		else if (evolucion.equals("Maskutchi")) {
			vida = vida -3;
			hambre = hambre - 14;
		}
		else if (evolucion.equals("Mametchi")) {
			vida--;
			hambre--;
		}
	}
	
	private void evolucionar() {
		if (cont <= 8) {
			evolucion = "Egg";
		}
		else if (8<cont && cont<=24) {
			evolucion = "Kuchipatchi";
		}
		else if (24<cont && cont<=32) {
			evolucion = "Mimitchi";
		}
		else if (cont>32) 
		{
			if (!ultima)
			{
				if (score>=100)
				{
					evolucion = "Mametchi";
					ultima=true;
				}
				else
				{
					evolucion = "Maskutchi";
					ultima=true;
				}
			}
		}
		Object[] objectArray = new Object[] {"Tamagotchi evolucionado",evolucion};
		setChanged();
		notifyObservers(objectArray);
	}
	
	private void gestionarCorazones() {
		int coraRestantes=0;
		if (vida>30) {
			coraRestantes = 4;
		}
		else if (vida>20 && vida<=30) {
			coraRestantes = 3;
		}
		else if (vida>10 && vida<=20) {
			coraRestantes = 2;
		}
		else if (vida<=10 && vida>0) {
			coraRestantes = 1;
		}
		else if(vida<=0)
		{
			coraRestantes = 0;
			muerto=true;
        	System.out.println("MUERTO por vida");
		}
		Object[] objectArray = new Object[] {"Actualizar corazones",coraRestantes}; 
		setChanged();
		notifyObservers(objectArray);
	}
	
	private void gestionarComida() {
		int comidaRestante = 0;
		if (hambre>30) {
			comidaRestante = 4;
		}
		else if (hambre>20 && hambre<=30) {
			comidaRestante = 3;
		}
		else if (hambre>10 && hambre<=20) {
			comidaRestante = 2;
		}
		else if (hambre<=10 && hambre>0) {
			comidaRestante = 1;
		}
		else if (hambre<=0) {
			comidaRestante = 0;
			muerto=true;
        	System.out.println("MUERTO por hambre");
		}
		Object[] objectArray = new Object[] {"Actualizar comida",comidaRestante}; 
		setChanged();
		notifyObservers(objectArray);
	}
	
	public int getVida() {
		return vida;
	}
	
	public int getHambre() {
		return hambre;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean getKK() {
		return this.cagado;
	}
	
	public boolean getEnf() {
		return this.enfermado;
	}
	
	private void reiniciar() {
		vida = 40;
		hambre = 40;
		evolucion = "egg";
		cont = 0;
		score = 0;
		cagado = false;
		enfermado = false;
		muerto = false;
		enMiniJuego = false;
		gestionarComida();
		gestionarCorazones();
		sopas = 0;
		candys = 0;
		ultima=false;
		//nombre=Menu.getMenu()
	}
	
	private void actualizarScores(String pNombre, int pScore) {
        try {
            FileWriter fileWriter = new FileWriter("LeaderBoard.txt", true); // Abre el archivo en modo de agregar (append)
            fileWriter.write(pNombre + "   " + pScore + "\n"); // Escribe el nombre y el puntaje al archivo
            fileWriter.close(); // Cierra el archivo despu�s de escribir
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo LeaderBoard.txt: " + e.getMessage());
        }
    }

	public void actualizarNombre(String nom) {
		this.nombre=nom;
	}

	public void sumarPuntuacion(int dureza) {
		this.score=this.score+dureza;
		Object[] objectArray = new Object[] {"Actualizar puntuacion",score}; 
		setChanged();
		notifyObservers(objectArray);
	}

	public String getEvol() {
		return this.evolucion;
	}
}
