package com.example.libre;

import android.os.Bundle;
import android.webkit.WebView;
import android.content.Intent;
//import android.text.Html;
import android.view.*;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class ShowDescription extends SherlockActivity 
{
    public void onCreate(Bundle icicle) 
    {
        super.onCreate(icicle);
        setContentView(R.layout.activity_show_description);
        

        String theStory = null;
	String title = "";
        
        Intent startingIntent = getIntent();
        
        if (startingIntent != null)
        {
            Bundle b = startingIntent.getBundleExtra("android.intent.extra.INTENT");
            if (b == null)
            {
                theStory = "bad bundle?";
            }
            else
            {
	    	title = b.getString("title");
                theStory = "<h1>" + title + "</h1>" + "\n\n" + b.getString("pubdate") 
				+ "\n\n" + b.getString("description").replace('\n',' ') 
				+ "\n\nMore information:\n" + b.getString("link");
            }
        }
        else
        {
            theStory = "Information Not Found.";
        
        }
        
        WebView db= (WebView) findViewById(R.id.storybox);

	db.loadDataWithBaseURL(null, theStory, "text/html", "UTF-8", null); 
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportMenuInflater().inflate(R.menu.show_description, menu);
        return true;
    }
}
