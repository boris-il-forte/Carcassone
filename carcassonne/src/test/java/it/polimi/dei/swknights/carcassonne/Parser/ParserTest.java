package it.polimi.dei.swknights.carcassonne.Parser;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;

import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest
{
	private static Queue<String>	listaTestOK;
	private static Queue<String>	listaTestOKOracolo;
	private String			stringaDaTestare;
	private String	stringaDaTestareOracolo;

	@BeforeClass
	public static void initializeTest() throws Exception
	{
		listaTestOK = new ArrayDeque<String>();
		listaTestOKOracolo = new ArrayDeque<String>();
		String arrayOK[] =
		{ "N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0", "N=N,R S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0"};
		for (String stringa : arrayOK)
		{
			listaTestOK.add(stringa);
		}
		String arrayOKOracolo[] =
			{ "N C S S 0 0 0 1 0 0 ", "N,R C S S 0 0 0 1 0 0 " };
		for (String stringa : arrayOKOracolo)
		{
			listaTestOKOracolo.add(stringa);
		}
	}

	@Before
	public void prendiStringa() throws Exception
	{
		this.stringaDaTestare = listaTestOK.poll();
		this.stringaDaTestareOracolo = listaTestOKOracolo.poll();
	}

	@Test
	public void testStringheCorrette() throws Exception
	{
		StringBuilder stringBuilder = new StringBuilder();
		try
		{
			Parser parser = new Parser(this.stringaDaTestare);
			for (PuntoCardinale punto : PuntoCardinale.values())
			{
				stringBuilder.append(parser.getData(punto));
				stringBuilder.append(" ");
			}

			for (Bussola ago : Bussola.values())
			{
				stringBuilder.append(parser.getData(ago));
				stringBuilder.append(" ");
			}

			assertEquals(this.stringaDaTestareOracolo, stringBuilder.toString());

		} catch (InvalidStringToParseException e)
		{
			fail();
		}
	}

}
