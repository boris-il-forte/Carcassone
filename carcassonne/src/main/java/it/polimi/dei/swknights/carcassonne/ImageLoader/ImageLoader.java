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

	protected Set<Entry<String,Image>> getOriginalSet()
	{
		return this.mappaImmagini.entrySet();
	}
	
	protected Image getErrore()
	{
		return this.errorImage;
	}

	private void apriFilesCartella() throws IOException
	{
		for (Entry<String, URL> entryURL : mappaURL.entrySet())
		{
			Image image = ImageIO.read(this.errorURL);
			this.mappaImmagini.put(entryURL.getKey(), image);
		}
	}
	
	private void leggiFileCartella()
	{
		InputStream cartella = ImageLoader.class.getResourceAsStream("/tiles");
		Scanner scannerCartella = new Scanner(cartella);
		while (scannerCartella.hasNext())
		{
			String stringImmagine = scannerCartella.nextLine();
			StringBuilder builderPercorso = new StringBuilder("/tiles/").append(stringImmagine);
			URL urlImmagine = ImageLoader.class.getResource(builderPercorso.toString());
			this.mappaURL.put(stringImmagine.split("\\.")[0], urlImmagine);
		}
	}

	private Image				errorImage;

	private URL					errorURL;

	private void setErrore() throws IOException
	{
		this.errorURL = ImageLoader.class.getResource("/error.jpg");
		this.errorImage = ImageIO.read(this.errorURL);
	}

	private Map<String, Image>	mappaImmagini;

	private Map<String, URL>	mappaURL;

}
