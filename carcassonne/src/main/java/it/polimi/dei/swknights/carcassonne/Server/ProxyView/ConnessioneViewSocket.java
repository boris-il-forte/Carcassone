package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocketPrinter;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ConnessioneViewSocket extends ConnessioneView
{

	public ConnessioneViewSocket(Socket socket, ProxyView proxy, int numeroConnessione) throws IOException
	{
		super(numeroConnessione);
		this.socket = socket;
		InputStream input = socket.getInputStream();
		OutputStream output = socket.getOutputStream();
		this.in = new Scanner(input);
		this.out = new CarcassonneSocketPrinter(output);
		this.proxy = proxy;

	}

	@Override
	public void run()
	{

		while (this.in.hasNext())
		{

			String stringaDaSocket = this.in.nextLine();

			if (this.getColoreConnessione().equals(this.proxy.getColoreCorrente()))
			{
				this.parsingStringa(stringaDaSocket);
			}
		}

	}

	public void invia(String string)
	{
		this.out.println(string);
	}

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

	private void parsingStringa(String line)
	{
		if (line.contains(",") && line.contains(":"))
		{
			if (line.matches("place:\\-?\\d+\\,\\-?\\d+")) // es place: 2,3
			{
				String[] partiPlace = line.split(":");
				String coord = partiPlace[PLACE_COORD];
				String[] partiCoord = coord.split(",");
				int x = Integer.parseInt(partiCoord[X]);
				int y = Integer.parseInt(partiCoord[Y]);
				this.proxy.fire(new PlaceEvent(this, new Coordinate(x, y)));
			}// es. reconnect: yellow,PARTITA02
		}
		else
		{
			if (line.contains(":"))
			{
				if (line.matches("tile:[NSWE]")) // es tile: c1
				{
					String[] partiTile = line.split(":");
					String side = partiTile[SIDE];
					PuntoCardinale punto = TokenCardinale.cardinalPointOf(side);
					this.proxy.fire(new TileEvent(this, this.getColoreConnessione(), punto));
				}
			}
			else
			{
				if (line.equalsIgnoreCase("rotate"))
				{
					this.proxy.fire(new RotateEvent(this));
				}
				if (line.equalsIgnoreCase("pass"))
				{
					this.proxy.fire(new PassEvent(this));
				}
			}
		}
	}

	private Socket						socket;

	private ProxyView					proxy;

	private Scanner						in;

	private CarcassonneSocketPrinter	out;

	private static final int			X			= 0;

	private static final int			Y			= 1;

	private static final int			SIDE		= 1;

	private static final int			PLACE_COORD	= 1;

	private enum TokenCardinale {
		N(PuntoCardinale.nord), S(PuntoCardinale.sud), W(PuntoCardinale.ovest), E(PuntoCardinale.est);

		private TokenCardinale(PuntoCardinale punto)
		{
			this.punto = punto;
		}

		public static PuntoCardinale cardinalPointOf(String punto)
		{
			return TokenCardinale.valueOf(punto).punto;
		}

		private PuntoCardinale	punto;
	}

}
