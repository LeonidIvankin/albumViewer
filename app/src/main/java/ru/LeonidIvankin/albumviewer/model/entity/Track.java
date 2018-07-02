package ru.LeonidIvankin.albumviewer.model.entity;

public class Track {

	private String wrapperType;
	private String trackName;
	private String artistName;
	private String collectionPrice;
	private String primaryGenreName;

	public String getArtistName() {
		return artistName;
	}

	public String getCollectionPrice() {
		return collectionPrice;
	}

	public String getPrimaryGenreName() {
		return primaryGenreName;
	}

	public String getTrackName() {
		return trackName;
	}

	public Track(String wrapperType) {
		this.wrapperType = wrapperType;
	}

	public String getWrapperType() {

		return wrapperType;
	}

	public void setWrapperType(String wrapperType) {
		this.wrapperType = wrapperType;
	}
}
