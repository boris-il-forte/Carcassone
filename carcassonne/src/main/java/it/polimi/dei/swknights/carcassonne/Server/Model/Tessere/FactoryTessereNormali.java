package it.polimi.dei.swknights.carcassonne.Server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;
import it.polimi.dei.swknights.carcassonne.Exceptions.NoFirstCardException;
import it.polimi.dei.swknights.carcassonne.Parser.Parser;
import it.polimi.dei.swknights.carcassonne.Util.Bussola;
import it.polimi.dei.swknights.carcassonne.Util.PuntoCardinale;

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

	/**
	 * Default constructor
	 */
	public FactoryTessereNormali()
	{
		super();
		this.descrizioniTessere = new ArrayList<String>();
	}

	/**
	 * Method that builds up all cards from file
	 */
	@Override
	public void acquisisciMazzoDaFile(String pathFileTessere)
	{

		try
		{
			this.estraiDescrizioniTessere(pathFileTessere);
			this.creaMazzoTessere();
		}
		catch (NoFirstCardException e)
		{
			throw new AssertionError(e);
		}
		catch (IOException e1)
		{
		}

	}

	private void creaMazzoTessere() throws NoFirstCardException
	{
		Tessera tesseraMagic = null;
		for (String descrizione : this.descrizioniTessere)
		{
			Tessera tessera;
			try
			{
				tessera = this.tesseraDaDescrzione(descrizione);
				if (this.isMagic(tessera) && tesseraMagic == null)
				{
					tesseraMagic = tessera;
				}
				else
				{
					this.aggiungiAlMazzo(tessera);
				}
			}
			catch (IllegalArgumentException e)
			{
			}
			catch (InvalidStringToParseException e)
			{
			}
			catch (BadAttributeValueExpException e)
			{
			}
		}

		if (tesseraMagic == null) { throw new NoFirstCardException(
				"controlla il file tessere e la factory perchè manca la tessera iniziale"); }

		this.setTesseraMagick(tesseraMagic);

	}

	private boolean isMagic(Tessera tessera)
	{
		boolean nordPrato = tessera.lati.getTipoElementoInDirezione(PuntoCardinale.nord) == Elemento.prato;
		boolean sudCitta = tessera.lati.getTipoElementoInDirezione(PuntoCardinale.sud) == Elemento.citta;
		boolean ovestStrada = tessera.lati.getTipoElementoInDirezione(PuntoCardinale.ovest) == Elemento.strada;
		boolean estStrada = tessera.lati.getTipoElementoInDirezione(PuntoCardinale.est) == Elemento.strada;
		return nordPrato && sudCitta && ovestStrada && estStrada;
	}

	private Tessera tesseraDaDescrzione(String descrizione) throws InvalidStringToParseException,
			IllegalArgumentException, BadAttributeValueExpException, IllegalArgumentException
	{
		Parser parser = new Parser(descrizione);
		Lati lati = this.creaElementi(parser);
		Link link = this.creaLinks(parser);
		return new TesseraNormale(lati, link);
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
		this.scriviTessereDaReader(scanner);
		scanner.close();
	}

	private void scriviTessereDaReader(Scanner in)
	{
		String inputLine = null;

		while ((in.hasNext()))
		{
			inputLine = in.nextLine();
			this.descrizioniTessere.add(inputLine);
		}

		in.close();

	}

	private List<String>	descrizioniTessere;

}
