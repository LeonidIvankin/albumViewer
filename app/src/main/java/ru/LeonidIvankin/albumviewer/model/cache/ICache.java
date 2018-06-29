package ru.LeonidIvankin.albumviewer.model.cache;


import ru.LeonidIvankin.albumviewer.model.entity.Album;

import io.reactivex.Observable;

public interface ICache {
	void putAlbum(Album album);

	Observable<Album> getAlbum();

}
