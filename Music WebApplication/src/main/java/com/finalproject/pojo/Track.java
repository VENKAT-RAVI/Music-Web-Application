package com.finalproject.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Track")
public class Track {
	
	public Track() {
		
	}
	
	public Track (String title, String artist, String URL, Uploader uploader) {
		this.title = title;
		this.artist = artist;
		this.URL = URL;
		this.uploader = uploader;
	}

	@Id
	@Column (name = "TrackId", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Title", nullable = false, unique = true)
	private String title;
	
	@Column(name = "Artist", nullable = false)
	private String artist;
	
	@Column(name = "URL", nullable = false, unique = true)
	private String URL;
	
	@ManyToOne
	private Uploader uploader;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}
	
	public Uploader getUploader() {
		return uploader;
	}
	
	public void setUploader(Uploader uploader) {
		this.uploader = uploader;
	}
	
}
