package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PassEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.PlaceEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.RotateEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.TileEvent;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
		this.out = new PrintWriter(output);
		this.proxy = proxy;

	}

	@Override
	public void run()
	{
		Debug.print("sono connessione socket numero " + this.getNumeroConnessione());
		while (this.in.hasNext())
		{
			String stringaDaSocket = this.in.nextLine();
			this.parsingStringa(stringaDaSocket);
		}
		// notifica chiusura al proxy
	}

	public void invia(String string)
	{
		Debug.print(" Connessione view socket " + string);
		this.out.print(string);
		this.out.flush();
	}

	public void close()
	{
		try
		{
			this.socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private void parsingStringa(String line)
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
				this.proxy.fire(new PlaceEvent(this, new Coordinate(x, y)));
			}// es. reconnect: yellow,PARTITA02

			if (line.matches("reconnect: (black|green|red|yellow|blue),.+"))
			{
				String[] recoPart = line.split(": ");
				String dopoReco = recoPart[DOPO_RECONNECT];
				String[] colorEPartita = dopoReco.split(",");
				String colore = colorEPartita[COLOR];
				String nomePartita = colorEPartita[PARTITA];
				// /proxy.fire(new )
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
					PuntoCardinale punto = this.puntoDaSigla(side);
					this.proxy.fire(new TileEvent(this, null, punto));

				}
			}
			else
			{
				if (line.equalsIgnoreCase("connect"))
				{

				}
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

	private PuntoCardinale puntoDaSigla(String side)
	{

		if (side.compareTo("N") == 0) { return PuntoCardinale.nord; }
		if (side.compareTo("S") == 0) { return PuntoCardinale.sud; }
		if (side.compareTo("W") == 0) { return PuntoCardinale.ovest; }
		if (side.compareTo("E") == 0) { return PuntoCardinale.est; }

		return null; // TODO throw new cccc

	}

	private Socket				socket;

	private ProxyView			proxy;

	private Scanner				in;

	private PrintWriter			out;

	private static final int	X				= 0;

	private static final int	Y				= 1;

	private static final int	SIDE			= 1;

	private static final int	PARTITA			= 1;

	private static final int	COLOR			= 0;

	private static final int	DOPO_RECONNECT	= 1;

	private static final int	PLACE_COORD		= 1;

}
