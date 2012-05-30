package it.polimi.dei.swknights.carcassonne;

public class GestoreFasi
{
	public GestoreFasi()
	{
		this.faseCorrente = FaseTurno.PreparazioneGioco;
	}

	public FaseTurno getCurrentFase()
	{
		return this.faseCorrente;
	}

	public FaseTurno getNextFase(AzioneGioco azione)
	{
		this.faseCorrente = azione.getNextFase();
		return this.faseCorrente;
	}
	
	public void cominciaTurno()
	{
		this.faseCorrente = FaseTurno.Inizio;
	}

	private FaseTurno	faseCorrente;

}
