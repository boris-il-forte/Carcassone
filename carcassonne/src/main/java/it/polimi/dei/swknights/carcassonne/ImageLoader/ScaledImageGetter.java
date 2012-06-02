package it.polimi.dei.swknights.carcassonne.ImageLoader;

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
		System.out.println("cerco immagine " + string );
		Image immagine = this.mappaImmaginiScalate.get(string);
		if (immagine != null)
		{
			return immagine;
		}
		else
		{
			System.out.println("c'è stato un errore, carico un'immagine sostitutiva");
			return this.getErrore();
		}
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

	private int					currentDim;

	private Map<String, Image>	mappaImmaginiScalate;


	public static final int	DIM_STANDARD	= 60;
}
