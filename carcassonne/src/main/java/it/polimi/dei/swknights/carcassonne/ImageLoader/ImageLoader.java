package it.polimi.dei.swknights.carcassonne.ImageLoader;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageLoader
{
	public ImageLoader()
	{
		this.mappaURL = new HashMap<String, URL>();
		this.mappaFiles = new HashMap<String, Icon>();
		this.setErrore();
		this.leggiFileCartella();
		this.apriFilesCartella();
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

	public Icon getIcon(String stringa)
	{
		Icon icon = this.mappaFiles.get(stringa);
		if (icon != null)
		{
			return icon;
		}
		else
		{
			return this.errorIcon;
		}
	}

	private void apriFilesCartella()
	{
		for (Entry<String, URL> entryURL : mappaURL.entrySet())
		{
			Icon icona = new ImageIcon(entryURL.getValue());
			this.mappaFiles.put(entryURL.getKey(), icona);
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

	private void setErrore()
	{
		this.errorURL = ImageLoader.class.getResource("/error.jpg");
		this.errorIcon = new ImageIcon(this.errorURL);
	}

	private Icon				errorIcon;

	private URL					errorURL;

	private Map<String, Icon>	mappaFiles;

	private Map<String, URL>	mappaURL;

}
