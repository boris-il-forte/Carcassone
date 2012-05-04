package it.polimi.dei.swknights.carcassonne.exceptions;

public class PartitaFinitaException extends Exception
{

	public PartitaFinitaException()
	{
		super("Le tessere sono terminate: La partita è conclusa");
	}
	private static final long serialVersionUID = 9013683308135792500L;

}
