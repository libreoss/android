package com.example.libre;

import java.util.List;
import android.os.AsyncTask;


public class Task extends AsyncTask<Void, Void, List<Post>>
{
	MainActivity ac; 
	
	Post membersPage;

	public Task(MainActivity a) 
	{
		super();
		this.ac = a;
	}
	protected List<Post> doInBackground(Void... params)  
	{
		WordpressJSON wp = new WordpressJSON("http://libre.lugons.org");
		membersPage = wp.getPageBySlug("libre-tim");
		return wp.getLatestPosts(10);
	}
	protected void onPostExecute(List<Post> result) 
	{
		this.ac.UpdateDisplay(result, membersPage);
	}
}
