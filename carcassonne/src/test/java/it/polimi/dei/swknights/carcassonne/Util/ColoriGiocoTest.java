package it.polimi.dei.swknights.carcassonne.Util;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;


public class ColoriGiocoTest
{
	
	@BeforeClass
	public static void setUp()
	{
		
	}
	
	@Test
	public void getListaColoriOrdineGiusto() throws Exception
	{
		List<Color> lista =  ColoriGioco.getListaColori();
		
		List<Color> listaExp = new LinkedList<Color>();
		//R, B, G, Y or K 
		listaExp.add(Color.RED);
		listaExp.add(Color.BLUE);
		listaExp.add(Color.GREEN);
		listaExp.add(Color.YELLOW);
		listaExp.add(Color.BLACK);
		
		
		assertEquals(lista, listaExp);
		
	}
	
	@Test
	public  void x()
	{
		
	}
	
	
	
}
