package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocketPrinter;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.PassHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.PlaceHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.ProxyControllerHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.RuotaHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.TileHandler;
import it.polimi.dei.swknights.carcassonne.Events.Game.Controller.ControllerEvent;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*
 */
public class ProxyController extends AbstractConnessioneController 
{

	public ProxyController(Socket socket) throws IOException
	{

		this.connessione = new ConnessioneControllerSocket(socket, this);
		this.contattaServerInizia(socket);
		this.inizializzaHandlers();
		Executor imperial =Executors.newFixedThreadPool(1); //lancia per ascoltare risp
		imperial.execute(connessione);
		
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
		//
	}
	
	@Override
	public void riceviInput(ViewEvent event) 
	{
		for(ProxyControllerHandler handler : this.handlers)
		{
			event.accept(handler); //tells proxy what has to send (string if socket)
		}
		this.inviaSocket();
	}
	
	
	private void inviaSocket()
	{

		this.connessione.invia(this.requestString);
	}

	public void inviaRMI(ControllerEvent event)
	{
		this.connessione.invia(event);

	}

	@Override
	public void request()
	{
	}



	// inizio fatto comunque via socket?
	private void contattaServerInizia(Socket socket)
	{
		Debug.print(" sono proxy controller - contattaServer ");
		try
		{
			printer = new CarcassonneSocketPrinter(socket.getOutputStream());
			printer.println("connect");

		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
			if(false)
			{
			try
			{

				PrintWriter printer = new PrintWriter(socket.getOutputStream());
				printer.println("connection request by " + socket.getLocalAddress() + ": "
						+ socket.getLocalPort());
				printer.flush();
				Scanner scann = new Scanner(socket.getInputStream());
				String s = scann.nextLine();
				if (s.length() > 0)
				{
					contatto = true;
				}
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			}
		}
		*/
	}

	private void inizializzaHandlers()
	{
		this.handlers = new ArrayList<ProxyControllerHandler>();

		this.handlers.add(new PassHandler(this));
		this.handlers.add(new PlaceHandler(this));
		this.handlers.add(new RuotaHandler(this));
		this.handlers.add(new TileHandler(this));

	}

	CarcassonneSocketPrinter printer;
	private List<ProxyControllerHandler>	handlers;
	private String							requestString;
	private ConnessioneController			connessione;

}
