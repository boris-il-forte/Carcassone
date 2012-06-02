package it.polimi.dei.swknights.carcassonne.server;

import it.polimi.dei.swknights.carcassonne.server.ProxyView.ProxyView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CarcassonneServer implements Runnable
{
	PrintWriter printer = new PrintWriter(System.out);
	
	public CarcassonneServer()
	{
		this.partite = new ArrayList<Partita>();
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(this);
	}

	public final void run()
	{
		try
		{
			this.print("Starting Server");
			
			ServerSocket serverSocket = new ServerSocket(PORTA_DEL_GRANDE_FRATELLO);
			while(true)
			{
				Socket socket = serverSocket.accept();
				this.gestisciConnessione(socket);
			}
		}
		catch (IOException e)
		{
			return;
		}
	}

	public void creaNuovaPartita()
	{
		Partita partita = new Partita();
		this.partite.add(partita);
	}

	private void gestisciConnessione(Socket socket)
	{
		// TODO passare la socket al proxy del server...
		ProxyView proxy = new ProxyView(socket);
		proxy.run();
	}

	private void print(String screenMessage)
	{
		this.printer.print(screenMessage);
		this.printer.flush();
	}
	
	private List<Partita>	partite;

	private static final int	PORTA_DEL_GRANDE_FRATELLO	= 1984;

}
