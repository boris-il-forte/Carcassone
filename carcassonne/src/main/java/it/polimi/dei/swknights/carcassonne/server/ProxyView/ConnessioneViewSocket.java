package it.polimi.dei.swknights.carcassonne.server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Connessione.ComandiConnessione;
import it.polimi.dei.swknights.carcassonne.Events.Game.ComandiView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.EventObject;
import java.util.Scanner;

public class ConnessioneViewSocket extends ConnessioneView
{

	public ConnessioneViewSocket(Socket socket)
	{
		this.socket = socket;
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
		// legge e chiede al parser di capirla

		this.socket.getOutputStream();
		Scanner in = new Scanner(this.socket.getInputStream());
		PrintWriter out = new PrintWriter(this.socket.getOutputStream());

		// chiamata da proxy
		line = in.nextLine();
		System.out.println("Proxy view, ricevuta: " + line);
		// se line == connect inizia la partita
		return line;
	}

	@Override
	public void riceviInput() throws IOException
	{
		System.out.println("ricevi inpu ");
		this.datoCorrente = this.dammiUltimoDato();

	}

	@Override
	public EventObject generaEvento()
	{
		// TODO Auto-generated method stub
		return null;
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

	private String datoCorrente ;
	
	private static final int	PLACE_COORD		= 1;
	private static final int	X				= 0;
	private static final int	Y				= 1;
	private static final int	SIDE			= 1;
	private static final int	DOPO_RECONNECT	= 1;
	private static final int	PARTITA			= 1;
	private static final int	COLOR			= 0;


}
