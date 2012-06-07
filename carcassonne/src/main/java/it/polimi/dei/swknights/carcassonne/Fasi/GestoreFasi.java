package it.polimi.dei.swknights.carcassonne.Fasi;

import it.polimi.dei.swknights.carcassonne.Debug;

/**
 * Phase Handler, gives simple methods (getters basically) to know weather the
 * player can do some action.
 * 
 * @author dave
 * 
 */

public class GestoreFasi
{
	public GestoreFasi()
	{
		this.faseCorrente = FaseTurno.PreparazioneGioco;
	}

	public void cominciaTurno()
	{
		this.faseCorrente = FaseTurno.Inizio;
		Debug.print("fase: " + this.faseCorrente.toString());
	}

	public void finePartita()
	{
		this.faseCorrente = FaseTurno.FinePartita;
	}

	public void nextFase()
	{
		this.faseCorrente = this.faseCorrente.next();
		Debug.print("fase: " + this.faseCorrente.toString());
	}

	public boolean partitaOk()
	{
		return !this.faseCorrente.equals(FaseTurno.FinePartita);
	}

	public boolean posizionaOk()
	{
		return this.faseCorrente.equals(FaseTurno.Inizio);
	}

	public boolean ruotaOk()
	{
		return this.posizionaOk();
	}

	public boolean fineTurnoOk()
	{
		return this.faseCorrente.equals(FaseTurno.Media);
	}

	public boolean inputOk()
	{
		return this.faseCorrente.equals(FaseTurno.Inizio) || this.faseCorrente.equals(FaseTurno.Media);
	}

	public boolean partitaCominciata()
	{
		return !this.faseCorrente.equals(FaseTurno.PreparazioneGioco);
	}

	public FaseTurno getCurrentFase()
	{
		return this.faseCorrente;
	}

	private FaseTurno	faseCorrente;

}
