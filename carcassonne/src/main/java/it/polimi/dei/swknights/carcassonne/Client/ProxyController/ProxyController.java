package it.polimi.dei.swknights.carcassonne.Client.ProxyController;


import it.polimi.dei.swknights.carcassonne.Debug;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/*
 */
public class ProxyController extends AbstractConnessioneController
{

	
	public ProxyController(Socket socket)
	{
		this.connessione = new ConnessioneControllerSocket(socket);
	}
	public ProxyController() //RMI
	{
	
	}
	
	
	@Override
	public void run()
	{
		Debug.print(" sono proxy controller, run");
		int n = 0;
		boolean nonCacciatoUser = true;
		while (nonCacciatoUser && n<5)
		{

			while (true && n<5)
			{
	
					try
					{
						connessione.riceviInput();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					n++;
		

			}

		}
		
		connessione.close();
	}
	
	@Override
	public void request()
	{
	}
	
	private ConnessioneController connessione;
	
}
