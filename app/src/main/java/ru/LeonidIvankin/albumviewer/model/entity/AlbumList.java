package ru.LeonidIvankin.albumviewer.model.entity;

import java.util.List;

public class AlbumList {
	private String resultCount;

	private List<Album> results;

	public void setResults(List<Album> results) {
		this.results = results;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public List<Album> getResults() {
		return results;
	}

	public String getResultCount() {
		return resultCount;
	}



}
