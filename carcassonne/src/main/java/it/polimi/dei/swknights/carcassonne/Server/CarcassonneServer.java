package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarcassonneServer implements Runnable
{
	PrintWriter	printer	= new PrintWriter(System.out);

	public CarcassonneServer()
	{
		this.partite = new ArrayDeque<Partita>();
		this.executor = Executors.newCachedThreadPool();
		this.timerScaduto = false;
		MonacoGong starter = new MonacoGong();
		this.executor.execute(starter);

	}

	public final void run()
	{
		Debug.print("starting Server");
		ServerSocket serverSocket=null;
		try
		{
			serverSocket = new ServerSocket(PORTA_DEL_GRANDE_FRATELLO);
			while (true)
			{

				
				Socket socket;
				socket = serverSocket.accept();
				Debug.print("sono carcassonne server-  ora chiamo gestisci connessione");
				this.gestisciConnessione(socket);

			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			Debug.print(" spengo server ");
			this.executor.shutdownNow();
		}
		finally
		{
			try
			{
				serverSocket.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			if (vuoleConnetteri(socket))
			{
				Debug.print("Carcassonne Server - ho ricevuto un connect: qualcuno vuole giocare");
				this.giocatoriAttivi++;
				if (this.giocatoriAttivi == 1)
				{
					this.creaNuovaPartita();
				}
				else if (this.giocatoriAttivi == 2)
				{
					Debug.print("due giocatori connessi: parte il timer!");
					Timer timerConn = new Timer();
					timerConn.setWhatIsLock(this);

					this.executor.execute(timerConn);
				}

				Partita partita = this.partite.peekLast();
				partita.addPlayer();

				this.proxyView = partita.getProxyView();
				this.proxyView.accettaConnessione(socket);
			}
			else
			{
				Debug.print("c' e qualcuno che mi manda roba sulla mia porta, al mio indirizzo, ma "
						+ "finche' non mi dice connect non lo faccio giocare!");
			}

		}
		catch (IOException e)
		{
			// TODO: annulla lo sbaglio fatto!
		}
	}

	private boolean vuoleConnetteri(Socket socket)
	{
		EspertoInizioConnessione espertoInizi = new EspertoInizioConnessione();
		return espertoInizi.vuoleConnettersi(socket);
	}

	private ExecutorService			executor;

	private boolean				timerScaduto;

	private final int			GIOCATORI_PARTITA			= 5;

	private ProxyView			proxyView;

	private Integer				giocatoriAttivi				= 0;

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
				Debug.print("ciclo timer");
				Thread.sleep(TIMEOUT);
				Debug.print("passati 20 sec");
				if (CarcassonneServer.this.giocatoriAttivi < CarcassonneServer.this.GIOCATORI_PARTITA)
				{
					synchronized (this.lock)
					{
						CarcassonneServer.this.timerScaduto = true;
						this.lock.notifyAll();

					}
				}

			} while (CarcassonneServer.this.giocatoriAttivi == 1);
		}

		private Object				lock;

		private static final int	TIMEOUT	= 2000;	// millisec

	}

	private class MonacoGong implements Runnable
	{

		public void run() // posso cominciare?
		{

			while (true)
			{
				if (CarcassonneServer.this.giocatoriAttivi == CarcassonneServer.this.GIOCATORI_PARTITA
						|| CarcassonneServer.this.timerScaduto)
				{
					Debug.print("DONNNNNG, cominciamo ");
					CarcassonneServer.this.partite.peekLast().cominciaPartita();
					CarcassonneServer.this.timerScaduto = false;
					CarcassonneServer.this.giocatoriAttivi = 0;
				}
				try
				{
					Thread.sleep(200);
				}
				catch (InterruptedException e)
				{
					Debug.print(" hai interrotto il sonno del monaco, maledetto! ");
				}

			}

		}

	}

}
