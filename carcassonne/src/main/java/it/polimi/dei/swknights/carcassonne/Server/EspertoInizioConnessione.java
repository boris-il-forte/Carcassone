package it.polimi.dei.swknights.carcassonne.Server;

import it.polimi.dei.swknights.carcassonne.Debug;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EspertoInizioConnessione
{

	public static boolean vuoleConnettersi(Socket socket)
	{
		try
		{
			Scanner scanner = new Scanner( socket.getInputStream());
			
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
			catch(NoSuchElementException e)
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
}
