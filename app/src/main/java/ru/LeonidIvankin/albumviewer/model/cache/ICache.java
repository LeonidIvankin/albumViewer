package ru.LeonidIvankin.albumviewer.model.cache;


import ru.LeonidIvankin.albumviewer.model.entity.Albums;

import io.reactivex.Observable;

public interface ICache {
	void putAlbum(Albums albums);

	Observable<Albums> getAlbum();

}
