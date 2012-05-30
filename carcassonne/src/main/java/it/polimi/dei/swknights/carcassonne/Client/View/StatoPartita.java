package it.polimi.dei.swknights.carcassonne.Client.View;

public class StatoPartita
{
	
	
	public boolean isPartitaCominciata()
	{
		return partitaCominciata;
	}
	public void setPartitaCominciata(boolean partitaCominciata)
	{
		this.partitaCominciata = partitaCominciata;
	}
	public boolean isPartitaFinita()
	{
		return partitaFinita;
	}
	public void setPartitaFinita(boolean partitaFinita)
	{
		this.partitaFinita = partitaFinita;
	}

	private boolean	partitaFinita		= false;

	private boolean	partitaCominciata	= false;
	
}
