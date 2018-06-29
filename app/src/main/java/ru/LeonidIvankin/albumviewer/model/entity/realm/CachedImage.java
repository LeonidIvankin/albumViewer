package ru.LeonidIvankin.albumviewer.model.entity.realm;

import io.realm.RealmObject;

public class CachedImage extends RealmObject {
	private String url;
	private String path;


	public void setUrl(String url) {
		this.url = url;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
