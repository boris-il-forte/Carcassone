package it.polimi.dei.swknights.carcassonne.ImageLoader;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
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
			this.aggiungiRuotate();
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

	protected Image scalaImmagine(BufferedImage original, int dim)
	{
		BufferedImage resized = new BufferedImage(dim, dim, original.getType());
	    Graphics2D g = resized.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(original, 0, 0, dim, dim, 0, 0, original.getWidth(), original.getHeight(), null);
	    g.dispose();
	    return resized;
	}

	private void aggiungiRuotate()
	{
		RuotaImmagini ruotatore = new RuotaImmagini(this.mappaImmagini, DIM_ORIGINALE);
		this.mappaImmagini.putAll(ruotatore.getMapRuotate());
	}

	private void apriFilesCartella() throws IOException
	{
		for (Entry<String, URL> entryURL : this.mappaURL.entrySet())
		{
			Image image  = this.scalaImmagine(ImageIO.read(entryURL.getValue()), DIM_ORIGINALE);
			this.mappaImmagini.put(entryURL.getKey(), image);
		}
	}

	private void leggiFileCartella()
	{
		URL urlCartella = ImageLoader.class.getResource("/tiles");
		File cartella = new File(urlCartella.getFile());
		for( String stringImmagine : cartella.list())
		{
			//TODO chiedere che mi sa che non va bene...
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
		this.errorImage = this.scalaImmagine(ImageIO.read(this.errorURL),DIM_ORIGINALE);
	}

	private Image				errorImage;

	private URL					errorURL;

	private Map<String, Image>	mappaImmagini;

	private Map<String, URL>	mappaURL;

	private static final int	DIM_ORIGINALE	= 150;
}
