package it.polimi.dei.swknights.carcassonne.Client.ProxyController;


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
	public void request()
	{
	}
	
	private ConnessioneController connessione;
	
}
