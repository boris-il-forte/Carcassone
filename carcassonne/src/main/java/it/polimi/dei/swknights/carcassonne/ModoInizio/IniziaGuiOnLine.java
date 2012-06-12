package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.IPAddressValidator;
import it.polimi.dei.swknights.carcassonne.Client.CarcassonneSocket;
import it.polimi.dei.swknights.carcassonne.Client.ProxyController.ProxyController;
import it.polimi.dei.swknights.carcassonne.Client.View.Gui.Gui;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;

import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IniziaGuiOnLine extends Inizio
{

		@Override
		public void inizia()
		{
			try
			{
				this.printer.println("gui on line");
				String ip = this.chiediIndirizzoIP();
				View view = new Gui(); // 1)
				Socket socket = CarcassonneSocket.dammiSocket(ip, 1984); //TODO: correggere!
				ProxyController controller;

				
				controller = new ProxyController(socket);
				controller.addListener(view);
				// 2
				view.addListener(controller);

				
				this.superStarDestroyer.execute(view); // 4)
				this.superStarDestroyer.execute(controller);
			}
			catch (IOException e)
			{
				e.printStackTrace();
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
			} while (!(ipValidator.validate(ip) || ip.equals("")));
			return (ip.equals(""))? "127.0.0.1" : ip;
		}

	}
