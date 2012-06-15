package it.polimi.dei.swknights.carcassonne.Client.View.Cli;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Util.Coordinate;

import java.awt.Dimension;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringBuilder2DTest
{

	@Test
	public void tutteFunzioniString2D()
	{
		StringBuilder2D sb = new StringBuilder2D(new Dimension(20, 30));
		sb.fillConCarattere(new Coordinate(0, 0), new Coordinate(0, 5), '?');

		assertTrue("", sb.getCharAt(new Coordinate(0, 1)) == '?' && 
				sb.getCharAt(new Coordinate(0, 2)) == '?' && 
				sb.getCharAt(new Coordinate(0, 3)) == '?');

		sb.fillConCarattere(new Coordinate(0, 0), new Coordinate(5, 0), '!');
		
		assertTrue("", sb.getCharAt(new Coordinate(1, 0)) == '!' && 
				sb.getCharAt(new Coordinate(2, 0)) == '!' && 
				sb.getCharAt(new Coordinate(3,0)) == '!');
		
		sb.scriviStringa(new Coordinate(3, 3), "(R)");
		assertTrue("", sb.getCharAt(new Coordinate(3, 3)) == '(' && 
				sb.getCharAt(new Coordinate(4, 3)) == 'R' && 
				sb.getCharAt(new Coordinate(5, 3)) == ')');
		
		
		
		sb.scriviCarattere(new Coordinate(1, 1), 'x');
		assertTrue("", sb.getCharAt(new Coordinate(1, 1)) == 'x');

		sb.scriviStringaCentrata(new Coordinate(4, 4), "[B]");
		assertTrue("", sb.getCharAt(new Coordinate(3, 4)) == '[' && 
				sb.getCharAt(new Coordinate(4, 4)) == 'B' && 
				sb.getCharAt(new Coordinate(5, 4)) == ']');
		
		
		
		
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{

				Debug.print("" + i + "," + j + sb.getCharAt(new Coordinate(i, j)));
			}
		}

	}

}
