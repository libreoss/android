package com.example.libre; 

import java.util.List;

import android.content.Context; 
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Page extends Fragment {
	
	public static final String ARG_SECTION_NUMBER = "section_number";

	ListView itemlist;
	WebView membersView; 

	List<Post> posts;
	Post membersPage;
	
	public Page(List<Post> _posts, Post _membersPage) {
		posts = _posts; 
		membersPage = _membersPage;
	}

	private void UpdateDisplay() {
		itemlist = new ListView((Context) getActivity());
		membersView = new WebView((Context) getActivity());
		
		ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(getActivity(),
				android.R.layout.simple_list_item_1, posts);
		
		itemlist.setAdapter(adapter);

		itemlist.setSelection(0);

		final android.support.v4.app.FragmentActivity mainActivityRef = getActivity();
		final List<Post> postsRef = posts;

		itemlist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parrent, View v, int index,
					long l) {
				Intent descIntent = new Intent(mainActivityRef,
						ShowDescription.class);

				Bundle b = new Bundle();

				Post samplePost = postsRef.get(index);
				b.putString("title", samplePost.getTitle());
				b.putString("description", samplePost.getContent());
				b.putString("link", samplePost.getUrl());
				b.putString("pubdate", samplePost.getPubDate().toString());
				descIntent.putExtra("android.intent.extra.INTENT", b);
				startActivity(descIntent);
			}
		});

		membersView.loadDataWithBaseURL(null, membersPage.getContent(), "text/html", "UTF-8", null);
	}
		

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		int i = getArguments().getInt(ARG_SECTION_NUMBER);
		UpdateDisplay(); 
		switch (i) 
		{
			case 0: return itemlist;
			case 2: return membersView; 
			default: return new View((Context) getActivity());
		}
	}

}
