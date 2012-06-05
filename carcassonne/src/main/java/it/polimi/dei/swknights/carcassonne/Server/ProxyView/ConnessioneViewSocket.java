package it.polimi.dei.swknights.carcassonne.Server.ProxyView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConnessioneViewSocket extends ConnessioneView
{

	public ConnessioneViewSocket(Socket socket) throws IOException
	{
		this.socket = socket;
		InputStream input = socket.getInputStream();
		OutputStream output = socket.getOutputStream();
		this.in = new Scanner(input);
		this.out = new PrintWriter(output);
	}
	
	public void run()
	{
		do
		{
			String stringaDaSocket  = this.in.nextLine();
			this.parsingStringa(stringaDaSocket);
			
		}while(this.in.hasNext());
	}

	public void invia(String string)
	{
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
			}// es. reconnect: yellow,PARTITA02
			if (line.matches("reconnect: (black|green|red|yellow|blue),.+"))
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

	private Socket					socket;
	
	private Scanner					in;

	private PrintWriter				out;

	private static final int		X				= 0;
	
	private static final int		Y				= 1;
	
	private static final int		SIDE			= 1;

	private static final int		PARTITA			= 1;
	
	private static final int		COLOR			= 0;

	private static final int		DOPO_RECONNECT	= 1;

	private static final int		PLACE_COORD		= 1;  

}
