package ru.LeonidIvankin.albumviewer.model.entity;

public class Track {

	String wrapperType;

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
