package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;
import it.polimi.dei.swknights.carcassonne.Server.ServerRMI;
import it.polimi.dei.swknights.carcassonne.Util.IPAddressValidator;

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
			
			this.print("cli on line");
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

			this.execute(view);
			this.execute(controller);
		}
		catch (IOException e)
		{
			this.print("Server Unreachable");
		}
		catch (NotBoundException e)
		{
			this.print("Server Unreachable - RMI error");
		}
	}

	private boolean vuoleRMI()
	{
		this.flush();
		Scanner scanner = new Scanner(System.in);
		String tecnologia;
		boolean vuoleRMI = false;
		
		do
		{
			this.print("Che tecnologia vuoi usare?");
			
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
			this.print("Inserisci indirizzo ip del server: ");
			try
			{
				ip = scanner.nextLine();
			}
			catch (InputMismatchException e)
			{
				this.print("Input non valido");
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
			this.print("Inserisci numero di porta");
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
