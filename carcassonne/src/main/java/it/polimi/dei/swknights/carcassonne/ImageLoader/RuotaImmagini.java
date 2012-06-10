package it.polimi.dei.swknights.carcassonne.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RuotaImmagini
{
	public RuotaImmagini(Map<String, BufferedImage> mappaImmagini, int dimensioneImmagini)
	{
		this.dimensioneImmagini = dimensioneImmagini;
		this.mappaImmagini = mappaImmagini;
		this.immaginiRuotate = new HashMap<String, BufferedImage>();
	}

	public Map<String, BufferedImage> getMapRuotate()
	{
		for (Entry<String, BufferedImage> entry : this.mappaImmagini.entrySet())
		{
			if (entry.getKey().length() == LUNGHEZZA_STRINGA_TESSERA)
			{
				this.ruotaEntry(entry);
			}
		}
		return this.immaginiRuotate;
	}

	private void ruotaEntry(Entry<String, BufferedImage> entry)
	{
		RuotaStringa rotator = new RuotaStringa(entry.getKey());
		BufferedImage immagine = entry.getValue();
		for (int i = 1; i < NUMERO_LATI; i++)
		{
			rotator.ruotaStringa();
			String stringaRuotata = rotator.toString();
			immagine = this.ruotaImmagine(immagine);
			this.immaginiRuotate.put(stringaRuotata, immagine);
		}
	}

	private BufferedImage ruotaImmagine(BufferedImage immagine)
	{
		BufferedImage vecchia = immagine;
		BufferedImage nuova = new BufferedImage(this.dimensioneImmagini, this.dimensioneImmagini,
				vecchia.getType());
		for (int i = 0; i < this.dimensioneImmagini; i++)
		{
			for (int j = 0; j < this.dimensioneImmagini; j++)
			{
				nuova.setRGB(this.dimensioneImmagini - 1 - j, i, vecchia.getRGB(i, j));
			}
		}
		return nuova;
	}

	private Map<String, BufferedImage>	mappaImmagini;

	private Map<String, BufferedImage>	immaginiRuotate;

	private int							dimensioneImmagini;

	private static final int			NUMERO_LATI					= 4;

	private static final int			LUNGHEZZA_STRINGA_TESSERA	= 10;

}
