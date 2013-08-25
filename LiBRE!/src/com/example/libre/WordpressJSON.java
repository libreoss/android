package com.example.libre; 

import java.util.ArrayList; 
import java.net.HttpURLConnection; 
import java.net.URL; 
import java.io.InputStream; 
import java.io.BufferedInputStream; 
import java.io.OutputStream; 
import java.io.BufferedOutputStream;
import java.util.Scanner; 

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
			//System.out.println("JSON " + url.toString());
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
			return scanner.next(); 
		}
		catch(java.io.IOException ex) 
		{
			return "";
		}
	}

	public ArrayList<Post> getLatestPosts(int count = 10) 
	{
		String response = execute("get_recent_posts", "count=" + count);
		JSONObject json = new JSONObject(response); 
		JSONArray jsonPosts = json.getJSONArray("posts");
		ArrayList<Post> posts = new ArrayList<Post>();
		for (int i = 0; i<jsonPosts.length(); i++) 
		{
			posts.add(new Post(jsonPosts.getJSONObject(i)));
		}
		return posts;
	}

	public Post getPostById(int id) 
	{
		JSONObject json = new JSONObject(execute("get_post", "id=" + id));
		return new Post(json.getJSONObject("post"));
	}	
}
