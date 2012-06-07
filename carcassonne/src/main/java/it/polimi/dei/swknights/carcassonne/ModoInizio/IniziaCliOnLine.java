package it.polimi.dei.swknights.carcassonne.ModoInizio;

import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

import it.polimi.dei.swknights.carcassonne.IPAddressValidator;
import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;

public class IniziaCliOnLine extends Inizio
{

	@Override
	public void inizia()
	{
		try
		{
			this.printer.println("cli on line");
			String ip = this.chiediIndirizzoIP();
			View view = new Cli(); // 1)
			Socket socket = CarcassonneSocket.dammiSocket(ip); // 2)
			ProxyController controller;

			controller = new ProxyController(socket);

			// 2
			view.addListener(controller); // 2)
			this.superStarDestroyer.execute(view); // 4)
			this.superStarDestroyer.execute(controller);
		}
		catch (IOException e)
		{

		}
	}

	private String chiediIndirizzoIP()
	{
		IPAddressValidator ipValidator = new IPAddressValidator();
		String ip = null;
		do
		{
			Scanner scanner = new Scanner(System.in);
			this.printer.println("Inserisci indirizzo ip del server: ");
			this.printer.flush();
			try
			{
				ip = scanner.nextLine();
			}
			catch (InputMismatchException e)
			{
				this.printer.println("Input non valido");
			}
		} while (ipValidator.validate(ip) == false);
		return ip;
	}

}
