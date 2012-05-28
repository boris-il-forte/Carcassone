package it.polimi.dei.swknights.carcassonne.Client.View;

public enum FasiTurno {
	Inizio("Place card or rotate"), Media("Tile or pass"), Attesa("wait server response..."), PreparazioneGioco("loading, please wait");

	private FasiTurno(String messaggio)
	{
		this.messaggioUtente = messaggio;
	}

	@Override
	public String toString()
	{
		return this.messaggioUtente;
	}

	// TODO: controllare
	public FasiTurno nextPhase()
	{
		switch (this)
		{
			case Inizio:
				return Media;
			case Media:
				return Attesa;
			case Attesa:
				return Inizio;

			default:
				return Attesa;
		}
	}

	private String	messaggioUtente;

}