package it.polimi.dei.swknights.carcassonne.server.ProxyView;

import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ProxyView extends AbstractConnessioneView
{

	public ConnessioneView	connessione;

	public ProxyView(Socket socket)
	{
		this.connessione = new ConnessioneViewSocket(socket);
	}

	public ProxyView() // RMI
	{
		this.connessione = new ConnessioneViewRMI();
	}

	@Override
	public void request()
	{
	}

	@Override
	public void run()
	{
		int n = 0;
		boolean nonCacciatoUser = true;
		while (nonCacciatoUser && n<5)
		{

			while (true && n<5)
			{
				try
				{
					connessione.riceviInput();
					//connessione.generaEvento();
					n++;
				}
				catch (IOException e)
				{
					n++;
					//continue;
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}

			}

		}
		
		connessione.close();
	}

	private Socket	socket;

	@Override
	public void riceviModificheModel(ControllerEvent event)
	{
		connessione.inviaProtocolloPerEvento(event);
		
	}

}
