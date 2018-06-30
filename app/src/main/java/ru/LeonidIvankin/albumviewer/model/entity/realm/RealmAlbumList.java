package ru.LeonidIvankin.albumviewer.model.entity.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.LeonidIvankin.albumviewer.app.Constant;

public class RealmAlbumList extends RealmObject {
	@PrimaryKey
	private String key = Constant.REALM_ALBUMS_KEY;

	private RealmList<RealmAlbum> results;

	public RealmList<RealmAlbum> getResults() {
		return results;
	}

}
