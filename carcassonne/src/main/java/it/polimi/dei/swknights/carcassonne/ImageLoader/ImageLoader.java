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
		this.leggiFileCartella();
		this.apriFilesCartella();
	}
	
	public URL getUrl(String stringa)
	{
		return this.mappaURL.get(stringa);
	}
	
	public Icon getIcon(String Stringa)
	{
		return this.mappaFiles.get(Stringa);
	}
	

	private void apriFilesCartella()
	{
		for(Entry<String, URL> entryURL : mappaURL.entrySet())
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

	private Map<String, Icon>	mappaFiles;

	private Map<String, URL>	mappaURL;

}
