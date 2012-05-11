package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.Bussola;
import it.polimi.dei.swknights.carcassonne.PuntoCardinale;
import it.polimi.dei.swknights.carcassonne.Exceptions.NoFirstCardException;

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
		}
		catch (NoFirstCardException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		// DEBUG System.out.println(this.mazzo);
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
		// TODO: controllo sia buona descrizione con regexp

		Tessera tessera;
		Elemento[] elementiTessera = new Elemento[PuntoCardinale.NUMERO_DIREZIONI];

		Link link;
		Lati lati;
		String[] partiDescrizione;
		partiDescrizione = descrizione.split(" ");

		for (PuntoCardinale direzione : PuntoCardinale.values())
		{
			int dir = direzione.toInt();
			char sigla = partiDescrizione[dir].charAt(PARAMETRO_PUNTOCARDINALE);
			elementiTessera[dir] = Elemento.getElemento(sigla);
		}

		lati = new Lati(elementiTessera);
		try
		{
			boolean[] links = new boolean[Bussola.NUMERO_DIREZIONI];
			for (Bussola bussola : Bussola.values())
			{
				final Integer firstElement = 0;
				final Integer secondElement = 1;
				int index = PuntoCardinale.NUMERO_DIREZIONI + bussola.toInt();
				String[] entry = partiDescrizione[index].split("=");
				Bussola agoBussola = Bussola.valueOf(entry[firstElement]);
				links[agoBussola.ordinal()] = this.charToBoolLink(entry[secondElement].charAt(firstElement));

			}
			link = new Link(links);
		}
		catch (BadAttributeValueExpException e)
		{
			return null;
		}

		tessera = new TesseraNormale(lati, link);

		return tessera;
	}

	private Boolean charToBoolLink(char siglaLink) throws BadAttributeValueExpException
	{
		switch (siglaLink)
		{
			case '1':
				return true;
			case '0':
				return false;
			default:
				throw new BadAttributeValueExpException(siglaLink);
		}
	}

	/**
	 * Fills cardDescriptions with all the lines included in the cards file
	 * 
	 * @param pathFileTessere
	 *            : path of the cards file
	 */

	private void estraiDescrizioniTessere(String pathFileTessere)
	{

		String percorsoCompletoFile = PercorsoFullDiRisorsa(pathFileTessere);

		try
		{
			FileReader fr = new FileReader(new File(percorsoCompletoFile));

			BufferedReader br = new BufferedReader(fr);
			String line;

			line = br.readLine();

			while (line != null)
			{
				this.descrizioniTessere.add(line);
				line = br.readLine();
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private String PercorsoFullDiRisorsa(String path)
	{
		java.net.URL imgURL = this.getClass().getResource(path);
		return imgURL.getFile();
	}

	private List<String>	descrizioniTessere;

	private final int		PARAMETRO_PUNTOCARDINALE	= 2;

}
