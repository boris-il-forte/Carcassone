package it.polimi.dei.swknights.carcassonne.server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTesseraObject;
import it.polimi.dei.swknights.carcassonne.Events.Connessione.ComandiConnessione;
import it.polimi.dei.swknights.carcassonne.Events.Game.ComandiView;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;
import it.polimi.dei.swknights.carcassonne.server.ProxyView.Handlers.InizioGiocoHandler;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConnessioneViewSocket extends ConnessioneView
{

	public ConnessioneViewSocket(Socket socket)
	{
		this.socket = socket;
		this.handlers = new ArrayList<ViewHandler>();
		this.handlers.add(new InizioGiocoHandler());
	}

	@Override
	public void inizializza()
	{

	}

	public void capisci(String line)
	{
		if (line.indexOf(",") != -1 && line.indexOf(":") != -1)
		{
			if (line.matches("place: \\-?\\d+\\,\\-?\\d+")) // es place: 2,3
			{
				String[] partiPlace = line.split(": ");
				String coord = partiPlace[PLACE_COORD];
				String[] partiCoord = coord.split(",");
				int x = Integer.parseInt(partiCoord[X]);
				int y = Integer.parseInt(partiCoord[Y]);
			}
			if (line.matches("reconnect: (black|green|red|yellow|blue),.+")) // es.
																				// reconnect:
																				// yellow,PARTITA02
			{
				String[] recoPart = line.split(": ");
				String dopoReco = recoPart[DOPO_RECONNECT];
				String[] colorEPartita = dopoReco.split(",");
				String colore = colorEPartita[COLOR];
				String nomePartita = colorEPartita[PARTITA];
			}
		}
		else
		{
			if (line.indexOf(":") != -1)
			{
				if (line.matches("tile: [SCsc][1-4]")) // es tile: c1
				{
					String[] partiTile = line.split(": ");
					String side = partiTile[SIDE];

				}
			}
			else
			{
				if (line.equalsIgnoreCase("connect"))
				{

				}
				if (line.equalsIgnoreCase("rotate"))
				{

				}
				if (line.equalsIgnoreCase("pass"))
				{

				}

			}
		}

	}

	public String dammiUltimoDato() throws IOException
	{
		String line = "";
		// connessione view socket

		this.socket.getOutputStream();
		Scanner in = new Scanner(this.socket.getInputStream());
		PrintWriter out = new PrintWriter(this.socket.getOutputStream());

		boolean got = false;
		
		while (got == false)
		{
			try
			{
				line = in.nextLine();
				got = true;
			}
			catch (Exception e)
			{
				Debug.print("nulla");
				got = false;
				continue;
			}
		}

		Debug.print("Connessione view Socket, ricevuta: " + line);
		// se line == connect inizia la partita
		return line;
	}

	@Override
	public void riceviInput() throws IOException
	{
		Debug.print("ricevi input \n ");
		this.datoCorrente = this.dammiUltimoDato();

	}

	@Override
	public EventObject generaEvento()
	{
		this.capisci(datoCorrente);
		return null;
	}
	
	
	@Override
 	public synchronized void riceviModificheModel(ControllerEvent event)
	{
		if (event instanceof InizioGiocoEvent)
		{
			InizioGiocoEvent ige = (InizioGiocoEvent) event;
			Debug.print(ige.toString());
		}
	}
	

	@Override
	public void close()
	{
		try
		{
			this.socket.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Socket				socket;

	private String				datoCorrente;

	private static final int	PLACE_COORD		= 1;
	private static final int	X				= 0;
	private static final int	Y				= 1;
	private static final int	SIDE			= 1;
	private static final int	DOPO_RECONNECT	= 1;
	private static final int	PARTITA			= 1;
	private static final int	COLOR			= 0;
	
	private List<ViewHandler>    handlers ;
	
	@Override
	public void inviaProtocolloPerEvento(ControllerEvent event)
	{
		
		for(ViewHandler handler :  this.handlers)
		{
			event.accept(handler);
		}
		
	}
	
	
	
	
	

}
