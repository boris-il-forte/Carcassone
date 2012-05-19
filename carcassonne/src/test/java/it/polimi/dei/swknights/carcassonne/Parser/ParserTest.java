package it.polimi.dei.swknights.carcassonne.Parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;



public class ParserTest
{
	private static List<String>		listaTestOK;
	private static List<String>		listaTestKO;
	private static Queue<String>	listaTestOKOracolo;

	@BeforeClass
	public static void initializeTest() throws Exception
	{
		listaTestOK = new ArrayList<String>();
		listaTestOKOracolo = new ArrayDeque<String>();
		listaTestKO = new ArrayList<String>();
		String arrayOK[] = { "N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0",
				"NW=0 S=C W=S E=S NS=0 NE=0 WE=1 SE=0 SW=0 N=N,R",
				"N=N,R S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0",
				"N=N S=C W=S E=S,R NS=0 NE=0 NW=0 WE=1 SE=0 SW=0 U",
				"N=N S=C W=S E=S,R NS=0 NE=0 NW=0 WE=1 SE=0 SW=0 U" };
		for (String stringa : arrayOK)
		{
			listaTestOK.add(stringa);
		}
		String arrayOKOracolo[] = { "N C S S 0 0 0 1 0 0 ", "N,R C S S 0 0 0 1 0 0 ",
				"N,R C S S 0 0 0 1 0 0 ", "N C S S,R 0 0 0 1 0 0 U", "N C S S,R 0 0 0 1 0 0 U" };
		for (String stringa : arrayOKOracolo)
		{
			listaTestOKOracolo.add(stringa);
		}
		String arrayKO[] = { "PIPPO", "SW=0 U=R" };
		for (String stringa : arrayKO)
		{
			listaTestKO.add(stringa);
		}
	}

	@Test
	public void testStringheCorrette() throws Exception
	{
		for (String stringaDaTestareOk : listaTestOK)
		{
			String stringaDaTestareOracoloOk = listaTestOKOracolo.poll();
			StringBuilder stringBuilder = new StringBuilder();
			try
			{
				Parser parser = new Parser(stringaDaTestareOk);
				for (PuntoCardinale punto : PuntoCardinale.values())
				{
					stringBuilder.append(parser.getData(punto));
					stringBuilder.append(" ");
				}

				for (Bussola ago : Bussola.values())
				{
					stringBuilder.append(parser.getDataChar(ago));
					stringBuilder.append(" ");
				}
				stringBuilder.append(parser.getDataType());
				
				assertEquals(stringaDaTestareOracoloOk, stringBuilder.toString());

			}
			catch (InvalidStringToParseException e)
			{
				e.printStackTrace();
				fail(stringaDaTestareOk);
			}
		}
	}

	@Test
	public void testStringheScorrette() throws Exception
	{
		for (String stringaDaTestareKO : listaTestKO)
		{
			try
			{
				new Parser(stringaDaTestareKO);
			}
			catch (InvalidStringToParseException e)
			{
				assertTrue(true);
			}
		}
	}

}
