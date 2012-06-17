package it.polimi.dei.swknights.carcassonne.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Static utility class use to create sockets
 * 
 * @author dave
 * 
 */
public final class CarcassonneSocket
{

	/**
	 * Default method to get the default socket for the localhost server on 1984
	 * port
	 * 
	 * @return the default socket
	 */
	public static Socket dammiSocket()
	{
		return CarcassonneSocket.dammiSocket(INDIRIZZO_SERVER, PORTA_GF);
	}

	/**
	 * Method to get a socket to connect a remote server
	 * 
	 * @param ipServer
	 *            the ip address of the server
	 * @param numeroPorta
	 *            the port number of the server
	 * @return a socket to comunicate to the server
	 */
	public static Socket dammiSocket(String ipServer, int numeroPorta)
	{
		Socket socket = null;
		try
		{
			socket = new Socket(ipServer, numeroPorta);
		}
		catch (UnknownHostException e)
		{
		}
		catch (IOException e)
		{
		}
		return socket;

	}

	private CarcassonneSocket()
	{
	}

	private static final String	INDIRIZZO_SERVER	= "127.0.0.1";

	private static final int	PORTA_GF			= 1984;
}
