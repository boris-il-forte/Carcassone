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

	public void  addGiocatoreConnesso(Socket socket)
	{
		this.connessione.addGiocatore(socket);
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
		while (nonCacciatoUser)
		{

			while (true)
			{
				try
				{
					connessione.riceviInput();
					//connessione.generaEvento();
					
				}
				catch (IOException e)
				{
					
					
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
