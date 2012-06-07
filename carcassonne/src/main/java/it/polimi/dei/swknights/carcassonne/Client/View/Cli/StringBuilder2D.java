package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 * A graphical tool used in CLI mode to simulate a 2D space, it basically
 * provide a 2D buffer. in where write. It is easy with it print something like
 * that
 * 
 * **************** * C1 * * * ****************
 * 
 * or hopefully even better.
 * 
 * @author edoardopasi & dave
 * 
 */

public class StringBuilder2D
{
	public StringBuilder2D(Dimension dimensione)
	{
		this.dimensione = dimensione;
		this.creaLinee();
	}

	/**
	 * Writes a character in the given coordinates of the buffer.
	 * 
	 * @param coordinate
	 * @param c
	 *            the character to write
	 */
	public void scriviCarattere(Coordinate coordinate, char c)
	{
		StringBuilder linea = this.linee.get(coordinate.getY());
		linea.setCharAt(coordinate.getX(), c);
	}

	/**
	 * Used to write a row or a column of symbols in the buffer. eg
	 * ################
	 * 
	 * @param start
	 *            the coordinate to start from
	 * @param end
	 *            the destination coordinate
	 * @param c
	 *            the character to fill with
	 * @throws IllegalArgumentException
	 */
	public void fillConCarattere(Coordinate start, Coordinate end, char c) throws IllegalArgumentException
	{
		if (!this.coordinateAccettabili(start, end)) { throw new IllegalArgumentException(
				"Coordinate non accettabili"); }
		int deltaX = end.getX() - start.getX();
		int deltaY = end.getY() - start.getY();
		Coordinate incrementoRelativo = new Coordinate(deltaX, deltaY).getVersore();

		for (Coordinate coordinate = start.getCoordinateA(incrementoRelativo); !coordinate.equals(end); coordinate = coordinate
				.getCoordinateA(incrementoRelativo))
		{
			this.scriviCarattere(coordinate, c);
		}
	}

	/**
	 * Write a string in the given position
	 * 
	 * @param coordinataInserimento
	 * @param string
	 */
	public void scriviStringa(Coordinate coordinataInserimento, String string)
	{
		StringBuilder linea = this.linee.get(coordinataInserimento.getY());
		int inizio = coordinataInserimento.getX();
		linea.replace(inizio, inizio + string.length(), string);
	}

	/**
	 * Write a string in the given position, in the center of the drawn card
	 * 
	 * @param coordinate
	 * @param coordinateString
	 */
	public void scriviStringaCentrata(Coordinate coordinate, String coordinateString)
	{
		StringBuilder linea = this.linee.get(coordinate.getY());
		int inizio = coordinate.getX() - coordinateString.length() / 2;
		linea.replace(inizio, inizio + coordinateString.length(), coordinateString);
	}

	/**
	 * returns a char in the given position
	 * 
	 * @param coordinate
	 * @return
	 */
	public char getCharAt(Coordinate coordinate)
	{
		StringBuilder linea = this.linee.get(coordinate.getY());
		return linea.charAt(coordinate.getX());
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(this.buildBound());
		for (StringBuilder linea : this.linee)
		{
			builder.append(this.buildHeader());
			builder.append(linea.toString());
			builder.append(this.buildTrailer());
		}
		builder.append(this.buildBound());
		return builder.toString();
	}

	private String buildBound()
	{
		if (this.isTesseraSingola()) { return ""; }
		StringBuilder boundBuilder = new StringBuilder();
		boundBuilder.append(this.buildHeader());
		int lenght = this.linee.get(0).length();
		for (int i = 0; i < lenght; i++)
		{
			boundBuilder.append("-");
		}
		boundBuilder.append(this.buildTrailer());
		return boundBuilder.toString();
	}

	private boolean isTesseraSingola()
	{
		final int singleHeight = 8;
		final int singleWidht = 15;
		return this.dimensione.getHeight() == singleHeight && this.dimensione.getWidth() == singleWidht;
	}

	private String buildHeader()
	{
		if (this.isTesseraSingola())
		{
			return "";
		}
		else
		{
			return "|";
		}

	}

	private String buildTrailer()
	{
		return this.buildHeader() + NEW_LINE;
	}

	private boolean coordinateAccettabili(Coordinate coordinata1, Coordinate coordinata2)
	{
		if (coordinata1.equals(coordinata2)) { return false; }
		if (!coordinata2.getX().equals(coordinata1.getX()) && !coordinata2.getY().equals(coordinata1.getY())) { return false; }

		return true;

	}

	private void creaLinee()
	{
		this.linee = new ArrayList<StringBuilder>();
		for (int i = 0; i < this.dimensione.height; i++)
		{
			this.linee.add(new StringBuilder());
		}
		this.azzeraLinee();

	}

	private void azzeraLinee()
	{
		int larghezza = this.dimensione.width;
		StringBuilder inizializzatore = new StringBuilder();

		for (int i = 0; i < larghezza; i++)
		{
			inizializzatore.append(' ');
		}
		for (StringBuilder linea : this.linee)
		{
			linea.append(inizializzatore);
		}
	}

	private Dimension			dimensione;

	private List<StringBuilder>	linee;

	private static final String	NEW_LINE	= System.getProperty("line.separator");

}
