package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;
import it.polimi.dei.swknights.carcassonne.Exceptions.NoFirstCardException;
import it.polimi.dei.swknights.carcassonne.Parser.Parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.management.BadAttributeValueExpException;

/**
 * That class is the main component of the factoy design pattern: it creates the
 * cards
 * 
 * @author Edo & Dave
 * 
 */

public class FactoryTessereNormali extends FactoryTessere
{

	public FactoryTessereNormali()
	{
		this.descrizioniTessere = new ArrayList<String>();
		this.mazzo = new ArrayList<Tessera>();
	}

	@Override
	public void acquisisciMazzoDaFile(String pathFileTessere)
	{

		// scrive in this.descrizioneTessere

		try
		{
			this.estraiDescrizioniTessere(pathFileTessere);
			this.creaMazzoTessere();
		}
		catch (NoFirstCardException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

	}

	private void creaMazzoTessere() throws NoFirstCardException
	{
		Tessera tesseraMagic = null;
		for (String descrizione : this.descrizioniTessere)
		{
			Tessera tessera = this.tesseraDaDescrzione(descrizione);

			if (tessera.lati.getTipoElementoInDirezione(PuntoCardinale.nord) == Elemento.prato
					&& tessera.lati.getTipoElementoInDirezione(PuntoCardinale.sud) == Elemento.citta
					&& tessera.lati.getTipoElementoInDirezione(PuntoCardinale.ovest) == Elemento.strada
					&& tessera.lati.getTipoElementoInDirezione(PuntoCardinale.est) == Elemento.strada)
			{
				tesseraMagic = this.tesseraDaDescrzione(descrizione); // con =
																		// tessera
																		// copierei
																		// riferimento
			}
			else
			{
				this.mazzo.add(tessera);
			}

		}

		if (tesseraMagic == null) { throw new NoFirstCardException(
				"controlla il file tessere e la factory perchè manca la tessera iniziale");
		// potrebbe mettercela dato che sa come farla volendo.. ma meglio
		}

		this.mazzo.add(tesseraMagic);

	}

	private Tessera tesseraDaDescrzione(String descrizione)
	{
		try
		{
			Parser parser = new Parser(descrizione);
			Lati lati = this.creaElementi(parser);
			Link link = this.creaLinks(parser);
			return new TesseraNormale(lati, link);
		}
		catch (InvalidStringToParseException e1)
		{
			e1.printStackTrace();
			return null; // TODO: propago eccezione?
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace(); // TODO propago eccezione?
			return null;
		}
		catch (BadAttributeValueExpException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private Lati creaElementi(Parser parser)
	{
		Elemento elementiTessera[] = new Elemento[PuntoCardinale.NUMERO_DIREZIONI];
		for (PuntoCardinale direzione : PuntoCardinale.values())
		{
			int dir = direzione.toInt();
			char elemento = parser.getDataChar(direzione);
			elementiTessera[dir] = Elemento.getElemento(elemento);
		}
		return new Lati(elementiTessera);
	}

	private Link creaLinks(Parser parser) throws BadAttributeValueExpException, IllegalArgumentException
	{
		boolean[] links = new boolean[Bussola.NUMERO_DIREZIONI];
		for (Bussola direzione : Bussola.values())
		{
			int dir = direzione.toInt();
			links[dir] = parser.getDataBool(direzione);
		}
		return new Link(links);
	}

	/**
	 * Fills cardDescriptions with all the lines included in the cards file
	 * 
	 * @param pathFileTessere
	 *            : path of the cards file
	 * @throws IOException
	 */

	private void estraiDescrizioniTessere(String pathFileTessere) throws IOException
	{
		URL resourceTxtFile = FactoryTessereNormali.class.getResource(pathFileTessere);
		InputStreamReader inputStreamReader = null;
		Scanner scanner;
		inputStreamReader = new InputStreamReader(resourceTxtFile.openStream());
		scanner = new Scanner(inputStreamReader);
		scriviTessereDaReader(scanner);
		scanner.close();
	}

	private void scriviTessereDaReader(Scanner in)
	{
		String inputLine = null;

		while ((in.hasNext()))
		{
			inputLine= in.nextLine();
			descrizioniTessere.add(inputLine);
		}

		in.close();

	}
	
	
	private boolean buonaLineaFile(String linea)
	{
		String s = "N=S S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0";
		
		String PUNTO="N|S|W|E";
		String COSTRUZIONE="N|S|C";
		String BOOL="0|1";
		String LINK="NS|NE|NW|WE|SE|SW";
		String reg="";
		for(PuntoCardinale punto: PuntoCardinale.values())
		{
			reg = reg +  "(" + PUNTO  + ")" + "=" + "(" + COSTRUZIONE + ")" + " " ;
		}
		int j=0;
		for(Bussola bussola : Bussola.values())
		{
			reg = reg +  "(" + LINK  + ")" + "=" + "(" + BOOL + ")";
			
			if(j!=Bussola.NUMERO_DIREZIONI-1)
			{
				reg= reg + " ";
			}
			j++;
		}
		return linea.matches(reg);
		
	}
	
	

	private List<String>	descrizioniTessere;

}
