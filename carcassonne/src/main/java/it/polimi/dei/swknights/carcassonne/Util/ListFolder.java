package it.polimi.dei.swknights.carcassonne.Util;

import it.polimi.dei.swknights.carcassonne.Debug;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ListFolder
{

	/**
	 * List directory contents for a resource folder. Not recursive. This is
	 * basically a brute-force implementation. Works for regular files and also
	 * JARs.
	 * 
	 * @author Greg Briggs
	 * @param clazz
	 *            Any java class that lives in the same place as the resources
	 *            you want.
	 * @param path
	 *            Should end with "/", but not start with one.
	 * @return Just the name of each member item, not the full paths.
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static String[] list(String path) throws URISyntaxException, IOException
	{
		URL dirURL = ListFolder.class.getResource("/"+path);
		if (dirURL != null && dirURL.getProtocol().equals("file"))
		{
			/* A file path: easy enough */
			return new File(dirURL.toURI()).list();
		}

		if (dirURL == null)
		{
			/*
			 * In case of a jar file, we can't actually find a directory. Have
			 * to assume the same jar as clazz.
			 */
			String me = ListFolder.class.getName().replace(".", "/") + ".class";
			dirURL = ListFolder.class.getClassLoader().getResource(me);
		}

		if (dirURL.getProtocol().equals("jar"))
		{
			/* A JAR path */
			// strip out only the JAR file
			String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
			JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
			Debug.print("dentro ramo jar:  " + jarPath);
			// gives ALL entries in jar
			Enumeration<JarEntry> entries = jar.entries();
			// avoid duplicates in case it is a subdirectory
			Set<String> result = new HashSet<String>();
			while (entries.hasMoreElements())
			{
				String name = entries.nextElement().getName();
				if (name.contains(path))
				{ // filter according to the path
					Debug.print("trovata " + name);
					String entry = name.substring(path.length());
					int checkSubdir = entry.indexOf("/");
					if (checkSubdir >= 0)
					{
						Debug.print("subdirectory " + name);
						entry = entry.substring(checkSubdir+1);
					}
					result.add(entry);
				}
			}
			return result.toArray(new String[result.size()]);
		}

		throw new UnsupportedOperationException("Cannot list files for URL " + dirURL);
	}

	private ListFolder()
	{
	}

}
