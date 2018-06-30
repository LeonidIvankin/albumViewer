package ru.LeonidIvankin.albumviewer.model.entity;

import java.util.List;

public class AlbumList {
	String resultCount;

	List<Album> results;

	public AlbumList(String resultCount) {
		this.resultCount = resultCount;
	}

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
