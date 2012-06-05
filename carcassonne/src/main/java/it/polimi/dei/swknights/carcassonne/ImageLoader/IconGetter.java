package it.polimi.dei.swknights.carcassonne.ImageLoader;

import it.polimi.dei.swknights.carcassonne.Debug;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconGetter
{
	public IconGetter()
	{
		this.immagini = new ScaledImageGetter();
		this.currentDim = ScaledImageGetter.DIM_STANDARD;
	}
	
	public Icon getOriginalTileIcon(String stringToFind)
	{
		IconFinder finder = new IconFinder(stringToFind);
		Image image = this.immagini.getOriginalTileImage(finder.toString());
		return new ImageIcon(image);
	}
	
	public Icon getOriginalSegnalinoIcon(String stringToFind)
	{
		Image image = this.immagini.getOriginalSegnalinoImage(stringToFind);
		return new ImageIcon(image);
	}
	
	public Icon getTileIcon(String stringToFind)
	{
		Debug.print("stringa in entrata: " + stringToFind);
		return this.getTileIcon(stringToFind,this.currentDim);
	}

	public Icon getTileIcon(String stringToFind, int dim)
	{
		this.setDim(dim);
		IconFinder finder = new IconFinder(stringToFind);
		Image image = this.immagini.getImage(finder.toString());
		return new ImageIcon(image);
	}

	public Icon getAlphaTileIcon(String stringToFind)
	{
		IconFinder finder = new IconFinder(stringToFind);
		Image image = this.immagini.getAlphaImage(finder.toString());
		return new ImageIcon(image);
	}

	private void setDim(int dim)
	{
		if(dim != this.currentDim)
		{
			this.currentDim = dim;
			this.immagini.setImageDim(this.currentDim);
		}
	}

	private int					currentDim;

	private ScaledImageGetter	immagini;
}
