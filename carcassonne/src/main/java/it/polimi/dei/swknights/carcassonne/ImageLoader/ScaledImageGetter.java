package it.polimi.dei.swknights.carcassonne.ImageLoader;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ScaledImageGetter extends ImageLoader
{
	public ScaledImageGetter()
	{
		super();
		this.mappaAttuale = null;
		this.setImageDim(DIM_STANDARD);
		this.currentDim = DIM_STANDARD;
	}

	public Image getImage(String string)
	{
		Image immagine = this.mappaAttuale.get(string);
		if (immagine != null)
		{
			return immagine;
		}
		else
		{
			return this.getErrore();
		}
	}

	public void setImageDim(int dimensione)
	{
		if(dimensione != this.currentDim)
		{
			this.currentDim = dimensione;
			this.mappaAttuale = new HashMap<String, Image>();
			for (Entry<String, Image> entryImage : this.getOriginalSet())
			{
				Image standardImage = entryImage.getValue().getScaledInstance(dimensione, dimensione,
						Image.SCALE_FAST);
				this.mappaAttuale.put(entryImage.getKey(), standardImage);
			}
		}
	}

	private int					currentDim;

	private Map<String, Image>	mappaAttuale;


	private static final int	DIM_STANDARD	= 60;
}
