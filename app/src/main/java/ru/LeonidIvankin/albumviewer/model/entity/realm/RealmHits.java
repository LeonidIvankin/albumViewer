package ru.LeonidIvankin.albumviewer.model.entity.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmHits extends RealmObject {

	@PrimaryKey
	private String previewURL;
	private String webformatURL;
	private String tags;

	public void setWebformatURL(String webformatURL) {
		this.webformatURL = webformatURL;
	}

	public String getWebformatURL() {
		return webformatURL;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPreviewURL() {
		return previewURL;
	}


}
