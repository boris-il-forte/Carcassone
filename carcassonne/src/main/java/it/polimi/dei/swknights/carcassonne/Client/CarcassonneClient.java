package it.polimi.dei.swknights.carcassonne.Client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.AbstractConnessioneController;
import it.polimi.dei.swknights.carcassonne.server.ProxyView.AbstractConnessioneView;

public class CarcassonneClient
{
	
	public static void main(String [] a)
	{
		boolean again = true;
		while(again)
		{
			try
		
			{
				Socket socket = new Socket(indirizzoServer, PORTA_GF);
				Debug.print("Connection established");
				
				Scanner socketIn = new Scanner(socket.getInputStream());
				PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
				Scanner stdin = new Scanner(System.in);
				
				while (true) 
				{
					Debug.print("inserisci qualcosa di sensato! :");
					String inputLine = stdin.nextLine();
					socketOut.println(inputLine);
					socketOut.flush();
					//il server	non risponde qua (dove risponde?)
					
					
				}
				
			}
			catch(Exception e)
			{
				Debug.print("something wrogn happened.. \n  Want to try again?");
				e.printStackTrace();
				Scanner s = new Scanner(System.in);
				String risposta = s.nextLine();
				if(risposta.compareToIgnoreCase("Y")==0 || risposta.compareToIgnoreCase("Yes") ==0
						|| risposta.compareToIgnoreCase("si")==0 || risposta.compareToIgnoreCase("ok")==0)
				{
					again = true;
				}
			}
		}
	}
	
	
	public static final int MAX_TRY = 100;
	
	public static final String indirizzoServer = "127.0.0.1";
	
	public static final int PORTA_GF = 1984;
	
	public AbstractConnessioneView			view;

	public AbstractConnessioneController	controller;

	public Integer		idPartita;

}
