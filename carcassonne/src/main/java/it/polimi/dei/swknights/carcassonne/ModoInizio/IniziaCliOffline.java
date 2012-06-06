package it.polimi.dei.swknights.carcassonne.ModoInizio;

import java.util.InputMismatchException;
import java.util.Scanner;

import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

public class IniziaCliOffline extends Inizio
{

	@Override
	public void inizia()
	{
		ModuloModel model = new ModuloModel();
		Controller controller = new ModuloController(model);

		this.printer.println("cli!!");
		View view = new Cli(); // 1)

		view.addListener(controller); // 2)
		model.addListener(view); // 3)
		int numeroGiocatori = this.setGiocatori();
		for (int i = 0; i < numeroGiocatori - 1; i++)
		{
			model.addPlayer();
		}
		this.superStarDestroyer.execute(view); // 4)
		this.superStarDestroyer.execute(controller);
	}

	private int setGiocatori()
	{
		int numeroGiocatori = 0;
		do
		{
			Scanner scanner = new Scanner(System.in);
			this.printer.println("Inserisci numero giocatori (max 5, min 2)");
			this.printer.flush();
			try
			{
				numeroGiocatori = scanner.nextInt();
			}
			catch (InputMismatchException e)
			{
				printer.println("Input non valido");
			}
		} while (numeroGiocatori < 2 || numeroGiocatori >= 5);
		printer.println(numeroGiocatori);
		return numeroGiocatori;
	}

}
