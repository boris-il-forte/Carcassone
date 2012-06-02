package it.polimi.dei.swknights.carcassonne.Client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import it.polimi.dei.swknights.carcassonne.Client.ProxyController.AbstractConnessioneController;
import it.polimi.dei.swknights.carcassonne.server.ProxyView.AbstractConnessioneView;

public class CarcassonneClient
{
	
	public static void main(String [] a)
	{
		
		try
		{
			Socket socket = new Socket(indirizzoServer, PORTA_GF);
			System.out.println("Connection established");
			
			Scanner socketIn = new Scanner(socket.getInputStream());
			PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
			Scanner stdin = new Scanner(System.in);
			
			while (true) 
			{
				System.out.println("inserisci qualcosa di sensato! :");
				String inputLine = stdin.nextLine();
				socketOut.println(inputLine);
				socketOut.flush();
				String socketLine = socketIn.nextLine();
				System.out.println("socket line : " + socketLine);
				
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println("something wrogn happened..");
			e.printStackTrace();
		}
	}
	
	
	public static final String indirizzoServer = "127.0.0.1";
	
	public static final int PORTA_GF = 1984;
	
	public AbstractConnessioneView			view;

	public AbstractConnessioneController	controller;

	public Integer		idPartita;

}
