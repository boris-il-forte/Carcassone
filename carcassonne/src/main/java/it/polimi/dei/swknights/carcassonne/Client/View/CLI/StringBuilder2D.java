package it.polimi.dei.swknights.carcassonne.Client.View.CLI;

import it.polimi.dei.swknights.carcassonne.Coordinate;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class StringBuilder2D
{
	public StringBuilder2D(Dimension dimensione)
	{
		this.dimensione = dimensione;
		this.creaLinee();
	}

	public void scriviCarattere(Coordinate coordinate, char c)
	{
		StringBuilder linea = this.linee.get(coordinate.getY());
		linea.setCharAt(coordinate.getX(), c);
	}

	public void fillConCarattere(Coordinate start, Coordinate end, char c) throws IllegalArgumentException
	{
		if (!coordinateAccettabili(start, end))
			throw new IllegalArgumentException("Coordinate non accettabili");
		int deltaX = end.getX() - start.getX();
		int deltaY = end.getY() - start.getY();
		Coordinate incrementoRelativo = new Coordinate(deltaX, deltaY).getVersore();

		for (Coordinate coordinate = start.getCoordinateA(incrementoRelativo); coordinate.equals(end); coordinate = coordinate
				.getCoordinateA(incrementoRelativo))
		{
			this.scriviCarattere(coordinate, c);
		}
	}

	public void scriviStringa(Coordinate coordinataInserimento, String string)
	{
		// TODO Auto-generated method stub
		
	}

	public void scriviStringaCentrata(Coordinate coordinate, String coordinateString)
	{
		StringBuilder linea = this.linee.get(coordinate.getY());
		int inizio = coordinate.getX() - coordinateString.length() / 2;
		linea.replace(inizio, inizio + coordinateString.length(), coordinateString);
	}
	
	public char getCharAt(Coordinate coordinate)
	{
		StringBuilder linea = this.linee.get(coordinate.getY());
		return linea.charAt(coordinate.getX());
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for (StringBuilder linea : this.linee)
		{
			builder.append(linea.toString());
			builder.append(';');
		}
		return builder.toString();
	}

	private boolean coordinateAccettabili(Coordinate coordinata1, Coordinate coordinata2)
	{
		if (coordinata1.equals(coordinata2))
			return false;
		if (coordinata2.getX() != coordinata1.getX() && coordinata2.getY() != coordinata1.getY())
			return false;

		return true;

	}

	private void creaLinee()
	{
		this.linee = new ArrayList<StringBuilder>();
		for (int i = 0; i < this.dimensione.height; i++)
		{
			linee.add(new StringBuilder());
		}
		this.azzeraLinee();

	}

	private void azzeraLinee()
	{
		int larghezza = this.dimensione.width;
		StringBuilder inizializzatore = new StringBuilder();

		for (int i = 0; i < larghezza; i++)
			inizializzatore.append(' ');
		for (StringBuilder linea : this.linee)
		{
			linea.append(inizializzatore);
		}
	}

	private Dimension			dimensione;

	private List<StringBuilder>	linee;

}
