package it.polimi.dei.swknights.carcassonne.ImageLoader;

import it.polimi.dei.swknights.carcassonne.Debug;
import it.polimi.dei.swknights.carcassonne.Util.ListFolder;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
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
			this.leggiFileCartella("tiles", this.mappaURLTiles);
			this.leggiFileCartella("segnalini", this.mappaURLSegnalini);
			this.apriFilesCartella(this.mappaImmaginiTiles, this.mappaURLTiles, DIM_ORIGINALE_TILES);
			this.apriFilesCartella(this.mappaImmaginiSegnalini, this.mappaURLSegnalini,
					DIM_ORIGINALE_SEGNALINI);
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

	public BufferedImage getOriginalTileImage(String stringa)
	{
		return this.getImage(this.mappaImmaginiTiles, stringa);
	}

	public BufferedImage getOriginalSegnalinoImage(String stringa)
	{
		return this.getImage(this.mappaImmaginiSegnalini, stringa);
	}

	protected Set<Entry<String, BufferedImage>> getOriginalSet()
	{
		return this.mappaImmaginiTiles.entrySet();
	}

	protected BufferedImage getErrore()
	{
		return this.errorImage;
	}

	protected BufferedImage scalaImmagine(BufferedImage original, int dim)
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
		this.mappaImmaginiTiles = new HashMap<String, BufferedImage>();
		this.mappaURLSegnalini = new HashMap<String, URL>();
		this.mappaImmaginiSegnalini = new HashMap<String, BufferedImage>();

	}

	private BufferedImage getImage(Map<String, BufferedImage> mappaImmagini, String stringa)
	{
		BufferedImage image = mappaImmagini.get(stringa);
		if (image != null)
		{
			return image;
		}
		else
		{
			return this.errorImage;
		}
	}

	private void aggiungiRuotate()
	{
		RuotaImmagini ruotatore = new RuotaImmagini(this.mappaImmaginiTiles, DIM_ORIGINALE_TILES);
		this.mappaImmaginiTiles.putAll(ruotatore.getMapRuotate());
	}

	private void apriFilesCartella(Map<String, BufferedImage> mappaImmagini, Map<String, URL> mappaURL,
			int dimOriginale) throws IOException
	{
		for (Entry<String, URL> entryURL : mappaURL.entrySet())
		{
			BufferedImage originalImage = ImageIO.read(entryURL.getValue());
			if (originalImage == null)
			{
				Debug.print("Non trovato url " + originalImage);
			}
			else
			{
				BufferedImage image = this.scalaImmagine(originalImage, dimOriginale);
				mappaImmagini.put(entryURL.getKey(), image);
			}
		}
	}

	private void leggiFileCartella(String stringCartella, Map<String, URL> map)
	{
		try
		{
			for (String stringImmagine : ListFolder.list(stringCartella))
			{
				Debug.print(stringImmagine);
				if (!stringImmagine.startsWith("."))
				{
					StringBuilder builderPercorso = new StringBuilder("/").append(stringCartella).append("/")
							.append(stringImmagine);
					URL urlImmagine = ImageLoader.class.getResource(builderPercorso.toString());
					map.put(stringImmagine.split("\\.")[0], urlImmagine);
				}
			}
		}
		catch (URISyntaxException e)
		{
		}
		catch (IOException e)
		{
		}
	}

	private void setErrore() throws IOException
	{
		this.errorURL = ImageLoader.class.getResource("/error.jpg");
		this.errorImage = this.scalaImmagine(ImageIO.read(this.errorURL), DIM_ORIGINALE_TILES);
	}

	private BufferedImage				errorImage;

	private URL							errorURL;

	private Map<String, BufferedImage>	mappaImmaginiSegnalini;

	private Map<String, URL>			mappaURLSegnalini;

	private Map<String, BufferedImage>	mappaImmaginiTiles;

	private Map<String, URL>			mappaURLTiles;

	private static final int			DIM_ORIGINALE_SEGNALINI	= 40;

	private static final int			DIM_ORIGINALE_TILES		= 150;
}
