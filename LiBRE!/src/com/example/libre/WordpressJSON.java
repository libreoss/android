package com.example.libre; 

import java.util.ArrayList; 
import java.net.HttpURLConnection; 
import java.net.URL; 
import java.io.InputStream; 
import java.io.BufferedInputStream; 
import java.io.OutputStream; 
import java.io.BufferedOutputStream;
import java.util.Scanner; 

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class WordpressJSON 
{
	private String urlString;

	public WordpressJSON(String _url) 
	{	
		urlString = _url;
	}

	private String execute(String method, String parameters)  
	{	
		try { 
			URL url = new URL(urlString + "/?json=" + method);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setChunkedStreamingMode(0);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			OutputStream out = new BufferedOutputStream(conn.getOutputStream());
			out.write(parameters.getBytes("UTF-8")); 
			out.flush(); 
			out.close();

			conn.connect();
	
			InputStream in = new BufferedInputStream(conn.getInputStream());
			Scanner scanner = new Scanner(in); 
			scanner.useDelimiter("\b");

			String result = scanner.next();
			return result; 
		}
		catch(java.io.IOException ex) 
		{
			return "";
		}
	}

	public ArrayList<Post> getLatestPosts(int count) 
	{
		String result = execute("get_recent_posts", "count=" + count);

		try {
			JSONObject json = new JSONObject(result);
			JSONArray jsonPosts = json.getJSONArray("posts");
			
			ArrayList<Post> posts = new ArrayList<Post>();

			for (int i = 0; i<jsonPosts.length(); i++) 
			{
				posts.add(new Post(jsonPosts.getJSONObject(i)));
			}

			return posts;
		}
		catch (JSONException e)
		{
			throw new RuntimeException(e);
		}
		
	}

	public Post getPostById(int id) 
	{
		try {
			String result = execute("get_post", "id=" + id);
			JSONObject json = new JSONObject(result);
			return new Post(json.getJSONObject("post"));
		}
		catch(JSONException e)
		{
			throw new RuntimeException(e);
			
		}
	}

	public Post getPageBySlug(String slug) 
	{	
		try {
			String result = execute("get_page", "slug=" + slug);
			JSONObject json = new JSONObject(result);
			return new Post(json.getJSONObject("page"));
		}
		catch(JSONException e)
		{
			throw new RuntimeException(e);
			
		}
	}
}
