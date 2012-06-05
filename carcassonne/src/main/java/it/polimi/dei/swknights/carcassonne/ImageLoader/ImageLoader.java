package it.polimi.dei.swknights.carcassonne.ImageLoader;

import it.polimi.dei.swknights.carcassonne.Debug;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;

public class ImageLoader
{
	public ImageLoader()
	{
		this.inizializzaMappe();
		
		try
		{
			this.setErrore();
			this.leggiFileCartella("/tiles", this.mappaURLTiles);
			this.leggiFileCartella("/segnalini", this.mappaURLSegnalini);
			this.apriFilesCartella(this.mappaImmaginiTiles, this.mappaURLTiles, DIM_ORIGINALE_TILES);
			this.apriFilesCartella(this.mappaImmaginiSegnalini, this.mappaURLSegnalini, DIM_ORIGINALE_SEGNALINI);
			this.aggiungiRuotate();
		}
		catch (IOException e)
		{
			return;
		}
	}

	public URL getUrl(String stringa)
	{
		URL url = this.mappaURLTiles.get(stringa);
		if (url != null)
		{
			return url;
		}
		else
		{
			return this.errorURL;
		}
	}

	public Image getOriginalTileImage(String stringa)
	{
		return getImage(this.mappaImmaginiTiles, stringa);
	}
	
	public Image getOriginalSegnalinoImage(String stringa)
	{
		return getImage(this.mappaImmaginiSegnalini, stringa);
	}
	
	
	protected Set<Entry<String, Image>> getOriginalSet()
	{
		return this.mappaImmaginiTiles.entrySet();
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

	private void inizializzaMappe()
	{
		this.mappaURLTiles = new HashMap<String, URL>();
		this.mappaImmaginiTiles = new HashMap<String, Image>();
		this.mappaURLSegnalini = new HashMap<String, URL>();
		this.mappaImmaginiSegnalini = new HashMap<String, Image>();
		
	}

	private Image getImage(Map<String, Image> mappaImmagini, String stringa)
	{
		Image image = mappaImmagini.get(stringa);
		if (image != null)
		{
			return image;
		}
		else
		{
			Debug.print("stringa immagine originale non trovata: " + stringa);
			return this.errorImage;
		}
	}

	private void aggiungiRuotate()
	{
		RuotaImmagini ruotatore = new RuotaImmagini(this.mappaImmaginiTiles, DIM_ORIGINALE_TILES);
		this.mappaImmaginiTiles.putAll(ruotatore.getMapRuotate());
	}

	private void apriFilesCartella(Map<String, Image> mappaImmagini, Map<String, URL> mappaURL, int dimOriginale) throws IOException
	{
		for (Entry<String, URL> entryURL : mappaURL.entrySet())
		{
			Image image  = this.scalaImmagine(ImageIO.read(entryURL.getValue()), dimOriginale);
			mappaImmagini.put(entryURL.getKey(), image);
		}
	}

	private void leggiFileCartella(String stringCartella, Map<String, URL> map)
	{
		Debug.print("Apro cartella: "  + stringCartella);
		URL urlCartella = ImageLoader.class.getResource(stringCartella);
		File cartella = new File(urlCartella.getFile());
		for( String stringImmagine : cartella.list())
		{
			//TODO chiedere che mi sa che non va bene...
			if (!stringImmagine.startsWith("."))
			{
				Debug.print(stringImmagine);
				StringBuilder builderPercorso = new StringBuilder(stringCartella).append("/").append(stringImmagine);
				URL urlImmagine = ImageLoader.class.getResource(builderPercorso.toString());
				map.put(stringImmagine.split("\\.")[0], urlImmagine);
			}
		}
	}

	private void setErrore() throws IOException
	{
		this.errorURL = ImageLoader.class.getResource("/error.jpg");
		this.errorImage = this.scalaImmagine(ImageIO.read(this.errorURL),DIM_ORIGINALE_TILES);
	}

	private Image				errorImage;

	private URL					errorURL;
	
	private Map<String, Image>	mappaImmaginiSegnalini;

	private Map<String, URL>	mappaURLSegnalini;

	private Map<String, Image>	mappaImmaginiTiles;

	private Map<String, URL>	mappaURLTiles;

	private static final int	DIM_ORIGINALE_SEGNALINI	= 40;

	private static final int	DIM_ORIGINALE_TILES	= 150;
}
