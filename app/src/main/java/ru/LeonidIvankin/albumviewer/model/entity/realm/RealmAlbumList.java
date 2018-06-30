package ru.LeonidIvankin.albumviewer.model.entity.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmAlbumList extends RealmObject {
	@PrimaryKey
	private String resultCount;
	private RealmList<RealmAlbum> results;

	public RealmList<RealmAlbum> getResults() {
		return results;
	}

	public String getResultCount() {
		return resultCount;
	}


}
