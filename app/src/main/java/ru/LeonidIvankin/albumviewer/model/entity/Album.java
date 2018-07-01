package ru.LeonidIvankin.albumviewer.model.entity;

public class Album {

	private int collectionId;

	private String collectionName;

	private String artworkUrl100;

	public int getCollectionId() {
		return collectionId;
	}

	public String getArtworkUrl100() {
		return artworkUrl100;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public void setArtworkUrl100(String artworkUrl100) {
		this.artworkUrl100 = artworkUrl100;
	}
}
