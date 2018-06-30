package ru.LeonidIvankin.albumviewer.model.entity.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmAlbum extends RealmObject{
	@PrimaryKey
	private String collectionId;
	private String collectionName;
	private String artworkUrl100;

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public void setArtworkUrl100(String artworkUrl100) {
		this.artworkUrl100 = artworkUrl100;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public String getArtworkUrl100() {
		return artworkUrl100;
	}

	public String getCollectionName() {
		return collectionName;
	}

}
