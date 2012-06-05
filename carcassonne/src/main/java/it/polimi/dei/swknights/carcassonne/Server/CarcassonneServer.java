package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CarcassonneServer implements Runnable
{
	PrintWriter	printer	= new PrintWriter(System.out);

	public CarcassonneServer()
	{
		this.partite = new ArrayDeque<Partita>();
	}

	public final void run()
	{
		Debug.print("starting Server");
		ServerSocket serverSocket;
		while (true)
		{
			try
			{
				serverSocket = new ServerSocket(PORTA_DEL_GRANDE_FRATELLO);
				Socket socket;
				socket = serverSocket.accept();
				Debug.print("parte il timer!");
				Timer timerConn = new Timer();
				timerConn.setWhatIsLock(this);
				Executor executor = Executors.newSingleThreadExecutor();
				executor.execute(timerConn);
				this.gestisciConnessione(socket);
			}
			catch (IOException e)
			{

			}
		}

	}

	private void creaNuovaPartita()
	{
		Partita partita = new Partita();
		this.partite.add(partita);
	}

	private void gestisciConnessione(Socket socket)
	{
		try
		{
			this.giocatoriAttivi++;
			if (giocatoriAttivi == 1)
			{
				this.creaNuovaPartita();
			}
			Partita partita = this.partite.peekLast();
			partita.addPlayer();
			this.proxyView = partita.getProxyView();
			this.proxyView.accettaConnessione(socket);
			if (this.giocatoriAttivi == GIOCATORI_PARTITA)
			{
				this.giocatoriAttivi = 0;
				synchronized (this)
				{
					this.notifyAll();
				}
			}
		}
		catch (IOException e)
		{
			// TODO: annulla lo sbaglio fatto!
		}
	}

	private final int			GIOCATORI_PARTITA			= 5;

	private ProxyView			proxyView;

	private int					giocatoriAttivi				= 0;

	private Deque<Partita>		partite;

	private static final int	PORTA_DEL_GRANDE_FRATELLO	= 1984;

	private class Timer implements Runnable
	{
		public void setWhatIsLock(Object o)
		{
			this.lock = o;
		}

		public void run()
		{
			try
			{
				this.ciclaTimer();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		private void ciclaTimer() throws InterruptedException
		{
			do
			{
				this.wait(TIMEOUT);
				Debug.print("passati 20 sec");
				if (giocatoriAttivi < GIOCATORI_PARTITA)
				{
					synchronized (this.lock)
					{
						this.lock.notifyAll();
					}
				}

			} while (giocatoriAttivi == 1);
		}

		private Object				lock;

		private static final int	TIMEOUT	= 20000;	// millisec

	}

}
