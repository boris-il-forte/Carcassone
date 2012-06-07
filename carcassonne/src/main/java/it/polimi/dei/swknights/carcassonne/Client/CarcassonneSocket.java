package it.polimi.dei.swknights.carcassonne.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class CarcassonneSocket
{
	
	public static Socket dammiSocket()
	{
		return CarcassonneSocket.dammiSocket("127.0.0.1");
		
	}
	
	
	public static Socket dammiSocket(String ipServer)
	{
		Socket socket=null;
		try
		{
			socket = new Socket(ipServer, PORTA_GF);
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
	
	
	
	
	private static final String indirizzoServer = "127.0.0.1";
	
	private static final int PORTA_GF = 1984;
}
