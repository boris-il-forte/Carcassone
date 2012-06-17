package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocketPrinter;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraString;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.CostruzioneCompletataEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.MossaNonValidaEvent;
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
import java.util.Scanner;

public class ConnessioneControllerSocket extends ConnessioneController
{
	/**
	 * Basic constructor, just assign the passed variable to private field
	 * 
	 * @param socket
	 *            the socket used by this connection
	 * @throws IOException
	 *             if there is a problem with te connection
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

	/**
	 * Run methods. Waits for command strings on the socket from the server
	 */
	@Override
	public void run()
	{
		boolean letsReadAgain = false;
		while (this.in.hasNext())
		{
			String stringaDaSocket = this.in.nextLine();
			do
			{
				if (letsReadAgain)
				{
					stringaDaSocket = this.in.nextLine();
				}
				try
				{
					letsReadAgain = this.parsingStringa(stringaDaSocket);

				}
				catch (InvalidStringToParseException e)
				{
					letsReadAgain = false;
					break;
				}
			} while (letsReadAgain);
			this.partiDiEventoComposto.clear();

		}
	}

	/**
	 * Send command strings to the server
	 */
	@Override
	public void invia(String message)
	{
		this.out.println(message);
	}

	/**
	 * Method that close the socket.
	 */
	@Override
	public void close()
	{
		try
		{
			this.socket.close();
		}
		catch (IOException e)
		{
		}

	}

	private boolean parsingStringa(String stringaDaSocket) throws InvalidStringToParseException
	{
		String line = stringaDaSocket;

		if (line.matches("score:" + REG_SCORES))
		{
			String[] comandoEArgomenti = line.split(":");
			String argomenti = comandoEArgomenti[1];
			/*
			 * arrivato qua ha tutto ciò che serve per costruzioneCOmpletata
			 * event
			 */
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
		if (line.contains(",") && line.contains(":"))
		{
			String[] comandoEArgomenti = line.split(":");
			String argomenti = comandoEArgomenti[ARGOMENTI];
			String[] partiArgomenti = argomenti.split(",");
			String tessera = partiArgomenti[TESSERA];

			new ExtraParser(tessera);

			// start:tile,name,color,num
			if (line.matches("start:" + REG_TESSERA + ",(black|green|red|yellow|blue)" + "," + "\\d+"))
			{
				String tesseraStart = partiArgomenti[TESSERA_START];
				String name = partiArgomenti[NOME];
				String color = partiArgomenti[COLORE_START];
				String numero = partiArgomenti[NUMERO];

				Color coloreGiocatore = ColoriGioco.getColor(color);
				this.proxy.fire(new InizioGiocoEvent(this, tesseraStart, coloreGiocatore, Integer
						.parseInt(numero), name));

			}
			// update:tile,2,3
			if (line.matches("update:" + REG_TESSERA + ",\\-?\\d+\\,\\-?\\d+"))
			{
				int x = Integer.parseInt(partiArgomenti[X]);
				int y = Integer.parseInt(partiArgomenti[Y]);
				this.proxy.fire(new UpdatePositionEvent(tessera, new Coordinate(x, y), this));

				return false;
			}
			// update:tile 2,3, update:tile 3,3, update: tile 4,3
			if (line.matches("update:" + REG_TESSERA + ",\\-?\\d+\\,\\-?\\d+,"))
			{
				String x = partiArgomenti[X];
				String y = partiArgomenti[Y];
				this.partiDiEventoComposto.add(tessera);
				this.partiDiEventoComposto.add(x);
				this.partiDiEventoComposto.add(y);
				return true; // ci sarà da leggere ancora

			}

		}
		else
		{
			if (line.contains(":"))
			{
				String[] comandoEArgomenti = line.split(":");
				String argomenti = comandoEArgomenti[ARGOMENTI];
				// turn:color;
				if (line.matches("turn:(black|green|red|yellow|blue)"))
				{
					/*
					 * metti la roba in lista poi quando ritornerà false sarà
					 * perchè avrà tutti gli elementi necessari per comporre
					 * l'evento
					 */
					String colore = argomenti;
					this.partiDiEventoComposto.add(colore);
					return true;
				}
				// es next: blabla
				if (line.matches("next:" + REG_TESSERA))
				{
					String colore = this.partiDiEventoComposto.get(0);
					String tessera = argomenti;
					new ExtraParser(tessera);
					this.proxy.fire(new UpdateTurnoEvent(this, ColoriGioco.getColor(colore), tessera));

					return false;
				}
				// es rotate: blabla
				if (line.matches("rotated:" + REG_TESSERA))
				{
					String tessera = argomenti;
					new ExtraParser(tessera);
					this.proxy.fire(new UpdateRotationEvent(tessera, this));
					return false;

				}

			}
			else
			{
				if (line.equalsIgnoreCase("move not valid"))
				{
					this.proxy.fire(new MossaNonValidaEvent(this));
					return false;
				}
			}
		}

		return false;
	}

	private List<String>				partiDiEventoComposto;

	private Socket						socket;

	private Scanner						in;

	private CarcassonneSocketPrinter	out;

	private ProxyController				proxy;

	private static final int			ARGOMENTI		= 1;
	private static final String			REG_SCORES		= "red=\\d+,blue=\\d+,green=\\d+,yellow=\\d+,black=\\d+";
	// score:red=4,blue=0,green=0,yellow=0,black=0
	private static final String			REG_TESSERA		= ".+";
	private static final int			TESSERA			= 0;
	private static final int			NOME			= 1;
	private static final int			COLORE_START	= 2;
	private static final int			NUMERO			= 3;
	private static final int			X				= 1;
	private static final int			Y				= 2;
	private static final int			COLORE_SCORE	= 0;
	private static final int			NUM_SCORE		= 1;
	private static final int			TESSERA_START	= 0;

}
