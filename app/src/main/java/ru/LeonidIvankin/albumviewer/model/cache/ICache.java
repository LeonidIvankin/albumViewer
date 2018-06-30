package ru.LeonidIvankin.albumviewer.model.cache;


import ru.LeonidIvankin.albumviewer.model.entity.AlbumList;

import io.reactivex.Observable;

public interface ICache {
	void putAlbum(AlbumList albumList);

	Observable<AlbumList> getAlbum();

}
