package it.polimi.dei.swknights.carcassonne.ImageLoader;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Map.Entry;

public class RuotaImmagini
{
	public RuotaImmagini(Map<String, Image> mappaImmagini, int dimensioneImmagini)
	{
		this.dimensioneImmagini = dimensioneImmagini;
		this.mappaImmagini = mappaImmagini;
	}

	public void ruotaImmagini()
	{
		for (Entry<String, Image> entry : mappaImmagini.entrySet())
		{
			RuotaStringa rotator = new RuotaStringa(entry.getKey());
			for (int i = 1; i < NUMERO_LATI; i++)
			{
				rotator.ruotaStringa();
				String stringaRuotata = rotator.toString();
				Image immagineruotata = this.ruotaImmagine(entry.getValue());
				this.immaginiRuotate.put(stringaRuotata, immagineruotata);
			}
		}
	}

	private Image ruotaImmagine(Image immagine)
	{
		if(immagine instanceof Image)
		{
			BufferedImage vecchia = (BufferedImage)immagine;
			BufferedImage biFlip = new BufferedImage(this.dimensioneImmagini, this.dimensioneImmagini, vecchia.getType());
			for (int i = 0; i < this.dimensioneImmagini; i++)
				for (int j = 0; j < this.dimensioneImmagini; j++)
					biFlip.setRGB(this.dimensioneImmagini - 1 - j, this.dimensioneImmagini - 1 - i, vecchia.getRGB(i, j));
			return biFlip;
		}
		else 
		{
			return immagine;
		}

	}

	private Map<String, Image>	mappaImmagini;

	private Map<String, Image>	immaginiRuotate;

	private int					dimensioneImmagini;

	private static final int	NUMERO_LATI	= 4;

}
