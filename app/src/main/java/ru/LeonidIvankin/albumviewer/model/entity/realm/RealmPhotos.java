package ru.LeonidIvankin.albumviewer.model.entity.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmPhotos extends RealmObject {
	@PrimaryKey
	private String totalHits;

	private RealmList<RealmHits> hits;

	public String getTotalHits() {
		return totalHits;
	}

	public RealmList<RealmHits> getHits() {
		return hits;
	}


}
