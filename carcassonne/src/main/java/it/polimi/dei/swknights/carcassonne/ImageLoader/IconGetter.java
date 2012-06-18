package it.polimi.dei.swknights.carcassonne.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * class used to get Icons
 * @author dave
 *
 */
public class IconGetter
{
	/**
	 * default constructor
	 */
	public IconGetter()
	{
		this.immagini = new ScaledImageGetter();
		this.currentDim = ScaledImageGetter.DIM_STANDARD;
	}

	/**
	 * Getter method
	 * @param stringToFind the string representing the icon to find
	 * @return the found tile icon at the original size
	 */
	public Icon getOriginalTileIcon(String stringToFind)
	{
		IconFinder finder = new IconFinder(stringToFind);
		BufferedImage image = this.immagini.getOriginalTileImage(finder.toString());
		return new ImageIcon(image);
	}

	/**
	 * Getter method
	 * @param stringToFind the string representing the icon to find
	 * @return the found marker icon at the original size
	 */
	public Icon getOriginalSegnalinoIcon(String stringToFind)
	{
		BufferedImage image = this.immagini.getOriginalSegnalinoImage(stringToFind);
		return new ImageIcon(image);
	}

	/**
	 * Getter method
	 * @param stringToFind the string representing the icon to find
	 * @return the found tile icon
	 */
	public Icon getTileIcon(String stringToFind)
	{
		return this.getTileIcon(stringToFind, this.currentDim);
	}

	/**
	 * Getter method
	 * @param stringToFind the string representing the icon to find
	 * @return the found icon at the specified size
	 */
	public Icon getTileIcon(String stringToFind, int dim)
	{
		this.setDim(dim);
		IconFinder finder = new IconFinder(stringToFind);
		BufferedImage image = this.immagini.getImage(finder.toString());
		return new ImageIcon(image);
	}

	/**
	 * Getter method
	 * @param stringToFind the string rappresenting the icon to find
	 * @return the found icon
	 */
	public Icon getSegnalinoIcon(String stringToFind, int dimensioneSegnalino)
	{
		IconFinder finder = new IconFinder(stringToFind);
		BufferedImage image = this.immagini.getSegnalino(finder.getSegnalino(), dimensioneSegnalino);
		if (image != null)
		{
			return new ImageIcon(image);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Getter method
	 * @return a map of GUI icons
	 */
	public Map<String, Icon> getButtonIconMap()
	{
		Map<String, Icon> mappa = new HashMap<String, Icon>();
		for (Entry<String, BufferedImage> entry : this.immagini.getIconGUIMap().entrySet())
		{
			Icon icona = new ImageIcon(entry.getValue());
			mappa.put(entry.getKey(), icona);
		}
		return mappa;
	}

	/**
	 * Getter method
	 * @param stringToFind the string representing the icon to find
	 * @return the found icon whit alpha channel set
	 */
	public Icon getAlphaTileIcon(String stringToFind)
	{
		IconFinder finder = new IconFinder(stringToFind);
		BufferedImage image = this.immagini.getAlphaImage(finder.toString());
		return new ImageIcon(image);
	}
	
	/**
	 * Getter method
	 * @return the background image
	 */
	public BufferedImage getSfondo(String stringa)
	{
		return this.immagini.getIconGUIMap().get(stringa);
	}

	private void setDim(int dim)
	{
		if (dim != this.currentDim)
		{
			this.currentDim = dim;
			this.immagini.setImageDim(this.currentDim);
		}
	}

	private int					currentDim;

	private ScaledImageGetter	immagini;
}
