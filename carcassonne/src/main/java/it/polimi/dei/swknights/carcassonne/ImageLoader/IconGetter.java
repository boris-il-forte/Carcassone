package it.polimi.dei.swknights.carcassonne.ImageLoader;

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
	
	public Icon getIcon(String stringToFind)
	{
		return this.getIcon(stringToFind,this.currentDim);
	}

	public Icon getIcon(String stringToFind, int dim)
	{
		this.setDim(dim);
		IconFinder finder = new IconFinder(stringToFind);
		Image image = this.immagini.getImage(finder.toString());
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
