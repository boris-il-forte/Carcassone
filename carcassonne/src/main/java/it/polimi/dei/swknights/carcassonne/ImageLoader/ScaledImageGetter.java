package it.polimi.dei.swknights.carcassonne.ImageLoader;

import it.polimi.dei.swknights.carcassonne.Debug;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ScaledImageGetter extends ImageLoader
{
	public ScaledImageGetter()
	{
		super();
		this.mappaImmaginiScalate = null;
		this.currentDim = 0;
		this.setImageDim(DIM_STANDARD);
	}

	public BufferedImage getImage(String string)
	{
		BufferedImage immagine = this.mappaImmaginiScalate.get(string);
		if (immagine != null)
		{
			return immagine;
		}
		else
		{
			Debug.print("stringa non trovata: " + string);
			return this.getErrore();
		}
	}

	public BufferedImage getSegnalino(String segnalino, int dimensioneSegnalino)
	{
		if (!segnalino.equals(""))
		{
			BufferedImage image = this.getOriginalSegnalinoImage(segnalino);
			return this.scalaImmagine(image, dimensioneSegnalino);
		}
		else return null;
	}

	public BufferedImage getAlphaImage(String string)
	{
		BufferedImage image = getImage(string);
		return this.addAlpha(image);
	}

	public void setImageDim(int dimensione)
	{
		if (dimensione != this.currentDim)
		{
			this.currentDim = dimensione;
			this.mappaImmaginiScalate = new HashMap<String, BufferedImage>();
			for (Entry<String, BufferedImage> entryImage : this.getOriginalSet())
			{
				BufferedImage standardImage = this.scalaImmagine(entryImage.getValue(), dimensione);
				this.mappaImmaginiScalate.put(entryImage.getKey(), standardImage);
			}
		}
	}

	private BufferedImage addAlpha(BufferedImage image)
	{
		BufferedImage immagine = image;
		BufferedImage immagineTrasparente = new BufferedImage(immagine.getWidth(), immagine.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < immagine.getWidth(); x++)
		{
			for (int y = 0; y < immagine.getHeight(); y++)
			{
				int rgb = immagine.getRGB(x, y);
				immagineTrasparente.setRGB(x, y, (rgb & 0x6FFFFFFF));
			}
		}
		return immagineTrasparente;

	}

	private int							currentDim;

	private Map<String, BufferedImage>	mappaImmaginiScalate;

	public static final int				DIM_STANDARD	= 60;
}
