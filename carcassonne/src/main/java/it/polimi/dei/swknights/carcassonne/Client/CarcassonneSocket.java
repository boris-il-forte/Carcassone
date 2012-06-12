package it.polimi.dei.swknights.carcassonne.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class CarcassonneSocket
{

	public static Socket dammiSocket()
	{
		return CarcassonneSocket.dammiSocket(INDIRIZZO_SERVER, PORTA_GF);

	}

	public static Socket dammiSocket(String ipServer, int numeroPorta)
	{
		Socket socket = null;
		try
		{
			socket = new Socket(ipServer, numeroPorta);
		}
		catch (UnknownHostException e)
		{

			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return socket;

	}
	

	private static final String	INDIRIZZO_SERVER	= "127.0.0.1";

	private static final int	PORTA_GF		= 1984;
}
