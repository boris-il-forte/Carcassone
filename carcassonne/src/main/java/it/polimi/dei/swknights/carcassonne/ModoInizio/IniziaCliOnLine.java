package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.IPAddressValidator;
import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;
import it.polimi.dei.swknights.carcassonne.Server.ServerRMI;

import java.io.IOException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IniziaCliOnLine extends Inizio
{

	@Override
	public void inizia()
	{
		try
		{
			ProxyController controller;
			View view;
			
			this.printer.println("cli on line");
			String ip = this.chiediIndirizzoIP();
			if(this.vuoleRMI())
			{
				ServerRMI server = (ServerRMI) Naming.lookup("//"+ip+"/ServerRMI");
				controller = new ProxyController(server);
			}
			else
			{
				int numeroPorta = this.chiediNumeroDiPorta();
				Socket socket = CarcassonneSocket.dammiSocket(ip, numeroPorta);
				controller = new ProxyController(socket);
			}
			
			view = new Cli();

			controller.addListener(view);
			view.addListener(controller);

			this.superStarDestroyer.execute(view);
			this.superStarDestroyer.execute(controller);
		}
		catch (IOException e)
		{
			this.printer.println("Server Unreachable");
			this.printer.flush();
			System.exit(0);
		}
		catch (NotBoundException e)
		{
			this.printer.println("Server Unreachable - RMI error");
			this.printer.flush();
			e.printStackTrace();
			System.exit(0);
		}
	}

	private boolean vuoleRMI()
	{
		this.printer.flush();
		Scanner scanner = new Scanner(System.in);
		String tecnologia;
		boolean vuoleRMI = false;
		
		do
		{
			this.printer.println("Che tecnologia vuoi usare?");
			this.printer.flush();
			
			tecnologia = scanner.nextLine();
			if(tecnologia.equalsIgnoreCase("RMI") || tecnologia.equalsIgnoreCase(""))
			{
				vuoleRMI = true;
			}
			else if(tecnologia.equalsIgnoreCase("SOCKET"))
			{
				vuoleRMI = false;
			}
			else
			{
				tecnologia = null;
			}
			
		} while(tecnologia == null);
		
		
		return vuoleRMI;
		
	}

	private String chiediIndirizzoIP()
	{
		Scanner scanner = new Scanner(System.in);
		IPAddressValidator ipValidator = new IPAddressValidator();
		String ip = null;
		do
		{
			this.printer.println("Inserisci indirizzo ip del server: ");
			this.printer.flush();
			try
			{
				ip = scanner.nextLine();
			}
			catch (InputMismatchException e)
			{
				this.printer.println("Input non valido");
				this.printer.flush();
			}
		} while (!(ipValidator.validate(ip) || ip.equals("")));
		return (ip.equals("")) ? "127.0.0.1" : ip;
	}

	private int chiediNumeroDiPorta()
	{
		Scanner scanner = new Scanner(System.in);
		int risposta = 0;
		String input = "nulla arrivato";
		while (risposta == 0)
		{
			this.printer.print("Inserisci numero di porta");
			this.printer.flush();
			try
			{
				input = scanner.nextLine();
				risposta = Integer.parseInt(input);
			}
			catch (NumberFormatException e)
			{
				risposta = input.equals("") ? PORTA_GF : 0;
			}
		}

		return risposta;
	}

	private static final int	PORTA_GF	= 1984;

}
