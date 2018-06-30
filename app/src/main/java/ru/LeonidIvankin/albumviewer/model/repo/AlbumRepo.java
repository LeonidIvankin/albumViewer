package ru.LeonidIvankin.albumviewer.model.repo;

import ru.LeonidIvankin.albumviewer.app.Constant;
import ru.LeonidIvankin.albumviewer.app.NetworkStatus;
import ru.LeonidIvankin.albumviewer.model.api.ApiService;
import ru.LeonidIvankin.albumviewer.model.cache.ICache;
import ru.LeonidIvankin.albumviewer.model.entity.Albums;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AlbumRepo {

	private ICache cache;
	private ApiService api;

	public AlbumRepo(ICache cache, ApiService api) {
		this.cache = cache;
		this.api = api;
	}

	public Observable<Albums> getAlbum(String request) {
		if (NetworkStatus.isOnline()) {
			//если онлайн, получаем из сети
			return api
					.getAlbum(Constant.ENTITY, Constant.COUNTRY, request, 5)
					.subscribeOn(Schedulers.io())
					.map(albums -> {
						//записываем в кеш
						cache.putAlbum(albums);
						return albums;
					});
		} else {
			//если офлайн, из кеша
			return cache.getAlbum();
		}

	}
}
