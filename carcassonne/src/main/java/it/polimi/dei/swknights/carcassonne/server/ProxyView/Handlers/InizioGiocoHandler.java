package it.polimi.dei.swknights.carcassonne.server.ProxyView.Handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.View.Handlers.ViewHandler;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.InizioGiocoEvent;

public class InizioGiocoHandler extends ViewHandler
{

	public InizioGiocoHandler(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void visit (InizioGiocoEvent event) 
	{
		Debug.print(event.toString());
		try
		{
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
			socketOut.println("view handler - inizio gioco handler - " + event.getGiocatore().toString());
			socketOut.flush();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	Socket socket;

}
