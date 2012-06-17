package it.polimi.dei.swknights.carcassonne.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Class to get images at a specified size
 * @author dave
 *
 */
public class ScaledImageGetter extends ImageLoader
{
	/**
	 * Default constructor
	 */
	public ScaledImageGetter()
	{
		super();
		this.mappaImmaginiScalate = null;
		this.currentDim = 0;
		this.setImageDim(DIM_STANDARD);
	}

	/**
	 * Getter method
	 * @param string the string representation of the resource
	 * @return the image resource searched
	 */
	public BufferedImage getImage(String string)
	{
		BufferedImage immagine = this.mappaImmaginiScalate.get(string);
		if (immagine != null)
		{
			return immagine;
		}
		else
		{
			return this.getErrore();
		}
	}

	/**
	 * getter method
	 * @param segnalino the string representing the color of the marker 
	 * @param dimensioneSegnalino the dimension of the marker
	 * @return the marker image at the specified size
	 */
	public BufferedImage getSegnalino(String segnalino, int dimensioneSegnalino)
	{
		if (!segnalino.equals(""))
		{
			BufferedImage image = this.getOriginalSegnalinoImage(segnalino);
			return this.scalaImmagine(image, dimensioneSegnalino);
		}
		else
		{
			return null;
		}
	}

	/**
	 * getter method
	 * @param string the string representation of the resource
	 * @return the image resource searched whit alpha channel set
	 */
	public BufferedImage getAlphaImage(String string)
	{
		BufferedImage image = this.getImage(string);
		return this.addAlpha(image);
	}

	/**
	 * Method used to set current dimension images
	 * @param dimensione the dimension of the side of the images
	 */
	public final void setImageDim(int dimensione)
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
				immagineTrasparente.setRGB(x, y, (rgb & ALPHA_CHANNEL));
			}
		}
		return immagineTrasparente;

	}

	private int							currentDim;

	private Map<String, BufferedImage>	mappaImmaginiScalate;

	public static final int				DIM_STANDARD	= 60;

	private static final int			ALPHA_CHANNEL	= 0x6FFFFFFF;
}
