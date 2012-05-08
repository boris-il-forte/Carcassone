package it.polimi.dei.swknights.carcassonne.server.Model.Tessere;

import it.polimi.dei.swknights.carcassonne.PuntoCardinale;

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

		this.creaMazzoTessere();

		// DEBUG System.out.println(this.mazzo);
	}

	private void creaMazzoTessere()
	{
		for (String descrizione : this.descrizioniTessere)
		{
			Tessera tessera = this.tesseraDaDescrzione(descrizione);
			this.mazzo.add(tessera);

		}

	}

	private Tessera tesseraDaDescrzione(String descrizione)
	{
		// TODO: controllo sia buona descrizione come??

		Tessera tessera;
		Elemento nord;
		Elemento sud;
		Elemento ovest;
		Elemento est;

		Link link = new Link();
		Lati lati;

		String[] partiDescrizione;
		partiDescrizione = descrizione.split(" ");
		
		nord = this
				.charToElemento(partiDescrizione[PuntoCardinale.nord.toInt()]
						.charAt(PARAMETRO_PUNTOCARDINALE));
		sud = this.charToElemento(partiDescrizione[PuntoCardinale.sud.toInt()]
				.charAt(PARAMETRO_PUNTOCARDINALE));
		ovest = this.charToElemento(partiDescrizione[PuntoCardinale.ovest
				.toInt()].charAt(PARAMETRO_PUNTOCARDINALE));
		est = this.charToElemento(partiDescrizione[PuntoCardinale.est.toInt()]
				.charAt(PARAMETRO_PUNTOCARDINALE));

		lati = new Lati(nord, sud, ovest, est);
		try
		{
			//TODO: kill the magic!
			link.SN = charToBoolLink(partiDescrizione[4].charAt(PARAMETRO_LINK));
			link.NE = charToBoolLink(partiDescrizione[5].charAt(PARAMETRO_LINK));
			link.NO = charToBoolLink(partiDescrizione[6].charAt(PARAMETRO_LINK));
			link.OE = charToBoolLink(partiDescrizione[7].charAt(PARAMETRO_LINK));
			link.SE = charToBoolLink(partiDescrizione[8].charAt(PARAMETRO_LINK));
			link.SO = charToBoolLink(partiDescrizione[9].charAt(PARAMETRO_LINK));
		} catch (BadAttributeValueExpException e)
		{
			return null;
		}

		tessera = new TesseraNormale(lati, link);

		return tessera;
	}

	private Boolean charToBoolLink(char siglaLink)
			throws BadAttributeValueExpException
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
	 * gets an element of the specified initial C stays for City N stay for
	 * Nothing S stays for Street
	 * 
	 * @param siglaElemento
	 *            : the initial of the wanted element
	 * @return: the wanted element
	 */
	private Elemento charToElemento(char siglaElemento)
	{
		switch (siglaElemento)
		{
			case 'S':
				return new Strada();
			case 'C':
				return new Citta();
			case 'N':
				return new Prato();
			default:
				return null; // TODO: exception!!!
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

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e)
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

	private List<String> descrizioniTessere;

	private final int PARAMETRO_PUNTOCARDINALE = 2;
	private final int PARAMETRO_LINK = 3;
}