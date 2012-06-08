package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocketPrinter;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraString;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Util.ColoriGioco;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.Punteggi;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConnessioneControllerSocket extends ConnessioneController
{
	/**
	 * Basic constructor, just assign the passed variable to private field
	 * 
	 * @param socket
	 * @throws IOException
	 */
	public ConnessioneControllerSocket(Socket socket, ProxyController proxy) throws IOException
	{
		this.socket = socket;
		InputStream input = socket.getInputStream();
		OutputStream output = socket.getOutputStream();
		this.in = new Scanner(input);
		this.out = new CarcassonneSocketPrinter(output);
		this.proxy = proxy;
		this.partiDiEventoComposto = new ArrayList<String>();
	}

	@Override
	public void run()
	{
		boolean letsReadAgain;
		while (this.in.hasNext())
		{
			String stringaDaSocket = this.in.nextLine();

			do
			{
				letsReadAgain = this.parsingStringa(stringaDaSocket); // true
																		// sse
																		// devo
																		// leggere
																		// ancora
			} while (letsReadAgain);
			this.partiDiEventoComposto.clear();

		}
	}

	private boolean parsingStringa(String stringaDaSocket)
	{
		String line = stringaDaSocket;
		if (line.indexOf(",") != -1 && line.indexOf(":") != -1) // composti
																// update:
																// tile,x,y
		{
			String[] comandoEArgomenti = line.split(": ");
			String argomenti = comandoEArgomenti[ARGOMENTI];
			String[] partiArgomenti = argomenti.split(",");
			String tessera = partiArgomenti[TESSERA];

			if (line.matches("start:" + regTessera + ",.+" + ",(black|green|red|yellow|blue)" + ","
					+ "\\d+")) // es start: tile,
								// name, color, num
			{
				String name = partiArgomenti[NOME];
				String color = partiArgomenti[COLORE_START];
				String numero = partiArgomenti[NUMERO];

				return false;
			}
			if (line.matches("update: " + regTessera + ",\\-?\\d+\\,\\-?\\d+")) // es.
																				// update:
			// tile 2,3
			{
				int x = Integer.parseInt(partiArgomenti[X]);
				int y = Integer.parseInt(partiArgomenti[Y]);

				Color coloreGiocatore = null; // TODO get Current player color
				this.proxy
						.fire(new UpdatePositionEvent(tessera, new Coordinate(x, y), coloreGiocatore, this));

				return false;
			}

			if (line.matches("update: " + regTessera + ",\\-?\\d+\\,\\-?\\d+,")) // es.
																					// update:
			// tile 2,3, update: tile 3,3, update: tile 4,3
			{
				String x = partiArgomenti[X];
				String y = partiArgomenti[Y];
				this.partiDiEventoComposto.add(x);
				this.partiDiEventoComposto.add(y);
				this.partiDiEventoComposto.add(tessera);
				return true; // ci sarà da leggere ancora

			}

		}
		else
		{
			if (line.indexOf(":") != -1)
			{
				String[] comandoEArgomenti = line.split(": ");
				String argomenti = comandoEArgomenti[ARGOMENTI];

				if (line.matches("turn: (black|green|red|yellow|blue)")) // es.
																			// turn:
																			// red
				{

					String colore = argomenti;
					this.partiDiEventoComposto.add(colore); // metti la roba in
															// lista
					// poi quando ritornerà false sarà perchè avrà tutti gli
					// elem necessari
					// per comporre l'evento
					return true;

				}
				if (line.matches("next: " + regTessera)) // es next: blabla
				{
					String colore = this.partiDiEventoComposto.get(0);
					String tessera = argomenti;
					this.proxy.fire(new UpdateTurnoEvent(this, ColoriGioco.getColor(colore), tessera));

					return false;
				}
				if (line.matches("rotated: " + regTessera)) // es rotate: blabla
				{
					Color giocatore = null; // TODO capire
					String tessera = argomenti;
					this.proxy.fire(new UpdateRotationEvent(tessera, giocatore, this));
					return false;

				}

				if (line.matches("score: " + regScores)) // es score: red=10
															// blu=20
				{
					// arrivato qua ha tutto ciò che serve per
					// costruzioneCOmpletata event
					Map<AdapterTessera, Coordinate> mappaAggiornate = new HashMap<AdapterTessera, Coordinate>();

					for (int i = 0; i < this.partiDiEventoComposto.size(); i++)
					{
						String parteEv = this.partiDiEventoComposto.get(i);
						AdapterTessera adTessera = new AdapterTesseraString(parteEv);
						i++;
						int xCoord = Integer.parseInt(this.partiDiEventoComposto.get(i));
						i++;
						int yCoord = Integer.parseInt(this.partiDiEventoComposto.get(i));
						mappaAggiornate.put(adTessera, new Coordinate(xCoord, yCoord));
					}

					Punteggi punti = new Punteggi();
					String[] coloriPunteggi = argomenti.split(",");

					for (String colorePunteggio : coloriPunteggi)
					{
						String[] partiColPunt = colorePunteggio.split("=");
						String coloreG = partiColPunt[COLORE_SCORE];
						int puneggioG = Integer.parseInt(partiColPunt[NUM_SCORE]);
						punti.addPunteggi(ColoriGioco.getColor(coloreG), puneggioG);
					}

					this.proxy.fire(new CostruzioneCompletataEvent(this, mappaAggiornate, punti));
					return false;

				}
				if (line.matches("leave: (black|green|red|yellow|blue)")) // es
																			// leave:
																			// yellow
				{ return false; }
				if (line.matches("end: " + regScores)) // es next: red=10
														// blue=30
				{

				return false; }

			}
			else
			{
				if (line.equalsIgnoreCase("move not valid")) { return false; }
				if (line.equalsIgnoreCase("lock")) { return false; }
				if (line.equalsIgnoreCase("unlock")) { return false; }

			}
		}

		return false;

	}

	public void eseguiComando()
	{
		// TODO Auto-generated method stub

	}

	public void invia(String message)
	{
		this.out.println(message);
	}

	@Override
	public void riceviInput()
	{
		Scanner socketIn = null;
		try
		{
			socketIn = new Scanner(this.socket.getInputStream());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = "";
		try
		{
			s = socketIn.nextLine();
		}
		catch (NoSuchElementException nsee)
		{
			Debug.print("mi sa che è morto il server ");
		}

		s = "socket non ha next";
		Debug.print("Connessione Controller Socket, ho ricevuto  " + s);
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub

	}

	private static final int	ARGOMENTI		= 1;

	private static final String	regScores		= "￼red=\\d+, blue=\\d+, green=\\d+, yellow=\\d+, black=\\d+";
	private static final String	regTessera		= "*";
	/*
	 * TODO: NO! I SEGNALINI !!
	 * "N=(S|C|N) S=(S|C|N) W=(S|C|N) E=(S|C|N) NS=(0|1) NE=(0|1)" +
	 * " NW=(0|1) WE=(0|1) SE=(0|1) SW=(0|1)";
	 */

	private List<String>		partiDiEventoComposto;

	private final static int	TESSERA			= 0;
	private final static int	NOME			= 1;
	private final static int	COLORE_START	= 2;
	private final static int	NUMERO			= 3;
	private final static int	X				= 0;
	private final static int	Y				= 1;
	private final static int	COLORE_SCORE	= 0;
	private final static int	NUM_SCORE		= 1;
	private Socket				socket;

	private Scanner				in;

	private CarcassonneSocketPrinter			out;

	private ProxyController		proxy;

}
