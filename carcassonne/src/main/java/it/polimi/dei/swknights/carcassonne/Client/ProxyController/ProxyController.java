package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.PassHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.PlaceHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.ProxyControllerHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.RuotaHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.TileHandler;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 */
public class ProxyController extends AbstractConnessioneController
{

	public ProxyController(Socket socket)
	{
		this.inizializzaHandlers();
		this.connessione = new ConnessioneControllerSocket(socket);
		this.contattaServerInizia(socket);
	}

	public ProxyController() // RMI
	{
		
		this.connessione = new ConnessioneControllerRMI();
	}

	public void setRequestString(String requestString)
	{
		this.requestString = requestString;
	}

	@Override
	public void run()
	{
		Debug.print(" sono proxy controller, run");
		int n = 0;
		boolean nonCacciatoUser = true;
		while (nonCacciatoUser && n < 5)
		{

			while (true && n < 5)
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

	
	private void inviaSocket()
	{

		connessione.invia(this.requestString);
	}

	private void inviaRMI(ControllerEvent event)
	{
		connessione.invia(event);

	}
	
	
	@Override
	public void request()
	{
	}

	// inizio fatto comunque via socket?
	private void contattaServerInizia(Socket socket)
	{
		try
		{
			Debug.print(" sono proxy controller - contattaServer ");
			PrintWriter printer = new PrintWriter(socket.getOutputStream());
			printer.println("connection request by " + socket.getLocalAddress() + ": "
					+ socket.getLocalPort());
			printer.flush();

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void inizializzaHandlers()
	{
		this.handlers = new ArrayList<ProxyControllerHandler>();

		this.handlers.add(new PassHandler(this));
		this.handlers.add(new PlaceHandler(this));
		this.handlers.add(new RuotaHandler(this));
		this.handlers.add(new TileHandler(this));

	}

	private List<ProxyControllerHandler>	handlers;
	private String							requestString;
	private ConnessioneController			connessione;

}
