package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import java.io.PrintWriter;
import java.util.Scanner;

import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Client.View.DatiMappa;
import it.polimi.dei.swknights.carcassonne.Client.View.View;

public class Cli extends View
{
	public Cli()
	{
		super();
		this.out = new PrintWriter(System.out);
		this.in = new Scanner(System.in);
	}
	
	
	@Override
	public void aggiornaMappa()
	{
		//TODO: tutto sbagliato
		Coordinate min,max;
		min = max = null;
		DatiMappa datiMappa = new DatiMappa(min, max);
		StampaMappa stampante = new StampaMappa(datiMappa);
		//Da qui in teoria è giusto
		String mappa = stampante.toString();
		this.out.print(mappa);
		this.out.flush();
	}
	
	public void getInput()
	{
		//TODO: manca tutto anche qui
		this.in.next();
	}


	private PrintWriter out;
	
	private Scanner in;
}
