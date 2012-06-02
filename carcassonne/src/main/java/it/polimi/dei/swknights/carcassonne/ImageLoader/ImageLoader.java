package it.polimi.dei.swknights.carcassonne.ImageLoader;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.imageio.ImageIO;

public class ImageLoader
{
	public ImageLoader()
	{
		this.mappaURL = new HashMap<String, URL>();
		this.mappaImmagini = new HashMap<String, Image>();
		try
		{
			this.setErrore();
			this.leggiFileCartella();
			this.apriFilesCartella();
			System.out.println("1) trovate " + this.mappaImmagini.size() + " immagini");
			this.aggiungiRuotate();
			System.out.println("2) trovate " + this.mappaImmagini.size() + " immagini");
		}
		catch (IOException e)
		{
			return;
		}
	}

	public URL getUrl(String stringa)
	{
		URL url = this.mappaURL.get(stringa);
		if (url != null)
		{
			return url;
		}
		else
		{
			return this.errorURL;
		}
	}

	public Image getOriginalImage(String stringa)
	{
		Image image = this.mappaImmagini.get(stringa);
		if (image != null)
		{
			return image;
		}
		else
		{
			return this.errorImage;
		}
	}

	protected Set<Entry<String, Image>> getOriginalSet()
	{
		return this.mappaImmagini.entrySet();
	}

	protected Image getErrore()
	{
		return this.errorImage;
	}

	private void aggiungiRuotate()
	{
		RuotaImmagini ruotatore = new RuotaImmagini(this.mappaImmagini, DIM_ORIGINALE);
		System.out.println("ottenute "+ruotatore.getMapRuotate().size()+ " tessere ruotate");
		this.mappaImmagini.putAll(ruotatore.getMapRuotate());
	}

	private void apriFilesCartella() throws IOException
	{
		for (Entry<String, URL> entryURL : this.mappaURL.entrySet())
		{
			Image image = ImageIO.read(entryURL.getValue());
			this.mappaImmagini.put(entryURL.getKey(), image);
			if (image == null)
			{
				System.out.println(entryURL.getKey() + "non caricata...");
			}
		}
	}

	private void leggiFileCartella()
	{
		InputStream cartella = ImageLoader.class.getResourceAsStream("/tiles");
		Scanner scannerCartella = new Scanner(cartella);
		while (scannerCartella.hasNext())
		{
			String stringImmagine = scannerCartella.nextLine();
			if (!stringImmagine.startsWith("."))
			{
				StringBuilder builderPercorso = new StringBuilder("/tiles/").append(stringImmagine);
				URL urlImmagine = ImageLoader.class.getResource(builderPercorso.toString());
				this.mappaURL.put(stringImmagine.split("\\.")[0], urlImmagine);
			}
		}
	}

	private void setErrore() throws IOException
	{
		this.errorURL = ImageLoader.class.getResource("/error.jpg");
		this.errorImage = ImageIO.read(this.errorURL);
	}

	private Image				errorImage;

	private URL					errorURL;

	private Map<String, Image>	mappaImmagini;

	private Map<String, URL>	mappaURL;

	private static final int	DIM_ORIGINALE	= 300;
}
