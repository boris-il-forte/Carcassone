package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Server.CarcassonneServer;
import it.polimi.dei.swknights.carcassonne.Util.IPAddressValidator;

import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class IniziaServer extends Inizio
{
	@Override
	public void inizia()
	{
		IPAddressValidator valido = new IPAddressValidator();
		Scanner scanner = new Scanner(System.in);
		String ip;
		this.print("Server");
		do
		{
			this.print("Indica l'indirizzo ip per raggiungere l'RMI registry: ");
			ip = scanner.nextLine();
			ip = ip.equals("") ? "127.0.0.1" : ip;			
		}while(!valido.validate(ip));
		CarcassonneServer server = new CarcassonneServer(ip);
		Executor superStarDestroyer = Executors.newFixedThreadPool(1);
		superStarDestroyer.execute(server);
	}
}
