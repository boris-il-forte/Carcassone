package it.polimi.dei.swknights.carcassonne.server.ProxyView;

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
	public ProxyView() //RMI
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
		boolean  nonCacciatoUser=true;
		while(nonCacciatoUser)
		{
			
			while(true)
			{
				try
				{
					connessione.riceviInput();
					connessione.generaEvento();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
					connessione.close();
				}
			}
			
		}
	}


	
	
	private Socket socket;
	
	
	
	
	

}
