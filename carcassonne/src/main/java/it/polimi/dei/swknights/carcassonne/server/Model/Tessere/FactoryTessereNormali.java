package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.InvalidStringToParseException;
import it.polimi.dei.swknights.carcassonne.Exceptions.NoFirstCardException;
import it.polimi.dei.swknights.carcassonne.Parser.Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

		this.estraiDescrizioniTessere(pathFileTessere);
		// scrive in this.descrizioneTessere

		try
		{
			this.creaMazzoTessere();
		} catch (NoFirstCardException e)
		{
			e.printStackTrace();
			System.exit(-1);
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
			} else
			{
				this.mazzo.add(tessera);
			}

		}

		if (tesseraMagic == null)
		{
			throw new NoFirstCardException("controlla il file tessere e la factory perchè manca la tessera iniziale");
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
			return null; //TODO: propago eccezione?
		} 
		catch (BadAttributeValueExpException e)
		{
			e.printStackTrace(); //TODO propago eccezione?
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
	
	private Link creaLinks(Parser parser) throws BadAttributeValueExpException
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
	 */

	private void estraiDescrizioniTessere(String pathFileTessere)
	{
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try
		{
			fileReader = new FileReader(new File(pathFileTessere));

			bufferedReader = new BufferedReader(fileReader);
			String line;

			line = bufferedReader.readLine();

			while (line != null)
			{
				this.descrizioniTessere.add(line);
				line = bufferedReader.readLine();
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		} finally
		{
			this.close(fileReader);
		}

	}
    /**
     * Potentially dangerous, may not end !
     * If nothing bad happens it ends without any problem
     * @param fileReader
     */
	private void close(FileReader fileReader)
	{
		if (fileReader == null)
		{
			return;
		}
		boolean chiuso = false;
		do
		{
			try
			{
				fileReader.close();
				chiuso = true;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} while (!chiuso);
	}

	private List<String>		descrizioniTessere;

}
