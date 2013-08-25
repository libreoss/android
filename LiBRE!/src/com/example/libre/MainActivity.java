package com.example.libre;

import java.net.URL;
import java.util.List;
import java.util.Vector;

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

	private Post samplePost;

	private void UpdateDisplay() {
		TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
		TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
		ListView itemlist = (ListView) findViewById(R.id.itemlist);
		List<Post> postList = new Vector<Post>(0);

		postList.add(samplePost);
		postList.add(samplePost);

		feedtitle.setVisibility(View.GONE);
		feedpubdate.setText(samplePost.getPubDate().toString());

		ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(this,
				android.R.layout.simple_list_item_1, postList);

		itemlist.setAdapter(adapter);

		itemlist.setSelection(0);

		final SherlockActivity mainActivityRef = this;

		itemlist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parrent, View v, int index,
					long l) {
				Intent descIntent = new Intent(mainActivityRef,
						ShowDescription.class);

				Bundle b = new Bundle();

				b.putString("title", samplePost.getTitle());
				b.putString("description", samplePost.getContent());
				b.putString("link", samplePost.getUrl());
				b.putString("pubdate", samplePost.getPubDate().toString());
				descIntent.putExtra("android.intent.extra.INTENT", b);

				startActivity(descIntent);
			}
		});

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost tabs = (TabHost) findViewById(R.id.TabHost01);
		tabs.setup();

		TabHost.TabSpec spec1 = tabs.newTabSpec("tag1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("News");
		tabs.addTab(spec1);

		TabHost.TabSpec spec2 = tabs.newTabSpec("tag2");
		spec2.setContent(R.id.tab2);
		spec2.setIndicator("Latest");
		tabs.addTab(spec2);

		samplePost = new Post();

		UpdateDisplay();

	}
}
