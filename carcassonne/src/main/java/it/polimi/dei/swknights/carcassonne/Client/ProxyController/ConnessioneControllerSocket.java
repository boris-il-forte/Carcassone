package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocketPrinter;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraString;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdatePositionEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateRotationEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.UpdateTurnoEvent;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;
import it.polimi.dei.swknights.carcassonne.Parser.ExtraParser;
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
		boolean letsReadAgain = false;
		while (this.in.hasNext())
		{
			String stringaDaSocket = this.in.nextLine();
			Debug.print(" sono connessione controller socket - ho ricevuto qualcosa  " + stringaDaSocket
					+ "   -  faccio il parsingStringa");
			do
			{
				if (letsReadAgain)
				{
					stringaDaSocket = this.in.nextLine();
					Debug.print(" sono connessione controller socket - ho ricevuto qualcosa  "
							+ stringaDaSocket + "   -  faccio il parsingStringa");
				}
				try
				{
					letsReadAgain = this.parsingStringa(stringaDaSocket);
				}
				catch (InvalidStringToParseException e)
				{
					break;
				} // true
					// sse
					// devo
					// leggere
					// ancora
			} while (letsReadAgain);
			this.partiDiEventoComposto.clear();

		}
	}

	public void eseguiComando()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void invia(String message)
	{
		Debug.print(" connessione controller socket - invio " + message);
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
		String s = "nulla";
		try
		{
			s = socketIn.nextLine();
		}
		catch (NoSuchElementException nsee)
		{
			Debug.print("mi sa che è morto il server ");
		}

		Debug.print("Connessione Controller Socket, ho ricevuto  " + s);
	}

	@Override
	public void close()
	{
		// TODO Auto-generated method stub

	}

	private boolean parsingStringa(String stringaDaSocket) throws InvalidStringToParseException
	{
		String line = stringaDaSocket;
		if (line.indexOf(",") != -1 && line.indexOf(":") != -1)
		{
			String[] comandoEArgomenti = line.split(":");
			String argomenti = comandoEArgomenti[ARGOMENTI];
			String[] partiArgomenti = argomenti.split(",");
			String tessera = partiArgomenti[TESSERA];

			new ExtraParser(tessera);

			if (line.matches("start:" + REG_TESSERA + ",.+" + ",(black|green|red|yellow|blue)" + "," + "\\d+")) // es
																												// start:tile,
																												// name,
																												// color,
																												// num
			{
				String tesseraStart = partiArgomenti[TESSERA_START];
				String name = partiArgomenti[NOME];
				String color = partiArgomenti[COLORE_START];
				String numero = partiArgomenti[NUMERO];

				Color coloreGiocatore = ColoriGioco.getColor(color);
				AdapterTesseraString ada = new AdapterTesseraString(tesseraStart);

				this.proxy.fire(new InizioGiocoEvent(this, ada, coloreGiocatore, Integer.parseInt(numero),
						name));

			}
			if (line.matches("update:" + REG_TESSERA + ",\\-?\\d+\\,\\-?\\d+")) // es.
																				// update:
			// tile 2,3
			{
				int x = Integer.parseInt(partiArgomenti[X]);
				int y = Integer.parseInt(partiArgomenti[Y]);
				this.proxy.fire(new UpdatePositionEvent(tessera, new Coordinate(x, y), this));
				return false;
			}

			if (line.matches("update:" + REG_TESSERA + ",\\-?\\d+\\,\\-?\\d+,")) // es.
																				// update:
			// tile 2,3, update:tile 3,3, update: tile 4,3
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
				String[] comandoEArgomenti = line.split(":");
				String argomenti = comandoEArgomenti[ARGOMENTI];

				if (line.matches("turn:(black|green|red|yellow|blue)")) // es.
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
				if (line.matches("next:" + REG_TESSERA)) // es next: blabla
				{
					String colore = this.partiDiEventoComposto.get(0);
					String tessera = argomenti;
					new ExtraParser(tessera);
					this.proxy.fire(new UpdateTurnoEvent(this, ColoriGioco.getColor(colore), tessera));

					return false;
				}
				if (line.matches("rotated:" + REG_TESSERA)) // es rotate: blabla
				{
					String tessera = argomenti;
					new ExtraParser(tessera);
					this.proxy.fire(new UpdateRotationEvent(tessera, this));
					return false;

				}

				if (line.matches("score:" + REG_SCORES)) // es score: red=10
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
				if (line.matches("leave:(black|green|red|yellow|blue)")) // es
																			// leave:
																			// yellow
				{ return false; }
				if (line.matches("end:" + REG_SCORES)) // es next: red=10
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

	private static final int			ARGOMENTI		= 1;

	private static final String			REG_SCORES		= "red=\\d+, blue=\\d+, green=\\d+, yellow=\\d+, black=\\d+";
	private static final String			REG_TESSERA		= ".+";
	private List<String>				partiDiEventoComposto;
	private static final int			TESSERA			= 0;
	private static final int			NOME			= 1;
	private static final int			COLORE_START	= 2;
	private static final int			NUMERO			= 3;
	private static final int			X				= 1;
	private static final int			Y				= 2;
	private static final int			COLORE_SCORE	= 0;
	private static final int			NUM_SCORE		= 1;
	private static final int			TESSERA_START	= 0;

	private Socket						socket;

	private Scanner						in;

	private CarcassonneSocketPrinter	out;

	private ProxyController				proxy;

}
