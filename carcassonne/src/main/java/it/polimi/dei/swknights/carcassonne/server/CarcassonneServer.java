package it.polimi.dei.swknights.carcassonne.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CarcassonneServer implements Runnable
{
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
	}

	private List<Partita>	partite;

	private static final int	PORTA_DEL_GRANDE_FRATELLO	= 1984;

}
