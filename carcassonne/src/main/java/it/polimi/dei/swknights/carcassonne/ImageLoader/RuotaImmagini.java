package it.polimi.dei.swknights.carcassonne.ImageLoader;

import it.polimi.dei.swknights.carcassonne.Debug;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RuotaImmagini
{
	public RuotaImmagini(Map<String, Image> mappaImmagini, int dimensioneImmagini)
	{
		this.dimensioneImmagini = dimensioneImmagini;
		this.mappaImmagini = mappaImmagini;
		this.immaginiRuotate = new HashMap<String, Image>();
	}

	public Map<String, Image> getMapRuotate()
	{
		for (Entry<String, Image> entry : this.mappaImmagini.entrySet())
		{
			if(entry.getKey().length() == 10)
			{
				this.ruotaEntry(entry);
			}
		}
		return this.immaginiRuotate;
	}

	private void ruotaEntry(Entry<String, Image> entry)
	{
		RuotaStringa rotator = new RuotaStringa(entry.getKey());
		Image immagine = entry.getValue();
		for (int i = 1; i < NUMERO_LATI; i++)
		{
			rotator.ruotaStringa();
			String stringaRuotata = rotator.toString();
			immagine = this.ruotaImmagine(immagine);
			this.immaginiRuotate.put(stringaRuotata, immagine);
		}
	}

	private Image ruotaImmagine(Image immagine)
	{
		if(immagine instanceof BufferedImage) //il male è a volte inevitabile...
		{
			BufferedImage vecchia = (BufferedImage)immagine;
			BufferedImage nuova = new BufferedImage(this.dimensioneImmagini, this.dimensioneImmagini, vecchia.getType());
			for (int i = 0; i < this.dimensioneImmagini; i++)
				for (int j = 0; j < this.dimensioneImmagini; j++)
					nuova.setRGB(this.dimensioneImmagini -1 - j, i, vecchia.getRGB(i, j));
			return nuova;
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
