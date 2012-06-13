package it.polimi.dei.swknights.carcassonne.ModoInizio;

import it.polimi.dei.swknights.carcassonne.Client.View.Cli.Cli;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.Controller;
import it.polimi.dei.swknights.carcassonne.ModuliAstratti.View;
import it.polimi.dei.swknights.carcassonne.Server.Controller.ModuloController;
import it.polimi.dei.swknights.carcassonne.Server.Model.ModuloModel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IniziaCliOffline extends Inizio
{

	@Override
	public void inizia()
	{
		ModuloModel model = new ModuloModel();
		Controller controller = new ModuloController(model);

		this.printer.println("cli - modalitàù offline");
		View view = new Cli();

		view.addListener(controller);
		model.addListener(view);
		int numeroGiocatori = this.setGiocatori();
		for (int i = 0; i < numeroGiocatori ; i++)
		{
			model.addPlayer();
		}
		this.superStarDestroyer.execute(view);
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
				this.printer.println("Input non valido");
			}
		} while (numeroGiocatori < MIN_PLAYER || numeroGiocatori > MAX_PLAYER);
		this.printer.println(numeroGiocatori);
		return numeroGiocatori;
	}

	private static final int	MIN_PLAYER	= 2;
	private static final int	MAX_PLAYER	= 5;
	


}
