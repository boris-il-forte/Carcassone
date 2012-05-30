package it.polimi.dei.swknights.carcassonne;

public class GestoreFasi
{
	public GestoreFasi()
	{
		this.faseCorrente = FaseTurno.PreparazioneGioco;
	}

	public void nextFase()
	{
		this.faseCorrente = this.faseCorrente.next();
		System.out.println("chiamata next...");
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
	
	public boolean iniziaNuovoTurnoOk()
	{
		return this.faseCorrente.equals(FaseTurno.Attesa);
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
