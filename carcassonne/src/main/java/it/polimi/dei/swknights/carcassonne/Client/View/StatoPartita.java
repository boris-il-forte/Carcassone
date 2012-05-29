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
	public boolean possoParlare()
	{
		return possoParlare;
	}
	public void setPossoParlare(boolean possoParlare)
	{
		this.possoParlare = possoParlare;
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
	private boolean	possoParlare		= false;
	private boolean	partitaCominciata	= false;
	
}
