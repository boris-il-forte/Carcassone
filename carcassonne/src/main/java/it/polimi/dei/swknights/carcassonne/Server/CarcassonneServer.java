package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.PortaleRMIImpl;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.ProxyView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarcassonneServer implements Runnable
{
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
		this.lanciaServerRMI();
		ServerSocket serverSocket = null;
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
			Debug.print(" spengo server ");
			this.executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}

	protected void gestisciConnessione(PortaleRMIImpl portale)
	{
		Partita partita = this.aggiungiGiocatore();
		this.proxyView = partita.getProxyView();
		this.proxyView.accettaConnessione(portale);
	}

	private void gestisciConnessione(Socket socket)
	{

		try
		{
			if (vuoleConnettersi(socket))
			{
				Partita partita = this.aggiungiGiocatore();
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
			// TODO: annulla l'aggiunta del giocatore!!!!
		}
	}

	private void creaNuovaPartita()
	{
		Partita partita = new Partita();
		this.partite.add(partita);
	}

	private Partita aggiungiGiocatore()
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
		return partita;
	}

	private void lanciaServerRMI()
	{
		System.setSecurityManager(new RMISecurityManager());
		ServerRMI pt;
		try
		{
			java.rmi.registry.LocateRegistry.createRegistry(DEFAULT_RMI_REGISTRY_PORT);
			Debug.print("RMI registry ready.");
			pt = new ServerRMIImpl(this);
			Naming.rebind("//localhost/ServerRMI", pt);
			Debug.print("Ready ");
		}
		catch (RemoteException e)
		{
		}
		catch (MalformedURLException e)
		{
		}
	}

	private boolean vuoleConnettersi(Socket socket)
	{
		try
		{
			Scanner scanner = new Scanner(socket.getInputStream());

			try
			{
				String line = (scanner.nextLine());
				if (line.equals("connect"))
				{
					return true;
				}
				else
				{
					socket.close();
					return false;
				}
			}
			catch (NoSuchElementException e)
			{
				socket.close();
				return false;
			}

		}
		catch (IOException e)
		{
			Debug.print("esperto inizio - IOException");
			return false;
		}
	}

	private ExecutorService		executor;

	private boolean				timerScaduto;

	private ProxyView			proxyView;

	private Integer				giocatoriAttivi				= 0;

	private Deque<Partita>		partite;

	private static final int	GIOCATORI_PARTITA			= 5;

	private static final int	PORTA_DEL_GRANDE_FRATELLO	= 1984;

	private static final int	DEFAULT_RMI_REGISTRY_PORT	= 1099;

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
			}
		}

		private void ciclaTimer() throws InterruptedException
		{
			do
			{
				Debug.print("ciclo timer");
				Thread.sleep(TIMEOUT);
				Debug.print("passati 20 sec");
				if (CarcassonneServer.this.giocatoriAttivi < CarcassonneServer.GIOCATORI_PARTITA)
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

		private static final int	TIMEOUT	= 2000; // millisec

	}

	private class MonacoGong implements Runnable
	{
		public void run() // posso cominciare?
		{
			try
			{
				while (true)
				{
					if (CarcassonneServer.this.giocatoriAttivi == CarcassonneServer.GIOCATORI_PARTITA
							|| CarcassonneServer.this.timerScaduto)
					{
						Debug.print("DONNNNNG, cominciamo ");
						CarcassonneServer.this.partite.peekLast().cominciaPartita();
						CarcassonneServer.this.timerScaduto = false;
						CarcassonneServer.this.giocatoriAttivi = 0;
					}

					final int tempoDiPolling = 200;
					Thread.sleep(tempoDiPolling);

				}
			}
			catch (InterruptedException e)
			{
				Debug.print(" hai interrotto il sonno del monaco, maledetto! ");
				Debug.print("DIE HARD!");
			}
		}
	}

}
