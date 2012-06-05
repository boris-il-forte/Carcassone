package it.polimi.dei.swknights.carcassonne.ImageLoader;

import it.polimi.dei.swknights.carcassonne.Debug;

import java.awt.Image;
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

	public Image getImage(String string)
	{
		Image immagine = this.mappaImmaginiScalate.get(string);
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

	public Image getAlphaImage(String string)
	{
		Image image = getImage(string);
		return this.addAlpha(image);
	}

	public void setImageDim(int dimensione)
	{
		if(dimensione != this.currentDim)
		{
			this.currentDim = dimensione;
			this.mappaImmaginiScalate = new HashMap<String, Image>();
			for (Entry<String, Image> entryImage : this.getOriginalSet())
			{
				Image standardImage = this.scalaImmagine((BufferedImage) entryImage.getValue(), dimensione);
				this.mappaImmaginiScalate.put(entryImage.getKey(), standardImage);
			}
		}
	}

	private Image addAlpha(Image image)
	{
		BufferedImage immagine = (BufferedImage) image;
		BufferedImage immagineTrasparente = new BufferedImage(immagine.getWidth(), immagine.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int x=0 ; x<immagine.getWidth(); x++)
		{
			for(int y=0; y<immagine.getHeight(); y++)
			{
				int rgb = immagine.getRGB(x, y);
				immagineTrasparente.setRGB(x, y, (rgb & 0x6FFFFFFF));
			}
		}
		return immagineTrasparente;
		
	}

	private int					currentDim;

	private Map<String, Image>	mappaImmaginiScalate;


	public static final int	DIM_STANDARD	= 60;
}
