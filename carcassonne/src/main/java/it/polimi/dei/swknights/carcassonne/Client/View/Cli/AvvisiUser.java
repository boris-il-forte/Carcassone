package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.AdapterColore;
import it.polimi.dei.swknights.carcassonne.Coordinate;
import it.polimi.dei.swknights.carcassonne.Client.View.FasiTurno;
import it.polimi.dei.swknights.carcassonne.Client.View.Vicinato;
import it.polimi.dei.swknights.carcassonne.Events.AdapterTessera;

import java.awt.Color;
import java.io.PrintWriter;

public class AvvisiUser
{

	public void chiediComando()
	{
		AdapterColore colore = new AdapterColore(this.coloreGiocatore);
		this.notificaVideo("Tocca a te giocatore " + colore.toName() + "!");
		this.notificaVideo("Inserisci comando");
		this.notificaVideo(faseTurno.toString());

	}

	public void setColore(Color colore)
	{
		this.coloreGiocatore = colore;

	}

	public AvvisiUser(PrintWriter out)
	{
		this.out = out;
	}

	public void notificaMossaNonValida()
	{
		this.notificaVideo("Mossa non valida!");
	}

	public void mostraTesseraCorrente()
	{

		// DatiMappa datiDeg = new DatiMappa(min, max)
		Stampante stampanteTessera = new Stampante();
		Vicinato vicinatoVuoto = new Vicinato(false);
		stampanteTessera.addTessera(new Coordinate(0, 0), this.tesseraCorrente.toCliString(), vicinatoVuoto);

		this.out.print(stampanteTessera);
		this.out.flush();

	}

	public void setPhase(FasiTurno faseTurno)
	{
		this.faseTurno = faseTurno;
	}

	public void setTesseraCorrente(AdapterTessera tessera)
	{
		this.tesseraCorrente = tessera;
	}

	public void notificaFinePartita()
	{
		this.notificaVideo("Fine Partita!");
	}

	private void notificaVideo(String message)
	{
		this.out.println(message);
		this.out.flush();

	}

	private AdapterTessera	tesseraCorrente;

	private PrintWriter		out;
	
	private FasiTurno		faseTurno;

	private Color			coloreGiocatore;

}
