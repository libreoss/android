package com.example.libre;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


@SuppressWarnings("unused")
public class MainActivity extends Activity {

	private RSSFeed getFeed(String urlToRssFeed)
	{
		
		try
		{
			// setup the URL
			URL url = new URL(urlToRssFeed);
			
			// create the factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// create a parser
			SAXParser parser = factory.newSAXParser();
			
			// create the reader (scanner)
			XMLReader xmlreader = parser.getXMLReader();
			// instantiate our handler
			RSSHandler theRssHandler = new RSSHandler();
			// assign our handler
			xmlreader.setContentHandler(theRssHandler);

			// get our data through the URL class
			InputSource is = new InputSource(url.openStream());
			// perform the synchronous parse           
			xmlreader.parse(is);
			// get the results - should be a fully populated RSSFeed instance, 
			// or null on error
			return theRssHandler.getFeed();
		}
		catch (Exception ee)
		{
			// if you have a problem, simply return null
			return null;
		}
	}
	
    private void UpdateDisplay()
    {
        TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
        TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
        ListView itemlist = (ListView) findViewById(R.id.itemlist);
  
        
        if (feed == null)
        {
            feedtitle.setText("No RSS Feed Available");
            return;
        }
        
        feedtitle.setText(feed.getTitle());
        feedpubdate.setText(feed.getPubDate());

        
        ArrayAdapter<RSSItem> adapter = new
        ArrayAdapter<RSSItem>(this,android.R.layout.
        simple_list_item_1,feed.getAllItems());

        itemlist.setAdapter(adapter);
        
        itemlist.setSelection(0);      
        
        
    }
    
	
	private RSSFeed feed = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        feed = getFeed("http://libre.lugons.org/index.php/category/librevesti/feed/");
        
        UpdateDisplay();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
