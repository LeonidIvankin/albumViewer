package ru.LeonidIvankin.albumviewer.model.entity;

import java.util.List;

public class Album {

	String resultCount;
	List<Results> results;

	public List<Results> getResults() {
		return results;
	}

	public String getResultCount() {
		return resultCount;
	}
}
