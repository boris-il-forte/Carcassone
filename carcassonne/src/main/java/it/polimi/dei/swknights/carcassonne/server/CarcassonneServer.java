package it.polimi.dei.swknights.carcassonne.server;

import it.polimi.dei.swknights.carcassonne.Debug;
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
	

			this.print("Starting Server");
			
			ServerSocket serverSocket = null;

			while(true)
			{
				try
				{
				serverSocket= new ServerSocket(PORTA_DEL_GRANDE_FRATELLO);
				Socket socket;
				socket = serverSocket.accept();
				Debug.print("parte il timer!");
				Timer timerConn = new Timer();
				timerConn.setWhatIsLock(this);
				Executor executor = Executors.newSingleThreadExecutor();
				executor.execute(timerConn);
				this.gestisciConnessione(socket);
				}
				catch(IOException e)
				{
					
				}
			}


	}

	public void creaNuovaPartita()
	{
		Partita partita = new Partita();
		this.partite.add(partita);
	}

	private void gestisciConnessione(Socket socket)
	{
		this.giocatoriAttivi++;
		
		if(this.giocatoriAttivi <= GIOCATORI_PARTITA  )
		{
			this.proxyView = this.partite.get(this.giocatoriAttivi-1).getPorxyView();
			proxyView.addGiocatoreConnesso(socket);
		}
		else
		{
			return;
		}

	}

	private void print(String screenMessage)
	{
		this.printer.print(screenMessage);
		this.printer.flush();
	}
	
	private final int GIOCATORI_PARTITA =5;
	
	private ProxyView proxyView ;
	
	private int giocatoriAttivi =0 ;
	
	private List<Partita>	partite;

	private static final int	PORTA_DEL_GRANDE_FRATELLO	= 1984;

	private class Timer implements Runnable
	{

		public void run()
		{
			while(true)
			{
				try
				{
					this.wait(TIMEOUT);

					Debug.print("passati 2 sec");
					synchronized (this.lock)
					{
						this.lock.notifyAll();
					}
					
				}
				catch (InterruptedException e)
				{
					
					e.printStackTrace();
				}
			}
			
			
			
		}
		
		public void setWhatIsLock(Object o)
		{
			this.lock = o;
		}
		
		private Object lock;
		
		private final int TIMEOUT = 20000; // millisec
		
	}


}




 
