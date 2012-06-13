package it.polimi.dei.swknights.carcassonne.Client.ProxyController;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocketPrinter;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.PassHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.PlaceHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.ProxyControllerHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.RuotaHandler;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyControllerHandlers.TileHandler;
import it.polimi.dei.swknights.carcassonne.Events.Game.View.ViewEvent;
import it.polimi.dei.swknights.carcassonne.Server.ServerRMI;
import it.polimi.dei.swknights.carcassonne.Server.RMI.PortaleRMI;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProxyController extends AbstractConnessioneController
{
	public ProxyController(Socket socket) throws IOException
	{
		this();
		this.connessione = new ConnessioneControllerSocket(socket, this);
		this.contattaServerInizia(socket);
		this.avviaConnesisone(this.connessione);
	}

	public ProxyController(ServerRMI server) throws RemoteException
	{
		this();
		PortaleRMI portale = this.contattaServerInizia(server);
		this.connessione = new ConnessioneControllerRMI(portale, this);
		this.avviaConnesisone(this.connessione);
	}

	@Override
	public void riceviInput(ViewEvent event)
	{
		for (ProxyControllerHandler handler : this.handlers)
		{
			event.accept(handler);
		}
		this.invia(event);
	}

	@Override
	public void run()
	{
	}

	public void setRequestString(String requestString)
	{
		this.requestString = requestString;
	}

	private ProxyController()
	{
		this.inizializzaHandlers();
	}

	private void avviaConnesisone(ConnessioneController connessione)
	{
		Executor imperial = Executors.newFixedThreadPool(1);
		imperial.execute(connessione);
	}

	private PortaleRMI contattaServerInizia(ServerRMI server) throws RemoteException
	{
		return server.connect();
	}

	private void invia(ViewEvent event)
	{
		this.connessione.invia(this.requestString);
		this.connessione.invia(event);
	}

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

	private CarcassonneSocketPrinter		printer;

	private List<ProxyControllerHandler>	handlers;

	private String							requestString;

	private ConnessioneController			connessione;

}
