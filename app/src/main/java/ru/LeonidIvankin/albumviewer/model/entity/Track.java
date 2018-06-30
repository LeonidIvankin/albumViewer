package ru.LeonidIvankin.albumviewer.model.entity;

public class Track {

	String wrapperType;
	String trackName;

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
