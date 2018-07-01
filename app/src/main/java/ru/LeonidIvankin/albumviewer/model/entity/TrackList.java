package ru.LeonidIvankin.albumviewer.model.entity;

import java.util.List;

public class TrackList {

	private String resultCount;

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public List<Track> getResults() {
		return results;
	}

	public void setResults(List<Track> results) {
		this.results = results;
	}

	List<Track> results;


}
