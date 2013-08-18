package com.example.libre; 

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class Downloader 
{
	private String url; 
	
	public Downloader(String _url) 
	{
		url = _url; 
	}

	URL downloadToFile(File filesDir, String filename) throws IOException
	{
		URL u = new URL(this.url);
		HttpURLConnection c = (HttpURLConnection) u.openConnection();
		c.setRequestMethod("GET");
		c.setDoOutput(true);
		c.connect();
		
		File output = new File(filesDir, filename);
		FileOutputStream f = new FileOutputStream(output);
		InputStream in = c.getInputStream();

		byte[] buffer = new byte[1024];
		int len1 = 0;
		while ( (len1 = in.read(buffer)) > 0 ) { 
			f.write(buffer,0, len1);
		}
		f.close();
		return output.toURI().toURL();
	}

}
