package com.example.libre;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TabHost;

import android.os.AsyncTask;

@SuppressWarnings("unused")
public class MainActivity extends SherlockActivity {

	private String feedUrl = "http://libre.lugons.org/index.php/category/librevesti/feed/";
	
	private class BackgroundDownload extends AsyncTask<Void, Void, RSSFeed>
	{
		protected RSSFeed doInBackground(Void... params) 
		{
			return getFeed(feedUrl);
		}

		protected void onPostExecute(RSSFeed result)
		{
			UpdateDisplay(result);
		}
	}

	private RSSFeed getFeed(String urlToRssFeed)
	{
		
		try
		{
			Downloader d = new Downloader(urlToRssFeed);

			// setup the URL
			URL url = d.downloadToFile(getFilesDir(), "rss.xml");

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
	
    private void UpdateDisplay(RSSFeed feed)
    {
        TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
        TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
        ListView itemlist = (ListView) findViewById(R.id.itemlist);
        
        if (feed == null)
        {
            feedtitle.setText("No RSS Feed Available");
            return;
        }
        
        feedtitle.setVisibility(View.GONE);
        feedpubdate.setText(feed.getPubDate());

        
        ArrayAdapter<RSSItem> adapter = new
        ArrayAdapter<RSSItem>(this,android.R.layout.
        simple_list_item_1,feed.getAllItems());

        itemlist.setAdapter(adapter);
        
        itemlist.setSelection(0);      
	
	// little crazy stuff xD 
	final SherlockActivity mainActivityRef = this; 
	final RSSFeed feedRef = feed;

	itemlist.setOnItemClickListener(new OnItemClickListener() 
	{
		@Override 
		public void onItemClick(AdapterView<?> parrent, View v,int index, long l) 
		{
			Intent descIntent = new Intent(mainActivityRef, ShowDescription.class);
			
			Bundle b = new Bundle();

			RSSItem rssitem = feedRef.getItem(index);
			
			b.putString("title", rssitem.getTitle());
			b.putString("description", rssitem.getDescription());
			b.putString("link", rssitem.getLink());
			b.putString("pubdate", rssitem.getPubDate());
			descIntent.putExtra("android.intent.extra.INTENT", b);

			startActivity(descIntent);
		}
	});
        
        
    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	TabHost tabs = (TabHost)findViewById(R.id.TabHost01);
	tabs.setup();

	TabHost.TabSpec spec1 = tabs.newTabSpec("tag1");
	spec1.setContent(R.id.tab1);
	spec1.setIndicator("News");
	tabs.addTab(spec1);

	TabHost.TabSpec spec2 = tabs.newTabSpec("tag2");
	spec2.setContent(R.id.tab2);
	spec2.setIndicator("Latest");
	tabs.addTab(spec2);
	
	BackgroundDownload task = new BackgroundDownload();
	task.execute();
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
    	switch (item.getItemId()) 
	{
		case R.id.action_reload:
			BackgroundDownload task = new BackgroundDownload();
			task.execute();
			return true; 
		default: 
			return super.onOptionsItemSelected(item);
	}
    }
    
    
}
