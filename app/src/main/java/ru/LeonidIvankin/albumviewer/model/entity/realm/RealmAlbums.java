package ru.LeonidIvankin.albumviewer.model.entity.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmAlbums extends RealmObject {
	@PrimaryKey
	private String resultCount;
	private RealmList<RealmAlbumsResults> results;

	public RealmList<RealmAlbumsResults> getResults() {
		return results;
	}

	public String getResultCount() {
		return resultCount;
	}


}
