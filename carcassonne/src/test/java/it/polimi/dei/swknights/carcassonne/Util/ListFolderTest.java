package it.polimi.dei.swknights.carcassonne.Util;

import it.polimi.dei.swknights.carcassonne.Debug;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListFolderTest
{
	@Test
	public void testerInvicta()
	{

		try
		{
			String[] filesECartelleC = ListFolder.list("C:\\");
			Debug.print(" in C c'è : " + filesECartelleC.toString());

		}
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue(true);
		}

	}

	@Test
	public void testDeiPolimiIt()
	{
		
		try
		{	/////Carcassonne/src/test/resources
			String[] filesECartelleC =  ListFolder.list("pippi");
			Debug.print(" in Util ci sono : ");
			for(int i=0; i<filesECartelleC.length; i++)
			{
				Debug.print("" + filesECartelleC[i]);
			}
			List<String> lista = Arrays.asList(filesECartelleC);
			
			if (! lista.contains("pippo1.txt") )
			{
				fail(" non c'è pippo1 !! ");					
			}
			if (! lista.contains("pippo2.txt") )
			{
				fail(" non c'è pippo2 !! ");					
			}
			if (! lista.contains("pippo3.txt") )
			{
				fail(" non c'è pippo3 !! ");					
			}
			
			if(! lista.contains("pippo4.txt"))
				fail(" non c'è pippo 4 !!");
			
		
		}
		catch (URISyntaxException e)
		{
			fail(" male, eccezione");
		}
		catch (IOException e)
		{
			fail("male, errore sconosciuto");
	
		}
		catch(UnsupportedOperationException e)
		{
			fail("male, non ha aperto la cartella ");
		}
		
		
	}

}
