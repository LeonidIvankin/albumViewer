package ru.LeonidIvankin.albumviewer.model.entity;

import java.util.List;

public class Albums {
	String resultCount;

	List<AlbumsResults> results;

	public Albums(String resultCount) {
		this.resultCount = resultCount;
	}

	public void setResults(List<AlbumsResults> results) {
		this.results = results;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public List<AlbumsResults> getResults() {
		return results;
	}

	public String getResultCount() {
		return resultCount;
	}



}
