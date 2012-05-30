package it.polimi.dei.swknights.carcassonne;

/**
 * Enum to group user command rotate: rotate the tile place: place the tile
 * pass: not to place the marker title: place the marker
 * 
 * @author edoardopasi & dave
 * 
 */
public enum AzioneGioco {
	rotate(FaseTurno.Inizio), place(FaseTurno.Media), pass(FaseTurno.Attesa), tile(FaseTurno.Attesa);
	
	private AzioneGioco(FaseTurno nextFase)
	{
		this.nextFase = nextFase;
	}
	
	public FaseTurno getNextFase()
	{
		return this.nextFase;
	}
	
	private FaseTurno	nextFase;

}
