package com.example.libre;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Post {
	private Comment[] _commentList;
	private JSONObject _json;
	
	private String 	_title;
	private int		_id;
	private String 	_content;
	private Date 	_pubDate;
	private Date 	_modDate;
	private String 	_author;
	private String	_url;
	
	public Post() {
		_title	 = "Sample title";
		_id		 = 1;
		_content = "Test content";
		_pubDate = new Date();
		_modDate = new Date();
		_author  = "Nikola Hardi";
		_url	 = "https://libre.lugons.org";
		
	}
	public Post(JSONObject json) {
		_json = json;
		
		try {
		String rawPubDate = _json.getString("date");
		String rawModDate = _json.getString("modified");
		String rawAuthor  = _json.getJSONObject("author").getString("slug");
		}
		catch(JSONException e) {
			throw new RuntimeException(e);
		}
	};

	public String getTitle() {
		return _title;
	}

	public int getId() {
		return _id;
	}

	public String getContent() {
		return _content;
	}

	public Date getPubDate() {
		return _pubDate;
	}

	public Date getModDate() {
		return _modDate;
	}
	
	public String getAuthor() {
		return _author;
	}
	
	public String getUrl() {
		return _url;
	}

	public Comment[] getCommentsList() {
		return _commentList;
	}
	
	public String toString() {
		return _title;
	}
}