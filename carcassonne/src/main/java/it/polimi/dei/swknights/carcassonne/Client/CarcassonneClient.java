package it.polimi.dei.swknights.carcassonne.Client;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.AbstractConnessioneController;
import it.polimi.dei.swknights.carcassonne.Server.ProxyView.AbstractConnessioneView;

import java.net.Socket;
import java.util.Scanner;

public class CarcassonneClient
{

	public void run()
	{
		boolean again = true;
		while (again)
		{
			try

			{
				Socket socket = new Socket(indirizzoServer, PORTA_GF);
				Debug.print("Connection established");

				Scanner socketIn = new Scanner(socket.getInputStream());
				socketOut = new  CarcassonneSocketPrinter(socket.getOutputStream());
				Scanner stdin = new Scanner(System.in);

				while (true)
				{
					Debug.print("inserisci qualcosa di sensato! :");
					String inputLine = stdin.nextLine();
					socketOut.println(inputLine); // invia in rete ! (o cmq in
													// uscita dal socket)

					// il server non risponde qua ()
				}

			}
			catch (Exception e)
			{
				Debug.print("something wrogn happened.. \n  Want to try again?");
				e.printStackTrace();
				Scanner s = new Scanner(System.in);
				String risposta = s.nextLine();
				if (risposta.compareToIgnoreCase("Y") == 0 || risposta.compareToIgnoreCase("Yes") == 0
						|| risposta.compareToIgnoreCase("si") == 0 || risposta.compareToIgnoreCase("ok") == 0)
				{
					again = true;
				}
			}
		}
	}

	private CarcassonneSocketPrinter        socketOut;
	
	public static final int					MAX_TRY			= 100;

	public static final String				indirizzoServer	= "127.0.0.1";

	public static final int					PORTA_GF		= 1984;

	public AbstractConnessioneView			view;

	public AbstractConnessioneController	controller;

	public Integer							idPartita;

}
